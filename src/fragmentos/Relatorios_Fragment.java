package fragmentos;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fullservicecar.R;

import funcoes.FAbastecimento;
import funcoes.FDespesa;
import funcoes.FRelatorios;
import funcoes.FServico;
import funcoes.Mensagem;
import grafico.Abastecimento_GraficoCombustivel;
import grafico.Despesa_GraficoDespesa;
import grafico.Geral_Comparativo;
import grafico.Servico_GraficoServico;


public class Relatorios_Fragment extends Fragment{
	
	public Relatorios_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.relatorios_fragment, container,false);
		
		// Geral
		
		((TextView) view.findViewById(R.id.Relatorios_Geral)).setText("GERAL (" + FRelatorios.getGeralConv(getActivity()) + ")");
		((TextView) view.findViewById(R.id.Relatorios_Geral_Total)).setText(FRelatorios.getTotalConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Geral_DistanciaPercorrida)).setText(FRelatorios.getDistanciaPercorridaConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Geral_NumeroDeDias)).setText(FRelatorios.getNumeroDeDiasConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Geral_CustoDiario)).setText(FRelatorios.getCustoDiarioConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Geral_CustoKm)).setText(FRelatorios.getCustoKmConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Geral_MediaKmDia)).setText(FRelatorios.getMediaKmDiaConv(getActivity()));

		
		// Abastecimentos
		
		((TextView) view.findViewById(R.id.Relatorios_Abastecimentos)).setText("ABASTECIMENTOS (" + FAbastecimento.quantidade(getActivity()) + ")");
		((TextView) view.findViewById(R.id.Relatorios_Abastecimentos_Total)).setText(FAbastecimento.valorTotalConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Abastecimentos_DistanciaPercorrida)).setText(FAbastecimento.kmPercorridoConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Abastecimentos_TotalLitros)).setText(FAbastecimento.litrosConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Abastecimentos_CustoDiario)).setText(FAbastecimento.custoDiarioConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Abastecimentos_CustoKm)).setText(FAbastecimento.custoKmConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Abastecimentos_Media)).setText(FAbastecimento.mediaLConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Abastecimentos_MediaKmDia)).setText(FAbastecimento.mediaKmDiaConv(getActivity()));
		
		// Despesas
		
		((TextView) view.findViewById(R.id.Relatorios_Despesas)).setText("DESPESAS (" + FDespesa.quantidade(getActivity()) + ")");
		((TextView) view.findViewById(R.id.Relatorios_Despesas_Total)).setText(FDespesa.valorTotalConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Despesas_CustoDiario)).setText(FDespesa.custoDiarioConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Despesas_CustoKm)).setText(FDespesa.custoKmConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Despesas_MediaKmDespesa)).setText(FDespesa.mediaKmDespesaConv(getActivity()));
				
		
		// Serviços
		((TextView) view.findViewById(R.id.Relatorios_Servicos)).setText("SERVIÇOS (" + FServico.quantidade(getActivity()) + ")");
		((TextView) view.findViewById(R.id.Relatorios_Servicos_Total)).setText(FServico.valorTotalConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Servicos_CustoDiario)).setText(FServico.custoDiarioConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Servicos_CustoKm)).setText(FServico.custoKmConv(getActivity()));
		((TextView) view.findViewById(R.id.Relatorios_Servicos_MediaKmServico)).setText(FServico.mediaKmServicoConv(getActivity()));
		
		
		((Button) view.findViewById(R.id.Relatorios_Geral_ComparativoGastos)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(FRelatorios.getGeral(getActivity()) > 0){
					startActivity(new Intent(getActivity(), Geral_Comparativo.class));
				}else{
					new Mensagem("Aviso!", "Faltam dados para gerar o relatório desejado!", "OK", R.drawable.icone_informativo, getActivity());
				}
				
			}
		});
		
		((Button) view.findViewById(R.id.Relatorios_Abastecimentos_GraficosDosAbastecimentos)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Integer.valueOf(FAbastecimento.quantidade(getActivity())) > 0){
					startActivity(new Intent(getActivity(), Abastecimento_GraficoCombustivel.class));
				}else{
					
				}
				
			}
		});
		
		((Button) view.findViewById(R.id.Relatorios_Despesas_GraficoDasDespesas)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Integer.valueOf(FDespesa.quantidade(getActivity())) > 0){
					startActivity(new Intent(getActivity(), Despesa_GraficoDespesa.class));
				}else{
					new Mensagem("Aviso!", "Faltam dados para gerar o relatório desejado!", "OK", R.drawable.icone_informativo, getActivity());
				}
				
			}
		});
		
		((Button) view.findViewById(R.id.Relatorios_Servicos_GraficosDosServicos)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Integer.valueOf(FServico.quantidade(getActivity())) > 0){
					startActivity(new Intent(getActivity(), Servico_GraficoServico.class));
				}else{
					new Mensagem("Aviso!", "Faltam dados para gerar o relatório desejado!", "OK", R.drawable.icone_informativo, getActivity());
				}
				
			}
		});
		return view;
	}

}
