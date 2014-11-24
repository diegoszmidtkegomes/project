package funcoes;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class Mensagem extends Fragment{
	
	
	public AlertDialog.Builder builder;
	
	public Mensagem(String titulo, String mensagem, String msgBotao, int icone, Object contexto){
		
		builder = new AlertDialog.Builder((Context) contexto); 
		builder.setTitle(titulo); 
		builder.setMessage(mensagem); 
		builder.setIcon(icone);
		builder.setNeutralButton(msgBotao, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		}); 
		builder.create();
		builder.show();
		
	}
	
	

}
