package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.query.Query;

import modelo.Aluno;
import modelo.Pessoa;
import modelo.Professor;
import modelo.Telefone;


public class Listar {
	protected static ObjectContainer manager;

	public Listar(){
		abrirBancoLocal();
		//abrirBancoServidor();
		
		listarPessoas();
		listarAlunos();
		listarProfessores();
		listarTelefones();

		manager.close();	
		System.out.println("\n\n aviso: feche sempre o plugin eclipse antes de executar aplicação");
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

	public void listarPessoas(){
		System.out.println("\nlistagem das pessoas:");
		Query q = manager.query();
		q.constrain(Pessoa.class);  				// select p from Pessoa p
		List<Pessoa> resultados = q.execute();
		
		for(Pessoa p: resultados)
			System.out.println(p);
	}
	public void listarAlunos(){
		System.out.println("\nlistagem das alunos:");
		Query q = manager.query();
		q.constrain(Aluno.class);  				
		List<Aluno> resultados = q.execute();
		
		for(Aluno a: resultados)
			System.out.println(a);
	}

	public void listarProfessores(){
		System.out.println("\nlistagem das professores:");
		Query q = manager.query();
		q.constrain(Professor.class);  				
		List<Professor> resultados = q.execute();
		
		for(Professor p: resultados)
			System.out.println(p);
	}

	public void listarTelefones(){
		System.out.println("\nlistagem das telefones:");
		Query q = manager.query();
		q.constrain(Telefone.class);  				
		List<Telefone> resultados = q.execute();
		for(Telefone t: resultados)
			System.out.println(t);
	}

	

	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}

