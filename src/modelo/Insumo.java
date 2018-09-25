package modelo;

public class Insumo {

	private int id;
	private String nome;
	private boolean lactose;
	private boolean glutem;
	
	public Insumo(int codinsumo, String nome, boolean lactose, boolean glutem) {
		super();
		this.id = codinsumo;
		this.nome = nome.toUpperCase();
		this.lactose = lactose;
		this.glutem = glutem;
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

	public boolean isGlutem() {
		return glutem;
	}

	public void setGlutem(boolean glutem) {
		this.glutem = glutem;
	}

	@Override
	public String toString() {
		return nome;
	}
	
}
