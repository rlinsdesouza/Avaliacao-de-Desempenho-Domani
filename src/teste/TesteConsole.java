package teste;

import java.util.List;

import fachada.Fachada;
import modelo.Avaliacao;
import modelo.Funcionario;
import modelo.Insumo;
import modelo.Prato;
import modelo.Producao;

public class TesteConsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TesteConsole ();
		Fachada.finalizar();

	}
	
	public TesteConsole () {
		Fachada.inicializar();
		cadastrar();
		consultar ();
		alterar();
		remover();
		consultar();
	}

	
	public void cadastrar(){
		System.out.println("cadastrando...");
		try {
			Funcionario f1 = Fachada.cadastrarFuncionario(1, "Rafael Lins","073.975.104-26",null, "linsdesouza@hotmail.com", "teste","teste",null,null, null, null,null);
			Funcionario f2 = Fachada.cadastrarFuncionario(2, "Maria Helena",null,null,null,null,null,null,null, null, null,null);

			Insumo i1 = Fachada.cadastrarInsumo("CREME DE LEITE", true, false);
			Insumo i2 = Fachada.cadastrarInsumo ("Farinha Yoki", true, true);
			
//			List<Prato> pratos = CSVReader.read("pratosbd.csv");
//			for (Prato prato : pratos) {
//				Fachada.cadastrarPrato(prato.getNome(), prato.getReceita(),prato.getDificuldade(), prato.getTempoProduzir(), prato.isLactose(), prato.isGluten(), prato.getInsumos());	
//			}
			Prato p1 = Fachada.cadastrarPrato("Farofa de calabresa", "FaÃ§a devagar pra nÃ£o se perder", 1,30,false,false,null);
			Prato p2 = Fachada.cadastrarPrato("Lasanha de frango", "Prepare a massa e depois o recheio", 2,45,false,false,null);
			Prato p3 = Fachada.cadastrarPrato("Feijoada", "Bote uma laranja pra cozinhar junto e absorver a gordura", 3,90,false,false,null);

			Producao r1 = Fachada.cadastrarProducao("2018-10-14",p1,f2);
			Producao r2 = Fachada.cadastrarProducao("2018-10-14", p2, f2);
			Producao r3 = Fachada.cadastrarProducao("2018-10-01", p3, f2);
			
			Fachada.cadastrarAvaliacao(r1, 10, 8,null,f1);
			Fachada.cadastrarAvaliacao(r2, 7, 7, "muito molho", f2);

			System.out.println("pre-cadastro realizado com sucesso!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterar () {
		try {
			System.out.println("adiconando um insumo ao prato");
			Fachada.adicionarInsumoAoPrato(Fachada.localizarPrato(2), Fachada.localizarInsumo(1));
			System.out.println("Prato antes de atualizar o gluten e lactose e depois de add insumo: "+Fachada.localizarPrato(2));
			System.out.println("atualizando um prato em funcao do seus insumos");
			Fachada.atualizarLactoseGluten(Fachada.localizarPrato(2));
			System.out.println("Prato depois de atualizar o gluten e lactose e depois de add insumo: "+Fachada.localizarPrato(2));
			System.out.println("Funcionario antes do update: "+Fachada.localizarFuncionario(1));
			Fachada.atualizarFuncionario(2, 1, "Rafael Lins de Souza", "073.975.104-26", null, "rlinsdesouza@gmail.com", null, null, null, null, null, null);
			System.out.println("Funcionario depois do update: "+Fachada.localizarFuncionario(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void remover () {
		try {
			System.out.println("removendo um insumo com prato vinculado: (CREME DE LEITE)");
			Fachada.removerInsumo(Fachada.localizarInsumo(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("removendo um prato com produção vinculada: (FEIJOADA) ");
			Fachada.removerPrato(Fachada.localizarPrato(3));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("removendo uma producao com avaliacao vinculada: (PROUCAO DIA 14/10/2018) - FUNCIONARIA HELENA (COD 3) ");
			Fachada.removerProducao(Fachada.listarProducoesPorDataFuncionario("2018-10-14",3).get(0));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("removendo uma producao com avaliacao vinculada: (PROUCAO DIA 14/10/2018) - FUNCIONARIA HELENA (COD 3) ");
			Producao p = Fachada.removerProducao(Fachada.listarProducoesPorDataFuncionario("2018-10-01",3).get(0));
			System.out.println("Removido com sucesso: "+p);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void consultar () {
		System.out.println("Todos os funcionarios: ");
		List<Funcionario> l = Fachada.listarFuncionarios();
		for (Funcionario funcionario : l) {
			System.out.println(funcionario);
		}
		
		System.out.println("Todos os insumos: ");
		List<Insumo> li = Fachada.listarInsumo();
		for (Insumo i : li) {
			System.out.println(i);
		}
		
		System.out.println("Todos os pratos: ");
		List<Prato> lp = Fachada.listarPratos();
		for (Prato p : lp) {
			System.out.println(p);
		}
		
		System.out.println("Todos as producoes: ");
		List<Producao> lprod = Fachada.listarProducoes();
		for (Producao prod : lprod) {
			System.out.println(prod);
		}
		
		System.out.println("Todos as avaliacoes: ");
		List<Avaliacao> la = Fachada.listarAvaliacaoes();
		for (Avaliacao a : la) {
			System.out.println(a);
		}
		
		System.out.println("Notas das produções do funcionario Helena(id 3) entre 01/10/2018 a 15/10/2018: ");
		List<Producao> lphelena = Fachada.listarProducoesPorDataFuncionario("01/10/2018", "15/10/2018", 3);
		System.out.println(Fachada.calculaNotaProducoes(lphelena));
	}
}
