package funcoes;


public class FF {

	/*public static double /*sGetDecimalStringAnyLocaleAsDouble getConv (String value) {

	    Locale theLocale = Locale.getDefault();
	    NumberFormat numberFormat = DecimalFormat.getInstance(theLocale);
	    Number theNumber;
	    try {
	        theNumber = numberFormat.parse(value);
	        return theNumber.doubleValue();
	    } catch (ParseException e) {

	        String valueWithDot = value.replaceAll(",",".");
	        return Double.valueOf(valueWithDot);
	    }
	}*/
	
	public static String treta(double tretaASerConvertida){
		return String.valueOf(String.valueOf(FAbastecimento.convFormat(tretaASerConvertida, "#.###")).replace(".", ","));
	}
}
