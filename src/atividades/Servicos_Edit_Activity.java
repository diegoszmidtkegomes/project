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
import banco_de_dados.DAOServicos;
import banco_de_dados.DAOVeiculo;
import banco_de_dados.Servicos;

import com.example.fullservicecar.R;

import fragmentos.Servicos_Fragment;
import funcoes.FData;

public class Servicos_Edit_Activity extends Activity implements Button.OnClickListener{

	private Button botao; 
	static final int DATE_DIALOG_ID = 0;
	private String data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicos_activity);
		
		getActionBar().setTitle("Atualizar serviço");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		botao = (Button) findViewById(R.id.Servicos_Botao_Data);
		data = DAOServicos.getInstancia(getApplicationContext()).buscarPorId(Servicos_Fragment.idServico).getData();
		botao.setText(data);
		botao.setOnClickListener(this);
		
		Spinner tipoDeServico = (Spinner) findViewById(R.id.Servicos_Spinner_TipoDeServico);
		
		String servicos[] = {
				"Selecione",
			    "Ar Condicionado",
			    "Bateria", "Buzinas",
			    "Carroceria/Chassis", "Cinto",
			    "Diretação Hidráulica",
			    "Filtro de Ar", "Filtro de Ar da Cabine", "Filtro de Óleo", 
			    "Fluído da Embreagem", "Fluído da Transmissão", 
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

		tipoDeServico.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, servicos));
		for(int x = 0; x < servicos.length; x++){
			if(tipoDeServico.getItemAtPosition(x).equals(DAOServicos.getInstancia(this).buscarPorId(Servicos_Fragment.idServico).getTipo())){
				tipoDeServico.setSelection(x);
				break;
			}
		}
		
		EditText odometro = (EditText) findViewById(R.id.Servicos_InserirTexto_Odometro);
		odometro.setText(String.valueOf(DAOServicos.getInstancia(this).buscarPorId(Servicos_Fragment.idServico).getOdometro()));
		
		
		EditText valorTotal = (EditText) findViewById(R.id.Servicos_InserirTexto_ValorTotal);
		valorTotal.setText(String.valueOf(DAOServicos.getInstancia(this).buscarPorId(Servicos_Fragment.idServico).getValorTotal()));
		
		
		EditText localDaDespesa = (EditText) findViewById(R.id.Servicos_InserirTexto_LocalDaDespesa);
		localDaDespesa.setText(String.valueOf(DAOServicos.getInstancia(this).buscarPorId(Servicos_Fragment.idServico).getLocal()));
		
		EditText observacao = (EditText) findViewById(R.id.Servicos_InserirTexto_Observacao);
		observacao.setText(String.valueOf(DAOServicos.getInstancia(this).buscarPorId(Servicos_Fragment.idServico).getObservacao()));
		
	
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

				startActivity(new Intent(Servicos_Edit_Activity.this, Servicos_View_Activity.class));
				return true;

			default:

				return super.onOptionsItemSelected(item);
			}
		}
		
		private void salvar() {

			final Spinner tipoDeServico = (Spinner) findViewById(R.id.Servicos_Spinner_TipoDeServico);
			final EditText odometro = (EditText) findViewById(R.id.Servicos_InserirTexto_Odometro);
			final EditText valorTotal = (EditText) findViewById(R.id.Servicos_InserirTexto_ValorTotal);
			final EditText localDoServico = (EditText) findViewById(R.id.Servicos_InserirTexto_LocalDaDespesa);
			final EditText observacao = (EditText) findViewById(R.id.Servicos_InserirTexto_Observacao);
			
			String opc[] = { "Tipo de despesa", "Odômetro", "Valor total",
					"Local da despesa"}, res[] = {
					tipoDeServico.getSelectedItem().toString(),
					odometro.getText().toString(), valorTotal.getText().toString(),
					localDoServico.getText().toString()};
			
			View focus[] = { tipoDeServico, odometro, valorTotal, localDoServico};
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
				
				DAOServicos.getInstancia(getApplicationContext()).editar(new Servicos(FData.getDataInMillis(data), Double.parseDouble(odometro.getText().toString()), Double.parseDouble(valorTotal.getText().toString()), tipoDeServico.getSelectedItem().toString(), localDoServico.getText().toString(), data.toString(), observacao.getText().toString()), Servicos_Fragment.idServico);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this); 
				builder.setTitle("Informativo!"); 
				builder.setMessage("O serviço " + tipoDeServico.getSelectedItem().toString() + ", no valor de R$ " + valorTotal.getText().toString() + " foi cadastrado com sucesso!"); 
				builder.setIcon(R.drawable.icone_informativo);
				builder.setNeutralButton("OK", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						startActivity(new Intent(Servicos_Edit_Activity.this, AtividadePrincipal.class));
					}
				}); 
				builder.create();
				builder.show();
				
			}

		}

	
}
