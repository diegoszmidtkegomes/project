package fragmentos;

import java.util.ArrayList;

import lista.Lembretes_AdapterListView;
import lista.Lembretes_ItemListView;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import atividades.Lembretes_View_Activity;
import banco_de_dados.DAOLembretes;
import banco_de_dados.Lembretes;

import com.example.fullservicecar.R;


public class Lembretes_Fragment extends Fragment implements OnItemClickListener{
	
	private ListView listView;
	private Lembretes_AdapterListView adapterListView;
	private ArrayList<Lembretes_ItemListView> itens;
	public static int idLembrete = 0;
	
	public Lembretes_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view;

		if(DAOLembretes.getInstancia(getActivity()).getTamanho() == 0){
			
			view = inflater.inflate(R.layout.lembretes_fragment_tutorial, container, false);
			
		}else{
			
			view = inflater.inflate(R.layout.lembretes_fragment, container, false);
			listView = (ListView) view.findViewById(R.id.Lembretes_ListView_ListaDeLembretesRegistrados);
			// Define o Listener quando alguem clicar no item.
			listView.setOnItemClickListener(this);
			createListView();
		}
		
		return view;
	}
	
	private void createListView() {
		// Criamos nossa lista que preenchera o ListView
		itens = new ArrayList<Lembretes_ItemListView>();

		Lembretes_ItemListView item[] = new Lembretes_ItemListView[DAOLembretes.getInstancia(getActivity()).getTamanho()];

		int id = 0;
		String tipo = "", motivo = "", data = "", observacao = "";
		double odometro = 0;
		
		//ArrayList<Lembretes> lista = new ArrayList<Lembretes>(DAOLembretes.getInstancia(getActivity()).recuperarPorQuery("SELECT * FROM " + DAOLembretes.NOME_TABELA + " ORDER BY " + DAOLembretes.COLUNA_DATA + ", " + DAOLembretes.COLUNA_ODOMETRO + " DESC"));
		ArrayList<Lembretes> lista = new ArrayList<Lembretes>(DAOLembretes.getInstancia(getActivity()).recuperarPorQuery("SELECT * FROM " + DAOLembretes.NOME_TABELA + " ORDER BY " + DAOLembretes.COLUNA_MILLIS + ", " + DAOLembretes.COLUNA_ODOMETRO + " DESC"));
		
		
		for (int x = 0; x < DAOLembretes.getInstancia(getActivity()).getTamanho(); x++) {

			
			
			id 	       = lista.get(x).getId();
			tipo	   = lista.get(x).getTipo();
			motivo	   = lista.get(x).getMotivo();
			data 	   = lista.get(x).getData();
			observacao = lista.get(x).getObservacao();
			odometro   = lista.get(x).getOdometro();
			

			item[x] = new Lembretes_ItemListView(id, tipo, motivo, data, observacao, odometro);
			
		}
		
		for (int x = 0; x < item.length; x++) {
			itens.add(item[x]);
		}
		
		// Cria o adapter
		adapterListView = new Lembretes_AdapterListView(getActivity(), itens);

		// Define o Adapter
		listView.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}
	

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// Pega o item que foi selecionado.
		Lembretes_ItemListView item = adapterListView.getItem(arg2);
		idLembrete = item.getId();
		startActivity(new Intent(getActivity(), Lembretes_View_Activity.class));
	}

}
