package lista;

public class Despesas_ItemListView {

	private int id;
	private String tipoDeDespesa, data, localDaDespesa;
	private double odometro, valorTotal;

	public Despesas_ItemListView() {
	}

	public Despesas_ItemListView(int id, String tipoDeDespesa, String data,
			String localDaDespesa, double odometro, double valorTotal) {
		this.id = id;
		this.tipoDeDespesa = tipoDeDespesa;
		this.data = data;
		this.localDaDespesa = localDaDespesa;
		this.odometro = odometro;
		this.valorTotal = valorTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoDeDespesa() {
		return tipoDeDespesa;
	}

	public void setTipoDeDespesa(String tipoDeDespesa) {
		this.tipoDeDespesa = tipoDeDespesa;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLocalDaDespesa() {
		return localDaDespesa;
	}

	public void setLocalDaDespesa(String localDaDespesa) {
		this.localDaDespesa = localDaDespesa;
	}

	public double getOdometro() {
		return odometro;
	}

	public void setOdometro(double odometro) {
		this.odometro = odometro;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
