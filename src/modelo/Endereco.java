package modelo;

public class Endereco {
	private int cep;
	private int numero;
	private String logradouro;
	
		
	public Endereco(int cep, int numero, String logradouro) {
		super();
		this.cep = cep;
		this.numero = numero;
		this.logradouro = logradouro.toUpperCase();
	}
	
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro.toUpperCase();
	}

	@Override
	public String toString() {
		return "Endereco [cep=" + cep + ", numero=" + numero + ", logradouro=" + logradouro + "]";
	}	
	
	
	
}
