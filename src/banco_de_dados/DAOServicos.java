package banco_de_dados;

import android.content.ContentValues;
import android.content.Context;

public class DAOServicos extends DAOBase<Servicos>{

	public static final String NOME_TABELA 			  = "servicos";
	public static final String COLUNA_ID 			  = "id";
	public static final String COLUNA_TIPODESPESA     = "tipodespesa";
	public static final String COLUNA_ODOMETRO		  = "odometro";
	public static final String COLUNA_VALORTOTAL	  = "valortotal";
	public static final String COLUNA_LOCALDESPESA    = "localdespesa";
	public static final String COLUNA_DATA			  = "data";
	public static final String COLUNA_MILLIS		  = "millis";
	public static final String COLUNA_OBSERVACAO      = "observacao";
	
	public static final String CREATE_TABLE = "CREATE TABLE " + 
				NOME_TABELA			   + " (" +
				COLUNA_ID 			   + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				COLUNA_TIPODESPESA 	   + " TEXT," +
				COLUNA_ODOMETRO		   + " DOUBLE," + 
				COLUNA_VALORTOTAL      + " DOUBLE," +
				COLUNA_LOCALDESPESA    + " TEXT," + 
				COLUNA_DATA            + " TEXT," +
				COLUNA_MILLIS		   + " UNSIGNED BIG INT," +
				COLUNA_OBSERVACAO      + " TEXT)";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NOME_TABELA;
	
	private static DAOServicos instancia;

	private DAOServicos(Context context){
		
		super(context);
		
	}
	
	public static DAOServicos getInstancia(Context context){
		
		if(instancia == null){
			
			instancia = new DAOServicos(context);
			
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
	public ContentValues entidadeParaContentValues(Servicos entidade) {
		
		ContentValues cv = new ContentValues();
		
		if(entidade.getId() > 0){
			
			cv.put(COLUNA_ID, entidade.getId());
			
		}
		
		cv.put(COLUNA_TIPODESPESA, entidade.getTipo());
		cv.put(COLUNA_ODOMETRO, entidade.getOdometro());
		cv.put(COLUNA_VALORTOTAL, entidade.getValorTotal());
		cv.put(COLUNA_LOCALDESPESA, entidade.getLocal());
		cv.put(COLUNA_DATA, entidade.getData());
		cv.put(COLUNA_MILLIS, entidade.getMillis());
		cv.put(COLUNA_OBSERVACAO, entidade.getObservacao());
		return cv;
		
	}

	@Override
	public Servicos contentValuesParaEntidade(ContentValues contentValues) {
		
		Servicos servicos = new Servicos();
		
		servicos.setId(contentValues.getAsInteger(COLUNA_ID));
		servicos.setTipo(contentValues.getAsString(COLUNA_TIPODESPESA));
		servicos.setOdometro(contentValues.getAsDouble(COLUNA_ODOMETRO));
		servicos.setValorTotal(contentValues.getAsDouble(COLUNA_VALORTOTAL));
		servicos.setLocal(contentValues.getAsString(COLUNA_LOCALDESPESA));
		servicos.setData(contentValues.getAsString(COLUNA_DATA));
		servicos.setMillis(contentValues.getAsLong(COLUNA_MILLIS));
		servicos.setObservacao(contentValues.getAsString(COLUNA_OBSERVACAO));
		
		return servicos;		
	}

	@Override
	public int getTamanho() {
		
		return recuperarPorQuery("SELECT * FROM " + NOME_TABELA).size();
		
	}
	
}
