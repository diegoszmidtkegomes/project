package banco_de_dados;

public class Motorista implements EntidadePersistivel {

	private int id;
	private String nome, email, cnh, dataNascimento;
	private byte[] foto;

	public Motorista() {

	}

	public Motorista(String nome, String email, String cnh,
			String dataNascimento, byte[] foto) {
		this.nome = nome;
		this.email = email;
		this.cnh = cnh;
		this.dataNascimento = dataNascimento;
		this.foto = foto;
	}

	public Motorista(int id, String nome, String email, String cnh,
			String dataNascimento, byte[] foto) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cnh = cnh;
		this.dataNascimento = dataNascimento;
		this.foto = foto;
	}



	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	public String getNome() {

		return nome;

	}

	public void setNome(String nome) {

		this.nome = nome;

	}

	public String getCnh() {

		return cnh;

	}

	public void setCnh(String cnh) {

		this.cnh = cnh;

	}

	public String getEmail() {

		return email;

	}

	public void setEmail(String email) {

		this.email = email;

	}

	public String getDataNascimento() {

		return dataNascimento;

	}

	public void setDataNascimento(String dataNascimento) {

		this.dataNascimento = dataNascimento;

	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

}
