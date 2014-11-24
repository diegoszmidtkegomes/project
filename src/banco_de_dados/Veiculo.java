package banco_de_dados;

public class Veiculo implements EntidadePersistivel{

	int id, ano, nDePortas, airbags;
	double volDoTanque, odometroAtual, potencia;
	String nome, marca, tipo,  placa, RENAVAM, chassi, modelo, tipoPotencia;
	
	public Veiculo() {
		
	}

	public Veiculo(String nome){
		this.nome = nome;
	}
	
	public Veiculo(int ano, int nDePortas, int airbags,
			double volDoTanque, double odometroAtual, double potencia,
			String nome, String marca, String tipo, String placa,
			String rENAVAM, String chassi, String modelo, String tipoPotencia) {
		this.ano = ano;
		this.nDePortas = nDePortas;
		this.airbags = airbags;
		this.volDoTanque = volDoTanque;
		this.odometroAtual = odometroAtual;
		this.potencia = potencia;
		this.nome = nome;
		this.marca = marca;
		this.tipo = tipo;
		this.placa = placa;
		RENAVAM = rENAVAM;
		this.chassi = chassi;
		this.modelo = modelo;
		this.tipoPotencia = tipoPotencia;
	}
	
	public Veiculo(int id, int ano, int nDePortas, int airbags,
			double volDoTanque, double odometroAtual, double potencia,
			String nome, String marca, String tipo, String placa,
			String rENAVAM, String chassi, String modelo, String tipoPotencia) {
		this.id = id;
		this.ano = ano;
		this.nDePortas = nDePortas;
		this.airbags = airbags;
		this.volDoTanque = volDoTanque;
		this.odometroAtual = odometroAtual;
		this.potencia = potencia;
		this.nome = nome;
		this.marca = marca;
		this.tipo = tipo;
		this.placa = placa;
		RENAVAM = rENAVAM;
		this.chassi = chassi;
		this.modelo = modelo;
		this.tipoPotencia = tipoPotencia;
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

	public int getnDePortas() {
		return nDePortas;
	}

	public void setnDePortas(int nDePortas) {
		this.nDePortas = nDePortas;
	}

	public int getAirbags() {
		return airbags;
	}

	public void setAirbags(int airbags) {
		this.airbags = airbags;
	}

	public double getVolDoTanque() {
		return volDoTanque;
	}

	public void setVolDoTanque(double volDoTanque) {
		this.volDoTanque = volDoTanque;
	}

	public double getOdometroAtual() {
		return odometroAtual;
	}

	public void setOdometroAtual(double odometroAtual) {
		this.odometroAtual = odometroAtual;
	}

	public double getPotencia() {
		return potencia;
	}

	public void setPotencia(double potencia) {
		this.potencia = potencia;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRENAVAM() {
		return RENAVAM;
	}

	public void setRENAVAM(String rENAVAM) {
		RENAVAM = rENAVAM;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipoPotencia() {
		return tipoPotencia;
	}

	public void setTipoPotencia(String tipoPotencia) {
		this.tipoPotencia = tipoPotencia;
	}
	
	

}
