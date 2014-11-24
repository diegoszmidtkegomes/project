package fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;

public class Home_Veiculos_Fragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.home_veiculos_fragment, container,false);

		// Veículos
		
		TextView Home_Veiculo_Nome 		 = (TextView) view.findViewById(R.id.Home_Veiculo_Nome);
		Home_Veiculo_Nome.setText(DAOVeiculo.getInstancia(getActivity()).buscarTodos().get(0).getNome());
		
		TextView Home_Veiculo_Marca		 = (TextView) view.findViewById(R.id.Home_Veiculo_Marca);
		Home_Veiculo_Marca.setText(DAOVeiculo.getInstancia(getActivity()).buscarTodos().get(0).getMarca());
		
		TextView Home_Veiculo_Odometro   = (TextView) view.findViewById(R.id.Home_Veiculo_OdometroAtual);
		Home_Veiculo_Odometro.setText(String.valueOf(DAOVeiculo.getInstancia(getActivity()).buscarTodos().get(0).getOdometroAtual()));
		
		
		TextView Home_Veiculo_Veiculo	 = (TextView) view.findViewById(R.id.Home_Veiculo_Placa);
		Home_Veiculo_Veiculo.setText(String.valueOf(DAOVeiculo.getInstancia(getActivity()).buscarTodos().get(0).getPlaca()));
		
		TextView Home_Veiculo_Ano 		 = (TextView) view.findViewById(R.id.Home_Veiculo_Ano);
		Home_Veiculo_Ano.setText(String.valueOf(DAOVeiculo.getInstancia(getActivity()).buscarTodos().get(0).getAno()));
		return view;
	}

}
