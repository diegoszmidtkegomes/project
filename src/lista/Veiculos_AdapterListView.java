package lista;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fullservicecar.R;

@SuppressLint("ViewHolder")
public class Veiculos_AdapterListView extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Veiculos_ItemListView> itens;

	public Veiculos_AdapterListView(Context context,
			ArrayList<Veiculos_ItemListView> itens) {
		this.itens = itens;
		mInflater = LayoutInflater.from(context);

	}

	public int getCount() {
		return itens.size();
	}

	public Veiculos_ItemListView getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	public View getView(int position, View view, ViewGroup parent) {
		// Pega o item de acordo com a posção.
		Veiculos_ItemListView item = itens.get(position);
		// infla o layout para podermos preencher os dados
		view = mInflater.inflate(R.layout.veiculos_fragment_layout_listview,
				null);

		((TextView) view.findViewById(R.id.Veiculo_ListView_Nome)).setText(item
				.getNome());
		((TextView) view.findViewById(R.id.Veiculo_ListView_Marca))
				.setText(String.valueOf(item.getMarca()));
		((TextView) view.findViewById(R.id.Veiculo_ListView_Placa))
				.setText(item.getPlaca());
		((TextView) view.findViewById(R.id.Veiculo_ListView_Ano))
				.setText(String.valueOf(item.getAno()));
		return view;
	}

}
