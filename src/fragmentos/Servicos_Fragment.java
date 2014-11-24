package fragmentos;

import java.util.ArrayList;

import lista.Servicos_AdapterListView;
import lista.Servicos_ItemListView;
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
import atividades.Servicos_View_Activity;
import banco_de_dados.DAOServicos;
import banco_de_dados.Servicos;

import com.example.fullservicecar.R;

import funcoes.FAbastecimento;

public class Servicos_Fragment extends Fragment implements OnItemClickListener{
	
	private ListView listView;
	private Servicos_AdapterListView adapterListView;
	private ArrayList<Servicos_ItemListView> itens;
	public static int idServico = 0;
	
	public Servicos_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view;

		if(DAOServicos.getInstancia(getActivity()).getTamanho() == 0){
			
			view = inflater.inflate(R.layout.servicos_fragment_tutorial, container, false);
			
		}else{
			
			view = inflater.inflate(R.layout.servicos_fragment, container, false);
			listView = (ListView) view.findViewById(R.id.Servicos_ListView_ListaDeServicosRegistrados);
			// Define o Listener quando alguem clicar no item.
			listView.setOnItemClickListener(this);
			createListView();
		}
		
		return view;
	}
	
	private void createListView() {
		// Criamos nossa lista que preenchera o ListView
		itens = new ArrayList<Servicos_ItemListView>();

		Servicos_ItemListView item[] = new Servicos_ItemListView[DAOServicos.getInstancia(getActivity()).getTamanho()];

		int id = 0;
		String tipoDeServico = "", data = "", localDoServico = "";
		double odometro = 0, valorTotal = 0;
		
		ArrayList<Servicos> lista = new ArrayList<Servicos>(DAOServicos.getInstancia(getActivity()).recuperarPorQuery("SELECT * FROM " + DAOServicos.NOME_TABELA + " ORDER BY " + DAOServicos.COLUNA_ODOMETRO + ", " + DAOServicos.COLUNA_MILLIS + ", " + DAOServicos.COLUNA_VALORTOTAL + " ASC"));
		
		
		for (int x = 0; x < DAOServicos.getInstancia(getActivity()).getTamanho(); x++) {

			
			
			id 	  		   = lista.get(x).getId();
			tipoDeServico  = lista.get(x).getTipo();
			localDoServico = lista.get(x).getLocal();
			odometro 	   = lista.get(x).getOdometro();
			data 		   = lista.get(x).getData();
			valorTotal 	   = lista.get(x).getValorTotal();

			item[x] = new Servicos_ItemListView(id, tipoDeServico, localDoServico, data, FAbastecimento.conv(odometro), FAbastecimento.conv(valorTotal));
			
		}

		for(int x = item.length - 1; x >= 0; x--){
			itens.add(item[x]);
		}
		

		// Cria o adapter
		adapterListView = new Servicos_AdapterListView(getActivity(), itens);

		// Define o Adapter
		listView.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}
	

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// Pega o item que foi selecionado.
		Servicos_ItemListView item = adapterListView.getItem(arg2);
		idServico = item.getId();
		startActivity(new Intent(getActivity(), Servicos_View_Activity.class));
	}

}
