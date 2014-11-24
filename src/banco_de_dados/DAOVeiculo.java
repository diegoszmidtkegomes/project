package banco_de_dados;

import android.content.ContentValues;
import android.content.Context;

public class DAOVeiculo extends DAOBase<Veiculo>{

	public static final String NOME_TABELA 			 = "veiculo";
	public static final String COLUNA_ID 			 = "id";
	public static final String COLUNA_NOME 			 = "nome";
	public static final String COLUNA_MARCA			 = "marca";
	public static final String COLUNA_TIPO			 = "tipo";
	public static final String COLUNA_PLACA 		 = "placa";
	public static final String COLUNA_ANO 			 = "ano";
	public static final String COLUNA_VOLDOTANQUE 	 = "volumedotanque";
	public static final String COLUNA_ODOMETRO 		 = "odometro";
	public static final String COLUNA_RENAVAM 		 = "renavam";
	public static final String COLUNA_CHASSI 		 = "chassi";
	public static final String COLUNA_MODELO 		 = "modelo";
	public static final String COLUNA_NDEPORTAS 	 = "numerodeportas";
	public static final String COLUNA_POTENCIA 		 = "potencia";
	public static final String COLUNA_TIPODEPOTENCIA = "tipodepotencia";
	public static final String COLUNA_AIRBAGS 		 = "airbags";
	
	public static final String CREATE_TABLE = "CREATE TABLE " + 
			NOME_TABELA			  + " (" +
			COLUNA_ID 			  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			COLUNA_NOME 		  + " TEXT," +
			COLUNA_MARCA 		  + " TEXT," +
			COLUNA_TIPO           + " TEXT," +
			COLUNA_PLACA          + " TEXT," + 
			COLUNA_ANO            + " INTEGER," +
			COLUNA_VOLDOTANQUE 	  + " DOUBLE," +
			COLUNA_ODOMETRO       + " DOUBLE," + 
			COLUNA_RENAVAM 		  + " TEXT," +
			COLUNA_CHASSI 		  + " TEXT," +
			COLUNA_MODELO 		  + " TEXT," + 
			COLUNA_NDEPORTAS 	  + " INTEGER," +
			COLUNA_POTENCIA 	  + " DOUBLE," +
			COLUNA_TIPODEPOTENCIA + " TEXT," + 
			COLUNA_AIRBAGS 	      + " INTEGER)" ;
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NOME_TABELA;
	
	private static DAOVeiculo instancia;
	
	private DAOVeiculo(Context context){
		
		super(context);
		
	}
	
	public static DAOVeiculo getInstancia(Context context){
		
		if(instancia == null){
			
			instancia = new DAOVeiculo(context);
			
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
	public ContentValues entidadeParaContentValues(Veiculo entidade) {
		
		ContentValues cv = new ContentValues();
		
		if(entidade.getId() > 0){
			
			cv.put(COLUNA_ID, entidade.getId());
			
		}
		
		cv.put(COLUNA_NOME			, entidade.getNome());
		cv.put(COLUNA_MARCA		, entidade.getMarca());
		cv.put(COLUNA_TIPO			, entidade.getTipo());
		cv.put(COLUNA_PLACA			, entidade.getPlaca());
		cv.put(COLUNA_ANO			, entidade.getAno());
		cv.put(COLUNA_VOLDOTANQUE	, entidade.getVolDoTanque());
		cv.put(COLUNA_ODOMETRO		, entidade.getOdometroAtual());
		cv.put(COLUNA_RENAVAM		, entidade.getRENAVAM());
		cv.put(COLUNA_CHASSI		, entidade.getChassi());
		cv.put(COLUNA_MODELO		, entidade.getModelo());
		cv.put(COLUNA_NDEPORTAS		, entidade.getnDePortas());
		cv.put(COLUNA_POTENCIA		, entidade.getPotencia());
		cv.put(COLUNA_TIPODEPOTENCIA, entidade.getTipoPotencia());
		cv.put(COLUNA_AIRBAGS		, entidade.getAirbags());
		return cv;
		
	}

	@Override
	public Veiculo contentValuesParaEntidade(ContentValues contentValues) {
		
		Veiculo veiculo = new Veiculo();
		
		veiculo.setId((contentValues.getAsInteger(COLUNA_ID)));
		veiculo.setNome(contentValues.getAsString(COLUNA_NOME));
		veiculo.setMarca((contentValues.getAsString(COLUNA_MARCA)));
		veiculo.setTipo((contentValues.getAsString(COLUNA_TIPO)));
		veiculo.setPlaca((contentValues.getAsString(COLUNA_PLACA)));
		veiculo.setAno((contentValues.getAsInteger(COLUNA_ANO)));
		veiculo.setVolDoTanque((contentValues.getAsDouble(COLUNA_VOLDOTANQUE)));
		veiculo.setOdometroAtual((contentValues.getAsDouble(COLUNA_ODOMETRO)));
		veiculo.setRENAVAM((contentValues.getAsString(COLUNA_RENAVAM)));
		veiculo.setChassi((contentValues.getAsString(COLUNA_CHASSI)));
		veiculo.setModelo((contentValues.getAsString(COLUNA_MODELO)));
		veiculo.setnDePortas((contentValues.getAsInteger(COLUNA_NDEPORTAS)));
		veiculo.setPotencia((contentValues.getAsDouble(COLUNA_POTENCIA)));
		veiculo.setTipoPotencia((contentValues.getAsString(COLUNA_TIPODEPOTENCIA)));
		veiculo.setAirbags((contentValues.getAsInteger(COLUNA_AIRBAGS)));
		
		return veiculo;		
	}
	
	@Override
	public int getTamanho() {
		
		return recuperarPorQuery("SELECT * FROM " + NOME_TABELA).size();
		
	}
	
}
