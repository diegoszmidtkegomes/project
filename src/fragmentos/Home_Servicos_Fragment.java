package fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fullservicecar.R;

import funcoes.FServico;

public class Home_Servicos_Fragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.home_servicos_fragment, container,false);

		// Serviços
		
		TextView Home_Texto_QuantidadeDeServicos = (TextView) view.findViewById(R.id.Home_Texto_QuantidadeDeServicos);
		Home_Texto_QuantidadeDeServicos.setText(FServico.quantidade(getActivity()));
		
		TextView Home_Texto_MediaDosServicos 	 = (TextView) view.findViewById(R.id.Home_Texto_MediaDosServicos);
		Home_Texto_MediaDosServicos.setText(FServico.mediaServicoConv(getActivity()));
		
		TextView Home_Texto_ServicoMaisCaro 	 = (TextView) view.findViewById(R.id.Home_Texto_ServicoMaisCaro);
		Home_Texto_ServicoMaisCaro.setText(FServico.maiorServicoConv(getActivity()));
		
		TextView Home_Texto_ServicoMaisBarato 	 = (TextView) view.findViewById(R.id.Home_Texto_ServicoMaisBarato);
		Home_Texto_ServicoMaisBarato.setText(FServico.menorServicoConv(getActivity()));		
		
		return view;
	}
	
}
