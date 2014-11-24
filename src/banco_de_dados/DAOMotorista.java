package banco_de_dados;

import android.content.ContentValues;
import android.content.Context;

public class DAOMotorista extends DAOBase<Motorista>{

	public static final String NOME_TABELA 			 = "motorista";
	public static final String COLUNA_ID 			 = "id";
	public static final String COLUNA_NOME 			 = "nome";
	public static final String COLUNA_EMAIL			 = "email";
	public static final String COLUNA_CNH 			 = "cnh";
	public static final String COLUNA_DATANASCIMENTO = "datanascimento";
	public static final String COLUNA_FOTO			 = "foto";
	
	public static final String CREATE_TABLE = "CREATE TABLE " + 
				NOME_TABELA			  + " (" +
				COLUNA_ID 			  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				COLUNA_NOME 		  + " TEXT," +
				COLUNA_EMAIL          + " TEXT," +
				COLUNA_CNH            + " TEXT," +
				COLUNA_DATANASCIMENTO + " DATE," + 
				COLUNA_FOTO 		  + " BLOB)";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NOME_TABELA;
	
	private static DAOMotorista instancia;

	private DAOMotorista(Context context){
		
		super(context);
		
	}
	
	public static DAOMotorista getInstancia(Context context){
		
		if(instancia == null){
			
			instancia = new DAOMotorista(context);
			
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
	public ContentValues entidadeParaContentValues(Motorista entidade) {
		
		ContentValues cv = new ContentValues();
		
		if(entidade.getId() > 0){
			
			cv.put(COLUNA_ID, entidade.getId());
			
		}
		
		cv.put(COLUNA_NOME, entidade.getNome());
		cv.put(COLUNA_EMAIL, entidade.getEmail());
		cv.put(COLUNA_CNH, entidade.getCnh());
		cv.put(COLUNA_DATANASCIMENTO, entidade.getDataNascimento());
		cv.put(COLUNA_FOTO, entidade.getFoto());
		
		return cv;
		
	}

	@Override
	public Motorista contentValuesParaEntidade(ContentValues contentValues) {
		
		Motorista motorista = new Motorista();
		
		motorista.setId(contentValues.getAsInteger(COLUNA_ID));
		motorista.setNome(contentValues.getAsString(COLUNA_NOME));
		motorista.setEmail(contentValues.getAsString(COLUNA_EMAIL));
		motorista.setCnh(contentValues.getAsString(COLUNA_CNH));
		motorista.setDataNascimento(contentValues.getAsString(COLUNA_DATANASCIMENTO));
		motorista.setFoto(contentValues.getAsByteArray(COLUNA_FOTO));
		return motorista;		
	}

	@Override
	public int getTamanho() {
		
		return recuperarPorQuery("SELECT * FROM " + NOME_TABELA).size();
		
	}

	
}
