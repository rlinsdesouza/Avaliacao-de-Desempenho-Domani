package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import dao.IDInterface;

@Entity
public class Prato implements IDInterface {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String receita;
	private int dificuldade;
	private int tempoProduzir;
	private boolean lactose;
	private boolean gluten;
	@ManyToMany(mappedBy="pratos",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Insumo> insumos = new ArrayList<Insumo>();
	@OneToMany(mappedBy="prato",cascade=CascadeType.ALL)
	private List<Producao> producoes = new ArrayList<Producao>();
	
	public Prato(String nome, String receita, int dificuldade, int tempoProduzir, boolean lactose,
			boolean gluten, List<Insumo> insumos) {
		super();
		this.nome = nome.toUpperCase();
		this.receita = receita;
		this.dificuldade = dificuldade;
		this.tempoProduzir = tempoProduzir;
		this.lactose = lactose;
		this.gluten = gluten;
		if (insumos != null)
			this.insumos = insumos;
	}
	
	public Prato () {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getReceita() {
		return receita;
	}

	public void setReceita(String receita) {
		this.receita = receita;
	}

	public int getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	public int getTempoProduzir() {
		return tempoProduzir;
	}

	public void setTempoProduzir(int tempoProduzir) {
		this.tempoProduzir = tempoProduzir;
	}
	
	public boolean isLactose() {
		return lactose;
	}

	public void setLactose(boolean lactose) {
		this.lactose = lactose;
	}

	public boolean isGluten() {
		return gluten;
	}

	public void setGluten(boolean gluten) {
		this.gluten = gluten;
	}


	public List<Insumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}

	@Override
	public String toString() {
		return "Prato [id=" + id + ", nome=" + nome + ", receita=" + receita + ", dificuldade="
				+ dificuldade + ", tempoProduzir=" + tempoProduzir + ", lactose=" + lactose + ", gl�ten=" + gluten
				+ ", insumos=" + insumos + "]";
	}
	
	
}
