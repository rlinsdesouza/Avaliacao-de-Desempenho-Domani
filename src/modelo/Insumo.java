package modelo;

public class Insumo {

	private int codinsumo;
	private String nome;
	private boolean lactose;
	private boolean glutem;
	
	public Insumo(int codinsumo, String nome, boolean lactose, boolean glutem) {
		super();
		this.codinsumo = codinsumo;
		this.nome = nome;
		this.lactose = lactose;
		this.glutem = glutem;
	}

	public int getCodinsumo() {
		return codinsumo;
	}

	public void setCodinsumo(int codinsumo) {
		this.codinsumo = codinsumo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isLactose() {
		return lactose;
	}

	public void setLactose(boolean lactose) {
		this.lactose = lactose;
	}

	public boolean isGlutem() {
		return glutem;
	}

	public void setGlutem(boolean glutem) {
		this.glutem = glutem;
	}

	@Override
	public String toString() {
		return "Insumo [codinsumo=" + codinsumo + ", nome=" + nome + ", lactose=" + lactose + ", glutem=" + glutem
				+ "]";
	}
	
}
