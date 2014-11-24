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
import banco_de_dados.DAOAbastecimento;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;

import fragmentos.Abastecimentos_Fragment;
import funcoes.FAbastecimento;
import funcoes.FVeiculo;

public class Abastecimento_View_Activity extends Activity {

	private Context contexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abastecimento_activity_view);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		contexto = this;
		
		getActionBar().setTitle("Abastecimento");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
		
		TextView motivo   = (TextView) findViewById(R.id.Abastecimento_View_Motivo);
		motivo.setText(DAOAbastecimento.getInstancia(this).buscarPorId(Abastecimentos_Fragment.idAbastecimento).getMotivo());
		
		TextView odometro = (TextView) findViewById(R.id.Abastecimento_View_Odometro);
		odometro.setText(FAbastecimento.convFormat(DAOAbastecimento.getInstancia(this).buscarPorId(Abastecimentos_Fragment.idAbastecimento).getOdometro(), "#.### Km"));
		
		TextView data 	  = (TextView) findViewById(R.id.Abastecimento_View_Data);
		data.setText(String.valueOf(DAOAbastecimento.getInstancia(this).buscarPorId(Abastecimentos_Fragment.idAbastecimento).getData()));
		
		TextView valor 	  = (TextView) findViewById(R.id.Abastecimento_View_ValorTotal);
		valor.setText(FAbastecimento.convFormat(DAOAbastecimento.getInstancia(this).buscarPorId(Abastecimentos_Fragment.idAbastecimento).getValorTotal(), "R$ #.###"));
		
		TextView precoL   = (TextView) findViewById(R.id.Abastecimento_View_PrecoL);
		precoL.setText(FAbastecimento.convFormat(DAOAbastecimento.getInstancia(this).buscarPorId(Abastecimentos_Fragment.idAbastecimento).getValorTotal() / DAOAbastecimento.getInstancia(this).buscarPorId(Abastecimentos_Fragment.idAbastecimento).getLitros(), "R$ #.###"));
		
		
		
		TextView litros   = (TextView) findViewById(R.id.Abastecimento_View_Litros);
		litros.setText(FAbastecimento.convFormat(DAOAbastecimento.getInstancia(this).buscarPorId(Abastecimentos_Fragment.idAbastecimento).getLitros(), "#.### L"));
		
		TextView tipo     = (TextView) findViewById(R.id.Abastecimento_View_TipoCombustivel);
		tipo.setText(String.valueOf(DAOAbastecimento.getInstancia(this).buscarPorId(Abastecimentos_Fragment.idAbastecimento).getTipoCombustivel()));
		
		TextView media    = (TextView) findViewById(R.id.Abastecimento_View_Media);
		media.setText(FAbastecimento.mediaLConv(this));
		
		TextView obs      = (TextView) findViewById(R.id.Abastecimento_View_Observacao);
		obs.setText(String.valueOf(DAOAbastecimento.getInstancia(this).buscarPorId(Abastecimentos_Fragment.idAbastecimento).getObservacao()));
		
		TextView proxO    = (TextView) findViewById(R.id.Abastecimento_View_ProximoOdometro);
		proxO.setText(FAbastecimento.proximoOdometroConv(getApplication()));
		
		TextView proxD    = (TextView) findViewById(R.id.Abastecimento_View_ProximaDistancia);
		proxD.setText(FAbastecimento.proximaDistanciaConv(getApplication()));
		
	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

		case R.id.action_editar:

			startActivity(new Intent(Abastecimento_View_Activity.this,
					Abastecimento_Edit_Activity.class));

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
					DAOAbastecimento.getInstancia(getApplicationContext()).deletar(DAOAbastecimento.NOME_TABELA, Abastecimentos_Fragment.idAbastecimento);
					FVeiculo.getOdometroAtual(contexto);
					startActivity(new Intent(Abastecimento_View_Activity.this,AtividadePrincipal.class));
				}
			}); 
			
			builder.setPositiveButton("Não", null); 
			builder.create();
			builder.show();
			
			
		
			return true;

		case android.R.id.home:

			startActivity(new Intent(Abastecimento_View_Activity.this,
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
