package menu_lateral;

public class SpinnerItem {

	public int fotoUsuario;
	public String nomeUsuario;
	public String emailUsuario;

	public SpinnerItem(int fotoUsuario, String nomeUsuario, String emailUsuario) {
		super();
		this.fotoUsuario = fotoUsuario;
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
	}

	
	public int getFotoUsuario() {
		return fotoUsuario;
	}


	public void setFotoUsuario(int fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	

}