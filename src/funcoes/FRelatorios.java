package funcoes;

import android.content.Context;
import banco_de_dados.DAOAbastecimento;
import banco_de_dados.DAODespesas;
import banco_de_dados.DAOServicos;

public class FRelatorios {
	
	public static int getGeral(Context context){
		
		return DAOAbastecimento.getInstancia(context).getTamanho() + DAODespesas.getInstancia(context).getTamanho() + DAOServicos.getInstancia(context).getTamanho();
		
	}
	
	public static String getGeralConv(Context context){
		
		return String.valueOf(getGeral(context));
		
	}
	
	public static double getTotal(Context context){
		
		return  FDespesa.valorTotal(context) + FServico.valorTotal(context) + FAbastecimento.valorTotal(context);
		
	}
	
	public static String getTotalConv(Context context){
		
		return FAbastecimento.convFormat(getTotal(context), "R$ #.###");
		
	}
	
	public static double getDistanciaPercorrida(Context context){
		
		if(getGeral(context) == 0){
			return 0;
		}
		
		double 
		menorAbastecimento = 0,
		maiorAbastecimento = 0,
		menorDespesa	   = 0,
		maiorDespesa       = 0,
		menorServico	   = 0,
		maiorServico	   = 0,
		menor = Double.MAX_VALUE, maior = Double.MIN_VALUE;
		
		if(DAOAbastecimento.getInstancia(context).getTamanho() > 0){
			
			menorAbastecimento = DAOAbastecimento.getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOAbastecimento.NOME_TABELA + " ORDER BY " + DAOAbastecimento.COLUNA_ODOMETRO + " ASC").get(0).getOdometro(); 
			maiorAbastecimento = DAOAbastecimento.getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOAbastecimento.NOME_TABELA + " ORDER BY " + DAOAbastecimento.COLUNA_ODOMETRO + " DESC").get(0).getOdometro();
					
			
			double valores[] = {menorAbastecimento, maiorAbastecimento};
			
