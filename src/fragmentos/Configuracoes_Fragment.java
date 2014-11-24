package fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import banco_de_dados.Configuracoes;
import banco_de_dados.DAOConfiguracoes;

import com.example.fullservicecar.R;


public class Configuracoes_Fragment extends Fragment{
	
	public Configuracoes_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.configuracoes_fragment, container,false);
		getActivity().getActionBar().setSubtitle("Full Service Car");
		
		CheckBox lembreteAtivo = (CheckBox) view.findViewById(R.id.Config_LembreteAtivo);
		
		if(DAOConfiguracoes.getInstancia(getActivity()).buscarTodos().get(0).getLembreteAtivo() == 1){
			lembreteAtivo.setChecked(true);
		}else{
			lembreteAtivo.setChecked(false);
		}
		
		lembreteAtivo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					DAOConfiguracoes.getInstancia(getActivity()).editar(new Configuracoes(1), 1);
				}else{
					DAOConfiguracoes.getInstancia(getActivity()).editar(new Configuracoes(0), 1);
				}
			}
		});
		
		return view;
	}
}
