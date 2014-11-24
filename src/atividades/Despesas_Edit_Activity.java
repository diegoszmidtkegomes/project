package atividades;

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

import fragmentos.Despesas_Fragment;
import funcoes.FData;

public class Despesas_Edit_Activity extends Activity implements Button.OnClickListener{

	private Button botao; 
	static final int DATE_DIALOG_ID = 0;
	private String data;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.despesas_activity);

		getActionBar().setTitle("Atualizar despesa");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		getActionBar().setDisplayHomeAsUpEnabled(true);
		String despesas[] = { "Selecione", "Estacionamento", "Financiamento",
				"Impostos (IPVA/DPVAT)", "Lava-rápido", "Licenciamento",
				"Multa", "Pedágio", "Reembolso", "Seguro", "Outros" };
		
		botao = (Button) findViewById(R.id.Despesas_Botao_Data);
		data = DAODespesas.getInstancia(getApplicationContext()).buscarPorId(Despesas_Fragment.idDespesa).getData();
		botao.setText(data);
		botao.setOnClickListener(this);
		
		Spinner tipoDeDespesa = (Spinner) findViewById(R.id.Despesas_Spinner_TipoDeDespesa);
		tipoDeDespesa.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, despesas));
		
		for(int x = 0; x < despesas.length; x++){
			if(tipoDeDespesa.getItemAtPosition(x).equals(DAODespesas.getInstancia(this).buscarPorId(Despesas_Fragment.idDespesa).getTipo())){
				tipoDeDespesa.setSelection(x);
				break;
			}
		}
		EditText odometro = (EditText) findViewById(R.id.Despesas_InserirTexto_Odometro);
		odometro.setText(String.valueOf(DAODespesas.getInstancia(this).buscarPorId(Despesas_Fragment.idDespesa).getOdometro()));
		
		
		EditText valorTotal = (EditText) findViewById(R.id.Despesas_InserirTexto_ValorTotal);
		valorTotal.setText(String.valueOf(DAODespesas.getInstancia(this).buscarPorId(Despesas_Fragment.idDespesa).getValorTotal()));
		
		
		EditText localDaDespesa = (EditText) findViewById(R.id.Despesas_InserirTexto_LocalDaDespesa);
		localDaDespesa.setText(String.valueOf(DAODespesas.getInstancia(this).buscarPorId(Despesas_Fragment.idDespesa).getLocal()));
		
		EditText observacao = (EditText) findViewById(R.id.Despesas_InserirTexto_Observacao);
		observacao.setText(String.valueOf(DAODespesas.getInstancia(this).buscarPorId(Despesas_Fragment.idDespesa).getObservacao()));
		
	}
	
	
	
	// Data Picker
	
	@Override
	protected Dialog onCreateDialog(int id) {
		int ano = Integer.valueOf(FData.getAno(data));
		int mes = Integer.valueOf(FData.getMes(data)) - 1;
		int dia = Integer.valueOf(FData.getDia(data));
		
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
			
			String data = String.valueOf(dayOfMonth) + "/"
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

			startActivity(new Intent(Despesas_Edit_Activity.this, Despesas_View_Activity.class));

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
			
			DAODespesas.getInstancia(getApplicationContext()).editar(new Despesas(FData.getDataInMillis(data), Double.parseDouble(odometro.getText().toString()), Double.parseDouble(valorTotal.getText().toString()), tipoDeDespesa.getSelectedItem().toString(), localDaDespesa.getText().toString(), data.toString(), observacao.getText().toString()), Despesas_Fragment.idDespesa);
			AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			builder.setTitle("Informativo!"); 
			builder.setMessage("A despesa " + tipoDeDespesa.getSelectedItem().toString() + ", no valor de R$ " + valorTotal.getText().toString() + " foi atualizada com sucesso!"); 
			builder.setIcon(R.drawable.icone_informativo);
			builder.setNeutralButton("OK", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					startActivity(new Intent(Despesas_Edit_Activity.this, AtividadePrincipal.class));
				}
			}); 
			builder.create();
			builder.show();
			
		}

	}

}
