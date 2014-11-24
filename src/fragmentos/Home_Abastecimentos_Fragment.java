package fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fullservicecar.R;

import funcoes.FAbastecimento;

public class Home_Abastecimentos_Fragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.home_abastecimentos_fragment, container,false);

		// Abastecimentos
		TextView Home_Abastecimentos_Quantidade   = (TextView) view.findViewById(R.id.Home_Abastecimentos_Quantidade);
		Home_Abastecimentos_Quantidade.setText(FAbastecimento.quantidade(getActivity()));
	    
		TextView Home_Abastecimentos_ValorTotal	  = (TextView) view.findViewById(R.id.Home_Abastecimentos_ValorTotal);
		Home_Abastecimentos_ValorTotal.setText(FAbastecimento.valorTotalConv(getActivity()));
	    
		TextView Home_Abastecimentos_KmPercorrido = (TextView) view.findViewById(R.id.Home_Abastecimentos_KmPercorrido);
		Home_Abastecimentos_KmPercorrido.setText(FAbastecimento.kmPercorridoConv(getActivity()));

		TextView Home_Abastecimentos_Litros   	  = (TextView) view.findViewById(R.id.Home_Abastecimentos_Litros);
		Home_Abastecimentos_Litros.setText(FAbastecimento.litrosConv(getActivity()));
		
		TextView Home_Abastecimentos_PrecoL   	  = (TextView) view.findViewById(R.id.Home_Abastecimentos_PrecoL);
		Home_Abastecimentos_PrecoL.setText(FAbastecimento.precoLConv(getActivity()));
		
		TextView Home_Abastecimentos_MediaL   	  = (TextView) view.findViewById(R.id.Home_Abastecimentos_MediaL);
		Home_Abastecimentos_MediaL.setText(FAbastecimento.mediaLConv(getActivity()));
		
		return view;
	}

}
