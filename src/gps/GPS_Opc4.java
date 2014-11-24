package gps;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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

public class GPS_Opc4 extends Activity {

	double Latitude;
	double Longitude;
	Geocoder geocoder;
	StringBuilder result = new StringBuilder();
	StringBuilder endereco;
	String enderecocerto;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps_menu4);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("GPS");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		Button btbuscarendereco = (Button) findViewById(R.id.op4btbuscar);
		btbuscarendereco.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText latitudebuscar = (EditText) findViewById(R.id.op4latitude);
				EditText longitudebuscar = (EditText) findViewById(R.id.op4longitude);

				String strLatidude = latitudebuscar.getText().toString();
				String strLongitude = longitudebuscar.getText().toString();
				List<Address> enderecos = null;
				if (latitudebuscar.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),
							"Digite a latitude", Toast.LENGTH_SHORT).show();
					latitudebuscar.requestFocus();
				} else {
					if (longitudebuscar.getText().toString().equals("")) {
						Toast.makeText(getApplicationContext(),
								"Digite a longitude", Toast.LENGTH_SHORT)
								.show();
						longitudebuscar.requestFocus();
					} else {
						Latitude = Double.parseDouble(strLatidude);
						Longitude = Double.parseDouble(strLongitude);

						Geocoder geocoder = new Geocoder(
								getApplicationContext(), Locale.getDefault());
						try {
							enderecos = geocoder.getFromLocation(Latitude,
									Longitude, 1);
						} catch (IOException e) {
							// TODO: handle exception
						}

						if (enderecos != null) {
							Address end = enderecos.get(0);
							enderecocerto = String.format(
									"%s, %s, %s",
									end.getMaxAddressLineIndex() > 0 ? end
											.getAddressLine(0) : "", end
											.getLocality(), end
											.getCountryName());
							AlertDialog.Builder altDialog = new AlertDialog.Builder(
									GPS_Opc4.this);
							altDialog.setTitle("Cálculos ");
							altDialog.setMessage("Endereço das Coordenadas "
									+ enderecocerto);
							altDialog.setNeutralButton("OK",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									});
							altDialog.show();
						} else {
							Toast.makeText(
									getApplicationContext(),
									"Não foi possível obter o endereço. Verifique as coordenadas e tente novamente.",
									Toast.LENGTH_SHORT).show();
						}

					}
				}
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:

			startActivity(new Intent(this,
					AtividadePrincipal.class));

			return true;

		default:

			return super.onOptionsItemSelected(item);
		}
	}
}
