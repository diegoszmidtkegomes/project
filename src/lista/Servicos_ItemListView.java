package lista;

public class Servicos_ItemListView {

	private int id;
	private String tipoDeServico, localDoServico, data;
	private double km, valor;

	public Servicos_ItemListView() {

	}
	
	public Servicos_ItemListView(String tipoDeServico,
			String localDoServico, String data, double km, double valor) {
		this.tipoDeServico = tipoDeServico;
		this.localDoServico = localDoServico;
		this.data = data;
		this.km = km;
		this.valor = valor;
	}


	public Servicos_ItemListView(int id, String tipoDeServico,
			String localDoServico, String data, double km, double valor) {
		this.id = id;
		this.tipoDeServico = tipoDeServico;
		this.localDoServico = localDoServico;
		this.data = data;
		this.km = km;
		this.valor = valor;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoDeServico() {
		return tipoDeServico;
	}

	public void setTipoDeServico(String tipoDeServico) {
		this.tipoDeServico = tipoDeServico;
	}

	public String getLocalDoServico() {
		return localDoServico;
	}

	public void setLocalDoServico(String localDoServico) {
		this.localDoServico = localDoServico;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	

}
