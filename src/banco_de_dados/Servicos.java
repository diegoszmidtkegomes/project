package banco_de_dados;

public class Servicos implements EntidadePersistivel {

	private int id;
	private long millis;
	private double odometro, valorTotal;
	private String tipo, local, data, observacao;

	public Servicos() {
	}

	public Servicos(long millis, double odometro, double valorTotal,
			String tipo, String local, String data, String observacao) {
		this.millis = millis;
		this.odometro = odometro;
		this.valorTotal = valorTotal;
		this.tipo = tipo;
		this.local = local;
		this.data = data;
		this.observacao = observacao;
	}

	public Servicos(int id, long millis, double odometro, double valorTotal,
			String tipo, String local, String data, String observacao) {
		this.id = id;
		this.millis = millis;
		this.odometro = odometro;
		this.valorTotal = valorTotal;
		this.tipo = tipo;
		this.local = local;
		this.data = data;
		this.observacao = observacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getMillis() {
		return millis;
	}

	public void setMillis(long millis) {
		this.millis = millis;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
