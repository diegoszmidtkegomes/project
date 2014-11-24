package menu_lateral;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import atividades.AtividadePrincipal;
import banco_de_dados.DAOMotorista;

import com.example.fullservicecar.R;

import funcoes.FImagem;

@SuppressLint("NewApi")
public class CustomSpinnerAdapter extends ArrayAdapter<SpinnerItem> {

	public CustomSpinnerAdapter(Context context, int layoutResourceID, int textViewResourceId, List<SpinnerItem> spinnerDataList) {
		
		super(context, layoutResourceID, textViewResourceId, spinnerDataList);
		this.context = context;
		this.layoutResID = layoutResourceID;
		this.spinnerData = spinnerDataList;

	}
	
	private static class SpinnerHolder {
		
		ImageButton fotoUsuario;
		TextView nomeUsuario, emailUsuario;

	}

	Context context;
	int layoutResID;
	List<SpinnerItem> spinnerData;

	public CustomSpinnerAdapter(Context context, int layoutResourceID,
			List<SpinnerItem> spinnerDataList) {
		super(context, layoutResourceID, spinnerDataList);

		this.context = context;
		this.layoutResID = layoutResourceID;
		this.spinnerData = spinnerDataList;

	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		SpinnerHolder holder;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();

			row = inflater.inflate(layoutResID, parent, false);
			holder = new SpinnerHolder();

			holder.fotoUsuario = (ImageButton) row.findViewById(R.id.fotoUsuario);
			holder.nomeUsuario = (TextView) row.findViewById(R.id.nomeUsuario);
			holder.emailUsuario = (TextView) row.findViewById(R.id.emailUsuario);

			row.setTag(holder);
		} else {
			holder = (SpinnerHolder) row.getTag();

		}

		SpinnerItem spinnerItem = spinnerData.get(position);

		//holder.fotoUsuario.setImageDrawable(row.getResources().getDrawable(spinnerItem.getFotoUsuario()));
		holder.fotoUsuario.setBackground(new BitmapDrawable(row.getResources(), FImagem.getImage(DAOMotorista.getInstancia(AtividadePrincipal.contexto).buscarTodos().get(0).getFoto())));
		holder.nomeUsuario.setText(spinnerItem.getNomeUsuario());
		holder.emailUsuario.setText(spinnerItem.getEmailUsuario());

		return row;

	}

}