package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Funcionario {
	
	private int matricula;
	private String nome;
	private int cpf;
	private List<Integer> telefone = new ArrayList<Integer>();
	private String email;
	private String senha;
	private Date dataAdmissao;
	private Date dataDemissao;
	private ContaBancaria conta;
	private Endereco endereco;
	private List<Producao> producoes = new ArrayList<Producao>();
	
	public Funcionario(int matricula, String nome) {
		super();
		this.matricula = matricula;
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public List<Integer> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Integer> telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public ContaBancaria getConta() {
		return conta;
	}

	public void setConta(ContaBancaria conta) {
		this.conta = conta;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Producao> getProducoes() {
		return producoes;
	}

	public void setProducoes(List<Producao> producoes) {
		this.producoes = producoes;
	}

	@Override
	public String toString() {
		return "Funcionario [matricula=" + matricula + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone
				+ ", email=" + email + ", senha=" + senha + ", dataAdmissao=" + dataAdmissao + ", dataDemissao="
				+ dataDemissao + ", conta=" + conta + ", endereco=" + endereco + "]";
	}
	

}
