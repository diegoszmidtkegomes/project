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
import banco_de_dados.Abastecimento;
import banco_de_dados.DAOAbastecimento;

import com.example.fullservicecar.R;

import funcoes.FAbastecimento;
import funcoes.FF;

@SuppressLint({ "InflateParams", "ViewHolder" })
public class Abastecimentos_AdapterListView extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Abastecimentos_ItemListView> itens;

	public Abastecimentos_AdapterListView(Context context,
			ArrayList<Abastecimentos_ItemListView> itens) {
		this.itens = itens;
		mInflater = LayoutInflater.from(context);

	}

	public int getCount() {
		return itens.size();
	}

	public Abastecimentos_ItemListView getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		// Pega o item de acordo com a posção.
		Abastecimentos_ItemListView item = itens.get(position);
		// infla o layout para podermos preencher os dados
		view = mInflater.inflate(
				R.layout.abastecimento_fragment_layout_listview, null);
		Abastecimento aux = DAOAbastecimento.getInstancia(
				AtividadePrincipal.contexto).buscarPorId(item.getId());
		((TextView) view
				.findViewById(R.id.Abastecimento_Item_ListView_Combustivel))
				.setText(item.getCombustivel());
		((TextView) view.findViewById(R.id.Abastecimento_Item_ListView_Data))
				.setText(item.getData());
		((TextView) view.findViewById(R.id.Abastecimento_Item_ListView_Km))
				.setText(FF.treta(aux.getOdometro()));
		((TextView) view
				.findViewById(R.id.Abastecimento_Item_ListView_MediaLitro))
				.setText(FF.treta(FAbastecimento
						.mediaL(AtividadePrincipal.contexto)));
		((TextView) view.findViewById(R.id.Abastecimento_Item_ListView_Posto))
				.setText(item.getPosto());
		((TextView) view
				.findViewById(R.id.Abastecimento_Item_ListView_PrecoCombustivel))
				.setText(FF.treta(aux.getValorTotal() / aux.getLitros()));
		((TextView) view
				.findViewById(R.id.Abastecimento_Item_ListView_QtdeCombustivel))
				.setText(FF.treta(aux.getLitros()));
		((TextView) view
				.findViewById(R.id.Abastecimento_Item_ListView_ValorTotal))
				.setText(FF.treta(aux.getValorTotal()));

		return view;
	}

}
