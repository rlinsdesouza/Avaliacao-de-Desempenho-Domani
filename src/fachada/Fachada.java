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

}
