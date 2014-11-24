package funcoes;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import banco_de_dados.DAOLembretes;
import banco_de_dados.DAOVeiculo;
import banco_de_dados.Lembretes;

import com.example.fullservicecar.R;

public class FLembrete extends Fragment{
	
	private String data = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/"
			+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "/"
			+ Calendar.getInstance().get(Calendar.YEAR);
	
	public void acionarLembrete(final Context context){
		
		
		
		if(DAOLembretes.getInstancia(context).getTamanho() > 0){
			
			final ArrayList<Lembretes> lista = new ArrayList<Lembretes>(DAOLembretes.getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOLembretes.NOME_TABELA + " ORDER BY " + DAOLembretes.COLUNA_ODOMETRO + ", " + DAOLembretes.COLUNA_MILLIS + " ASC"));
			
			for(int x = 0; x < lista.size(); x++){
				
				if(DAOVeiculo.getInstancia(context).buscarTodos().get(0).getOdometroAtual() >= lista.get(x).getOdometro() || FData.getDataInMillis(data) >= lista.get(x).getMillis()){
					
					final Dialog dialog = new Dialog(context);
					dialog.setContentView(R.layout.lembrete_dialogo);
					dialog.setTitle("Lembrete");
					
					final int atual = x;
					
					((TextView) dialog.findViewById(R.id.Lembretes_Dialogo_NomeVeiculo)).setText(DAOVeiculo.getInstancia(context).buscarTodos().get(0).getNome());
				    ((TextView) dialog.findViewById(R.id.Lembretes_Dialogo_Placa)).setText(DAOVeiculo.getInstancia(context).buscarTodos().get(0).getPlaca());
					((TextView) dialog.findViewById(R.id.Lembretes_Dialogo_TipoLembrete)).setText(lista.get(x).getTipo());
					((TextView) dialog.findViewById(R.id.Lembretes_Dialogo_Motivo)).setText(lista.get(x).getMotivo());
					((TextView) dialog.findViewById(R.id.Lembretes_Dialogo_Odometro)).setText(FAbastecimento.convFormat(lista.get(x).getOdometro(), "#.### Km"));
					((TextView) dialog.findViewById(R.id.Lembretes_Dialogo_Data)).setText(lista.get(x).getData());
					((TextView) dialog.findViewById(R.id.Lembretes_Dialogo_Observacao)).setText(lista.get(x).getObservacao());
					
					((TextView) dialog.findViewById(R.id.Lembretes_Dialogo_Nao)).setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					
					((TextView) dialog.findViewById(R.id.Lembretes_Dialogo_Sim)).setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							DAOLembretes.getInstancia(context).deletar(DAOLembretes.NOME_TABELA, lista.get(atual).getId());
							dialog.dismiss();
						}
					});
					dialog.show();
	
				}
				
			}
			
		}
	}
	
}
