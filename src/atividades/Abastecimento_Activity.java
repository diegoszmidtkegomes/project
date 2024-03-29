package atividades;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import banco_de_dados.Abastecimento;
import banco_de_dados.DAOAbastecimento;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;

import funcoes.FData;
import funcoes.FVeiculo;

public class Abastecimento_Activity extends Activity implements Button.OnClickListener{
	
	private Button botao; 
	static final int DATE_DIALOG_ID = 0;
	private String data = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/"
			+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/"
			+ Calendar.getInstance().get(Calendar.YEAR);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abastecimento_activity);
		
		getActionBar().setTitle("Cadastrar abastecimento");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		String motivos[] = {"Selecione", "Trabalho", "Viagem", "Outros"},
			   tipoComb[] = {"Selecione", "Diesel", "Etanol", "Gasolina Aditivada", "Gasolina Comum", "Outros"};
		
		((Spinner) findViewById(R.id.Abastecimento_Spinner_Motivo)).setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, motivos));;
		((Spinner) findViewById(R.id.Abastecimento_Spinner_TipoDeCombustivel)).setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tipoComb));;
		
		botao = (Button) findViewById(R.id.Abastecimento_Botao_Data);
		botao.setText(data);
		botao.setOnClickListener(this);
		
	}
	

	// Data Picker
	
		@Override
		protected Dialog onCreateDialog(int id) {
			Calendar calendario = Calendar.getInstance();
			int ano = calendario.get(Calendar.YEAR);
			int mes = calendario.get(Calendar.MONTH);
			int dia = calendario.get(Calendar.DAY_OF_MONTH);
			data = ano + "/"
					+ mes + 1 + "/"
					+ dia;
			switch (id) {
			case DATE_DIALOG_ID:
				return new DatePickerDialog(this, mDateSetListener, ano, mes, dia);
			}
			return null;
		}

		private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				
				data = String.valueOf(dayOfMonth) + "/"
						+ String.valueOf(monthOfYear + 1) + "/"
						+ String.valueOf(year);
				botao.setText(data);
			}
		};

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			if (v == botao)
				showDialog(DATE_DIALOG_ID);
		}
		
		// Atpe aqui
		
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

				startActivity(new Intent(Abastecimento_Activity.this, AtividadePrincipal.class));

				return true;

			default:

				return super.onOptionsItemSelected(item);
			}
		}
		
		private void salvar() {

			final Spinner  motivo = (Spinner) findViewById(R.id.Abastecimento_Spinner_Motivo);
			final EditText odometro = (EditText) findViewById(R.id.Abastecimento_InserirTexto_Odometro);
			final EditText posto = (EditText) findViewById(R.id.Abastecimento_InserirTexto_Posto);
			final Spinner  tipoCombustivel = (Spinner) findViewById(R.id.Abastecimento_Spinner_TipoDeCombustivel);
			final EditText valorTotal = (EditText) findViewById(R.id.Abastecimento_InserirTexto_ValorTotal);
			final EditText litros = (EditText) findViewById(R.id.Abastecimento_InserirTexto_Litros);
			final EditText observacao = (EditText) findViewById(R.id.Abastecimento_InserirTexto_Observacao);
			//observacao.setText("");
			
			String opc[] = {"Motivo", "Od�metro", "Posto", "Tipo de Combust�vel", "Valor total", "Litros"},
		
			
			
			res[] = {
					motivo.getSelectedItem().toString(),
					odometro.getText().toString(),
					posto.getText().toString(),
					tipoCombustivel.getSelectedItem().toString(),
					valorTotal.getText().toString(),
					litros.getText().toString()};
			
			View focus[] = {motivo, odometro, posto, tipoCombustivel, valorTotal, litros};
			boolean troca = true;

			for (int i = 0; i < opc.length; i++) {

				if (res[i].equalsIgnoreCase("") || res[i] == null|| (res[0].equalsIgnoreCase("Selecione") && i == 0) || (res[3].equalsIgnoreCase("Selecione") && i == 3)) {

					focus[i].requestFocusFromTouch();
					focus[i].requestFocus();
					focus[i].performClick();
					Toast.makeText(getApplicationContext(),
							"� obrigat�rio o preenchimento do campo " + opc[i],
							Toast.LENGTH_SHORT).show();
					troca = false;
					break;
				}

			}

			if (troca) {
				
				DAOAbastecimento.getInstancia(getApplicationContext()).inserir(new Abastecimento(FData.getDataInMillis(data), Double.parseDouble(odometro.getText().toString()), Double.parseDouble(valorTotal.getText().toString()), Double.parseDouble(litros.getText().toString()), tipoCombustivel.getSelectedItem().toString(), motivo.getSelectedItem().toString(), posto.getText().toString(), observacao.getText().toString(), data.toString()));
				//DAOAbastecimento.getInstancia(getApplicationContext()).inserir(new Abastecimento(FData.getDataInMillis(data), Double.parseDouble(odometro.getText().toString().replace(",", ".")), Double.parseDouble(valorTotal.getText().toString().replace(",", ".")), Double.parseDouble(litros.getText().toString().replace(",", ".")), tipoCombustivel.getSelectedItem().toString(), motivo.getSelectedItem().toString(), posto.getText().toString(), observacao.getText().toString(), data.toString()));
				FVeiculo.getOdometroAtual(this);
				Log.i("ll", String.valueOf(DAOAbastecimento.getInstancia(getApplicationContext()).buscarTodos().get(0).getValorTotal()));
				AlertDialog.Builder builder = new AlertDialog.Builder(this); 
				builder.setTitle("Informativo!"); 
				builder.setMessage("O abastecimento de " + litros.getText().toString() + " litro(s) de " + tipoCombustivel.getSelectedItem().toString() + ", no valor de R$ " + valorTotal.getText().toString() + " foi cadastrado com sucesso!"); 
				builder.setIcon(R.drawable.icone_informativo);
				builder.setNeutralButton("OK", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						startActivity(new Intent(Abastecimento_Activity.this, AtividadePrincipal.class));
					}
				}); 
				builder.create();
				builder.show();
				
			}

		}


}
