package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import dao.IDInterface;

@Entity
public class Avaliacao implements IDInterface  {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Producao produto;
	private int notaSabor;
	private int notaAparencia;
	private String justificativa;
	private Funcionario avaliador;
	
	public Avaliacao () {
		
	}
	
	public Avaliacao(Producao produto, int notaSabor, int notaAparencia, String justificativa,
			Funcionario avaliador) {
		super();
		this.produto = produto;
		this.notaSabor = notaSabor;
		this.notaAparencia = notaAparencia;
		if (justificativa != null) 
			this.justificativa = justificativa.toUpperCase();
		this.avaliador = avaliador;
	}

	public int getIdavaliacao() {
		return id;
	}

	public void setIdavaliacao(int idavaliacao) {
		this.id = idavaliacao;
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
		this.justificativa = justificativa.toUpperCase();
	}

	public Funcionario getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Funcionario avaliador) {
		this.avaliador = avaliador;
	}

	@Override
	public String toString() {
		return "Avaliacao [idavaliacao=" + id + ", notaSabor=" + notaSabor + ", notaAparencia=" + notaAparencia
				+ ", justificativa=" + justificativa + "]";
	}


	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
		
	}
	
	
	
}
