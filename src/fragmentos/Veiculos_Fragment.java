package fragmentos;

import java.util.ArrayList;

import lista.Veiculos_AdapterListView;
import lista.Veiculos_ItemListView;
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
import atividades.Veiculos_View_Activity;
import banco_de_dados.DAOVeiculo;
import banco_de_dados.Veiculo;

import com.example.fullservicecar.R;

public class Veiculos_Fragment extends Fragment implements OnItemClickListener{
	
	private ListView listView;
	private Veiculos_AdapterListView adapterListView;
	private ArrayList<Veiculos_ItemListView> itens;
	public static int idVeiculo = 0;
	
	public Veiculos_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.veiculos_fragment, container, false);
		listView = (ListView) view.findViewById(R.id.Veiculos_ListView_ListaDeVeiculosRegistrados);
		// Define o Listener quando alguem clicar no item.
		listView.setOnItemClickListener(this);
		createListView();
		return view;
	}
	
	private void createListView() {
		// Criamos nossa lista que preenchera o ListView
		itens = new ArrayList<Veiculos_ItemListView>();

		
		int id = 0, ano = 0;
		String nome, marca, placa;
		

		Veiculos_ItemListView item[] = new Veiculos_ItemListView[DAOVeiculo.getInstancia(getActivity()).getTamanho()];


		ArrayList<Veiculo> lista = new ArrayList<Veiculo>(DAOVeiculo.getInstancia(getActivity()).recuperarPorQuery("SELECT * FROM " + DAOVeiculo.NOME_TABELA));
		
		
		for (int x = 0; x < DAOVeiculo.getInstancia(getActivity()).getTamanho(); x++) {

			
			
			id 	  = lista.get(x).getId();
			ano   = lista.get(x).getAno();
			nome  = lista.get(x).getNome();
			marca = lista.get(x).getMarca();
			placa = lista.get(x).getPlaca();

			item[x] = new Veiculos_ItemListView(id, ano, nome, marca, placa);

		}
		
		for (int x = item.length - 1; x >= 0; x--) {
			itens.add(item[x]);
		}
		
		// Cria o adapter
		adapterListView = new Veiculos_AdapterListView(getActivity(), itens);

		// Define o Adapter
		listView.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// Pega o item que foi selecionado.
		Veiculos_ItemListView item = adapterListView.getItem(arg2);
		idVeiculo = item.getId();
		startActivity(new Intent(getActivity(), Veiculos_View_Activity.class));
		
	}

}
