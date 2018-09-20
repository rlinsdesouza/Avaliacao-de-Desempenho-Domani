package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;

import modelo.Aluno;
import modelo.Pessoa;
import modelo.Professor;
import modelo.Telefone;


public class Cadastrar {
	protected static ObjectContainer manager;

	public Cadastrar(){
		//abrirBancoLocal();
		abrirBancoServidor();
		cadastrar();
		manager.close();	
		System.out.println("\n\naviso: feche sempre o plugin eclipse antes de executar aplicação");
	}

	public void abrirBancoLocal(){
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // 0,1,2,3...
		config.common().objectClass(Pessoa.class).cascadeOnDelete(true);;
		config.common().objectClass(Pessoa.class).cascadeOnUpdate(true);;
		config.common().objectClass(Pessoa.class).cascadeOnActivate(true);
		config.common().objectClass(Aluno.class).cascadeOnDelete(true);;
		config.common().objectClass(Aluno.class).cascadeOnUpdate(true);;
		config.common().objectClass(Aluno.class).cascadeOnActivate(true);
		config.common().objectClass(Professor.class).cascadeOnDelete(true);;
		config.common().objectClass(Professor.class).cascadeOnUpdate(true);;
		config.common().objectClass(Professor.class).cascadeOnActivate(true);
		config.common().objectClass(Telefone.class).cascadeOnDelete(true);;
		config.common().objectClass(Telefone.class).cascadeOnUpdate(true);;
		config.common().objectClass(Telefone.class).cascadeOnActivate(true);		
		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
		IDControl.registrarManager(manager); 
	}
	public static void abrirBancoServidor(){
		ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
		config.common().messageLevel(0);   //0,1,2,3,4
		config.common().objectClass(Pessoa.class).cascadeOnDelete(true);;
		config.common().objectClass(Pessoa.class).cascadeOnUpdate(true);;
		config.common().objectClass(Pessoa.class).cascadeOnActivate(true);
		config.common().objectClass(Aluno.class).cascadeOnDelete(true);;
		config.common().objectClass(Aluno.class).cascadeOnUpdate(true);;
		config.common().objectClass(Aluno.class).cascadeOnActivate(true);
		config.common().objectClass(Professor.class).cascadeOnDelete(true);;
		config.common().objectClass(Professor.class).cascadeOnUpdate(true);;
		config.common().objectClass(Professor.class).cascadeOnActivate(true);
		config.common().objectClass(Telefone.class).cascadeOnDelete(true);;
		config.common().objectClass(Telefone.class).cascadeOnUpdate(true);;
		config.common().objectClass(Telefone.class).cascadeOnActivate(true);		
		String ip = JOptionPane.showInputDialog("Digite o IP do servidor");
		if (ip==null || ip.isEmpty())	{
			System.out.println("ip invalido");
			System.exit(0);
		}
		manager = Db4oClientServer.openClient(config,ip,34000,"usuario1","senha1");	
		IDControl.registrarManager(manager); 
	}
	public void cadastrar(){
		Pessoa p1;
		System.out.println("cadastrando...");
		p1 = new Pessoa("joao");
		p1.adicionarTelefone(new Telefone("88880000"));
		p1.adicionarTelefone(new Telefone("88881111"));		
		manager.store(p1);	
		manager.commit();

		p1 = new Pessoa("maria");
		p1.adicionarTelefone(new Telefone("87882222"));
		p1.adicionarTelefone(new Telefone("88883333"));
		manager.store(p1);
		manager.commit();

		p1 = new Pessoa("jose");
		p1.adicionarTelefone(new Telefone("87884444"));
		manager.store(p1);		
		manager.commit();

		p1 = new Aluno("paulo",9);
		manager.store(p1);		
		manager.commit();

		p1 = new Professor ("fausto", 1000.00);
		manager.store(p1);		
		manager.commit();

	}	


	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}


