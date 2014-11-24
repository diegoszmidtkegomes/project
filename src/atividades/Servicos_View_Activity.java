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
import banco_de_dados.DAOServicos;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;

import fragmentos.Servicos_Fragment;
import funcoes.FAbastecimento;

public class Servicos_View_Activity extends Activity {

	private Context contexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.servicos_activity_view);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		contexto = this;
		
		getActionBar().setTitle("Serviço");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		
		TextView motivo   = (TextView) findViewById(R.id.Servicos_View_Motivo);
		motivo.setText(DAOServicos.getInstancia(this).buscarPorId(Servicos_Fragment.idServico).getTipo());
		
		TextView odometro = (TextView) findViewById(R.id.Servicos_View_Odometro);
		odometro.setText(FAbastecimento.convFormat(DAOServicos.getInstancia(this).buscarPorId(Servicos_Fragment.idServico).getOdometro(), " #.### Km"));
		
		TextView data 	  = (TextView) findViewById(R.id.Servicos_View_Data);
		data.setText(String.valueOf(DAOServicos.getInstancia(this).buscarPorId(Servicos_Fragment.idServico).getData()));
		
		TextView valor 	  = (TextView) findViewById(R.id.Servicos_View_ValorTotal);
		valor.setText(FAbastecimento.convFormat(DAOServicos.getInstancia(this).buscarPorId(Servicos_Fragment.idServico).getValorTotal(), "R$ #.###"));
		
		TextView local   = (TextView) findViewById(R.id.Servicos_View_LocalDespesa);
		local.setText(String.valueOf(DAOServicos.getInstancia(this).buscarPorId(Servicos_Fragment.idServico).getLocal()));
		
		TextView obs     = (TextView) findViewById(R.id.Servicos_View_Observacao);
		obs.setText(String.valueOf(DAOServicos.getInstancia(this).buscarPorId(Servicos_Fragment.idServico).getObservacao()));
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

		case R.id.action_editar:

			startActivity(new Intent(Servicos_View_Activity.this,
					Servicos_Edit_Activity.class));
			
			return true;
			
		case R.id.action_remover:

			AlertDialog.Builder builder = new AlertDialog.Builder(contexto); 
			builder.setTitle("Atenção!"); 
			builder.setMessage("Deseja excluir este abastecimento?"); 
			builder.setIcon(R.drawable.icone_aviso);
			builder.setNegativeButton("Sim", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					DAOServicos.getInstancia(getApplicationContext()).deletar(DAOServicos.NOME_TABELA, Servicos_Fragment.idServico);
					startActivity(new Intent(Servicos_View_Activity.this,AtividadePrincipal.class));
				}
			}); 
			
			builder.setPositiveButton("Não", null); 
			builder.create();
			builder.show();
			
			
		
			return true;

		case android.R.id.home:

			startActivity(new Intent(Servicos_View_Activity.this,
					AtividadePrincipal.class));

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