			for(int x = 0; x < valores.length; x++){
				
				if(valores[x] < menor){
					menor = valores[x];
				}
				
				if(valores[x] > maior){
					maior = valores[x];
				}
				
			}
			
		}

		if(DAODespesas.getInstancia(context).getTamanho() > 0){
			
			menorDespesa	   = DAODespesas     .getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAODespesas	 .NOME_TABELA + " ORDER BY " + DAODespesas	   .COLUNA_ODOMETRO + " ASC").get(0).getOdometro(); 
			maiorDespesa       = DAODespesas	 .getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAODespesas	 .NOME_TABELA + " ORDER BY " + DAODespesas	   .COLUNA_ODOMETRO + " DESC").get(0).getOdometro(); 
					
			
			double valores[] = {menorDespesa, maiorDespesa};
			
			for(int x = 0; x < valores.length; x++){
				
				if(valores[x] < menor){
					menor = valores[x];
				}
				
				if(valores[x] > maior){
					maior = valores[x];
				}
				
			}
			
		}
		
		if(DAOServicos.getInstancia(context).getTamanho() > 0){
			
			menorServico	   = DAOServicos	 .getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOServicos	 .NOME_TABELA + " ORDER BY " + DAOServicos	   .COLUNA_ODOMETRO + " ASC").get(0).getOdometro(); 
			maiorServico	   = DAOServicos	 .getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOServicos	 .NOME_TABELA + " ORDER BY " + DAOServicos	   .COLUNA_ODOMETRO + " DESC").get(0).getOdometro();
					
			double valores[] = {menorServico, maiorServico};
			
			for(int x = 0; x < valores.length; x++){
				
				if(valores[x] < menor){
					menor = valores[x];
				}
				
				if(valores[x] > maior){
					maior = valores[x];
				}
				
			}
		}
		
		return maior - menor;
		
	}
	
	public static String getDistanciaPercorridaConv(Context context){
		
		return FAbastecimento.convFormat(getDistanciaPercorrida(context), "#.### Km");
		
	}
	
	public static long getNumeroDeDias(Context context){
		
		if(getGeral(context) == 0){
			
			return 0;
			
		}
		
		long 
		menorAbastecimento = 0,
		maiorAbastecimento = 0,
		menorDespesa	   = 0,
		maiorDespesa       = 0,
		menorServico	   = 0,
		maiorServico	   = 0,
		menor = Long.MAX_VALUE, maior = Long.MIN_VALUE;
		
		if(DAOAbastecimento.getInstancia(context).getTamanho() > 0){
			
			menorAbastecimento = DAOAbastecimento.getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOAbastecimento.NOME_TABELA + " ORDER BY " + DAOAbastecimento.COLUNA_MILLIS   + " ASC").get(0).getMillis(); 
			maiorAbastecimento = DAOAbastecimento.getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOAbastecimento.NOME_TABELA + " ORDER BY " + DAOAbastecimento.COLUNA_MILLIS   + " DESC").get(0).getMillis();
					
			
			long valores[] = {menorAbastecimento, maiorAbastecimento};
			
			for(int x = 0; x < valores.length; x++){
				
				if(valores[x] < menor){
					menor = valores[x];
				}
				
				if(valores[x] > maior){
					maior = valores[x];
				}
				
			}
			
		}

		if(DAODespesas.getInstancia(context).getTamanho() > 0){
			
			menorDespesa	   = DAODespesas     .getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAODespesas	 .NOME_TABELA + " ORDER BY " + DAODespesas	   .COLUNA_MILLIS + " ASC").get(0).getMillis(); 
			maiorDespesa       = DAODespesas	 .getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAODespesas	 .NOME_TABELA + " ORDER BY " + DAODespesas	   .COLUNA_MILLIS + " DESC").get(0).getMillis(); 
					
			
			long valores[] = {menorDespesa, maiorDespesa};
			
			for(int x = 0; x < valores.length; x++){
				
				if(valores[x] < menor){
					menor = valores[x];
				}
				
				if(valores[x] > maior){
					maior = valores[x];
				}
				
			}
			
		}
		
		if(DAOServicos.getInstancia(context).getTamanho() > 0){
			
			menorServico	   = DAOServicos	 .getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOServicos	 .NOME_TABELA + " ORDER BY " + DAOServicos	   .COLUNA_MILLIS + " ASC").get(0).getMillis(); 
			maiorServico	   = DAOServicos	 .getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOServicos	 .NOME_TABELA + " ORDER BY " + DAOServicos	   .COLUNA_MILLIS + " DESC").get(0).getMillis();
					
			long valores[] = {menorServico, maiorServico};
			
			for(int x = 0; x < valores.length; x++){
				
				if(valores[x] < menor){
					menor = valores[x];
				}
				
				if(valores[x] > maior){
					maior = valores[x];
				}
				
			}
		}
		
		long x = maior - menor;
		double m = x / 86400000;
		
		return (long) m + 1;
		
	}
	
	public static String getNumeroDeDiasConv(Context context){
		
		return String.valueOf(getNumeroDeDias(context));
		
	}
	
	public static double getCustoDiario(Context context){
		
		if(getGeral(context) == 0){
			return 0;
		}
		
		return getTotal(context) / getNumeroDeDias(context);
		
	}
	
	public static String getCustoDiarioConv(Context context){
		
		return FAbastecimento.convFormat(getCustoDiario(context), "R$ #.###");
		
	}
	
	public static double getCustoKm(Context context){
		
		if(getDistanciaPercorrida(context) == 0 || getTotal(context) == 0){
			return 0;
		}else{
			return getTotal(context) / getDistanciaPercorrida(context);
		}
		
	}
	
	public static String getCustoKmConv(Context context){
		
		return FAbastecimento.convFormat(getCustoKm(context), "R$ #.###");
		
	}
	
	public static double getMediaKmDia(Context context){
		
		if(getDistanciaPercorrida(context) == 0){
			return 0;
		}else{
			return getDistanciaPercorrida(context) / getNumeroDeDias(context);
		}
		
	}
	
	public static String getMediaKmDiaConv(Context context){
		
		return FAbastecimento.convFormat(getMediaKmDia(context), "#.### Km");
		
	}

}
