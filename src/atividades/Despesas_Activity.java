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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import banco_de_dados.DAODespesas;
import banco_de_dados.DAOVeiculo;
import banco_de_dados.Despesas;

import com.example.fullservicecar.R;

import funcoes.FData;

public class Despesas_Activity extends Activity implements Button.OnClickListener{

	private Button botao; 
	static final int DATE_DIALOG_ID = 0;
	private String data = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/"
			+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/"
			+ Calendar.getInstance().get(Calendar.YEAR);
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.despesas_activity);

		getActionBar().setTitle("Cadastrar despesas");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		Spinner tipoDeDespesa = (Spinner) findViewById(R.id.Despesas_Spinner_TipoDeDespesa);

		String despesas[] = { "Selecione", "Estacionamento", "Financiamento",
				"Impostos (IPVA/DPVAT)", "Lava-rápido", "Licenciamento",
				"Multa", "Pedágio", "Reembolso", "Seguro", "Outros" };

		tipoDeDespesa.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, despesas));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		botao = (Button) findViewById(R.id.Despesas_Botao_Data);
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

			startActivity(new Intent(Despesas_Activity.this, AtividadePrincipal.class));

			return true;

		default:

			return super.onOptionsItemSelected(item);
		}
	}
	
	private void salvar() {

		final Spinner tipoDeDespesa = (Spinner) findViewById(R.id.Despesas_Spinner_TipoDeDespesa);
		final EditText odometro = (EditText) findViewById(R.id.Despesas_InserirTexto_Odometro);
		final EditText valorTotal = (EditText) findViewById(R.id.Despesas_InserirTexto_ValorTotal);
		final EditText localDaDespesa = (EditText) findViewById(R.id.Despesas_InserirTexto_LocalDaDespesa);
		final EditText observacao = (EditText) findViewById(R.id.Despesas_InserirTexto_Observacao);

		String opc[] = { "Tipo de despesa", "Odômetro", "Valor total",
				"Local da despesa"}, res[] = {
				tipoDeDespesa.getSelectedItem().toString(),
				odometro.getText().toString(), valorTotal.getText().toString(),
				localDaDespesa.getText().toString()};
		
		View focus[] = { tipoDeDespesa, odometro, valorTotal, localDaDespesa,};
		boolean troca = true;

		for (int i = 0; i < opc.length; i++) {

			if (res[i].equalsIgnoreCase("") || res[i] == null|| (res[0].equalsIgnoreCase("Selecione") && i == 0)) {

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
			
			DAODespesas.getInstancia(getApplicationContext()).inserir(new Despesas(FData.getDataInMillis(data), Double.parseDouble(odometro.getText().toString()), Double.parseDouble(valorTotal.getText().toString()), tipoDeDespesa.getSelectedItem().toString(), localDaDespesa.getText().toString(), data.toString(), observacao.getText().toString()));

			AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			builder.setTitle("Informativo!"); 
			builder.setMessage("A despesa " + tipoDeDespesa.getSelectedItem().toString() + ", no valor de R$ " + valorTotal.getText().toString() + " foi cadastrada com sucesso!"); 
			builder.setIcon(R.drawable.icone_informativo);
			builder.setNeutralButton("OK", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					startActivity(new Intent(Despesas_Activity.this, AtividadePrincipal.class));
				}
			}); 
			builder.create();
			builder.show();
			
		}

	}

}
