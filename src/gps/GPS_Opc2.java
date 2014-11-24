package gps;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import atividades.AtividadePrincipal;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;

import funcoes.FAbastecimento;

public class GPS_Opc2 extends Activity {

	double Latidude1;
	double Latidude2;
	double Longitude1;
	double Longitude2;
	ListView listaendereco1;
	ListView listaendereco2;
	private ArrayAdapter<String> adapter1;
	private ArrayAdapter<String> adapter2;
	Geocoder geocoder1;
	Geocoder geocoder2;
	final static int maxResults = 5;
	List<Address> locationList2;
	List<Address> locationList1;
	List<String> locationNameList2;
	List<String> locationNameList1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps_menu2);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("GPS");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		final EditText enderecobuscar2 = (EditText) findViewById(R.id.op2endereco2);
		final EditText enderecobuscar1 = (EditText) findViewById(R.id.op2endereco1);
		listaendereco1 = (ListView) findViewById(R.id.op2lisendereco1);
		listaendereco2 = (ListView) findViewById(R.id.op2listaenederecos2);
		Button btendereco1 = (Button) findViewById(R.id.op2btbuscar1);
		Button btendereco2 = (Button) findViewById(R.id.op2btbusca2);
		btendereco1.setOnClickListener(btendereco1OnClickListener);
		btendereco2.setOnClickListener(btendereco2OnClickListener);
		geocoder1 = new Geocoder(this, Locale.getDefault());
		geocoder2 = new Geocoder(this, Locale.getDefault());
		locationNameList1 = new ArrayList<String>(); // vazio quando iniciado
		locationNameList2 = new ArrayList<String>(); // vazio quando iniciado
		adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, locationNameList1);
		adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, locationNameList2);
		listaendereco1.setAdapter(adapter1);
		listaendereco2.setAdapter(adapter2);
		listaendereco1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Latidude1 = locationList1.get(position).getLatitude();
				Longitude1 = locationList1.get(position).getLongitude();
				String endereco = locationList1.get(position).getFeatureName();
				/*Toast.makeText(getApplicationContext(),
						"Endereço 1 " + endereco + Latidude1 + Longitude1,
						Toast.LENGTH_SHORT).show();*/
				enderecobuscar1.setText(endereco);
				if (enderecobuscar2.getText() == null) {
					Toast.makeText(getApplicationContext(),
							"Digite o outro endereço", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
		listaendereco2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Latidude2 = locationList2.get(position).getLatitude();
				Longitude2 = locationList2.get(position).getLongitude();
				String endereco = locationList2.get(position).getFeatureName();
				Toast.makeText(getApplicationContext(),
						"Endereço 2 " + endereco + Latidude2 + Longitude2,
						Toast.LENGTH_SHORT).show();
				enderecobuscar2.setText(endereco);
				double dLat = Math.toRadians(Latidude2 - Latidude1);
				double dLng = Math.toRadians(Longitude2 - Longitude1);
				double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
						+ Math.cos(Math.toRadians(Latidude1))
						* Math.cos(Math.toRadians(Latidude2))
						* Math.sin(dLng / 2) * Math.sin(dLng / 2);
				double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
				double d = (6371 * c) * 1.34;
				/*Toast.makeText(
						getApplicationContext(),
						"latitude 1 " + Latidude1 + " longitude 1 "
								+ Longitude1, Toast.LENGTH_SHORT).show();
				Toast.makeText(
						getApplicationContext(),
						"latitude 2 " + Latidude2 + " longitude 2 "
								+ Longitude2, Toast.LENGTH_SHORT).show();*/
				DecimalFormat df = new DecimalFormat("#.##");
				double distanciaformatada = Double.parseDouble(df.format(d));
				AlertDialog.Builder altDialog = new AlertDialog.Builder(
						GPS_Opc2.this);
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
		});

	}

	OnClickListener btendereco2OnClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			EditText enderecobuscar2 = (EditText) findViewById(R.id.op2endereco2);
			String locationName = enderecobuscar2.getText().toString();
			if (locationName == null) {
				Toast.makeText(getApplicationContext(),
						"Digite um endereço válido para buscar",
						Toast.LENGTH_SHORT).show();
			} else {
				try {
					locationList2 = geocoder2.getFromLocationName(locationName,
							maxResults);
					if (locationList2 == null) {
						Toast.makeText(getApplicationContext(),
								"Digite um endereço válido para buscar",
								Toast.LENGTH_SHORT).show();
					} else {
						if (locationList2.isEmpty()) {
							Toast.makeText(getApplicationContext(),
									"Endereço não encontrado no Google",
									Toast.LENGTH_SHORT).show();
						} else {
							locationNameList2.clear();
							for (Address i : locationList2) {
								if (i.getFeatureName() == null) {
									locationNameList2.add("Desconhecido");
								} else {
									locationNameList2.add(i.getFeatureName()
											+ ", " + i.getLocality() + ", "
											+ i.getCountryName());
									// enderecocerto =
									// String.format("%s, %s, %s",
									// end.getMaxAddressLineIndex() > 0 ?
									// end.getAddressLine(0) : "",
									// end.getLocality(), end.getCountryName());

								}
							}
							adapter2.notifyDataSetChanged();
						}
					}
				} catch (IOException e) {
					Toast.makeText(
							getApplicationContext(),
							"Conexão com a Internet não disponível. Verifique sua Conexão",
							Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
			}
		}
	};

	OnClickListener btendereco1OnClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {

			EditText enderecobuscar1 = (EditText) findViewById(R.id.op2endereco1);
			String locationName = enderecobuscar1.getText().toString();
			if (locationName == null) {
				Toast.makeText(getApplicationContext(),
						"Digite um endereço válido para buscar",
						Toast.LENGTH_SHORT).show();
			} else {
				try {
					locationList1 = geocoder1.getFromLocationName(locationName,
							maxResults);
					if (locationList1 == null) {
						Toast.makeText(getApplicationContext(),
								"Digite um endereço válido para buscar",
								Toast.LENGTH_SHORT).show();
					} else {
						if (locationList1.isEmpty()) {
							Toast.makeText(getApplicationContext(),
									"Endereço não encontrado no Google",
									Toast.LENGTH_SHORT).show();
						} else {
							locationNameList1.clear();
							for (Address i : locationList1) {
								if (i.getFeatureName() == null) {
									locationNameList1.add("Desconhecido");
								} else {
									locationNameList1.add(i.getFeatureName());
								}
							}
							adapter1.notifyDataSetChanged();
						}
					}

				} catch (IOException e) {
					Toast.makeText(
							getApplicationContext(),
							"Conexão com a Internet não disponível. Verifique sua Conexão",
							Toast.LENGTH_SHORT).show();
					e.printStackTrace();
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
