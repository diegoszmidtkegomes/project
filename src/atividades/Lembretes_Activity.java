package atividades;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import banco_de_dados.DAOLembretes;
import banco_de_dados.DAOVeiculo;
import banco_de_dados.Lembretes;

import com.example.fullservicecar.R;

import funcoes.FData;

public class Lembretes_Activity extends Activity implements Button.OnClickListener{

	private Button botao;
	static final int DATE_DIALOG_ID = 0;
	private String data = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/"
			+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/"
			+ Calendar.getInstance().get(Calendar.YEAR);
	public static Context contexto;
	private String tipo = "Despesas";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lembretes_activity);

		getActionBar().setTitle("Cadastrar lembretes");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		getActionBar().setDisplayHomeAsUpEnabled(true);

		contexto = this;
		
		final Spinner spinnerLembrete = (Spinner) findViewById(R.id.Lembretes_ListView_Opc);
		
		
		final String despesas[] = { "Selecione", "Estacionamento", "Financiamento",
				"Impostos (IPVA/DPVAT)", "Lava-rápido", "Licenciamento",
				"Multa", "Pedágio", "Reembolso", "Seguro", "Outros" };

		final String servicos[] = {
				"Selecione",
			    "Ar Condicionado",
			    "Bateria", "Buzinas",
			    "Carroceria/Chassis", "Cinto",
			    "Diretação Hidráulica",
			    "Filtro de Ar", "Filtro de Ar da Cabine", "Filtro de Óleo", 
			    "Fluído da Embreagem", "Fluído da Embreagem", "Fluído da Transmissão", 
			    "Fluído de Freio",
			    "Inspeção Técnica", 
			    "Limpadores de Para-brisa", "Luzes",
			    "Mão de Obra",
			    "Pastilha de Freio", "Pneus", "Pneus - Alinhamento/Balanceamento",
			    "Pneus - Calibragem", "Pneus - Rodízio",
			    "Radiador", "Reparo no Motor", "Revisão",
			    "Sistema de Aquecimento", "Sistema de Embreagem", "Sistema de Exaustão",
			    "Sistema de Refrigeramento", "Suspensão/Amortecedores",
			    "Troca de Freio", "Troca de Óleo",
			    "Velas de Ignição", "Vidros/Espelhos",
			    "Outros"};
		
		
		RadioButton rg1 = (RadioButton) findViewById(R.id.Lembretes_RadioB_Despesas);
		RadioButton rg2 = (RadioButton) findViewById(R.id.Lembretes_RadioB_Servicos);
		
		spinnerLembrete.setAdapter(new ArrayAdapter<String>(contexto, android.R.layout.simple_spinner_item, despesas));
		
			rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					tipo = ((RadioButton) findViewById(R.id.Lembretes_RadioB_Despesas)).getText().toString();
					spinnerLembrete.setAdapter(new ArrayAdapter<String>(
							contexto, android.R.layout.simple_spinner_item,
							despesas));

				}
			}
		});

		rg2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					tipo = ((RadioButton) findViewById(R.id.Lembretes_RadioB_Servicos)).getText().toString();
					spinnerLembrete.setAdapter(new ArrayAdapter<String>(
							contexto, android.R.layout.simple_spinner_item,
							servicos));
		
				}
			}
		});
	
	
		
		botao = (Button) findViewById(R.id.Lembretes_InserirTexto_Data);
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
		if (v == botao){
			showDialog(DATE_DIALOG_ID);
		}
			
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

			startActivity(new Intent(Lembretes_Activity.this, AtividadePrincipal.class));

			return true;

		default:

			return super.onOptionsItemSelected(item);
		}
	}
	
	private void salvar() {

		EditText odometro = (EditText) findViewById(R.id.Lembretes_InserirTexto_Odometro);
		Spinner  lista    = (Spinner) findViewById(R.id.Lembretes_ListView_Opc);
		EditText obs      = (EditText) findViewById(R.id.Lembretes_InserirTexto_Observacao);
		
		String opc[] = {"Motivo", "Odômetro", "Observação"}, 
				
				res[] = {
				lista.getSelectedItem().toString(),
				odometro.getText().toString(),
				obs.getText().toString()
				};
		
		View focus[] = {lista, odometro, obs};
		
		boolean troca = true;

		for (int i = 0; i < opc.length; i++) {

			if (res[i].equalsIgnoreCase("") || res[0].equals("Selecione") && i == 0) {

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
			
			DAOLembretes.getInstancia(this).inserir(new Lembretes(FData.getDataInMillis(data), tipo, lista.getSelectedItem().toString(), data, obs.getText().toString(), Double.parseDouble(odometro.getText().toString())));
			AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			builder.setTitle("Informativo!"); 
			builder.setMessage("O lembrete " + res[0] +  " foi cadastrado com sucesso!"); 
			builder.setIcon(R.drawable.icone_informativo);
			builder.setNeutralButton("OK", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					startActivity(new Intent(Lembretes_Activity.this, AtividadePrincipal.class));
				}
			}); 
			builder.create();
			builder.show();
			
		}

	}

}
