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
import android.location.Location;
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

public class GPS_Opc1 extends Activity {

	double Latidude1; 
	double Latidude2;
	double Longitude1;
	double Longitude2;
	ListView searchOut;
	private ArrayAdapter<String> adapter;
	Geocoder geocoder;
	final static int maxResults = 5;
	List<Address> locationList;
	List<String> locationNameList;
	Location l;
	GPSTracker gps;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps_menu1);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("GPS");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		gps = new GPSTracker(GPS_Opc1.this);
		// checar se o gps está ativado
		if(gps.canGetLocation()){                  
			Latidude1   = gps.getLatitude();
			Longitude1  = gps.getLongitude();                   
			//Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + Latidude1 + "\nLong: " + Longitude1, Toast.LENGTH_LONG).show();   
		}else{
			gps.showSettingsAlert();
		}  
		searchOut= (ListView)findViewById(R.id.listaenderecos);
		Button btatualizar = (Button) findViewById(R.id.btatualizar);
		Button btbuscar = (Button) findViewById(R.id.btbuscar);
		btatualizar.setOnClickListener(btatualizarOnClickListener);
		btbuscar.setOnClickListener(btbuscarOnClickListener);
		geocoder = new Geocoder(this, Locale.getDefault());
		locationNameList = new ArrayList<String>(); 
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locationNameList);
		searchOut.setAdapter(adapter);
		searchOut.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, 	int position, long id) {
				Latidude2 = locationList.get(position).getLatitude();
				Longitude2= locationList.get(position).getLongitude();
				double dLat = Math.toRadians(Latidude2-Latidude1);
				double dLng = Math.toRadians(Longitude2-Longitude1);
				double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(Latidude1)) * Math.cos(Math.toRadians(Latidude2)) * Math.sin(dLng/2) * Math.sin(dLng/2);

				double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
				double d = (6371 * c) * 1.34;				
				DecimalFormat df = new DecimalFormat("#.##");
				double distanciaformatada = Double.parseDouble(df.format(d));
				/*Toast.makeText(getApplicationContext(), "latitude 1 " + Latidude1 + "longitude" + Longitude1, Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(), "latitude 2 " + Latidude2  + "longitude" + Longitude2, Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(), "distancia: " + distanciaformatada, Toast.LENGTH_SHORT).show();*/
				
				AlertDialog.Builder altDialog= new AlertDialog.Builder(GPS_Opc1.this);
				altDialog.setTitle("Cálculos ");
				
				double distancia = distanciaformatada, 
						   autonomia = FAbastecimento.proximaDistancia(AtividadePrincipal.contexto),
						   litros    =  FAbastecimento.mediaL(AtividadePrincipal.contexto),
						   res = (distancia - autonomia) / litros;
				
				if(distanciaformatada > FAbastecimento.proximaDistancia(AtividadePrincipal.contexto)){
					altDialog.setMessage("Distância entre os locais é de " + distanciaformatada + " Km\n" +
							"A autonomia do veículo é de "  + FAbastecimento.proximaDistanciaConv(AtividadePrincipal.contexto) + "\n" +
							"Será necessário mais " + res + " L"
							//"Será necessário mais " + FAbastecimento.conv(((distanciaformatada - FAbastecimento.proximaDistancia(AtividadePrincipal.contexto) / FAbastecimento.mediaL(AtividadePrincipal.contexto)))) + " L para completar o percurso" 
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
	OnClickListener btatualizarOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			gps = new GPSTracker(GPS_Opc1.this);
			// check if GPS enabled       
			if(gps.canGetLocation()){                  
				Latidude1   = gps.getLatitude();
				Longitude1  = gps.getLongitude();            
			Toast.makeText(getApplicationContext(), "A sua localização é: \nLat: " + Latidude1 + "\nLong: " + Longitude1, Toast.LENGTH_LONG).show();   
			}else{
				// can't get location
				// GPS or Network is not enabled
				// Ask user to enable GPS/network in settings
				gps.showSettingsAlert();
			}  

		}
	};


	OnClickListener btbuscarOnClickListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			
			EditText enderecobuscar = (EditText) findViewById(R.id.endereco2);
			String locationName = enderecobuscar.getText().toString();

			if(locationName == null){
				Toast.makeText(getApplicationContext(), "Digite um endereço válido para buscar", Toast.LENGTH_SHORT).show();
			}else{
				try {
					locationList = geocoder.getFromLocationName(locationName, maxResults);
					if(locationList == null){
						Toast.makeText(getApplicationContext(),"Digite um endereço válido para buscar", Toast.LENGTH_SHORT).show();
					}else{
						if(locationList.isEmpty()){
							Toast.makeText(getApplicationContext(), "Endereço não encontrado no Google", Toast.LENGTH_SHORT).show();
						}else{

							locationNameList.clear();

							for(Address i : locationList){
								if(i.getFeatureName() == null){
									locationNameList.add("Desconhecido");
								}else{
									String endereco = i.getFeatureName();									
									String cidade = i.getLocality();
									String pais = i.getCountryName();
									String estado = i.getAdminArea();
									//Toast.makeText(getApplicationContext(), estado + cidade + endereco + pais, Toast.LENGTH_SHORT).show();
									if(endereco == null ){
										endereco = "";
									}else{
										endereco = endereco + ", ";
									}
									if(cidade == null ){
										cidade = "";
									}else{
										cidade = cidade + ", ";
									}
									if(pais == null ){
										pais = "";
									}else{
										pais = pais + ", ";
									}
									if(estado == null ){
										estado = "";
									}else{
										estado = estado + ", ";
									}
									locationNameList.add(endereco + cidade+ pais + estado);
									//locationNameList.add(i.getFeatureName() + ", " + i.getLocality() + ", " + i.getCountryName());
								}
							}
							adapter.notifyDataSetChanged();
						}
					}

				} catch (IOException e) {
					Toast.makeText(getApplicationContext(), "Conexão com a Internet não disponível. Verifique sua Conexão", Toast.LENGTH_SHORT).show();
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

			startActivity(new Intent(this,
					AtividadePrincipal.class));

			return true;

		default:

			return super.onOptionsItemSelected(item);
		}
	}
}
