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
import banco_de_dados.DAOVeiculo;
import banco_de_dados.Veiculo;

import com.example.fullservicecar.R;

import funcoes.Mask;


public class Veiculos_Activity extends Activity{
	
	private String modeloDoVeiculo[] = {
			
			"Selecione",
			"Hatch",
			"Sedan",
			"Outros"
			
	};
	
	private String potencias[] = {
			
			"CC",
			"CV",
			"HP",
			"Kgf",
			"Outros"
			
	};
	
	private String carros[] = {
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
	
	private String veiculo[] = {
			"Selecione",
			"Camioneta", "Camionete", "Caminhão", "Carro", 
			"Jipe",
			"Motocicleta",
			"Pickup",
			"Veículo Oficial",
			"Outros"
	};
	
	 private EditText nomeDoVeiculo  ;
	 private EditText placa          ;
	 private EditText volDoTanque    ;
	 private Spinner  marca          ;
	 private Spinner  tipoDeVeiculo  ;
	 private Spinner  ano			;
	 private EditText RENAVAM 	    ;
	 private EditText chassi 		;
	 private Spinner  modelo 		;
	 private EditText nDePortas		;
	 private EditText potencia		;
	 private  Spinner  tipoPotencia;
	 private  EditText airbags;
    
 
	
	private String anos[] = new String[Calendar.getInstance().get(Calendar.YEAR) + 3 - 1900];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.veiculos_activity);
		getActionBar().setTitle("Atualizar veículo");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		 nomeDoVeiculo  = (EditText) findViewById(R.id.Veiculos_InserirTexto_NomeDoVeiculo);
		 placa          = (EditText) findViewById(R.id.Veiculos_InserirTexto_Placa);
		 volDoTanque    = (EditText) findViewById(R.id.Veiculos_InserirTexto_VolumeDoTanque);
		 marca          = (Spinner)  findViewById(R.id.Veiculos_Spinner_MarcaDoVeiculo);
		 tipoDeVeiculo  = (Spinner)  findViewById(R.id.Veiculos_Spinner_TipoDeVeiculo);
		 ano			= (Spinner)  findViewById(R.id.Veiculos_Spinner_Ano);
		 RENAVAM 	    = (EditText) findViewById(R.id.Veiculos_InserirTexto_RENAVAM);
		 chassi 		= (EditText) findViewById(R.id.Veiculos_InserirTexto_Chassi);
		 modelo 		= (Spinner)  findViewById(R.id.Veiculos_Spinner_Modelo);
		 nDePortas		= (EditText) findViewById(R.id.Veiculos_InserirTexto_NumeroDePortas);
		 potencia		= (EditText) findViewById(R.id.Veiculos_InserirTexto_Potencia);
		 tipoPotencia	= (Spinner)  findViewById(R.id.Veiculos_Spinner_TipoDePotencia);
		 airbags		= (EditText) findViewById(R.id.Veiculos_InserirTexto_AirBags);
		
		
		chassi.addTextChangedListener(Mask.insert("#################", chassi));
		
		int z = 1;
		anos[0] = "Selecione";
		
