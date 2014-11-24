package banco_de_dados;

import android.content.ContentValues;
import android.content.Context;

public class DAOAbastecimento extends DAOBase<Abastecimento>{

	public static final String NOME_TABELA 			  = "abastecimento";
	public static final String COLUNA_ID 			  = "id";
	public static final String COLUNA_MOTIVO 		  = "motivo";
	public static final String COLUNA_ODOMETRO		  = "odometro";
	public static final String COLUNA_POSTO			  = "posto";
	public static final String COLUNA_TIPOCOMBUSTIVEL = "tipocombustivel";
	public static final String COLUNA_DATA			  = "data";
	public static final String COLUNA_MILLIS		  = "millis";
	public static final String COLUNA_VALORTOTAL 	  = "valortotal";
	public static final String COLUNA_LITROS 		  = "litros";
	public static final String COLUNA_OBSERVACAO      = "observacao";
	
	public static final String CREATE_TABLE = "CREATE TABLE " + 
				NOME_TABELA			   + " (" +
				COLUNA_ID 			   + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				COLUNA_MOTIVO 		   + " TEXT," +
				COLUNA_ODOMETRO		   + " DOUBLE," + 
				COLUNA_POSTO           + " TEXT," +
				COLUNA_TIPOCOMBUSTIVEL + " TEXT," +
				COLUNA_DATA            + " TEXT," +
				COLUNA_MILLIS		   + " UNSIGNED BIG INT," +
				COLUNA_VALORTOTAL      + " DOUBLE," +
				COLUNA_LITROS          + " DOUBLE," +
				COLUNA_OBSERVACAO      + " TEXT)";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NOME_TABELA;
	
	private static DAOAbastecimento instancia;

	private DAOAbastecimento(Context context){
		
		super(context);
		
	}
	
	public static DAOAbastecimento getInstancia(Context context){
		
		if(instancia == null){
			
			instancia = new DAOAbastecimento(context);
			
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
	public ContentValues entidadeParaContentValues(Abastecimento entidade) {
		
		ContentValues cv = new ContentValues();
		
		if(entidade.getId() > 0){
			
			cv.put(COLUNA_ID, entidade.getId());
			
		}
		
		cv.put(COLUNA_MOTIVO, entidade.getMotivo());
		cv.put(COLUNA_ODOMETRO, entidade.getOdometro());
		cv.put(COLUNA_POSTO, entidade.getPosto());
		cv.put(COLUNA_TIPOCOMBUSTIVEL, entidade.getTipoCombustivel());
		cv.put(COLUNA_DATA, entidade.getData());
		cv.put(COLUNA_MILLIS, entidade.getMillis());
		cv.put(COLUNA_VALORTOTAL, entidade.getValorTotal());
		cv.put(COLUNA_LITROS, entidade.getLitros());
		cv.put(COLUNA_OBSERVACAO, entidade.getObservacao());
		return cv;
		
	}

	@Override
	public Abastecimento contentValuesParaEntidade(ContentValues contentValues) {
		
		Abastecimento abastecimento = new Abastecimento();
		
		abastecimento.setId(contentValues.getAsInteger(COLUNA_ID));
		abastecimento.setMotivo(contentValues.getAsString(COLUNA_MOTIVO));
		abastecimento.setOdometro(contentValues.getAsDouble(COLUNA_ODOMETRO));
		abastecimento.setPosto(contentValues.getAsString(COLUNA_POSTO));
		abastecimento.setTipoCombustivel(contentValues.getAsString(COLUNA_TIPOCOMBUSTIVEL));
		abastecimento.setData(contentValues.getAsString(COLUNA_DATA));
		abastecimento.setMillis(contentValues.getAsLong(COLUNA_MILLIS));
		abastecimento.setValorTotal(contentValues.getAsDouble(COLUNA_VALORTOTAL));
		abastecimento.setLitros(contentValues.getAsDouble(COLUNA_LITROS));
		abastecimento.setObservacao(contentValues.getAsString(COLUNA_OBSERVACAO));
		
		return abastecimento;		
	}

	@Override
	public int getTamanho() {
		
		return recuperarPorQuery("SELECT * FROM " + NOME_TABELA).size();
		
	}
	
}
