package fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fullservicecar.R;

import funcoes.FDespesa;

public class Home_Despesas_Fragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.home_despesas_fragment, container,false);

		// Despesas
		TextView Home_Texto_QuantidadeDeDespesas = (TextView) view.findViewById(R.id.Home_Texto_QuantidadeDeDespesas);
		Home_Texto_QuantidadeDeDespesas.setText(FDespesa.quantidade(getActivity()));
		
		TextView Home_Texto_MediaDasDespesas 	 = (TextView) view.findViewById(R.id.Home_Texto_MediaDasDespesas);
		Home_Texto_MediaDasDespesas.setText(FDespesa.mediaDespesaConv(getActivity()));
		
		TextView Home_Texto_MaiorDespesa 		 = (TextView) view.findViewById(R.id.Home_Texto_MaiorDespesa);
		Home_Texto_MaiorDespesa.setText(FDespesa.maiorDespesaConv(getActivity()));
		
		TextView Home_Texto_MenorDespesa 		 = (TextView) view.findViewById(R.id.Home_Texto_MenorDespesa);
		Home_Texto_MenorDespesa.setText(FDespesa.menorDespesaConv(getActivity()));		
		
		return view;
	}

}
