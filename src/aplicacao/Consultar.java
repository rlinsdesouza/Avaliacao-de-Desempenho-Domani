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
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Aluno;
import modelo.Pessoa;
import modelo.Professor;
import modelo.Telefone;


public class Consultar {
	protected static ObjectContainer manager;

	public Consultar(){
		abrirBancoLocal();
		//abrirBancoServidor();
		consultar();

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
	
	public void consultar() {
		Query q;
		List<Pessoa> pessoas ; 
		List<Telefone> telefones;

		System.out.println("\npessoa que possui o numero 88883333 ");
		q = manager.query();
		q.constrain(Pessoa.class);
		q.descend("telefones").descend("numero").constrain("88883333");
		pessoas = q.execute();
		for(Pessoa p : pessoas)
			System.out.println(p.getNome());
		
		System.out.println("\ntelefones da operadora 87 ordenados por nome");
		q = manager.query();
		q.constrain(Telefone.class);
		q.descend("numero").constrain("87").startsWith(true);
		//q.descend("numero").orderAscending();
		q.descend("pessoa").descend("nome").orderDescending();
		telefones = q.execute(); 
		for(Telefone t : telefones)
			System.out.println(t);

		System.out.println("\nTotal de Pessoas");
		q = manager.query();
		q.constrain(Pessoa.class);
		int total = q.execute().size(); 
		System.out.println("total="+total);
		
		System.out.println("\npessoas sem telefone");
		q = manager.query();
		q.constrain(Pessoa.class);
		q.constrain(new Filtro1());
		pessoas = q.execute(); 
		for(Pessoa p : pessoas)
			System.out.println(p);
		
		System.out.println("\npessoas com nome jo");
		q = manager.query();
		q.constrain(Pessoa.class);
		q.descend("nome").constrain("jo").like();
		pessoas = q.execute(); 
		for(Pessoa p : pessoas)
			System.out.println(p.getNome());

		System.out.println("\ntelefones de nome jo (A)");
		q = manager.query();
		q.constrain(Telefone.class);
		q.descend("pessoa").descend("nome").constrain("jo").contains();
		telefones = q.execute(); 
		for(Telefone t : telefones)
			System.out.println(t);

		System.out.println("\ntelefones de nome jo (B)");
		q = manager.query();
		q.constrain(Telefone.class);
		q.constrain(new Filtro2());
		telefones = q.execute(); 
		for(Telefone t : telefones)
			System.out.println(t);
	}


	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}

//====================================================================
class Filtro1  implements Evaluation {
	public void evaluate(Candidate candidate) {
		Pessoa p = (Pessoa) candidate.getObject();
		boolean resultado = p.getTelefones().size()==0		;
		candidate.include(resultado);
	}
}

class Filtro2  implements Evaluation {
	public void evaluate(Candidate candidate) {
		Telefone t = (Telefone) candidate.getObject();
		boolean resultado; 
		resultado=false;
		if (t.getPessoa().getNome().contains("jo"))	
			resultado = true	;
		candidate.include(resultado);
	}
}