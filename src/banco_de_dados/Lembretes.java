package banco_de_dados;

public class Lembretes implements EntidadePersistivel {

	private int id;
	private long millis;
	private String tipo, motivo, data, observacao;
	private double odometro;

	public Lembretes() {
	}

	public Lembretes(long millis, String tipo, String motivo, String data,
			String observacao, double odometro) {
		this.millis = millis;
		this.tipo = tipo;
		this.motivo = motivo;
		this.data = data;
		this.observacao = observacao;
		this.odometro = odometro;
	}

	public Lembretes(int id, long millis, String tipo, String motivo,
			String data, String observacao, double odometro) {
		this.id = id;
		this.millis = millis;
		this.tipo = tipo;
		this.motivo = motivo;
		this.data = data;
		this.observacao = observacao;
		this.odometro = odometro;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
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

	public double getOdometro() {
		return odometro;
	}

	public void setOdometro(double odometro) {
		this.odometro = odometro;
	}

}
