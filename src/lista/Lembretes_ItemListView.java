package lista;

public class Lembretes_ItemListView {

	private int id;
	private String tipo, motivo, data, observacao;
	private double odometro;

	public Lembretes_ItemListView() {
	}

	public Lembretes_ItemListView(String tipo, String motivo, String data,
			String observacao, double odometro) {
		this.tipo = tipo;
		this.motivo = motivo;
		this.data = data;
		this.observacao = observacao;
		this.odometro = odometro;
	}

	public Lembretes_ItemListView(int id, String tipo, String motivo, String data,
			String observacao, double odometro) {
		this.id = id;
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
