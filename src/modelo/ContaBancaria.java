package modelo;

public class ContaBancaria {
	private int banco;
	private String agencia;
	private String operacao;
	private String conta;
	
	public ContaBancaria(int banco, String agencia, String operacao, String conta) {
		super();
		this.banco = banco;
		this.agencia = agencia;
		this.operacao = operacao;
		this.conta = conta;
	}

	public int getBanco() {
		return banco;
	}

	public void setBanco(int banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "ContaBancaria [banco=" + banco + ", agencia=" + agencia + ", operacao=" + operacao + ", conta=" + conta
				+ "]";
	}	

}
