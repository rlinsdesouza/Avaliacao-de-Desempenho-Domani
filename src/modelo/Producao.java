package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Producao {
	private int id;
	private String data;
	private Prato prato;
	private Funcionario cozinheiro;
	private List<Avaliacao> avaliacoes = new ArrayList <Avaliacao> ();
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
