package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dao.IDInterface;

@Entity
public class Funcionario implements IDInterface {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	private int matricula;
	private String nome;
	private String cpf;
	private List<Integer> telefone = new ArrayList<Integer>();
	private String email;
	private String senha;
	private String salt;
	private Date dataAdmissao;
	private Date dataDemissao;
	@OneToMany(mappedBy="cozinheiro")
	private List<Producao> producoes;
	@OneToMany(mappedBy="avaliador")
	private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
	
	public Funcionario () {
		
	}
		
	public Funcionario(int matricula, String nome, String cpf, List<Integer> telefone, String email, String senha, String salt,
			Date dataAdmissao, Date dataDemissao, List<Producao> producoes) {
		super();
		this.matricula = matricula;
		this.nome = nome.toUpperCase();
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.salt = salt;
		this.dataAdmissao = dataAdmissao;
		this.dataDemissao = dataDemissao;
		this.producoes = new ArrayList<Producao>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		this.nome = nome.toUpperCase();
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
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
		this.email = email.toLowerCase();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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
				+ dataDemissao + "]";
	}
	

}
