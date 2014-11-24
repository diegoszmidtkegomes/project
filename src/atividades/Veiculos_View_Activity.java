package atividades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;

import fragmentos.Veiculos_Fragment;

public class Veiculos_View_Activity extends Activity implements OnItemClickListener {

	private Context contexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.veiculos_activity_view);
		getActionBar().setTitle("Veículo");
		getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getNome());
		getActionBar().setDisplayHomeAsUpEnabled(true);
		contexto = this;
		
		((TextView) findViewById(R.id.Veiculo_Fragment_NomeDoVeiculo)).
		setText(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getNome().toString());
		
		((TextView) findViewById(R.id.Veiculo_Fragment_MarcaDoVeiculo)).
		setText(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getMarca().toString());
		
		((TextView) findViewById(R.id.Veiculo_Fragment_TipoDeVeiculo)).
		setText(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getTipo().toString());
		
		((TextView) findViewById(R.id.Veiculo_Fragment_Placa)).
		setText(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getPlaca().toString());
		
		((TextView) findViewById(R.id.Veiculo_Fragment_Ano)).
		setText(String.valueOf(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getAno()));
		
		
		((TextView) findViewById(R.id.Veiculo_Fragment_VolDoTanque)).
		setText(String.valueOf(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getVolDoTanque()) + " L");
		
		((TextView) findViewById(R.id.Veiculo_Fragment_OdometroAtual)).
		setText(String.valueOf(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getOdometroAtual()) + " Km");
		
		((TextView) findViewById(R.id.Veiculo_Fragment_Chassi)).
		setText(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getChassi().toString());
		
		((TextView) findViewById(R.id.Veiculo_Fragment_RENAVAM)).
		setText(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getRENAVAM().toString());
		
		((TextView) findViewById(R.id.Veiculo_Fragment_Modelo)).
		setText(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getModelo().toString());
		
		((TextView) findViewById(R.id.Veiculo_Fragment_NDePortas)).
		setText(String.valueOf(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getnDePortas()));
		
		((TextView) findViewById(R.id.Veiculo_Fragment_Potencia)).
		setText(String.valueOf(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getPotencia()));
		
		((TextView) findViewById(R.id.Veiculo_Fragment_TipoPotencia)).
		setText(String.valueOf(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getTipoPotencia()));
		
		
		((TextView) findViewById(R.id.Veiculo_Fragment_Airbags)).
		setText(String.valueOf(DAOVeiculo.getInstancia(this).buscarPorId(Veiculos_Fragment.idVeiculo).getAirbags()));
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

		case R.id.action_editar:

			startActivity(new Intent(Veiculos_View_Activity.this,
					Veiculos_Activity.class));

			return true;
			
		case R.id.action_remover:

			AlertDialog.Builder builder = new AlertDialog.Builder(contexto); 
			builder.setTitle("Atenção!"); 
			builder.setMessage("Disponível somente na versão PRO!"); 
			builder.setIcon(R.drawable.icone_aviso);
			builder.setNeutralButton("OK", null);
			builder.create();
			builder.show();
			
			
		
			return true;

		case android.R.id.home:

			startActivity(new Intent(Veiculos_View_Activity.this,
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		startActivity(new Intent(this, Veiculos_Activity.class));
		
	}

}
