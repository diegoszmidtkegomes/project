package atividades;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import banco_de_dados.DAOVeiculo;
import banco_de_dados.Motorista;

import com.example.fullservicecar.R;

import funcoes.FImagem;
import funcoes.Mask;
import funcoes.Mensagem;

public class Perfil_Inicial_Activity extends Activity implements Button.OnClickListener{

	private Button botao; 
	static final int DATE_DIALOG_ID = 0;
	private int ano, mes, dia;
	private ImageButton img;
	private String dataNascimento = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "-"
			+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-"
			+ Calendar.getInstance().get(Calendar.YEAR);
	
	private byte imagem[];
	static final int REQUEST_IMAGE_CAPTURE = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perfil_activity);
		getActionBar().setTitle("Adicionar perfil");
		getActionBar().setSubtitle("Full Service Car");
		Intent intent;
		if(DAOMotorista.getInstancia(this).getTamanho() > 0){
			if(DAOVeiculo.getInstancia(this).getTamanho() == 0){
				intent = new Intent(Perfil_Inicial_Activity.this, Veiculos_Inicial_Activity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}else{
				intent = new Intent(Perfil_Inicial_Activity.this, AtividadePrincipal.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		}
		new Mensagem("Informativo!", "Voc� dever� registrar um usu�rio para prosseguir!", "Ok", R.drawable.icone_informativo, Perfil_Inicial_Activity.this);
		botao = (Button) findViewById(R.id.Perfil_Botao_DataNascimentoDoUsuario);
		img = (ImageButton) findViewById(R.id.bt_CapturarFoto);
		img.setOnClickListener(this);
		botao.setText(dataNascimento.replace("-", "/"));
		botao.setOnClickListener(this);
		//Bitmap m = BitmapFactory.decodeResource(getResources() ,R.drawable.user_hq);
		imagem = FImagem.getBytes(BitmapFactory.decodeResource(getResources() ,R.drawable.user_hq));
	}

	// Data Picker

	@Override
	protected Dialog onCreateDialog(int id) {
		Calendar calendario = Calendar.getInstance();
		ano = calendario.get(Calendar.YEAR);
		mes = calendario.get(Calendar.MONTH);
		dia = calendario.get(Calendar.DAY_OF_MONTH);
		dataNascimento = ano + "-"
				+ mes + 1 + "-"
				+ ano;
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, ano, mes, dia);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			dataNascimento = String.valueOf(dayOfMonth) + "-"
					+ String.valueOf(monthOfYear + 1) + "-"
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

		default:

			return super.onOptionsItemSelected(item);
		}
	}

	private void salvar() {

		final EditText nome = (EditText) findViewById(R.id.Perfil_InserirTexto_NomeDoUsuario);
		final EditText email = (EditText) findViewById(R.id.Perfil_InserirTexto_EmailDoUsuario);
		final EditText cnh = (EditText) findViewById(R.id.Perfil_InserirTexto_CNHDoUsuario);
		cnh.addTextChangedListener(Mask.insert("###########", cnh));
		
		String opc[] = { "Nome do usu�rio ", "E-mail do usu�rio", "CNH" }, res[] = {
				nome.getText().toString(), email.getText().toString(),
				cnh.getText().toString() };

		View focus[] = { nome, email, cnh };
		boolean troca = true;

		for (int i = 0; i < opc.length; i++) {

			if (res[i].equalsIgnoreCase("") || res[i] == null) {

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

			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			DAOMotorista.getInstancia(getApplicationContext()).inserir(new Motorista(nome.getText().toString(), email.getText().toString(), cnh.getText().toString(), dataNascimento, imagem));
			builder.setTitle("Informativo!");
			builder.setMessage("O usu�rio " + nome.getText().toString()
					+ ", portador do CNH " + cnh.getText().toString()
					+ " foi cadastrado com sucesso!");
			builder.setIcon(R.drawable.icone_informativo);
			builder.setNeutralButton("OK", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					startActivity(new Intent(Perfil_Inicial_Activity.this, Veiculos_Inicial_Activity.class));
					
				}
			});
			builder.create();
			builder.show();

		}

	}
}
