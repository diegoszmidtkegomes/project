package fragmentos;

import java.util.ArrayList;

import lista.Abastecimentos_AdapterListView;
import lista.Abastecimentos_ItemListView;
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
import atividades.Abastecimento_View_Activity;
import banco_de_dados.Abastecimento;
import banco_de_dados.DAOAbastecimento;

import com.example.fullservicecar.R;

import funcoes.FAbastecimento;


public class Abastecimentos_Fragment extends Fragment implements OnItemClickListener{
	
	private ListView listView;
	private Abastecimentos_AdapterListView adapterListView;
	private ArrayList<Abastecimentos_ItemListView> itens;
	public static int idAbastecimento = 0;
	
	public Abastecimentos_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view;
		
		if(DAOAbastecimento.getInstancia(getActivity()).getTamanho() == 0){
			view = inflater.inflate(R.layout.abastecimento_fragment_tutorial, container,false);
		}else{
			view = inflater.inflate(R.layout.abastecimento_fragment, container,false);
			listView = (ListView) view.findViewById(R.id.Abastecimentos_ListView_ListaDeAbastecimentosRegistrados);
			// Define o Listener quando alguem clicar no item.
			listView.setOnItemClickListener(this);
			createListView();
		}
		
		
		
		return view;
	}

	private void createListView() {
		// Criamos nossa lista que preenchera o ListView
		itens = new ArrayList<Abastecimentos_ItemListView>();
		
		Abastecimentos_ItemListView item[] = new Abastecimentos_ItemListView[DAOAbastecimento.getInstancia(getActivity()).getTamanho()];

		
		
		int id = 0;
		String posto = "", combustivel = "", data = "";
		double precoLitro = 0, km = 0, valorTotal = 0, qtdeLitros = 0;
		
		ArrayList<Abastecimento> lista = new ArrayList<Abastecimento>(DAOAbastecimento.getInstancia(getActivity()).recuperarPorQuery("SELECT * FROM " + DAOAbastecimento.NOME_TABELA 
				+ " ORDER BY " + DAOAbastecimento.COLUNA_ODOMETRO + ", " + DAOAbastecimento.COLUNA_MILLIS + ", " + DAOAbastecimento.COLUNA_VALORTOTAL + " ASC"));
		
		
		for (int x = 0; x < DAOAbastecimento.getInstancia(getActivity()).getTamanho(); x++) {
			
			id			= lista.get(x).getId();
			posto       = lista.get(x).getPosto(); 
			combustivel = lista.get(x).getTipoCombustivel();
			data		= lista.get(x).getData();
			km 		    = lista.get(x).getOdometro();
			valorTotal  = lista.get(x).getValorTotal();
			qtdeLitros  = lista.get(x).getLitros();
			precoLitro  = valorTotal / qtdeLitros;

			if(qtdeLitros == 0 || valorTotal == 0){
				precoLitro = 0;
			}
			
			item[x] = new Abastecimentos_ItemListView(id, posto, combustivel, data, FAbastecimento.conv(precoLitro), FAbastecimento.mediaL(getActivity()), FAbastecimento.conv(km), FAbastecimento.conv(valorTotal), FAbastecimento.conv(qtdeLitros));
			
		}
		for(int x = item.length - 1; x >= 0; x--){
			itens.add(item[x]);
		}
		
		// Cria o adapter
		adapterListView = new Abastecimentos_AdapterListView(getActivity(), itens);

		// Define o Adapter
		listView.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// Pega o item que foi selecionado.
		Abastecimentos_ItemListView item = adapterListView.getItem(arg2);
		idAbastecimento = item.getId();
		startActivity(new Intent(getActivity(), Abastecimento_View_Activity.class));
		//Toast.makeText(getActivity(), "Você Clicou em: " + idAbastecimento,Toast.LENGTH_SHORT).show();
		
	}
	
}
