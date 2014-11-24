package atividades;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import banco_de_dados.DAOMotorista;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;

public class SplashScreen extends Activity {
	
	public static boolean isLoad = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		new Thread() {

			public void run() {

				for (int i = 1; i <= 100; i++) {

					try {

						((ProgressBar) findViewById(R.id.splashScreen_progressBar)).setProgress(i);
						sleep(new Random().nextInt(30));

					} catch (Exception e) {

					}
					
				}
				Intent intent;
				if(DAOMotorista.getInstancia(getApplicationContext()).getTamanho() == 0){
					intent = new Intent(SplashScreen.this, Perfil_Inicial_Activity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					startActivity(intent);
					//startActivity(new Intent(SplashScreen.this, Perfil_Inicial_Activity.class));
				}else{
					
					if(DAOVeiculo.getInstancia(getApplicationContext()).getTamanho() == 0){
						intent = new Intent(SplashScreen.this, Veiculos_Inicial_Activity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
						startActivity(intent);
					}else{
						intent = new Intent(SplashScreen.this, AtividadePrincipal.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
						startActivity(intent);
					}
				}
				
				finish();
				
			}

		}.start();
	}
	
	
}