package banco_de_dados;

public class Configuracoes implements EntidadePersistivel {

	private int id, lembreteAtivo = 1;

	public Configuracoes() {
	}

	public Configuracoes(int lembreteAtivo) {
		this.lembreteAtivo = lembreteAtivo;
	}

	public Configuracoes(int id, int lembreteAtivo) {
		this.id = id;
		this.lembreteAtivo = lembreteAtivo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLembreteAtivo() {
		return lembreteAtivo;
	}

	public void setLembreteAtivo(int lembreteAtivo) {
		this.lembreteAtivo = lembreteAtivo;
	}

}
