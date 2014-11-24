package banco_de_dados;

import android.content.ContentValues;
import android.content.Context;

public class DAODespesas extends DAOBase<Despesas>{

	public static final String NOME_TABELA 			  = "despesas";
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
	
	private static DAODespesas instancia;

	private DAODespesas(Context context){
		
		super(context);
		
	}
	
	public static DAODespesas getInstancia(Context context){
		
		if(instancia == null){
			
			instancia = new DAODespesas(context);
			
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
	public ContentValues entidadeParaContentValues(Despesas entidade) {
		
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
	public Despesas contentValuesParaEntidade(ContentValues contentValues) {
		
		Despesas despesas = new Despesas();
		
		despesas.setId(contentValues.getAsInteger(COLUNA_ID));
		despesas.setTipo(contentValues.getAsString(COLUNA_TIPODESPESA));
		despesas.setOdometro(contentValues.getAsDouble(COLUNA_ODOMETRO));
		despesas.setValorTotal(contentValues.getAsDouble(COLUNA_VALORTOTAL));
		despesas.setLocal(contentValues.getAsString(COLUNA_LOCALDESPESA));
		despesas.setData(contentValues.getAsString(COLUNA_DATA));
		despesas.setMillis(contentValues.getAsLong(COLUNA_MILLIS));
		despesas.setObservacao(contentValues.getAsString(COLUNA_OBSERVACAO));
		
		return despesas;		
	}

	@Override
	public int getTamanho() {
		
		return recuperarPorQuery("SELECT * FROM " + NOME_TABELA).size();
		
	}
	
}
