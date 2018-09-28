package modelo;

public class Insumo {

	private int id;
	private String nome;
	private boolean lactose;
	private boolean gluten;
	
	public Insumo(int codinsumo, String nome, boolean lactose, boolean gluten) {
		super();
		this.id = codinsumo;
		this.nome = nome.toUpperCase();
		this.lactose = lactose;
		this.gluten = gluten;
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

	@Override
	public String toString() {
		return nome;
	}
	
}
