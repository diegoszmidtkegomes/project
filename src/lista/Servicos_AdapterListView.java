package lista;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import atividades.AtividadePrincipal;
import banco_de_dados.DAOServicos;
import banco_de_dados.Servicos;

import com.example.fullservicecar.R;

import funcoes.FF;

@SuppressLint("ViewHolder")
public class Servicos_AdapterListView extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Servicos_ItemListView> itens;

	public Servicos_AdapterListView(Context context,
			ArrayList<Servicos_ItemListView> itens) {
		// Itens que preencheram o listview
		this.itens = itens;
		// responsavel por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);

	}

	/**
	 * Retorna a quantidade de itens
	 * 
	 * @return
	 */
	public int getCount() {
		return itens.size();
	}

	/**
	 * Retorna o item de acordo com a posicao dele na tela.
	 * 
	 * @param position
	 * @return
	 */
	public Servicos_ItemListView getItem(int position) {
		return itens.get(position);
	}

	/**
	 * Sem implementa��o
	 * 
	 * @param position
	 * @return
	 */
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	public View getView(int position, View view, ViewGroup parent) {
		// Pega o item de acordo com a pos��o.
		Servicos_ItemListView item = itens.get(position);
		// infla o layout para podermos preencher os dados
		view = mInflater.inflate(R.layout.servicos_fragment_layout_listview,
				null);
		/*
		 * ((TextView)
		 * view.findViewById(R.id.Servicos_Item_ListView_TipoDeServico
		 * )).setText(item.getTipoDeServico()); ((TextView)
		 * view.findViewById(R.id
		 * .Servicos_Item_ListView_Km)).setText(String.valueOf(item.getKm()));
		 * ((TextView)
		 * view.findViewById(R.id.Servicos_Item_ListView_Data)).setText
		 * (item.getData()); ((TextView)
		 * view.findViewById(R.id.Servicos_Item_ListView_Valor
		 * )).setText(String.valueOf(item.getValor()));
		 */
		Servicos aux = DAOServicos.getInstancia(AtividadePrincipal.contexto)
				.buscarPorId(item.getId());

		((TextView) view
				.findViewById(R.id.Servicos_Item_ListView_TipoDeServico))
				.setText(item.getTipoDeServico());
		((TextView) view.findViewById(R.id.Servicos_Item_ListView_Km))
				.setText(FF.treta(aux.getOdometro()));
		((TextView) view.findViewById(R.id.Servicos_Item_ListView_Data))
				.setText(item.getData());
		((TextView) view.findViewById(R.id.Servicos_Item_ListView_Valor))
				.setText(FF.treta(aux.getValorTotal()));

		return view;
	}

}
