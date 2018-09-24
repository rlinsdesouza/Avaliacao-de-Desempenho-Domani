package fachada;

import java.util.Date;
import java.util.List;

import aplicacao.CSVReader;
import dao.DAO;
import dao.DAOFuncionario;
import dao.DAOInsumo;
import dao.DAOPrato;
import modelo.ContaBancaria;
import modelo.Endereco;
import modelo.Funcionario;
import modelo.Insumo;
import modelo.Prato;
import modelo.Producao;

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
//		Funcionario p1;
//		System.out.println("cadastrando...");
//		p1 = new Funcionario(1,"Rafael Lins");
//		daofuncionario.create(p1);
//	

	
		List<Prato> pratos = CSVReader.read("pratosbd.csv");
		for (Prato prato : pratos) {
			daoprato.create(prato);	
		}
		System.out.println("pre-cadastro realizado com sucesso!");
	}	

	public static Funcionario cadastrarFuncionario (int matricula, String nome, int cpf, List<Integer> telefone, String email, String senha, String salt,
			Date dataAdmissao, Date dataDemissao, ContaBancaria conta, Endereco endereco, List<Producao> producoes) throws Exception {
		
		int key = daofuncionario.getKey();
		DAO.begin();			
		Funcionario i = daofuncionario.read(key);
		if(i != null) {
			throw new Exception("ja cadastrado:" + nome);
		}
		i = new Funcionario(key,matricula, nome, cpf, telefone, email,senha,salt,dataAdmissao, dataDemissao, conta, endereco,producoes);
		daofuncionario.create(i);		
		DAO.commit();
		return i;
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
	
	
	public static Insumo cadastrarInsumo (String nome, boolean lactose, boolean glutem) throws Exception {
		
		int key = daoinsumo.getKey();
		DAO.begin();			
		Insumo i = daoinsumo.read(key);
		if(i != null) {
			throw new Exception("produto ja cadastrado:" + nome);
		}

		i = new Insumo(key, nome, lactose, glutem);
		daoinsumo.create(i);		
		DAO.commit();
		return i;
	}
	
	
	public static List <Funcionario> listarFuncionarios () {
		return daofuncionario.readAll();
	}
	
	public static List <Funcionario> listarFuncionarios (String nome) {
		return daofuncionario.readAll(nome);
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

	public static Insumo localizarInsumo(int id) {
		return daoinsumo.read(id);
	}
	
	public static Prato localizarPrato(int id) {
		return daoprato.read(id);
	}
	
	public static Funcionario localizarFuncionario(int id) {
		return daofuncionario.read(id);
	}

	public static Insumo atualizarInsumo(int id,String nome,boolean lactose,boolean glutem) {
		Insumo p = daoinsumo.read(id);
		p.setNome(nome);
		p.setLactose(lactose);
		p.setGlutem(glutem);
		daoinsumo.refresh(p);
		return p;
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
	
	public static Funcionario atualizarFuncionario(int id, int matricula, String nome, int cpf, List<Integer> telefone, String email, String senha,String salt,
			Date dataAdmissao, Date dataDemissao, ContaBancaria conta, Endereco endereco, List<Producao> producoes) {
		Funcionario p = daofuncionario.read(id);
		p.setNome(nome);
		p.setMatricula(matricula);
//		p.setCpf(cpf);
//		p.setTelefone(telefone);
//		p.setEmail(email);
		if (senha != null) {
			p.setSenha(senha);
			p.setSalt(salt);
		}
			
//		p.setDataAdmissao(dataAdmissao);
//		p.setDataDemissao(dataDemissao);
//		p.setConta(conta);

		daofuncionario.refresh(p);
		return p;
	}

}
