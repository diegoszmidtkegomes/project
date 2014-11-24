package banco_de_dados;

public class Abastecimento implements EntidadePersistivel {

	private int id;
	private long millis;
	private double odometro, valorTotal, litros;
	private String tipoCombustivel, motivo, posto, observacao, data;

	public Abastecimento() {
	}

	public Abastecimento(long millis, double odometro, double valorTotal,
			double litros, String tipoCombustivel, String motivo, String posto,
			String observacao, String data) {
		this.millis = millis;
		this.odometro = odometro;
		this.valorTotal = valorTotal;
		this.litros = litros;
		this.tipoCombustivel = tipoCombustivel;
		this.motivo = motivo;
		this.posto = posto;
		this.observacao = observacao;
		this.data = data;
	}

	public Abastecimento(int id, long millis, double odometro,
			double valorTotal, double litros, String tipoCombustivel,
			String motivo, String posto, String observacao, String data) {
		this.id = id;
		this.millis = millis;
		this.odometro = odometro;
		this.valorTotal = valorTotal;
		this.litros = litros;
		this.tipoCombustivel = tipoCombustivel;
		this.motivo = motivo;
		this.posto = posto;
		this.observacao = observacao;
		this.data = data;
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

	public double getLitros() {
		return litros;
	}

	public void setLitros(double litros) {
		this.litros = litros;
	}

	public String getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getPosto() {
		return posto;
	}

	public void setPosto(String posto) {
		this.posto = posto;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
