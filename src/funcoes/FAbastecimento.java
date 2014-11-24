package funcoes;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import banco_de_dados.Abastecimento;
import banco_de_dados.DAOAbastecimento;

public class FAbastecimento {

	public static final DecimalFormat dinheiro = new DecimalFormat("R$ #,###");
	public static final DecimalFormat km = new DecimalFormat("#,### Km");
	public static final DecimalFormat l = new DecimalFormat("#,### L");
	public static final DecimalFormat kml = new DecimalFormat("#,### Km/L");
	public static final DecimalFormat valor = new DecimalFormat("#,###");
	private static DecimalFormat valorF;

	public static String quantidade(Context context) {

		return String.valueOf(DAOAbastecimento.getInstancia(context)
				.getTamanho());

	}

	public static double conv(double g) {
		return Double.parseDouble(valor.format((g)));
	}

	public static String convFormat(double g, String f) {
		valorF = new DecimalFormat(f);
		return valorF.format(g);
	}

	public static double valorTotal(Context context) {

		double valorTotal = 0;

		for (int x = 0; x < DAOAbastecimento.getInstancia(context).getTamanho(); x++) {

			valorTotal += DAOAbastecimento.getInstancia(context).buscarTodos()
					.get(x).getValorTotal();

		}

		return valorTotal;

	}

	public static String valorTotalConv(Context context) {

		return convFormat(valorTotal(context), "R$ #.###");

	}

	public static double kmPercorrido(Context context) {

		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;

		if (DAOAbastecimento.getInstancia(context).getTamanho() == 0) {
			return 0;
		}

		for (int x = 0; x < DAOAbastecimento.getInstancia(context).getTamanho(); x++) {

			if (DAOAbastecimento.getInstancia(context).buscarTodos().get(x).getOdometro() > max) {
				max = DAOAbastecimento.getInstancia(context).buscarTodos().get(x).getOdometro();
			}

			if (DAOAbastecimento.getInstancia(context).buscarTodos().get(x).getOdometro() < min) {
				min = DAOAbastecimento.getInstancia(context).buscarTodos().get(x).getOdometro();
			}

		}
		
		return max - min;

	}

	public static String kmPercorridoConv(Context context) {

		return convFormat(kmPercorrido(context), "#.### Km");
	}

	public static double litros(Context context) {

		double litros = 0;

		for (int x = 0; x < DAOAbastecimento.getInstancia(context).getTamanho(); x++) {

			litros += DAOAbastecimento.getInstancia(context).buscarTodos()
					.get(x).getLitros();

		}

		return litros;

	}

	public static String litrosConv(Context context) {

		return convFormat(litros(context), "#.### L");

	}

	public static double precoL(Context context) {

		double litros = 0, valorTotal = 0;

		if (DAOAbastecimento.getInstancia(context).getTamanho() == 0) {
			return 0;
		}

		for (int x = 0; x < DAOAbastecimento.getInstancia(context).getTamanho(); x++) {

			litros += DAOAbastecimento.getInstancia(context).buscarTodos()
					.get(x).getLitros();
			valorTotal += DAOAbastecimento.getInstancia(context).buscarTodos()
					.get(x).getValorTotal();
		}

		if (litros == 0 || valorTotal == 0) {
			return 0;
		} else {
			return valorTotal / litros;
		}

	}

	public static String precoLConv(Context context) {

		return convFormat(precoL(context), "R$ #.###");

	}

	public static double mediaL(Context context) {

		double litros = 0, min = Double.MAX_VALUE, max = Double.MIN_VALUE;

		for (int x = 0; x < DAOAbastecimento.getInstancia(context).getTamanho(); x++) {

			if (x < DAOAbastecimento.getInstancia(context).getTamanho() - 1) {
				litros += DAOAbastecimento.getInstancia(context).buscarTodos()
						.get(x).getLitros();
			}

			if (DAOAbastecimento.getInstancia(context).buscarTodos().get(x)
					.getOdometro() > max) {
				max = DAOAbastecimento.getInstancia(context).buscarTodos()
						.get(x).getOdometro();
			}

			if (DAOAbastecimento.getInstancia(context).buscarTodos().get(x)
					.getOdometro() < min) {
				min = DAOAbastecimento.getInstancia(context).buscarTodos()
						.get(x).getOdometro();
			}

		}

		if (DAOAbastecimento.getInstancia(context).getTamanho() < 2
				|| litros == 0 || max == 0) {
			return 0;
		} else {
			return ((max - min) / litros);
		}

	}

	public static String mediaLConv(Context context) {

		if (DAOAbastecimento.getInstancia(context).getTamanho() < 2) {
			return "0";
		} else {
			return convFormat(mediaL(context), "#.### Km/L");
		}

	}

	public static double custoDiario(Context context) {

		if (FRelatorios.getNumeroDeDias(context) == 0) {
			return 0;
		}

		return valorTotal(context) / FRelatorios.getNumeroDeDias(context);

	}

	public static String custoDiarioConv(Context context) {
		return convFormat(custoDiario(context), "R$ #.###");
	}

	public static double custoKm(Context context) {

		if (FRelatorios.getDistanciaPercorrida(context) == 0) {
			return 0;
		}

		return valorTotal(context)
				/ FRelatorios.getDistanciaPercorrida(context);

	}

	public static String custoKmConv(Context context) {
		return convFormat(custoKm(context), "R$ #.###");
	}
	
	public static double mediaKmDia(Context context){
		
		if(FRelatorios.getDistanciaPercorrida(context) == 0){
			return 0;
		}
		
		return kmPercorrido(context) / FRelatorios.getNumeroDeDias(context);
	}
	
	public static String mediaKmDiaConv(Context context){
		return convFormat(mediaKmDia(context), "#.### Km");
	}
	
	public static double proximoOdometro(Context context){
		
		if(DAOAbastecimento.getInstancia(context).getTamanho() < 2){
			return 0;
		}
		
		ArrayList<Abastecimento> lista = new ArrayList<Abastecimento>(DAOAbastecimento.getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOAbastecimento.NOME_TABELA 
				+ " ORDER BY " + DAOAbastecimento.COLUNA_ODOMETRO + " ASC"));
		
		
		return lista.get(lista.size() - 1).getOdometro() + proximaDistancia(context);
	}
	
	
	public static String proximoOdometroConv(Context context){
		
		return convFormat(proximoOdometro(context), "#.### Km");
	}
	
	public static double proximaDistancia(Context context){
		
		if(DAOAbastecimento.getInstancia(context).getTamanho() < 2){
			return 0;
		}
		
		ArrayList<Abastecimento> lista = new ArrayList<Abastecimento>(DAOAbastecimento.getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOAbastecimento.NOME_TABELA 
				+ " ORDER BY " + DAOAbastecimento.COLUNA_ODOMETRO + " ASC"));
		
		return mediaL(context) * lista.get(lista.size() - 1).getLitros();
		
	}
	
	public static String proximaDistanciaConv(Context context){
		
		return convFormat(proximaDistancia(context), " #.### Km");
		
	}

}
