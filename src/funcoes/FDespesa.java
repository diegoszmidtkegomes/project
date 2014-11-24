package funcoes;


import java.text.DecimalFormat;

import android.content.Context;
import banco_de_dados.DAODespesas;

public class FDespesa{
	
	public static final DecimalFormat dinheiro = new DecimalFormat("R$ #.###");
	public static final DecimalFormat km       = new DecimalFormat("#.### Km");
	public static final DecimalFormat l        = new DecimalFormat("#.### L");
	public static final DecimalFormat kml      = new DecimalFormat("#.### Km/L");
	public static final DecimalFormat valor    = new DecimalFormat("#.###");
	private static		DecimalFormat valorF;
	
	
	public static String quantidade(Context context){
		
		return String.valueOf(DAODespesas.getInstancia(context).getTamanho());
		
	}
	
	public static double conv(double g){
		return Double.parseDouble(valor.format((g)));
	}
	
	public static String convFormat(double g, String f){
		valorF = new DecimalFormat(f);
		return valorF.format(g);
	}
	
	public static double maiorDespesa(Context context){
		
		double maior = Double.MIN_VALUE;
		
		if(DAODespesas.getInstancia(context).getTamanho() == 0){
			return 0;
		}
		
		for(int x = 0; x < DAODespesas.getInstancia(context).getTamanho(); x++){
			
			if(DAODespesas.getInstancia(context).buscarTodos().get(x).getValorTotal() > maior){
				maior = DAODespesas.getInstancia(context).buscarTodos().get(x).getValorTotal();
			}
		
		}
			
		return maior;
		
	}
	
	public static String maiorDespesaConv(Context context){
		
		return FAbastecimento.convFormat(maiorDespesa(context), "R$ #.###");
		
	}
	
	public static double menorDespesa(Context context){
		
		double menor = Double.MAX_VALUE;
		
		if(DAODespesas.getInstancia(context).getTamanho() == 0){
			return 0;
		}
		
		for(int x = 0; x < DAODespesas.getInstancia(context).getTamanho(); x++){
			
			if(DAODespesas.getInstancia(context).buscarTodos().get(x).getValorTotal() < menor){
				menor = DAODespesas.getInstancia(context).buscarTodos().get(x).getValorTotal();
			}
		
		}
			
		return menor;
		
	}
	
	public static String menorDespesaConv(Context context){
		
		return FAbastecimento.convFormat(menorDespesa(context), "R$ #.###");
		
	}
	
	public static double mediaDespesa(Context context){
		
		double media = 0;
		
		if(DAODespesas.getInstancia(context).getTamanho() == 0){
			return 0;
		}
		
		for(int x = 0; x < DAODespesas.getInstancia(context).getTamanho(); x++){
			media += DAODespesas.getInstancia(context).buscarTodos().get(x).getValorTotal();
		}
		
		return media / DAODespesas.getInstancia(context).getTamanho();
		
	}
	
	public static String mediaDespesaConv(Context context){
		
		return FAbastecimento.convFormat(mediaDespesa(context), "R$ #.###");
		
	}
	
	public static double valorTotal(Context context){
		
		double valor = 0;
		
		for(int x = 0; x < DAODespesas.getInstancia(context).getTamanho(); x++){
			
			valor += DAODespesas.getInstancia(context).buscarTodos().get(x).getValorTotal();
			
		}
		
		return valor;
		
	}
	
	public static String valorTotalConv(Context context){
		
		return FAbastecimento.convFormat(valorTotal(context), "R$ #.###");
		
	}

	public static double kmPercorrido(Context context){
		
		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
		
		if(DAODespesas.getInstancia(context).getTamanho() == 0){
			return 0;
		}
		
		for(int x = 0; x < DAODespesas.getInstancia(context).getTamanho(); x++){
			
			if(DAODespesas.getInstancia(context).buscarTodos().get(x).getOdometro() > max){
				max = DAODespesas.getInstancia(context).buscarTodos().get(x).getOdometro();
			}
			
			if(DAODespesas.getInstancia(context).buscarTodos().get(x).getOdometro() < min){
				min = DAODespesas.getInstancia(context).buscarTodos().get(x).getOdometro();
			}
				
		}
		
		
		return max - min;
		
	}
	
	public static String kmPercorridoConv(Context context){
		
		return FAbastecimento.convFormat(kmPercorrido(context), "#.### Km");
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
	
	public static double mediaKmDespesa(Context context){
		
		if(DAODespesas.getInstancia(context).getTamanho() == 0){
			return 0;
		}
		return FRelatorios.getDistanciaPercorrida(context) / DAODespesas.getInstancia(context).getTamanho();
		
	}
	
	public static String mediaKmDespesaConv(Context context){
		
		return FAbastecimento.convFormat(mediaKmDespesa(context), "#.### Km");
		
	}
	
	
	
}
