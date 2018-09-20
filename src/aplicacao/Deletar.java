package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.query.Query;

import modelo.Aluno;
import modelo.Pessoa;
import modelo.Telefone;


public class Deletar {
	protected static ObjectContainer manager;

	public Deletar(){
		abrirBancoLocal();
		//abrirBancoServidor();
		deletar();
		manager.close();	
		System.out.println("\n\n aviso: feche sempre o plugin eclipse antes de executar aplicação");
	}

	public void abrirBancoLocal(){
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(3);  // 0,1,2,3...
		config.common().objectClass(Pessoa.class).cascadeOnDelete(true);;
		config.common().objectClass(Pessoa.class).cascadeOnUpdate(true);;
		config.common().objectClass(Pessoa.class).cascadeOnActivate(true);
		config.common().objectClass(Aluno.class).cascadeOnDelete(true);;
		config.common().objectClass(Aluno.class).cascadeOnUpdate(true);;
		config.common().objectClass(Aluno.class).cascadeOnActivate(true);
		config.common().objectClass(Telefone.class).cascadeOnDelete(true);;
		config.common().objectClass(Telefone.class).cascadeOnUpdate(true);;
		config.common().objectClass(Telefone.class).cascadeOnActivate(true);		
		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
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
		config.common().objectClass(Telefone.class).cascadeOnDelete(true);;
		config.common().objectClass(Telefone.class).cascadeOnUpdate(true);;
		config.common().objectClass(Telefone.class).cascadeOnActivate(true);		
		//manager = Db4oClientServer.openClient(config,"??????",34000,"usuario1","senha1");	
	}
	public void deletar(){
		System.out.println("deletar...");
		Pessoa p = localizarPessoa("maria");	
		if(p != null) {
			manager.delete(p);
			manager.commit();
		}else
			System.out.println("inexistente");
	}

	public Pessoa localizarPessoa(String nome){
		Query q = manager.query();
		q.constrain(Pessoa.class);  		// select p from Pessoa p where nome=:nome		
		q.descend("nome").constrain(nome);		 
		List<Pessoa> resultados = q.execute();

		if(resultados.size()>0) {
			Pessoa p = resultados.get(0);
			return p;
		}else
			return null;
	} 



	//=================================================
	public static void main(String[] args) {
		new Deletar();
	}
}

