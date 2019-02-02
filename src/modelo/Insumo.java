package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import dao.IDInterface;

@Entity
public class Insumo implements IDInterface {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private boolean lactose;
	private boolean gluten;
	@ManyToMany (cascade=CascadeType.ALL)
	private List<Prato> pratos = new ArrayList<Prato>();
	

	public Insumo () {
		
	}
	
	public Insumo(String nome, boolean lactose, boolean gluten) {
		super();
		this.nome = nome.toUpperCase();
		this.lactose = lactose;
		this.gluten = gluten;
		this.pratos = new ArrayList <Prato>();
	}

	public int getId() {
		return id;
	}

	public void setId(int codinsumo) {
		this.id = codinsumo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
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
	
	public List<Prato> getPratos() {
		return this.pratos;
	}

	public void setPratos(List<Prato> pratos) {
		this.pratos = pratos;
	}

	@Override
	public String toString() {
		return nome;
	}
	
}
