package funcoes;

import java.util.ArrayList;

import android.content.Context;
import banco_de_dados.Abastecimento;
import banco_de_dados.DAOAbastecimento;
import banco_de_dados.DAOVeiculo;
import banco_de_dados.Veiculo;

public class FVeiculo {
	
	public static double getOdometroAtual(Context context){
		
		if(DAOAbastecimento.getInstancia(context).getTamanho() == 0){
			
			DAOVeiculo.getInstancia(context).editar(new Veiculo(
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getAno(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getnDePortas(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getAirbags(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getVolDoTanque(), 
					0, 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getPotencia(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getNome(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getMarca(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getTipo(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getPlaca(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getRENAVAM(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getChassi(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getModelo(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getTipoPotencia()), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getId()
			);
			return 0;
		}else{
			
			ArrayList<Abastecimento> lista = new ArrayList<Abastecimento>(DAOAbastecimento.getInstancia(context).recuperarPorQuery("SELECT * FROM " + DAOAbastecimento.NOME_TABELA 
					+ " ORDER BY " + DAOAbastecimento.COLUNA_ODOMETRO + " DESC"));
			
			
			DAOVeiculo.getInstancia(context).editar(new Veiculo(
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getAno(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getnDePortas(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getAirbags(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getVolDoTanque(), 
					lista.get(0).getOdometro(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getPotencia(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getNome(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getMarca(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getTipo(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getPlaca(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getRENAVAM(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getChassi(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getModelo(), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getTipoPotencia()), 
					DAOVeiculo.getInstancia(context).buscarTodos().get(0).getId()
			);
			return DAOVeiculo.getInstancia(context).buscarTodos().get(0).getOdometroAtual();
		
		}
		
	}

}
