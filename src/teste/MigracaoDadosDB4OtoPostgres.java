package teste;

import java.util.List;

import fachada.Fachada;
import fachada.Fachadaold;
import modelo.Avaliacao;
import modelo.Funcionario;
import modelo.Insumo;
import modelo.Prato;
import modelo.Producao;

public class MigracaoDadosDB4OtoPostgres {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MigracaoDadosDB4OtoPostgres ();
		Fachadaold.finalizar();
		Fachada.finalizar();

	}
	
	public MigracaoDadosDB4OtoPostgres () {
		Fachadaold.inicializar();
		Fachada.inicializar();
		cadastrar();
//		consultar ();
//		alterar();
//		remover();
//		consultar();
	}

	
	public void cadastrar(){
		System.out.println("migrando...");
		try {
			List<Funcionario> bancofuncionario = Fachadaold.listarFuncionarios();
			for (Funcionario funcionario : bancofuncionario) {
				Fachada.cadastrarFuncionario(funcionario.getMatricula(), funcionario.getNome(), funcionario.getCpf(), null, funcionario.getEmail(), funcionario.getSenha(), funcionario.getSalt(), funcionario.getDataAdmissao(), funcionario.getDataDemissao(),null);
			}
			
			List<Insumo> bancoinsumos = Fachadaold.listarInsumo();
			for (Insumo insumo : bancoinsumos) {
				Fachada.cadastrarInsumo(insumo.getNome(), insumo.isLactose(), insumo.isGluten());
			}
			
			List<Prato> bancopratos = Fachadaold.listarPratos();
			for (Prato prato : bancopratos) {
				Fachada.cadastrarPrato(prato.getNome(), prato.getReceita(), prato.getDificuldade(), prato.getTempoProduzir(), prato.isLactose(), prato.isGluten(), prato.getInsumos());
//				int idPrato = Fachada.listarPratos(prato.getNome()).get(0).getId();
				if(prato.getInsumos()!=null) {
					for (Insumo insumo : prato.getInsumos()) {
//						int idInsumo = Fachada.listarInsumo(insumo.getNome()).get(0).getId();
						Fachada.adicionarInsumoAoPrato(prato, insumo);
					}
				}
				
			}
			
//			Prato p1 = Fachadaold.cadastrarPrato("Farofa de calabresa", "FaÃ§a devagar pra nÃ£o se perder", 1,30,false,false,null);
//			Prato p2 = Fachadaold.cadastrarPrato("Lasanha de frango", "Prepare a massa e depois o recheio", 2,45,false,false,null);
//			Prato p3 = Fachadaold.cadastrarPrato("Feijoada", "Bote uma laranja pra cozinhar junto e absorver a gordura", 3,90,false,false,null);

//			Producao r1 = Fachadaold.cadastrarProducao("14/10/2018",p1,f2);
//			Producao r2 = Fachadaold.cadastrarProducao("14/10/2018", p2, f2);
//			Producao r3 = Fachadaold.cadastrarProducao("01/10/2018", p3, f2);
			
//			Fachadaold.cadastrarAvaliacao(r1, 10, 8,null,f1);
//			Fachadaold.cadastrarAvaliacao(r2, 7, 7, "muito molho", f2);

			System.out.println("migração realizado com sucesso!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterar () {
		try {
			System.out.println("adiconando um insumo ao prato");
			Fachadaold.adicionarInsumoAoPrato(Fachadaold.localizarPrato(2), Fachadaold.localizarInsumo(1));
			System.out.println("Prato antes de atualizar o gluten e lactose e depois de add insumo: "+Fachadaold.localizarPrato(2));
			System.out.println("atualizando um prato em funcao do seus insumos");
			Fachadaold.atualizarLactoseGluten(Fachadaold.localizarPrato(2));
			System.out.println("Prato depois de atualizar o gluten e lactose e depois de add insumo: "+Fachadaold.localizarPrato(2));
			System.out.println("Funcionario antes do update: "+Fachadaold.localizarFuncionario(2));
			Fachadaold.atualizarFuncionario(2, 1, "Rafael Lins de Souza", "073.975.104-26", null, "rlinsdesouza@gmail.com", null, null, null, null);
			System.out.println("Funcionario depois do update: "+Fachadaold.localizarFuncionario(2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void remover () {
		try {
			System.out.println("removendo um insumo com prato vinculado: (CREME DE LEITE)");
			Fachadaold.removerInsumo(Fachadaold.localizarInsumo(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("removendo um prato com produï¿½ï¿½o vinculada: (FEIJOADA) ");
			Fachadaold.removerPrato(Fachadaold.localizarPrato(3));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("removendo uma producao com avaliacao vinculada: (PROUCAO DIA 14/10/2018) - FUNCIONARIA HELENA (COD 3) ");
			Fachadaold.removerProducao(Fachadaold.listarProducoesPorDataFuncionario("14/10/2018",3).get(0));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("removendo uma producao sem avaliacao vinculada: (PROUCAO DIA 01/10/2018) - FUNCIONARIA HELENA (COD 3) ");
			Producao p = Fachadaold.removerProducao(Fachadaold.listarProducoesPorDataFuncionario("01/10/2018",3).get(0));
			System.out.println("Removido com sucesso: "+p);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("FALHA TESTE");
		}
	}
	
	public void consultar () {
		System.out.println("Todos os funcionarios: ");
		List<Funcionario> l = Fachadaold.listarFuncionarios();
		for (Funcionario funcionario : l) {
			System.out.println(funcionario);
		}
		
		System.out.println("Todos os insumos: ");
		List<Insumo> li = Fachadaold.listarInsumo();
		for (Insumo i : li) {
			System.out.println(i);
		}
		
		System.out.println("Todos os pratos: ");
		List<Prato> lp = Fachadaold.listarPratos();
		for (Prato p : lp) {
			System.out.println(p);
		}
		
		System.out.println("Todos as producoes: ");
		List<Producao> lprod = Fachadaold.listarProducoes();
		for (Producao prod : lprod) {
			System.out.println(prod);
		}
		
		System.out.println("Todos as avaliacoes: ");
		List<Avaliacao> la = Fachadaold.listarAvaliacaoes();
		for (Avaliacao a : la) {
			System.out.println(a);
		}
		
		System.out.println("Notas das produï¿½ï¿½es do funcionario Helena(id 3) entre 01/10/2018 a 15/10/2018: ");
		List<Producao> lphelena = Fachadaold.listarProducoesPorDataFuncionario("01/10/2018", "15/10/2018", 3);
		System.out.println(Fachadaold.calculaNotaProducoes(lphelena));
	}
}
