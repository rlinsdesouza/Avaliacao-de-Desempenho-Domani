package modelo;

public class Avaliacao {

	private int idavaliacao;
	private Producao produto;
	private int notaSabor;
	private int notaAparencia;
	private String justificativa;
	private Funcionario avaliador;
	
	public Avaliacao(int idavaliacao, Producao produto, int notaSabor, int notaAparencia, String justificativa,
			Funcionario avaliador) {
		super();
		this.idavaliacao = idavaliacao;
		this.produto = produto;
		this.notaSabor = notaSabor;
		this.notaAparencia = notaAparencia;
		this.justificativa = justificativa;
		this.avaliador = avaliador;
	}

	public int getIdavaliacao() {
		return idavaliacao;
	}

	public void setIdavaliacao(int idavaliacao) {
		this.idavaliacao = idavaliacao;
	}

	public Producao getProduto() {
		return produto;
	}

	public void setProduto(Producao produto) {
		this.produto = produto;
	}

	public int getNotaSabor() {
		return notaSabor;
	}

	public void setNotaSabor(int notaSabor) {
		this.notaSabor = notaSabor;
	}

	public int getNotaAparencia() {
		return notaAparencia;
	}

	public void setNotaAparencia(int notaAparencia) {
		this.notaAparencia = notaAparencia;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Funcionario getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Funcionario avaliador) {
		this.avaliador = avaliador;
	}

	@Override
	public String toString() {
		return "Avaliacao [idavaliacao=" + idavaliacao + ", notaSabor=" + notaSabor + ", notaAparencia=" + notaAparencia
				+ ", justificativa=" + justificativa + "]";
	}
	
	
	
}
