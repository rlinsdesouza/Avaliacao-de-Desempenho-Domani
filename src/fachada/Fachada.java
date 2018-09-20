package fachada;

import java.util.List;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.query.Query;

import aplicacao.IDControl;
import modelo.Avaliacao;
import modelo.ContaBancaria;
import modelo.Endereco;
import modelo.Funcionario;
import modelo.Insumo;
import modelo.Prato;
import modelo.Producao;

public class Fachada {
	protected static ObjectContainer manager;

	public static void abrirBancoLocal(){
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // 0,1,2,3...
		config.common().objectClass(ContaBancaria.class).cascadeOnDelete(true);;
		config.common().objectClass(ContaBancaria.class).cascadeOnUpdate(true);;
		config.common().objectClass(ContaBancaria.class).cascadeOnActivate(true);
		config.common().objectClass(Endereco.class).cascadeOnDelete(true);;
		config.common().objectClass(Endereco.class).cascadeOnUpdate(true);;
		config.common().objectClass(Endereco.class).cascadeOnActivate(true);
		config.common().objectClass(Funcionario.class).cascadeOnDelete(true);;
		config.common().objectClass(Funcionario.class).cascadeOnUpdate(true);;
		config.common().objectClass(Funcionario.class).cascadeOnActivate(true);
		config.common().objectClass(Avaliacao.class).cascadeOnDelete(true);;
		config.common().objectClass(Avaliacao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Avaliacao.class).cascadeOnActivate(true);		
		config.common().objectClass(Producao.class).cascadeOnDelete(true);;
		config.common().objectClass(Producao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Producao.class).cascadeOnActivate(true);
		config.common().objectClass(Insumo.class).cascadeOnDelete(true);;
		config.common().objectClass(Insumo.class).cascadeOnUpdate(true);;
		config.common().objectClass(Insumo.class).cascadeOnActivate(true);
		config.common().objectClass(Prato.class).cascadeOnDelete(true);;
		config.common().objectClass(Prato.class).cascadeOnUpdate(true);;
		config.common().objectClass(Prato.class).cascadeOnActivate(true);

		
		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
		IDControl.registrarManager(manager); 
	}
	public static void abrirBancoServidor(){
		ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
		config.common().messageLevel(0);   //0,1,2,3,4
		config.common().objectClass(ContaBancaria.class).cascadeOnDelete(true);;
		config.common().objectClass(ContaBancaria.class).cascadeOnUpdate(true);;
		config.common().objectClass(ContaBancaria.class).cascadeOnActivate(true);
		config.common().objectClass(Endereco.class).cascadeOnDelete(true);;
		config.common().objectClass(Endereco.class).cascadeOnUpdate(true);;
		config.common().objectClass(Endereco.class).cascadeOnActivate(true);
		config.common().objectClass(Funcionario.class).cascadeOnDelete(true);;
		config.common().objectClass(Funcionario.class).cascadeOnUpdate(true);;
		config.common().objectClass(Funcionario.class).cascadeOnActivate(true);
		config.common().objectClass(Avaliacao.class).cascadeOnDelete(true);;
		config.common().objectClass(Avaliacao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Avaliacao.class).cascadeOnActivate(true);
		config.common().objectClass(Producao.class).cascadeOnDelete(true);;
		config.common().objectClass(Producao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Producao.class).cascadeOnActivate(true);
		config.common().objectClass(Insumo.class).cascadeOnDelete(true);;
		config.common().objectClass(Insumo.class).cascadeOnUpdate(true);;
		config.common().objectClass(Insumo.class).cascadeOnActivate(true);
		config.common().objectClass(Prato.class).cascadeOnDelete(true);;
		config.common().objectClass(Prato.class).cascadeOnUpdate(true);;
		config.common().objectClass(Prato.class).cascadeOnActivate(true);

		String ip = JOptionPane.showInputDialog("Digite o IP do servidor");
		if (ip==null || ip.isEmpty())	{
			System.out.println("ip invalido");
			System.exit(0);
		}
		manager = Db4oClientServer.openClient(config,ip,34000,"usuario1","senha1");	
		IDControl.registrarManager(manager); 
	}
	
	public static void fechaBanco ()  {
		manager.close();
	}
	
	public static void cadastrar(){
		Funcionario p1;
		System.out.println("cadastrando...");
		p1 = new Funcionario(1,"Rafael Lins");
//		p1.adicionarTelefone(new Telefone("88880000"));
//		p1.adicionarTelefone(new Telefone("88881111"));		
		manager.store(p1);	
		manager.commit();

//		p1 = new Pessoa("maria");
//		p1.adicionarTelefone(new Telefone("87882222"));
//		p1.adicionarTelefone(new Telefone("88883333"));
//		manager.store(p1);
//		manager.commit();
//
//		p1 = new Pessoa("jose");
//		p1.adicionarTelefone(new Telefone("87884444"));
//		manager.store(p1);		
//		manager.commit();

//		p1 = new Aluno("paulo",9);
//		manager.store(p1);		
//		manager.commit();
//
//		p1 = new Professor ("fausto", 1000.00);
//		manager.store(p1);		
//		manager.commit();

	}	
	
	
	public static Insumo cadastrarInsumo (int codinsumo, String nome, boolean lactose, boolean glutem) throws Exception {
		
		if(localizarInsumo(codinsumo)!=null) {
			throw new Exception ("Insumo já cadastrado!");
		}
		Insumo i = new Insumo(codinsumo,nome,lactose,glutem);
		manager.store(i);
		manager.commit();
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
