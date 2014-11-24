package fragmentos;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import banco_de_dados.DAOMotorista;

import com.example.fullservicecar.R;


public class Home_Perfil_Fragment extends Fragment{

	public Home_Perfil_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.home_perfil_fragment, container,false);
		
		// Perfil
		
		TextView Home_Texto_NomeUsuario 		 = (TextView) view.findViewById(R.id.Home_Texto_NomeUsuario);
		Home_Texto_NomeUsuario.setText(DAOMotorista.getInstancia(getActivity()).buscarTodos().get(0).getNome().toString());
				
		TextView Home_Texto_Email			     = (TextView) view.findViewById(R.id.Home_Texto_Email);
		Home_Texto_Email.setText(DAOMotorista.getInstancia(getActivity()).buscarTodos().get(0).getEmail().toString());
		
		TextView Home_Texto_DataDeNascimento     = (TextView) view.findViewById(R.id.Home_Texto_DataDeNascimento);
		Home_Texto_DataDeNascimento.setText(DAOMotorista.getInstancia(getActivity()).buscarTodos().get(0).getDataNascimento().toString().replace("-", "/"));
		
		TextView Home_Texto_CNH                  = (TextView) view.findViewById(R.id.Home_Texto_CNH);
		Home_Texto_CNH.setText(DAOMotorista.getInstancia(getActivity()).buscarTodos().get(0).getCnh().toString());
		
		return view;
	}
	

	
}
