package banco_de_dados;

import android.content.ContentValues;
import android.content.Context;

public class DAOConfiguracoes extends DAOBase<Configuracoes>{

	public static final String NOME_TABELA 			  = "configuracoes";
	public static final String COLUNA_ID 			  = "id";
	public static final String COLUNA_LEMBRETE_ATIVO  = "lembrete";
	
	public static final String CREATE_TABLE = "CREATE TABLE " + 
				NOME_TABELA			   + " (" +
				COLUNA_ID 			   + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				COLUNA_LEMBRETE_ATIVO  + " INT)";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NOME_TABELA;
	
	private static DAOConfiguracoes instancia;

	private DAOConfiguracoes(Context context){
		
		super(context);
		
	}
	
	public static DAOConfiguracoes getInstancia(Context context){
		
		if(instancia == null){
			
			instancia = new DAOConfiguracoes(context);
			
		}
		
		return instancia;
		
	}
	
	
	@Override
	public String getNomeColunaPrimaryKey() {
		
		return COLUNA_ID;
		
	}

	@Override
	public String getNomeTabela() {
		
		return NOME_TABELA;
		
	}

	@Override
	public ContentValues entidadeParaContentValues(Configuracoes entidade) {
		
		ContentValues cv = new ContentValues();
		
		if(entidade.getId() > 0){
			
			cv.put(COLUNA_ID, entidade.getId());
			
		}
	
		
		cv.put(COLUNA_LEMBRETE_ATIVO, entidade.getLembreteAtivo());
		return cv;
		
	}

	@Override
	public Configuracoes contentValuesParaEntidade(ContentValues contentValues) {
		
		Configuracoes config = new Configuracoes();
		
		config.setId(contentValues.getAsInteger(COLUNA_ID));
		config.setLembreteAtivo(contentValues.getAsInteger(COLUNA_LEMBRETE_ATIVO));
		
		return config;		
	}

	@Override
	public int getTamanho() {
		
		return recuperarPorQuery("SELECT * FROM " + NOME_TABELA).size();
		
	}
	
}
