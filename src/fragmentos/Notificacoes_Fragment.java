package fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fullservicecar.R;

import funcoes.Mensagem;


public class Notificacoes_Fragment extends Fragment{
	
	public Notificacoes_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.notificacoes_fragment, container,false);

		return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		new Mensagem("Aviso!", "Falta implementar!", "OK", R.drawable.icone_aviso, getActivity());
		
	}

}
