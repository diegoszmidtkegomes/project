package fragmentos;

import java.util.Calendar;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fullservicecar.R;

import funcoes.FAbastecimento;


public class CustoVeicular_Fragment extends Fragment{
	
	public CustoVeicular_Fragment() {

	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.custoveicular_fragment, container,false);
		
		final EditText valorVeiculo = (EditText) view.findViewById(R.id.valorVeiculo);

		final EditText anoVeiculo   = (EditText) view.findViewById(R.id.anoVeiculo);
		final Button   calcular	  = (Button)   view.findViewById(R.id.Calcular);
		
		calcular.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean troca = true;
				String valores[] = {valorVeiculo.getText().toString(), anoVeiculo.getText().toString()},
					   msg    [] = {"Valor atual do veículo", "Ano do veículo"};
				
				for(int x = 0; x < valores.length; x++){
					if(valores[x].equals("")){
						Toast.makeText(getActivity(), "É obrigatório o preenchimento do campo " + msg[x], Toast.LENGTH_SHORT).show();
						troca = false;
						break;
					}
				}
				
				if(troca){
					
					final Dialog dialog = new Dialog(getActivity());
					dialog.setContentView(R.layout.custoveicular_dialogo);
					dialog.setTitle("Custo veícular");
					
					double vVeiculo = Double.parseDouble(valorVeiculo.getText().toString()),
						   aVeiculo = Double.parseDouble(anoVeiculo.getText().toString()),
						   IPVA = 0, DPVAT = 101.16, Seguro = 0, CustoO = 0, DPA = 0, DPM = 0;
					
					if(Calendar.getInstance().get(Calendar.YEAR) - aVeiculo >= 5){
						
						DPA = vVeiculo * 0.1;
						
					}else{
						
						DPA = vVeiculo * 0.2;
						
					}
					
					Seguro = vVeiculo * 0.05;
					DPM = DPA / 12;
					IPVA = vVeiculo * 0.04;
					CustoO = vVeiculo * 0.06;
					
					((TextView) dialog.findViewById(R.id.cv_IPVA))  .setText(FAbastecimento.convFormat(IPVA, "R$ #.##"));
					((TextView) dialog.findViewById(R.id.cv_DPVAT)) .setText(FAbastecimento.convFormat(DPVAT, "R$ #.##"));
					((TextView) dialog.findViewById(R.id.cv_Seguro)).setText(FAbastecimento.convFormat(Seguro, "R$ #.##"));
					((TextView) dialog.findViewById(R.id.cv_CustoO)).setText(FAbastecimento.convFormat(CustoO, "R$ #.##"));
					((TextView) dialog.findViewById(R.id.cv_DPA))   .setText(FAbastecimento.convFormat(DPA, "R$ #.##"));
					((TextView) dialog.findViewById(R.id.cv_DPM))   .setText(FAbastecimento.convFormat(DPM, "R$ #.##"));
					
					((Button) dialog.findViewById(R.id.cv_btOk)).setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					dialog.show();
					
				}
			}
		});
		
		
		return view;
	}
	

}
