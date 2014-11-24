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
import banco_de_dados.DAOLembretes;

import com.example.fullservicecar.R;

import funcoes.FF;

@SuppressLint("ViewHolder")
public class Lembretes_AdapterListView extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Lembretes_ItemListView> itens;

	public Lembretes_AdapterListView(Context context,
			ArrayList<Lembretes_ItemListView> itens) {
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
	public Lembretes_ItemListView getItem(int position) {
		return itens.get(position);
	}

	/**
	 * Sem implementação
	 * 
	 * @param position
	 * @return
	 */
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	public View getView(int position, View view, ViewGroup parent) {
		// Pega o item de acordo com a posção.
		Lembretes_ItemListView item = itens.get(position);
		// infla o layout para podermos preencher os dados
		view = mInflater.inflate(R.layout.lembretes_fragment_layout_listview,
				null);

		/*
		 * ((TextView)
		 * view.findViewById(R.id.Lembretes_Item_ListView_Motivo)).setText
		 * (String.valueOf(item.getMotivo())); ((TextView)
		 * view.findViewById(R.id
		 * .Lembretes_Item_ListView_Odometro)).setText(String
		 * .valueOf(item.getOdometro())); ((TextView)
		 * view.findViewById(R.id.Lembretes_Item_ListView_Tipo
		 * )).setText(String.valueOf(item.getTipo())); ((TextView)
		 * view.findViewById
		 * (R.id.Lembretes_Item_ListView_Data)).setText(String.valueOf
		 * (item.getData()));
		 */
		((TextView) view.findViewById(R.id.Lembretes_Item_ListView_Motivo))
				.setText(String.valueOf(item.getMotivo()));
		((TextView) view.findViewById(R.id.Lembretes_Item_ListView_Odometro))
				.setText(FF.treta(DAOLembretes
						.getInstancia(AtividadePrincipal.contexto)
						.buscarPorId(item.getId()).getOdometro()));
		((TextView) view.findViewById(R.id.Lembretes_Item_ListView_Tipo))
				.setText(String.valueOf(item.getTipo()));
		((TextView) view.findViewById(R.id.Lembretes_Item_ListView_Data))
				.setText(String.valueOf(item.getData()));

		return view;
	}

}
