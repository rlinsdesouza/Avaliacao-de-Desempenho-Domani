package fachada;

import java.util.Date;
import java.util.List;

//import dao.DAO;
import daojpa.DAO;
import daojpa.DAOAvaliacao;
import daojpa.DAOFuncionario;
import daojpa.DAOInsumo;
import daojpa.DAOPrato;
import daojpa.DAOProducao;
import modelo.Avaliacao;
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

	public static Funcionario cadastrarFuncionario (int matricula, String nome, String cpf, List<Integer> telefone, String email, String senha, String salt,
			Date dataAdmissao, Date dataDemissao, List<Producao> producoes) throws Exception {
		
		DAO.begin();			
		Funcionario i = daofuncionario.readByNome(nome);
		if(i != null) {
			throw new Exception("ja cadastrado:" + nome);
		}
		i = new Funcionario(matricula, nome.toUpperCase(), cpf, telefone, email,senha,salt,dataAdmissao, dataDemissao, producoes);
		daofuncionario.create(i);		
		DAO.commit();
		return i;
	}
	
	public static Prato cadastrarPrato (String nome, String receita, int dificuldade, int tempoProduzir, boolean lactose,
			boolean gluten, List<Insumo> insumos) throws Exception {
		
		DAO.begin();			
		Prato i = daoprato.readByNome(nome);
		if(i != null) {
			throw new Exception("produto ja cadastrado:" + nome);
//			return null;
		}else {
			i = new Prato(nome.toUpperCase(),receita.toUpperCase(),dificuldade,tempoProduzir,lactose,gluten,insumos);
			daoprato.create(i);		
			DAO.commit();
			return i;
		}
		
	}
	
	
	public static Insumo cadastrarInsumo (String nome, boolean lactose, boolean gluten) throws Exception {
		
		DAO.begin();			
		Insumo i = daoinsumo.readByNome(nome);
		if(i != null) {
			throw new Exception("produto ja cadastrado:" + nome);
		}

		i = new Insumo(nome.toUpperCase(), lactose, gluten);
		daoinsumo.create(i);		
		DAO.commit();
		return i;
	}
	
	public static Producao cadastrarProducao (String data, Prato prato, Funcionario cozinheiro) throws Exception {
		
		DAO.begin();			
		Producao i = new Producao(data,prato,cozinheiro);
		daoproducao.create(i);
		cozinheiro.getProducoes().add(i);
		daofuncionario.update(cozinheiro);
		DAO.commit();
		return i;
	}
	
	public static Avaliacao cadastrarAvaliacao (Producao producao, int notaSabor, int notaAparencia, String justificativa,
			Funcionario avaliador) throws Exception {
		
		DAO.begin();			
		Avaliacao i = new Avaliacao(producao,notaSabor,notaAparencia,justificativa,avaliador);
		daoavaliacao.create(i);
		producao.getAvaliacoes().add(i);
		daoproducao.update(producao);
		DAO.commit();
		return i;
	}
	
	public static Prato removerPrato (Prato p) throws Exception {
		DAO.begin();
		List<Producao> l = daoprato.ProducoesComPrato(p.getNome());
		if (l.isEmpty()) {
			daoprato.delete(p);			
		}else {
			throw new Exception ("Impossível excluir, prato com producao vinculada!");
		}
		DAO.commit();
		return p;
	}
	
	public static Insumo removerInsumo (Insumo p) throws Exception {
		DAO.begin();
		List<Prato> l = daoinsumo.PratosComInsumo(p.getNome());
		if (l.isEmpty()) {
			daoinsumo.delete(p);		
		}else {
			throw new Exception ("Impossível excluir, insumo com pratos vinculados!");
		}
		DAO.commit();
		return p;
	}
	
	public static Producao removerProducao (Producao p) throws Exception {
		DAO.begin();
		List<Avaliacao> l = p.getAvaliacoes();
		if (l.isEmpty()) {
			p.getCozinheiro().getProducoes().remove(p);
			daofuncionario.update(p.getCozinheiro());
			daoproducao.delete(p);
		}else {
			throw new Exception ("Impossível excluir, producao com avaliacoes vinculados!");
		}
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
	
	public static List <Avaliacao> listarAvaliacaoes () {
		return daoavaliacao.readAll();
	
	}
	
	public static List <Producao> listarProducoes () {
		return daoproducao.readAll();
	}
	
	public static List <Producao> listarProducoesPorData (String data) {
		return daoproducao.consultarProducoesPorDia(data);
	}
	
	public static List <Producao> listarProducoesPorDataFuncionario (String data, int id) {
		return daoproducao.consultarProducoesPorDiaFuncionario(data,id);
	}
	
	public static List <Producao> listarProducoesPorDataFuncionario (String datainicial, String datafinal, int id) {
		return daoproducao.consultarProducoesPorDiaFuncionario(datainicial,datafinal,id);
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

	public static Insumo atualizarInsumo(int id,String nome,boolean lactose,boolean gluten) {
		DAO.begin();
		Insumo p = daoinsumo.read(id);
		p.setNome(nome);
		p.setLactose(lactose);
		p.setGluten(gluten);
		daoinsumo.update(p);
		DAO.commit();
		return p;
	}
	
	public static Prato atualizarPrato(int id,String nome,String receita,int dificuldade,int tempo,boolean lactose,boolean glutem,List<Insumo> insumos) {
		DAO.begin();
		Prato p = daoprato.read(id);
		p.setNome(nome);
		p.setReceita(receita);
		p.setDificuldade(dificuldade);
		p.setTempoProduzir(tempo);
		p.setLactose(lactose);
		p.setGluten(glutem);
		p.setInsumos(insumos);
		daoprato.update(p);
		DAO.commit();
		return p;
	}
	
	public static Prato adicionarInsumoAoPrato (Prato p, Insumo i) {
		DAO.begin();
		p.getInsumos().add(i);
		i.getPratos().add(p);
		daoprato.update(p);
		DAO.commit();
		return p;
	}
	
	public static Funcionario atualizarFuncionario(int id, int matricula, String nome, String cpf, List<Integer> telefone, String email, String senha,String salt,
			Date dataAdmissao, Date dataDemissao) {
		DAO.begin();
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

		daofuncionario.update(p);
		DAO.commit();
		return p;
	}
	
	public static Prato atualizarLactoseGluten (Prato p) throws Exception {
		List<Insumo> insumos = p.getInsumos();
		if (insumos != null && !insumos.isEmpty()) {
			DAO.begin();
			p.setLactose(false);
			p.setGluten(false);
			for (Insumo insumo : insumos) {
				if (insumo.isGluten())
					p.setGluten(true);
				if (insumo.isLactose())
					p.setLactose(true);
			}
			daoprato.update(p);
			DAO.commit();
			return p;	
		}else {
			throw new Exception ("Produto sem insumos cadastrados!");
		}
	}
	
	public static double calculaNotaProducoes (List<Producao> p) {
		double nota = 0.0;
		double numerador=0.0;
		double denominador=0.0;
		
		for (Producao producao : p) {
			if (!producao.getAvaliacoes().isEmpty()) {
				for (Avaliacao a : producao.getAvaliacoes()) {
					numerador = numerador + ((((a.getNotaAparencia()+a.getNotaSabor())/2)*producao.getPrato().getDificuldade()));
					denominador = denominador + producao.getPrato().getDificuldade();	
				}
			}
		}
		nota = numerador/denominador;
		return nota;
	}
	
	public static int calculaProdutividadeProducoes (List<Producao> p) {
		int produtividade = 0;
		
		for (Producao producao : p) {
			produtividade += producao.getPrato().getDificuldade();
		}
		
		return produtividade;
	}

}
