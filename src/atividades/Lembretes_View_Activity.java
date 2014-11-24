package atividades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import banco_de_dados.DAODespesas;
import banco_de_dados.DAOLembretes;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;

import fragmentos.Lembretes_Fragment;

public class Lembretes_View_Activity extends Activity{

private Context contexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lembretes_activity_view);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		contexto = this;
		 
		getActionBar().setTitle("Lembrete");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		
		((TextView) findViewById(R.id.Lembretes_Item_ListView_TipoLembrete)).setText(DAOLembretes.getInstancia(this).buscarPorId(Lembretes_Fragment.idLembrete).getTipo());
		((TextView) findViewById(R.id.Lembretes_Item_ListView_Motivo)).setText(DAOLembretes.getInstancia(this).buscarPorId(Lembretes_Fragment.idLembrete).getMotivo());
		((TextView) findViewById(R.id.Lembretes_Item_ListView_Odometro)).setText(String.valueOf(DAOLembretes.getInstancia(this).buscarPorId(Lembretes_Fragment.idLembrete).getOdometro()));
		((TextView) findViewById(R.id.Lembretes_Item_ListView_Data)).setText(DAOLembretes.getInstancia(this).buscarPorId(Lembretes_Fragment.idLembrete).getData());
		((TextView) findViewById(R.id.Lembretes_Item_ListView_Observacao)).setText(DAOLembretes.getInstancia(this).buscarPorId(Lembretes_Fragment.idLembrete).getObservacao());
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

		case R.id.action_editar:

			startActivity(new Intent(this, Lembretes_Edit_Activity.class));

			return true;
			
		case R.id.action_remover:

			AlertDialog.Builder builder = new AlertDialog.Builder(contexto); 
			builder.setTitle("Atenção!"); 
			builder.setMessage("Deseja excluir este lembrete?"); 
			builder.setIcon(R.drawable.icone_aviso);
			builder.setNegativeButton("Sim", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					DAOLembretes.getInstancia(getApplicationContext()).deletar(DAODespesas.NOME_TABELA, Lembretes_Fragment.idLembrete);
					startActivity(new Intent(contexto ,AtividadePrincipal.class));
				}
			}); 
			
			builder.setPositiveButton("Não", null); 
			builder.create();
			builder.show();
			
			
		
			return true;

		case android.R.id.home:

			startActivity(new Intent(this, AtividadePrincipal.class));

			return true;

		default:

			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.trocar, menu);
		return true;
	}

}
