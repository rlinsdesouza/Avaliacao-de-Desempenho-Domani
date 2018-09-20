package modelo;

import java.util.ArrayList;
import java.util.List;

public class Prato {
	private int codprato;
	private String nome;
	private String receita;
	private int dificuldade;
	private int tempoProduzir;
	private List<Insumo> insumos = new ArrayList<Insumo>();
	
	public Prato(int codprato, String nome, String receita, int dificuldade, int tempoProduzir, List<Insumo> insumos) {
		super();
		this.codprato = codprato;
		this.nome = nome;
		this.receita = receita;
		this.dificuldade = dificuldade;
		this.tempoProduzir = tempoProduzir;
		this.insumos = insumos;
	}

	public int getCodprato() {
		return codprato;
	}

	public void setCodprato(int codprato) {
		this.codprato = codprato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public List<Insumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}

	@Override
	public String toString() {
		return "Prato [codprato=" + codprato + ", nome=" + nome + ", receita=" + receita + ", dificuldade="
				+ dificuldade + ", tempoProduzir=" + tempoProduzir + ", insumos=" + insumos + "]";
	}
	
	
}
