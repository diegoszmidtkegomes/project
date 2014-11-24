package atividades;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import banco_de_dados.DAOMotorista;
import banco_de_dados.DAOVeiculo;
import banco_de_dados.Veiculo;

import com.example.fullservicecar.R;

import funcoes.Mask;
import funcoes.Mensagem;


public class Veiculos_Inicial_Activity extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.veiculos_activity);
		getActionBar().setTitle("Cadastrar veículo");
		getActionBar().setSubtitle(DAOMotorista.getInstancia(this).buscarTodos().get(0).getNome());
		if(DAOVeiculo.getInstancia(this).getTamanho() > 0){
			Intent intent = new Intent(Veiculos_Inicial_Activity.this, AtividadePrincipal.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(intent);
		}
		new Mensagem("Informativo!", "Você deverá registrar um veículo para prosseguir!", "Ok", R.drawable.icone_informativo, Veiculos_Inicial_Activity.this);
		String carros[] = {
				"Selecione", 
				"Agrale", "Aston Martin", "Audi",
				"Bentley", "BMW",
				"Changan", "Cherry", "Chevrolet", "Chrysler", "Citröen",
				"Dodge", 
				"Effa",
				"Ferrari",  "Fiat", "Ford",  
				"Gelly", 
				"Hafei",  "Honda",  "Hiunday", 
				"Iveco",  
				"JAC Motors",  "Jaguar",  "Jeep",  "Jinbei",   
				"Kia", 
				"Lamborghini",  "Land Rover",  "Lifan", 
				"Mahindra",  "Maserati",  "Mercedes-Benz",  "MG Motors",  "Mini",  "Mitsubishi", 
				"Nissan",
				"Peugeot",  "Porsche", 
				"Ram",  "Renault",  
				"Smart",  "Ssangyong",  "Subaru", "Suzuki",
				"Toyota",  "Troller",
				"Volkswagen",  "Volvo", 
				"Outros"
		};
		
		String veiculo[] = {
				"Selecione",
				"Camioneta", "Camionete", "Caminhão", "Carro", 
				"Jipe",
				"Motocicleta",
				"Pickup",
				"Veículo Oficial",
				"Outros"
		};
		
		String anos[] = new String[Calendar.getInstance().get(Calendar.YEAR) + 3 - 1900];
		
		int x = 1;
		anos[0] = "Selecione";
		
		for(int i = Calendar.getInstance().get(Calendar.YEAR) + 1; i >= 1900; i--){
			anos[x] = String.valueOf(i);
			x++;
		}
		String modeloDoVeiculo[] = {
				
				"Selecione",
				"Hatch",
				"Sedan",
				"Outros"
				
		};
		
		String potencias[] = {
				
				"CC",
				"CV",
				"HP",
				"Kgf",
				"Outros"
				
		};
		
		((Spinner) findViewById(R.id.Veiculos_Spinner_MarcaDoVeiculo)) .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carros));
		((Spinner) findViewById(R.id.Veiculos_Spinner_TipoDeVeiculo))  .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, veiculo));
		((Spinner) findViewById(R.id.Veiculos_Spinner_Ano))            .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, anos));
		((Spinner) findViewById(R.id.Veiculos_Spinner_Modelo))		   .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modeloDoVeiculo));
		((Spinner) findViewById(R.id.Veiculos_Spinner_TipoDePotencia)) .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, potencias));
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.salvar, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

		case R.id.action_salvar:

			salvar();
			

			return true;

		case android.R.id.home:

			startActivity(new Intent(this, Perfil_Inicial_Activity.class));

			return true;

		default:

			return super.onOptionsItemSelected(item);
		}
	}
	
	private void salvar() {

		final EditText nomeDoVeiculo  = (EditText) findViewById(R.id.Veiculos_InserirTexto_NomeDoVeiculo);
		EditText placa          = (EditText) findViewById(R.id.Veiculos_InserirTexto_Placa);
		EditText volDoTanque    = (EditText) findViewById(R.id.Veiculos_InserirTexto_VolumeDoTanque);
		
		Spinner  marca          = (Spinner)  findViewById(R.id.Veiculos_Spinner_MarcaDoVeiculo);
		Spinner  tipoDeVeiculo  = (Spinner)  findViewById(R.id.Veiculos_Spinner_TipoDeVeiculo);
	    Spinner  ano			= (Spinner)  findViewById(R.id.Veiculos_Spinner_Ano);

	    EditText RENAVAM 	    = (EditText) findViewById(R.id.Veiculos_InserirTexto_RENAVAM);
		EditText chassi 		= (EditText) findViewById(R.id.Veiculos_InserirTexto_Chassi);
		chassi.addTextChangedListener(Mask.insert("#################", chassi));
		
		Spinner  modelo 		= (Spinner)  findViewById(R.id.Veiculos_Spinner_Modelo);
		EditText nDePortas		= (EditText) findViewById(R.id.Veiculos_InserirTexto_NumeroDePortas);
		EditText potencia		= (EditText) findViewById(R.id.Veiculos_InserirTexto_Potencia);
		Spinner  tipoPotencia	= (Spinner)  findViewById(R.id.Veiculos_Spinner_TipoDePotencia);
		EditText airbags		= (EditText) findViewById(R.id.Veiculos_InserirTexto_AirBags);

		
		String opc[] = {"Nome do carro", "Marca do veículo", "Tipo de veículo", "Placa", "Ano", "Vol. do Tanque"}, 
				
				res[] = {
				nomeDoVeiculo.getText().toString(),
				marca.getSelectedItem().toString(),
				tipoDeVeiculo.getSelectedItem().toString(),
				placa.getText().toString(),
				ano.getSelectedItem().toString(),
				volDoTanque.getText().toString(),
				};
		
		View focus[] = {nomeDoVeiculo, marca, tipoDeVeiculo, placa, ano, volDoTanque};
		boolean troca = true;

		for (int i = 0; i < opc.length; i++) {

			if (res[i].equalsIgnoreCase("") || res[i] == null || (res[1].equalsIgnoreCase("Selecione") && i == 1) || (res[2].equalsIgnoreCase("Selecione") && i == 2) || (res[4].equalsIgnoreCase("Selecione") && i == 4) ) {

				focus[i].requestFocusFromTouch();
				focus[i].requestFocus();
				focus[i].performClick();
				Toast.makeText(getApplicationContext(),
						"É obrigatório o preenchimento do campo " + opc[i],
						Toast.LENGTH_SHORT).show();
				troca = false;
				break;
			}

		}

		if (troca) {
			
			String aux;
			
			if(modelo.getSelectedItem().toString().equalsIgnoreCase("Selecione")){
				aux = "";
			}else{
				aux = modelo.getSelectedItem().toString();
			}
			
			DAOVeiculo.getInstancia(getApplicationContext()).inserir(new Veiculo(
					Integer.valueOf(ano.getSelectedItem().toString()), 
					Integer.valueOf(nDePortas.getText().toString()), 
					Integer.valueOf(airbags.getText().toString()), 
					Double.parseDouble(volDoTanque.getText().toString()), 
					Double.parseDouble("0"),
					Double.parseDouble(potencia.getText().toString()), 
					nomeDoVeiculo.getText().toString(), 
					marca.getSelectedItem().toString(),
					tipoDeVeiculo.getSelectedItem().toString(), 
					placa.getText().toString(),
					RENAVAM.getText().toString(), 
					chassi.getText().toString(), 
					aux, 
					tipoPotencia.getSelectedItem().toString()));
			
			
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			builder.setTitle("Informativo!"); 
			builder.setMessage("O cadastro do veículo " + nomeDoVeiculo.getText().toString() + " (" + ano.getSelectedItem().toString() + "), da " + marca.getSelectedItem().toString() + " foi cadastrado com sucesso!"); 
			builder.setIcon(R.drawable.icone_informativo);
			builder.setNeutralButton("OK", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					startActivity(new Intent(Veiculos_Inicial_Activity.this, AtividadePrincipal.class));
				}
			}); 
			builder.create();
			builder.show();
		}

	}
	
}
