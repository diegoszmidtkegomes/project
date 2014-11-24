package fragmentos;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import atividades.Perfil_Activity;
import banco_de_dados.DAOLembretes;
import banco_de_dados.DAOMotorista;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;

import funcoes.FImagem;

@SuppressLint("NewApi")
public class Home_Fragment extends Fragment{
	
	public Home_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.home_fragment, container,false);
		getFragmentManager().beginTransaction().replace(R.id.Home_Fragmento, new Home_Perfil_Fragment()).commit();
		
		// Perfil
		
		TextView Home_Texto_NomeUsuario 		 = (TextView) view.findViewById(R.id.Home_Texto_NomeUsuario);
		Home_Texto_NomeUsuario.setText(DAOMotorista.getInstancia(getActivity()).buscarTodos().get(0).getNome().toString());
		
		ImageButton perfil         = (ImageButton) view.findViewById(R.id.Home_Botao_Perfil);
		ImageButton veiculos       = (ImageButton) view.findViewById(R.id.Home_Botao_Veiculos);
		ImageButton abastecimentos = (ImageButton) view.findViewById(R.id.Home_Botao_Abastecimentos);
		ImageButton despesas 	   = (ImageButton) view.findViewById(R.id.Home_Botao_Despesas);
		ImageButton servicos	   = (ImageButton) view.findViewById(R.id.Home_Botao_Servicos);
		ImageButton img = (ImageButton) view.findViewById(R.id.Home_Imagem_FotoUsuario);
		img.setBackground(new BitmapDrawable(getResources(), FImagem.getImage(DAOMotorista.getInstancia(getActivity()).buscarTodos().get(0).getFoto())));
		//img.setImageBitmap(FImagem.getImage(DAOMotorista.getInstancia(getActivity()).buscarTodos().get(0).getFoto()));
		
		Button		editarPerfil   = (Button)      view.findViewById(R.id.Home_Botao_EditarPerfil);
		((TextView) view.findViewById(R.id.Home_Texto_Lembretes)).setText(String.valueOf(DAOLembretes.getInstancia(getActivity()).getTamanho()));
		((TextView) view.findViewById(R.id.Home_Texto_Veiculos)).setText(String.valueOf(DAOVeiculo.getInstancia(getActivity()).getTamanho()));

		editarPerfil.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), Perfil_Activity.class));
			}
		});
		perfil.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				getFragmentManager().beginTransaction().replace(R.id.Home_Fragmento, new Home_Perfil_Fragment()).commit();
			
			}
		});
		
		veiculos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				getFragmentManager().beginTransaction().replace(R.id.Home_Fragmento, new Home_Veiculos_Fragment()).commit();
			
			}
		});
		
		abastecimentos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				getFragmentManager().beginTransaction().replace(R.id.Home_Fragmento, new Home_Abastecimentos_Fragment()).commit();
			
			}
		});


		despesas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				getFragmentManager().beginTransaction().replace(R.id.Home_Fragmento, new Home_Despesas_Fragment()).commit();
			
			}
		});
		
		servicos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				getFragmentManager().beginTransaction().replace(R.id.Home_Fragmento, new Home_Servicos_Fragment()).commit();
			
			}
		});
		
		return view;
	
	}
	

}
