package teste;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
				try {
					Fachada.cadastrarInsumo(insumo.getNome(), insumo.isLactose(), insumo.isGluten());		
				} catch (Exception e) {
					System.out.println(e.getMessage());
			
				}
				
			}
			
			List<Prato> bancopratos = Fachadaold.listarPratos();
			for (Prato prato : bancopratos) {
				try {
					Prato pratopost = Fachada.cadastrarPrato(prato.getNome(), prato.getReceita(), prato.getDificuldade(), prato.getTempoProduzir(), prato.isLactose(), prato.isGluten(), null);
					int idPrato = Fachada.listarPratos(prato.getNome()).get(0).getId();
					if(prato.getInsumos()!=null) {
						for (Insumo insumo : prato.getInsumos()) {
							if (insumo != null) {
								int idInsumo = Fachada.listarInsumo(insumo.getNome()).get(0).getId();
								try {
									Fachada.adicionarInsumoAoPrato(Fachada.localizarPrato(idPrato), Fachada.localizarInsumo(idInsumo));
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
								
							}
							
						}
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				
			}
			
			List<Producao> bancoproducao = Fachadaold.listarProducoes();
			DateFormat formatBR = new SimpleDateFormat("dd/mm/yyyy");
			DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
			
			for (Producao producao : bancoproducao) {
				//Primeiro converte de String para Date
				Date date = formatBR.parse(producao.getData());
				//Depois formata data
				String dateFormated = formatUS.format(date);
				
//				System.out.println(dateFormated);
//				System.out.println(producao.getPrato().getNome());
//				System.out.println(Fachada.listarPratos(producao.getPrato().getNome()));
//				System.out.println(producao.getCozinheiro().getNome());
//				System.out.println(Fachada.listarFuncionarios(producao.getCozinheiro().getNome().toUpperCase()));
				
				try {
					Producao producaoPostgres = Fachada.cadastrarProducao(dateFormated, Fachada.listarPratos(producao.getPrato().getNome()).get(0), Fachada.listarFuncionarios(producao.getCozinheiro().getNome().toUpperCase()).get(0));
					if(producao.getAvaliacoes() !=null) {
						for (Avaliacao avaliacao : producao.getAvaliacoes()) {
							Fachada.cadastrarAvaliacao(producaoPostgres, avaliacao.getNotaSabor(), avaliacao.getNotaAparencia(), avaliacao.getJustificativa(), Fachada.listarFuncionarios(avaliacao.getAvaliador().getNome()).get(0));
						}
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				
			}

//			Producao r1 = Fachadaold.cadastrarProducao("14/10/2018",p1,f2);
//			Producao r2 = Fachadaold.cadastrarProducao("14/10/2018", p2, f2);
//			Producao r3 = Fachadaold.cadastrarProducao("01/10/2018", p3, f2);
			
//			Fachadaold.cadastrarAvaliacao(r1, 10, 8,null,f1);
//			Fachadaold.cadastrarAvaliacao(r2, 7, 7, "muito molho", f2);

			System.out.println("migra��o realizado com sucesso!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
