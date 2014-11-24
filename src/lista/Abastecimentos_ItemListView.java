package lista;

public class Abastecimentos_ItemListView {

	private int id;
	private String posto, combustivel, data;
	private double precoLitro, kmL, km, valorTotal, qtdeLitros;

	public Abastecimentos_ItemListView() {

	}

	public Abastecimentos_ItemListView(int id, String posto,
			String combustivel, String data, double precoLitro, double kmL,
			double km, double valorTotal, double qtdeLitros) {
		this.id = id;
		this.posto = posto;
		this.combustivel = combustivel;
		this.data = data;
		this.precoLitro = precoLitro;
		this.kmL = kmL;
		this.km = km;
		this.valorTotal = valorTotal;
		this.qtdeLitros = qtdeLitros;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosto() {
		return posto;
	}

	public void setPosto(String posto) {
		this.posto = posto;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getPrecoLitro() {
		return precoLitro;
	}

	public void setPrecoLitro(double precoLitro) {
		this.precoLitro = precoLitro;
	}

	public double getKmL() {
		return kmL;
	}

	public void setKmL(double kmL) {
		this.kmL = kmL;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getQtdeLitros() {
		return qtdeLitros;
	}

	public void setQtdeLitros(double qtdeLitros) {
		this.qtdeLitros = qtdeLitros;
	}

	
	
}
