package banco_de_dados;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public abstract class DAOBase <T extends EntidadePersistivel>{

	protected SQLiteDatabase db;
	
	public DAOBase(Context context){
		
		DbHelper bdHelper = DbHelper.getInstancia(context);
		db = bdHelper.getWritableDatabase();
		
	}
	
	public abstract String getNomeColunaPrimaryKey();
	public abstract String getNomeTabela();
	public abstract ContentValues entidadeParaContentValues(T entidade);
	public abstract T contentValuesParaEntidade(ContentValues contentValues);
	public abstract int getTamanho();

	public void inserir(T entidade){
		
		db.insert(getNomeTabela(), null, entidadeParaContentValues(entidade));
	
	}
	
	public void deletar(String nomeTabela, int id){
		
		db.delete(getNomeTabela(), getNomeColunaPrimaryKey() + " = " + id, null);
		
	}
	
	public void teste(T entidade){
		
		String [] valores = {String.valueOf(entidade.getId())};
		db.delete(getNomeTabela(), getNomeColunaPrimaryKey() + " = ?", valores);
		
	}
	
	public void editar(T entidade, int id) {

		db.update(getNomeTabela(), entidadeParaContentValues(entidade),
				getNomeColunaPrimaryKey() + " = " + id, null);
	}
	
	public List<T> buscarTodos(){
		
		return recuperarPorQuery("SELECT * FROM " + getNomeTabela());
		
	}
	
	public T buscarPorId(int id){
		
		List<T> resultado = recuperarPorQuery("SELECT * FROM " + getNomeTabela() + " WHERE " + getNomeColunaPrimaryKey() + " = " + id);
		
		if(resultado.size() > 0){
		
			return resultado.get(0);
			
		}
		
		return null;
		
	}
	
	public List<T> recuperarPorQuery(String query){
		
		Cursor c = db.rawQuery(query, null);
		
		List<T> listaRetorno = new ArrayList<T>();
		
		if(c.moveToFirst()){

			do{
				
				ContentValues cv = new ContentValues();
				DatabaseUtils.cursorRowToContentValues(c, cv);
				listaRetorno.add(contentValuesParaEntidade(cv));
				
			}while(c.moveToNext());
				
		}
		
		return listaRetorno;
		
	}
	
}
