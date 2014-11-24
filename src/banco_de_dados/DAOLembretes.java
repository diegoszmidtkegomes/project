package banco_de_dados;

import android.content.ContentValues;
import android.content.Context;

public class DAOLembretes extends DAOBase<Lembretes>{

	public static final String NOME_TABELA 			  = "lembretes";
	public static final String COLUNA_ID 			  = "id";
	public static final String COLUNA_TIPO		      = "tipo";
	public static final String COLUNA_MOTIVO	      = "motivo";
	public static final String COLUNA_ODOMETRO		  = "odometro";
	public static final String COLUNA_DATA			  = "data";
	public static final String COLUNA_MILLIS		  = "millis";
	public static final String COLUNA_OBSERVACAO      = "observacao";
	
	public static final String CREATE_TABLE = "CREATE TABLE " + 
				NOME_TABELA			   + " (" +
				COLUNA_ID 			   + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				COLUNA_TIPO			   + " TEXT," +
				COLUNA_MOTIVO		   + " TEXT," +
				COLUNA_ODOMETRO		   + " DOUBLE," + 
				COLUNA_DATA            + " TEXT," +
				COLUNA_MILLIS		   + " UNSIGNED BIG INT," +
				COLUNA_OBSERVACAO      + " TEXT)";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NOME_TABELA;
	
	private static DAOLembretes instancia;

	private DAOLembretes(Context context){
		
		super(context);
		
	}
	
	public static DAOLembretes getInstancia(Context context){
		
		if(instancia == null){
			
			instancia = new DAOLembretes(context);
			
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
	public ContentValues entidadeParaContentValues(Lembretes entidade) {
		
		ContentValues cv = new ContentValues();
		
		if(entidade.getId() > 0){
			
			cv.put(COLUNA_ID, entidade.getId());
			
		}
	
		
		cv.put(COLUNA_TIPO, entidade.getTipo());
		cv.put(COLUNA_MOTIVO, entidade.getMotivo());
		cv.put(COLUNA_ODOMETRO, entidade.getOdometro());
		cv.put(COLUNA_DATA, entidade.getData());
		cv.put(COLUNA_MILLIS, entidade.getMillis());
		cv.put(COLUNA_OBSERVACAO, entidade.getObservacao());
		return cv;
		
	}

	@Override
	public Lembretes contentValuesParaEntidade(ContentValues contentValues) {
		
		Lembretes lembretes = new Lembretes();
		
		lembretes.setId(contentValues.getAsInteger(COLUNA_ID));
		lembretes.setTipo(contentValues.getAsString(COLUNA_TIPO));
		lembretes.setMotivo(contentValues.getAsString(COLUNA_MOTIVO));
		lembretes.setOdometro(contentValues.getAsDouble(COLUNA_ODOMETRO));
		lembretes.setData(contentValues.getAsString(COLUNA_DATA));
		lembretes.setMillis(contentValues.getAsLong(COLUNA_MILLIS));
		lembretes.setObservacao(contentValues.getAsString(COLUNA_OBSERVACAO));
		
		return lembretes;		
	}

	@Override
	public int getTamanho() {
		
		return recuperarPorQuery("SELECT * FROM " + NOME_TABELA).size();
		
	}
	
}
