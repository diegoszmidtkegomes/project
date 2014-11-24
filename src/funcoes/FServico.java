package funcoes;

import java.text.DecimalFormat;

import android.content.Context;
import banco_de_dados.DAOServicos;

public class FServico {

	public static final DecimalFormat dinheiro = new DecimalFormat("R$ #.###");
	public static final DecimalFormat km       = new DecimalFormat("#.### Km");
	public static final DecimalFormat l        = new DecimalFormat("#.### L");
	public static final DecimalFormat kml      = new DecimalFormat("#.### Km/L");
	public static final DecimalFormat valor    = new DecimalFormat("#.###");
	private static		DecimalFormat valorF;
	
	
	public static String quantidade(Context context){
		
		return String.valueOf(DAOServicos.getInstancia(context).getTamanho());
		
	}
	
	public static double conv(double g){
		return Double.parseDouble(valor.format((g)));
	}
	
	public static String convFormat(double g, String f){
		valorF = new DecimalFormat(f);
		return valorF.format(g);
	}
	
	public static double maiorServico(Context context){
		
		double maior = Double.MIN_VALUE;
		
		if(DAOServicos.getInstancia(context).getTamanho() == 0){
			return 0;
		}
		
		for(int x = 0; x < DAOServicos.getInstancia(context).getTamanho(); x++){
			
			if(DAOServicos.getInstancia(context).buscarTodos().get(x).getValorTotal() > maior){
				maior = DAOServicos.getInstancia(context).buscarTodos().get(x).getValorTotal();
			}
		
		}
			
		return maior;
		
	}
	
	public static String maiorServicoConv(Context context){
		
		return FAbastecimento.convFormat(maiorServico(context), "R$ #.###");
		
	}
	
	public static double menorServico(Context context){
		
		double menor = Double.MAX_VALUE;
		
		if(DAOServicos.getInstancia(context).getTamanho() == 0){
			return 0;
		}
		
		for(int x = 0; x < DAOServicos.getInstancia(context).getTamanho(); x++){
			
			if(DAOServicos.getInstancia(context).buscarTodos().get(x).getValorTotal() < menor){
				menor = DAOServicos.getInstancia(context).buscarTodos().get(x).getValorTotal();
			}
		
		}
			
		return menor;
		
	}
	
	public static String menorServicoConv(Context context){
		return FAbastecimento.convFormat(menorServico(context), "R$ #.###");
		
	}
	
	public static double mediaServico(Context context){
		
		double media = 0;
		
		if(DAOServicos.getInstancia(context).getTamanho() == 0){
			return 0;
		}
		
		for(int x = 0; x < DAOServicos.getInstancia(context).getTamanho(); x++){
			media += DAOServicos.getInstancia(context).buscarTodos().get(x).getValorTotal();
		}
		
		return media / DAOServicos.getInstancia(context).getTamanho();
		
	}
	
	public static String mediaServicoConv(Context context){
		
		return FAbastecimento.convFormat(mediaServico(context), "R$ #.###");
		
	}
	
	public static double valorTotal(Context context){
		
		double valor = 0;
		
		for(int x = 0; x < DAOServicos.getInstancia(context).getTamanho(); x++){
			
			valor += DAOServicos.getInstancia(context).buscarTodos().get(x).getValorTotal();
			
		}
		
		return valor;
		
	}
	
	public static String valorTotalConv(Context context){
		
		return FAbastecimento.convFormat(valorTotal(context), "R$ #.###");
		
	}
	
	public static double custoDiario(Context context){
		
		if(FRelatorios.getNumeroDeDias(context) == 0){
			return 0;
		}
		
		return valorTotal(context) / FRelatorios.getNumeroDeDias(context);
		
	}
	
	public static String custoDiarioConv(Context context){
		return FAbastecimento.convFormat(custoDiario(context), "R$ #.###");
	}
	
	public static double custoKm(Context context){
		
		if(FRelatorios.getDistanciaPercorrida(context) == 0){
			return 0;
		}
		
		return valorTotal(context) / FRelatorios.getDistanciaPercorrida(context);
		
	}
	
	public static String custoKmConv(Context context){
		return FAbastecimento.convFormat(custoKm(context), "R$ #.###");
	}
	
	
	public static double mediaKmServico(Context context){
		
		if(DAOServicos.getInstancia(context).getTamanho() == 0){
			return 0;
		}
		return FRelatorios.getDistanciaPercorrida(context) / DAOServicos.getInstancia(context).getTamanho();
		
	}
	
	public static String mediaKmServicoConv(Context context){
		
		return FAbastecimento.convFormat(mediaKmServico(context), "#.### Km");
		
	}
	
	
	
}
