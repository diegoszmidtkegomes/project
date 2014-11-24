package atividades;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import banco_de_dados.DAOMotorista;
import banco_de_dados.Motorista;

import com.example.fullservicecar.R;

import funcoes.FImagem;
import funcoes.Mask;

public class Perfil_Activity extends Activity implements Button.OnClickListener{

	private Button botao; 
	private ImageButton img;
	static final int DATE_DIALOG_ID = 0;
	private String dataNascimento = DAOMotorista.getInstancia(this).buscarTodos().get(0).getDataNascimento().toString();
	static final int REQUEST_IMAGE_CAPTURE = 1;
	private byte imagem[];
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perfil_activity);
		getActionBar().setTitle("Editar perfil");
		getActionBar().setSubtitle(DAOMotorista.getInstancia(this).buscarTodos().get(0).getNome());
		getActionBar().setDisplayHomeAsUpEnabled(true);
		imagem = FImagem.getBytes(FImagem.getImage(DAOMotorista.getInstancia(this).buscarTodos().get(0).getFoto()));
		EditText nome  = (EditText) findViewById(R.id.Perfil_InserirTexto_NomeDoUsuario);
		nome.setText(DAOMotorista.getInstancia(this).buscarTodos().get(0).getNome().toString());
		EditText email = (EditText) findViewById(R.id.Perfil_InserirTexto_EmailDoUsuario);
		email.setText(DAOMotorista.getInstancia(this).buscarTodos().get(0).getEmail().toString());
		
		EditText cnh   = (EditText) findViewById(R.id.Perfil_InserirTexto_CNHDoUsuario);
		cnh.addTextChangedListener(Mask.insert("###########", cnh));
		cnh.setText(DAOMotorista.getInstancia(this).buscarTodos().get(0).getCnh().toString());
		
		img = (ImageButton) findViewById(R.id.bt_CapturarFoto);
		img.setOnClickListener(this);
		
		botao = (Button) findViewById(R.id.Perfil_Botao_DataNascimentoDoUsuario);
		
		botao.setText(dataNascimento.replace("-", "/"));
		img.setBackground(new BitmapDrawable(getResources(), FImagem.getImage(DAOMotorista.getInstancia(this).buscarTodos().get(0).getFoto())));
		
		//botao.setText(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/" + Calendar.getInstance().get(Calendar.YEAR));
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
				
				dataNascimento  = String.valueOf(dayOfMonth) + "/"
						+ String.valueOf(monthOfYear + 1) + "/"
						+ String.valueOf(year);
				botao.setText(dataNascimento.replace("-", "/"));
			}
		};

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			if (v == botao){
				showDialog(DATE_DIALOG_ID);
			}
			if(v == img){
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			    }
			}
				
		}
		
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
		        Bundle extras = data.getExtras();
		        Bitmap imageBitmap = (Bitmap) extras.get("data");
		        imagem = FImagem.getBytes(imageBitmap);
		        //imgConv = FImagem.getBytes(imagem);
		        img.setImageBitmap(imageBitmap);
		    }
		}
		
		// Atpe aqui
		
		@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
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

				startActivity(new Intent(Perfil_Activity.this, AtividadePrincipal.class));

				return true;

			default:

				return super.onOptionsItemSelected(item);
			}
		}
		
		private void salvar() {

			final EditText nome  = (EditText) findViewById(R.id.Perfil_InserirTexto_NomeDoUsuario);
			final EditText email = (EditText) findViewById(R.id.Perfil_InserirTexto_EmailDoUsuario);
			final EditText cnh   = (EditText) findViewById(R.id.Perfil_InserirTexto_CNHDoUsuario);

			String opc[] = {"Nome do usuário ", "E-mail do usuário", "CNH"}, res[] = {
					nome.getText().toString(), email.getText().toString(),
					cnh.getText().toString()};
			
			View focus[] = { nome, email, cnh};
			boolean troca = true;

			for (int i = 0; i < opc.length; i++) {

				if (res[i].equalsIgnoreCase("") || res[i] == null) {

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
				
				DAOMotorista.getInstancia(getApplicationContext()).editar(new Motorista(nome.getText().toString(), email.getText().toString(), cnh.getText().toString(), dataNascimento.toString(), imagem), DAOMotorista.getInstancia(getApplicationContext()).buscarTodos().get(0).getId());
				AlertDialog.Builder builder = new AlertDialog.Builder(this); 
				builder.setTitle("Informativo!"); 
				builder.setMessage("O usuário " + nome.getText().toString() + ", portador do CNH " + cnh.getText().toString() + " foi atualizado com sucesso!"); 
				builder.setIcon(R.drawable.icone_informativo);
				builder.setNeutralButton("OK", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						startActivity(new Intent(Perfil_Activity.this, AtividadePrincipal.class));
						overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
					}
				}); 
				builder.create();
				builder.show();
				
			}

		}
	
}
