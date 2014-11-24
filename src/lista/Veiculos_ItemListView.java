package lista;

public class Veiculos_ItemListView {

	private int id, ano;
	private String nome, marca, placa;
	
	
	
	public Veiculos_ItemListView() {
	}
	public Veiculos_ItemListView(int ano, String nome, String marca,
			String placa) {
		this.ano = ano;
		this.nome = nome;
		this.marca = marca;
		this.placa = placa;
	}
	public Veiculos_ItemListView(int id, int ano, String nome, String marca,
			String placa) {
		this.id = id;
		this.ano = ano;
		this.nome = nome;
		this.marca = marca;
		this.placa = placa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	

}
