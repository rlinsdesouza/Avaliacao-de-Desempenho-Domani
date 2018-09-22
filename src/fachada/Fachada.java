package fachada;

import java.util.List;

import aplicacao.CSVReader;
import dao.DAO;
import dao.DAOFuncionario;
import dao.DAOInsumo;
import dao.DAOPrato;
import modelo.Funcionario;
import modelo.Insumo;
import modelo.Prato;

public class Fachada {
	private static DAOInsumo daoinsumo = new DAOInsumo();
	private static DAOFuncionario daofuncionario = new DAOFuncionario() ;
	private static DAOPrato daoprato = new DAOPrato ();
	
	
	public static void inicializar () {
		DAO.open();
	}
	
	public static void finalizar () {
		DAO.close();
	}
		
	public static void cadastrar(){
		Funcionario p1;
		System.out.println("cadastrando...");
		p1 = new Funcionario(1,"Rafael Lins");
		daofuncionario.create(p1);
	

	
		List<Prato> pratos = CSVReader.read("pratosbd.csv");
		for (Prato prato : pratos) {
			daoprato.create(prato);	
		}
		System.out.println("pre-cadastro realizado com sucesso!");
	}	

	public static Prato cadastrarPrato (String nome, String receita, int dificuldade, int tempoProduzir, boolean lactose,
			boolean glutem, List<Insumo> insumos) throws Exception {
		
		int key = daoprato.getKey();
		DAO.begin();			
		Prato i = daoprato.read(key);
		if(i != null) {
			throw new Exception("produto ja cadastrado:" + nome);
		}
		i = new Prato(key, nome,receita,dificuldade,tempoProduzir,lactose,glutem,insumos);
		daoprato.create(i);		
		DAO.commit();
		return i;
	}
	
	
	public static Insumo cadastrarInsumo (int codinsumo, String nome, boolean lactose, boolean glutem) throws Exception {
		
		DAO.begin();			
		Insumo i = daoinsumo.read(codinsumo);
		if(i != null) {
			throw new Exception("produto ja cadastrado:" + nome);
		}

		i = new Insumo(codinsumo, nome, lactose, glutem);
		daoinsumo.create(i);		
		DAO.commit();
		return i;
	}
	
	
	public static List <Funcionario> listarFuncionarios () {
		return daofuncionario.readAll();
	}
	
	public static List <Prato> listarPratos () {
		return daoprato.readAll();
	}
	
	public static List <Prato> listarPratos (String nome) {
		return daoprato.readAll(nome);
	}

	public static List<Insumo> listarInsumo() {
		return daoinsumo.readAll();
	}
	
	public static List<Insumo> listarInsumo(String nome) {
		return daoinsumo.readAll(nome);
	}

	public static Prato localizarPrato(int id) {
		return daoprato.read(id);
	}

	public static Prato atualizarPrato(int id,String nome,String receita,int dificuldade,int tempo,boolean lactose,boolean glutem,List<Insumo> insumos) {
		Prato p = daoprato.read(id);
		p.setNome(nome);
		p.setReceita(receita);
		p.setDificuldade(dificuldade);
		p.setTempoProduzir(tempo);
		p.setLactose(lactose);
		p.setGlutem(glutem);
		p.setInsumos(insumos);
		daoprato.refresh(p);
		return p;
	}

}
