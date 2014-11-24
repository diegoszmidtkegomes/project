package gps;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import atividades.AtividadePrincipal;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;

import funcoes.FAbastecimento;

public class GPS_Opc3 extends Activity {

	double Latidude1;
	double Latidude2;
	double Longitude1;
	double Longitude2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps_menu3);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("GPS");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		Button btcalcularcoordenadas = (Button) findViewById(R.id.op3btcalcular);
		btcalcularcoordenadas
				.setOnClickListener(btcalcularcoordenadasOnClickListener);

	}

	OnClickListener btcalcularcoordenadasOnClickListener = new OnClickListener() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		@Override
		public void onClick(View arg0) {
			EditText latitudebuscar1 = (EditText) findViewById(R.id.op3latitude1);
			EditText latitudebuscar2 = (EditText) findViewById(R.id.op3latitude2);
			EditText longitudebuscar2 = (EditText) findViewById(R.id.op3longitude2);
			EditText longitudebuscar1 = (EditText) findViewById(R.id.op3longitude1);

			String strLatidude1 = latitudebuscar1.getText().toString();
			String strLatidude2 = latitudebuscar2.getText().toString();
			String strLongitude1 = longitudebuscar1.getText().toString();
			String strLongitude2 = longitudebuscar2.getText().toString();
			if (latitudebuscar1.getText().toString().equals("")) {
				Toast.makeText(getApplicationContext(),
						"Digite a primeira latitude", Toast.LENGTH_SHORT)
						.show();
				latitudebuscar1.requestFocus();
			} else {
				if (longitudebuscar1.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Digite a primeira longitude", Toast.LENGTH_SHORT)
							.show();
					longitudebuscar1.requestFocus();
				} else {
					if (latitudebuscar2.getText().toString().equals("")) {
						Toast.makeText(getApplicationContext(),
								"Digite a segunda latitude", Toast.LENGTH_SHORT)
								.show();
						latitudebuscar2.requestFocus();
					} else {
						if (longitudebuscar2.getText().toString().equals("")) {
							Toast.makeText(getApplicationContext(),
									"Digite a segunda longitude",
									Toast.LENGTH_SHORT).show();
							latitudebuscar1.requestFocus();
						} else {

							Latidude1 = Double.parseDouble(strLatidude1);
							Latidude2 = Double.parseDouble(strLatidude2);
							Longitude1 = Double.parseDouble(strLongitude1);
							Longitude2 = Double.parseDouble(strLongitude2);
						
							double dLat = Math.toRadians(Latidude2-Latidude1);
							double dLng = Math.toRadians(Longitude2-Longitude1);
							double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(Latidude1)) * Math.cos(Math.toRadians(Latidude2)) * Math.sin(dLng/2) * Math.sin(dLng/2);

							double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
							double d = (6371 * c) * 1.34;				
							DecimalFormat df = new DecimalFormat("#.##");
							double distanciaformatada = Double.parseDouble(df.format(d));

							
							AlertDialog.Builder altDialog = new AlertDialog.Builder(
									GPS_Opc3.this);
							altDialog.setTitle("Cálculos ");
							if(distanciaformatada > FAbastecimento.proximaDistancia(AtividadePrincipal.contexto)){
								altDialog.setMessage("Distância entre os locais é de " + distanciaformatada + " Km\n" +
										"A autonomia do veículo é de "  + FAbastecimento.proximaDistanciaConv(AtividadePrincipal.contexto) + "\n" +
										"Será necessário mais " + FAbastecimento.conv(((distanciaformatada - FAbastecimento.proximaDistancia(AtividadePrincipal.contexto) / FAbastecimento.mediaL(AtividadePrincipal.contexto)))) + " L para completar o percurso" 
										);
							}else{
								altDialog.setMessage("Distância entre os locais é de " + distanciaformatada + " Km\n" +
										"A autonomia do veículo é de "  + FAbastecimento.proximaDistanciaConv(AtividadePrincipal.contexto));
							}
							altDialog.setNeutralButton("OK", null);
							altDialog.show();

						}
					}
				}

			}

		}
	};

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:

			startActivity(new Intent(this, AtividadePrincipal.class));

			return true;

		default:

			return super.onOptionsItemSelected(item);
		}
	}
}