		for(int i = Calendar.getInstance().get(Calendar.YEAR) + 1; i >= 1900; i--){
			anos[z] = String.valueOf(i);
			z++;
		}
		
		
		((Spinner) findViewById(R.id.Veiculos_Spinner_MarcaDoVeiculo)) .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carros));
		((Spinner) findViewById(R.id.Veiculos_Spinner_TipoDeVeiculo))  .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, veiculo));
		((Spinner) findViewById(R.id.Veiculos_Spinner_Ano))            .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, anos));
		((Spinner) findViewById(R.id.Veiculos_Spinner_Modelo))		   .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modeloDoVeiculo));
		((Spinner) findViewById(R.id.Veiculos_Spinner_TipoDePotencia)) .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, potencias));
	
		nomeDoVeiculo.setText(String.valueOf(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getNome().toString()));
		
		placa.setText(String.valueOf(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getPlaca()));
		
		volDoTanque.setText(String.valueOf(String.valueOf(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getVolDoTanque())));
		
		
		for(int x = 0; x < carros.length; x++){
			if(marca.getItemAtPosition(x).equals(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getMarca())){
				marca.setSelection(x);
				break;
			}
		}
		
		for(int x = 0; x < veiculo.length; x++){
			if(tipoDeVeiculo.getItemAtPosition(x).equals(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getTipo())){
				tipoDeVeiculo.setSelection(x);
				break;
			}
		}
		
		for(int x = 0; x < anos.length; x++){
			if(ano.getItemAtPosition(x).equals(String.valueOf(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getAno()))){
				ano.setSelection(x);
				break;
			}
		}
		
		
	    RENAVAM.setText(String.valueOf(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getRENAVAM()));
	   
	    
		chassi.setText(String.valueOf(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getChassi()));
		
		for(int x = 0; x < modeloDoVeiculo.length; x++){
			if(modelo.getItemAtPosition(x).equals(String.valueOf(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getModelo()))){
				modelo.setSelection(x);
				break;
			}
		}
		
		nDePortas.setText(String.valueOf(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getnDePortas()));
		
		potencia.setText(String.valueOf(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getPotencia()));
		
		
		for(int x = 0; x < modeloDoVeiculo.length; x++){
			if(tipoPotencia.getItemAtPosition(x).equals(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getTipoPotencia())){
				tipoPotencia.setSelection(x);
				break;
			}
		}
		
		
		airbags.setText(String.valueOf(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getAirbags()));
		

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

			startActivity(new Intent(this, AtividadePrincipal.class));

			return true;

		default:

			return super.onOptionsItemSelected(item);
		}
	}
	
	private void salvar() {
		
		
		EditText nomeDoVeiculo = (EditText) findViewById(R.id.Veiculos_InserirTexto_NomeDoVeiculo);
		EditText placa = (EditText) findViewById(R.id.Veiculos_InserirTexto_Placa);
		EditText volDoTanque = (EditText) findViewById(R.id.Veiculos_InserirTexto_VolumeDoTanque);
		Spinner marca = (Spinner) findViewById(R.id.Veiculos_Spinner_MarcaDoVeiculo);
		Spinner tipoDeVeiculo = (Spinner) findViewById(R.id.Veiculos_Spinner_TipoDeVeiculo);
		Spinner ano = (Spinner) findViewById(R.id.Veiculos_Spinner_Ano);
		EditText RENAVAM = (EditText) findViewById(R.id.Veiculos_InserirTexto_RENAVAM);
		EditText chassi = (EditText) findViewById(R.id.Veiculos_InserirTexto_Chassi);
		Spinner modelo = (Spinner) findViewById(R.id.Veiculos_Spinner_Modelo);
		EditText nDePortas = (EditText) findViewById(R.id.Veiculos_InserirTexto_NumeroDePortas);
		EditText potencia = (EditText) findViewById(R.id.Veiculos_InserirTexto_Potencia);
		Spinner tipoPotencia = (Spinner) findViewById(R.id.Veiculos_Spinner_TipoDePotencia);
		EditText airbags = (EditText) findViewById(R.id.Veiculos_InserirTexto_AirBags);
		
		
		String opc[] = {"Nome do carro", "Marca do veículo", "Tipo de veículo", "Placa", "Ano", "Vol. do Tanque"}, 
				
				res[] = {
				nomeDoVeiculo.getText().toString(),
				marca.getSelectedItem().toString(),
				tipoDeVeiculo.getSelectedItem().toString(),
				placa.getText().toString(),
				ano.getSelectedItem().toString(),
				volDoTanque.getText().toString()
				
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
			
			DAOVeiculo.getInstancia(getApplicationContext()).editar(new Veiculo(
					Integer.valueOf(ano.getSelectedItem().toString()), 
					Integer.valueOf(nDePortas.getText().toString()), 
					Integer.valueOf(airbags.getText().toString()), 
					Double.parseDouble(volDoTanque.getText().toString()), 
					DAOVeiculo.getInstancia(this).buscarTodos().get(0).getOdometroAtual(),
					Double.parseDouble(potencia.getText().toString()), 
					nomeDoVeiculo.getText().toString(), 
					marca.getSelectedItem().toString(),
					tipoDeVeiculo.getSelectedItem().toString(), 
					placa.getText().toString(),
					RENAVAM.getText().toString(), 
					chassi.getText().toString(), 
					aux, 
					tipoPotencia.getSelectedItem().toString()), DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getId());
			
			
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			builder.setTitle("Informativo!"); 
			builder.setMessage("O cadastro do veículo " + nomeDoVeiculo.getText().toString() + " (" + ano.getSelectedItem().toString() + "), da " + marca.getSelectedItem().toString() + " foi cadastrado com sucesso!"); 
			builder.setIcon(R.drawable.icone_informativo);
			builder.setNeutralButton("OK", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					startActivity(new Intent(Veiculos_Activity.this, AtividadePrincipal.class));
				}
			}); 
			builder.create();
			builder.show();
		}

	}
	
}
