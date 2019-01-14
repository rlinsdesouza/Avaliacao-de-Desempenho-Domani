package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import dao.IDInterface;

@Entity
public class Producao implements IDInterface{
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	private String data;
	@ManyToOne(cascade=CascadeType.ALL)
	private Prato prato;
	@ManyToOne(cascade=CascadeType.ALL)
	private Funcionario cozinheiro;
	@OneToMany(mappedBy="produto",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Avaliacao> avaliacoes = new ArrayList <Avaliacao> ();
	
	public Producao () {
		
	}
	
	public Producao(String data, Prato prato, Funcionario cozinheiro) {
		super();
		this.data = data;
		this.prato = prato;
		this.cozinheiro = cozinheiro;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Prato getPrato() {
		return prato;
	}
	public void setPrato(Prato prato) {
		this.prato = prato;
	}
	public Funcionario getCozinheiro() {
		return cozinheiro;
	}
	public void setCozinheiro(Funcionario cozinheiro) {
		this.cozinheiro = cozinheiro;
	}
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	@Override
	public String toString() {
		return "Producao [data=" + data + ", prato=" + prato + ", cozinheiro=" + cozinheiro + ", avaliacoes="
				+ avaliacoes + "]";
	}
	
	
	

}
