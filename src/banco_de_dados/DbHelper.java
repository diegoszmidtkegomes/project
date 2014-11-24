package banco_de_dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

	private static final String NOME_BASE   = "FullServiceCar";
	private static final int    VERSAO_BASE = 1;
	
	private static DbHelper instancia;
	
	private DbHelper(Context context) {
		
		super(context, NOME_BASE, null, VERSAO_BASE);
	}
	
	
	public static DbHelper getInstancia(Context context){
		
		if(instancia == null){
			
			instancia = new DbHelper(context);
			
		}
		
		return instancia;
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(DAOVeiculo.CREATE_TABLE);
		db.execSQL(DAOMotorista.CREATE_TABLE);
		db.execSQL(DAOAbastecimento.CREATE_TABLE);
		db.execSQL(DAODespesas.CREATE_TABLE);
		db.execSQL(DAOServicos.CREATE_TABLE);
		db.execSQL(DAOLembretes.CREATE_TABLE);
		db.execSQL(DAOConfiguracoes.CREATE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL(DAOVeiculo.DROP_TABLE);
		db.execSQL(DAOMotorista.DROP_TABLE);
		db.execSQL(DAOAbastecimento.DROP_TABLE);
		db.execSQL(DAODespesas.DROP_TABLE);
		db.execSQL(DAOServicos.DROP_TABLE);
		db.execSQL(DAOLembretes.DROP_TABLE);
		db.execSQL(DAOConfiguracoes.DROP_TABLE);
		onCreate(db);
		
	}
	
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL(DAOVeiculo.DROP_TABLE);
		db.execSQL(DAOMotorista.DROP_TABLE);
		db.execSQL(DAOAbastecimento.DROP_TABLE);
		db.execSQL(DAODespesas.DROP_TABLE);
		db.execSQL(DAOServicos.DROP_TABLE);
		db.execSQL(DAOLembretes.DROP_TABLE);
		db.execSQL(DAOConfiguracoes.DROP_TABLE);
		
		onCreate(db);
		
	}

}
