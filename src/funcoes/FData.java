package funcoes;

import java.util.Calendar;

public class FData {
	
	public static long getDataInMillis(String data){
		
		Calendar calendario = Calendar.getInstance();
		calendario.set(conv(getAno(data)), conv(getMes(data)), conv(getDia(data)));
		return calendario.getTimeInMillis();
	}
	
	public static String getDataSQLFormat(String data){
		
		return getAno(data) + "-" + getMes(data) + "-" + getDia(data);
		
	}
	
	public static int conv(String num){
		return Integer.valueOf(num);
	}
	
	public static String getAno(String data){
		
		String ano = "";
		int flag = 0;
		
		for(int x = 0; x < data.length(); x++){
			
			if(String.valueOf(data.charAt(x)).equals("/")){
				flag++;
				if(flag == 2){
					for(int i = x + 1; i < data.length(); i++){
						ano += data.charAt(i);
					}
					break;
				}
			}
			
		}
		
		return ano;
		
	}
	
	public static String getMes(String data){
		
		String mes = "";
		
		for(int x = 0; x < data.length(); x++){
			
			if(String.valueOf(data.charAt(x)).equals("/")){
				for(int i = x + 1; i < data.length(); i++){
					
					if(String.valueOf(data.charAt(i)).equals("/")){
						break;
					}else{
						mes += data.charAt(i);
					}
					
				}
				
				break;
			}
			
		}
		
		return mes;
			
	}

	public static String getDia(String data){
		
		String dia = "";
		
		for(int x = 0; x < data.length(); x++){
			
			if(String.valueOf(data.charAt(x)).equals("/")){
				
				break;
				
			}
			
			dia += data.charAt(x);
			
		}
		
		return dia;
		
	}

}
