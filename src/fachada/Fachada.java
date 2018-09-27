package fachada;

import java.util.Date;
import java.util.List;

import aplicacao.CSVReader;
import dao.DAO;
import dao.DAOAvaliacao;
import dao.DAOFuncionario;
import dao.DAOInsumo;
import dao.DAOPrato;
import dao.DAOProducao;
import modelo.Avaliacao;
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
	private static DAOProducao daoproducao = new DAOProducao ();
	private static DAOAvaliacao daoavaliacao = new DAOAvaliacao ();
	
	public static void inicializar () {
		DAO.open();
	}
	
	public static void finalizar () {
		DAO.close();
	}
		
	public static void cadastrar(){
		System.out.println("cadastrando...");
		try {
			cadastrarFuncionario(0, "Rafael Lins", "073.975.104-26", null, "linsdesouza@hotmail.com", "teste", "teste", null, null, null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	
		List<Prato> pratos = CSVReader.read("pratosbd.csv");
		for (Prato prato : pratos) {
			daoprato.create(prato);	
		}
		System.out.println("pre-cadastro realizado com sucesso!");
	}	

	public static Funcionario cadastrarFuncionario (int matricula, String nome, String cpf, List<Integer> telefone, String email, String senha, String salt,
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
	
	public static Producao cadastrarProducao (String data, Prato prato, Funcionario cozinheiro) throws Exception {
		
		int key = daoproducao.getKey();
		DAO.begin();			
		Producao i = daoproducao.read(key);
		if(i != null) {
			throw new Exception("ja cadastrado:" + prato.getNome());
		}

		i = new Producao(key,data,prato,cozinheiro);
		daoproducao.create(i);
		cozinheiro.getProducoes().add(i);
		daofuncionario.update(cozinheiro);
		DAO.commit();
		return i;
	}
	
	public static Avaliacao cadastrarAvaliacao (Producao producao, int notaSabor, int notaAparencia, String justificativa,
			Funcionario avaliador) throws Exception {
		
		int key = daoavaliacao.getKey();
		DAO.begin();			
		Avaliacao i = daoavaliacao.read(key);
		if(i != null) {
			throw new Exception("ja cadastrado:" + i);
		}

		i = new Avaliacao(key,producao,notaSabor,notaAparencia,justificativa,avaliador);
		daoavaliacao.create(i);
		producao.getAvaliacoes().add(i);
		daoproducao.update(producao);
		DAO.commit();
		return i;
	}
	
	public static Producao removerProducao (Producao p) {
		DAO.begin();
		p.getCozinheiro().getProducoes().remove(p);
		daofuncionario.update(p.getCozinheiro());
		daoproducao.delete(p);		
		DAO.commit();
		return p;
	}
	
	public static Avaliacao removerAvaliacao (Avaliacao p) {
		DAO.begin();
		p.getProduto().getAvaliacoes().remove(p);
		daoproducao.update(p.getProduto());
		daoavaliacao.delete(p);	
		DAO.commit();
		return p;
	}
	
	public static List <Producao> listarProducoes () {
		return daoproducao.readAll();
	}
	
	public static List <Producao> listarProducoesPorData (String data) {
		return daoproducao.consultarProducoesPorDiaFuncionario(data);
	}
	
	public static List <Producao> listarProducoesPorData (String data, int id) {
		return daoproducao.consultarProducoesPorDiaFuncionario(data,id);
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
	
	public static Funcionario atualizarFuncionario(int id, int matricula, String nome, String cpf, List<Integer> telefone, String email, String senha,String salt,
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
