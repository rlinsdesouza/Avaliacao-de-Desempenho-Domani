package teste;

import java.util.Date;

import fachada.Fachada;
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
			Prato p1 = Fachada.cadastrarPrato("Farofa de calabresa", "Faça devagar pra não se perder", 1,30,false,false,null);
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
}
