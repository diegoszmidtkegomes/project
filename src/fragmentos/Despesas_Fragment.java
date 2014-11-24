package fragmentos;

import java.util.ArrayList;

import lista.Despesas_AdapterListView;
import lista.Despesas_ItemListView;
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
import atividades.Despesas_View_Activity;
import banco_de_dados.DAODespesas;
import banco_de_dados.Despesas;

import com.example.fullservicecar.R;

import funcoes.FAbastecimento;

public class Despesas_Fragment extends Fragment implements OnItemClickListener{
	
	private ListView listView;
	private Despesas_AdapterListView adapterListView;
	private ArrayList<Despesas_ItemListView> itens;
	public static int idDespesa = 0;
	
	public Despesas_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view;

		if(DAODespesas.getInstancia(getActivity()).getTamanho() == 0){
			
			view = inflater.inflate(R.layout.despesas_fragment_tutorial, container, false);
			
		}else{
			
			view = inflater.inflate(R.layout.despesas_fragment, container, false);
			listView = (ListView) view.findViewById(R.id.Despesas_ListView_ListaDeDespesasRegistrados);
			// Define o Listener quando alguem clicar no item.
			listView.setOnItemClickListener(this);
			createListView();
		}
		
		return view;
	}
	
	private void createListView() {
		// Criamos nossa lista que preenchera o ListView
		itens = new ArrayList<Despesas_ItemListView>();

		
		int id = 0;
		String tipoDeDespesa = "", data = "", localDaDespesa = "";
		double odometro = 0, valorTotal = 0;

		Despesas_ItemListView item[] = new Despesas_ItemListView[DAODespesas.getInstancia(getActivity()).getTamanho()];


		ArrayList<Despesas> lista = new ArrayList<Despesas>(DAODespesas.getInstancia(getActivity()).recuperarPorQuery("SELECT * FROM " + DAODespesas.NOME_TABELA + " ORDER BY " + DAODespesas.COLUNA_ODOMETRO + ", " + DAODespesas.COLUNA_MILLIS + ", " + DAODespesas.COLUNA_VALORTOTAL + " ASC"));
		
		
		for (int x = 0; x < DAODespesas.getInstancia(getActivity()).getTamanho(); x++) {

			
			
			id 	  		   = lista.get(x).getId();
			tipoDeDespesa  = lista.get(x).getTipo();
			localDaDespesa = lista.get(x).getLocal();
			odometro 	   = lista.get(x).getOdometro();
			data 		   = lista.get(x).getData();
			valorTotal 	   = lista.get(x).getValorTotal();

			item[x] = new Despesas_ItemListView(id, tipoDeDespesa, data, localDaDespesa, FAbastecimento.conv(odometro), FAbastecimento.conv(valorTotal));

		}
		
		for (int x = item.length - 1; x >= 0; x--) {
			itens.add(item[x]);
		}
		
		// Cria o adapter
		adapterListView = new Despesas_AdapterListView(getActivity(),itens);

		// Define o Adapter
		listView.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// Pega o item que foi selecionado.
		Despesas_ItemListView item = adapterListView.getItem(arg2);
		idDespesa = item.getId();
		startActivity(new Intent(getActivity(), Despesas_View_Activity.class));
		
	}

}
