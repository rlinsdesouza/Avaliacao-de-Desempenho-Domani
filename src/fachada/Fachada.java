package fachada;

import java.util.List;

import com.db4o.query.Query;

import dao.DAO;
import dao.DAOFuncionario;
import dao.DAOInsumo;
import modelo.Funcionario;
import modelo.Insumo;

public class Fachada {
	private static DAOInsumo daoinsumo = new DAOInsumo();
	private static DAOFuncionario daofuncionario = new DAOFuncionario() ;
	
	
	public static void inicializar () {
		DAO.open();
	}
	
	public static void finalizar () {
		DAO.close();
	}
		
//	public static void cadastrar(){
//		Funcionario p1;
//		System.out.println("cadastrando...");
//		p1 = new Funcionario(1,"Rafael Lins");
////		p1.adicionarTelefone(new Telefone("88880000"));
////		p1.adicionarTelefone(new Telefone("88881111"));		
//		manager.store(p1);	
//		manager.commit();
//		
//		List<Prato> pratos = CSVReader.read("pratosbd.csv");
//		manager.store(pratos);
//		manager.commit();
//
////		p1 = new Pessoa("maria");
////		p1.adicionarTelefone(new Telefone("87882222"));
////		p1.adicionarTelefone(new Telefone("88883333"));
////		manager.store(p1);
////		manager.commit();
////
////		p1 = new Pessoa("jose");
////		p1.adicionarTelefone(new Telefone("87884444"));
////		manager.store(p1);		
////		manager.commit();
//
////		p1 = new Aluno("paulo",9);
////		manager.store(p1);		
////		manager.commit();
////
////		p1 = new Professor ("fausto", 1000.00);
////		manager.store(p1);		
////		manager.commit();
//		System.out.println("pre-cadastro realizado com sucesso!");
//	}	
	
	
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
	
	
	public static Insumo localizarInsumo(int codinsumo){
		Query q = manager.query();
		q.constrain(Insumo.class);  		// select p from Insumo p where nome=:nome		
		q.descend("codinsumo").constrain(codinsumo);		 
		List<Insumo> resultados = q.execute();

		if(resultados.size()>0) {
			Insumo p = resultados.get(0);
			return p;
		}else
			return null;
	}
	
	public static List <Funcionario> listarFuncionarios () {
		Query q;
		List<Funcionario> funcionarios ; 
		
		q = manager.query();
		q.constrain(Funcionario.class);
		q.descend("nome").orderDescending();
		funcionarios = q.execute();
		return funcionarios;
	}

}
