package menu_lateral;

public class DrawerItem {

	private String tituloFuncao;
	private int iconeFuncao;
	private String nomeSessao;
	private boolean isSpinner;
	private String contador = "0";
	private boolean isContadorVisivel = false; 

	public DrawerItem(boolean isSpinner) {
		this(null, 0);
		this.isSpinner = isSpinner;
	}
	
	public DrawerItem(String nomeSessao) {
		this(null, 0);
		this.nomeSessao = nomeSessao;
	}
	
	public DrawerItem(String tituloFuncao, int iconeFuncao) {
		super();
		this.tituloFuncao = tituloFuncao;
		this.iconeFuncao = iconeFuncao;
	}	
	
	public DrawerItem(String tituloFuncao, int iconeFuncao, boolean isContadorVisivel, String contador){
		
		this.tituloFuncao = tituloFuncao;
		this.iconeFuncao = iconeFuncao;
		this.isContadorVisivel = isContadorVisivel;
		this.contador = contador;
	
	}

	public String getNomeSessao() {
		return nomeSessao;
	}

	public void setNomeSessao(String nomeSessao) {
		this.nomeSessao = nomeSessao;
	}

	public boolean isSpinner() {
		return isSpinner;
	}

	public String getTituloFuncao() {
		return tituloFuncao;
	}

	public void setTituloFuncao(String tituloFuncao) {
		this.tituloFuncao = tituloFuncao;
	}

	public void setSpinner(boolean isSpinner) {
		this.isSpinner = isSpinner;
	}

	public int getIconeFuncao() {
		return iconeFuncao;
	}

	public void setIconeFuncao(int iconeFuncao) {
		this.iconeFuncao = iconeFuncao;
	}


	public String getContador() {
		return contador;
	}
	

	public void setContador(String contador) {
		this.contador = contador;
	}
	

	public boolean isContadorVisivel() {
		return isContadorVisivel;
	}
	

	public void setContadorVisivel(boolean isContadorVisivel) {
		this.isContadorVisivel = isContadorVisivel;
	}	
	
}