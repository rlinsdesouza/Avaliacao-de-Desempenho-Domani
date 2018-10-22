/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package dao;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.query.Query;

import fachada.Fachada;
import modelo.Avaliacao;
import modelo.ContaBancaria;
import modelo.Endereco;
import modelo.Funcionario;
import modelo.Insumo;
import modelo.Prato;
import modelo.Producao;
import uteis.PasswordUtils;


public abstract class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;
	private static Properties dadosserver; 

	public static void open(){	
		if(manager==null){		
//			abrirBancoLocal();
			abrirBancoServidor();
		}
	}
	private static void abrirBancoLocal(){
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // 0,1,2,3...
		config.common().objectClass(ContaBancaria.class).cascadeOnDelete(false);;
		config.common().objectClass(ContaBancaria.class).cascadeOnUpdate(true);;
		config.common().objectClass(ContaBancaria.class).cascadeOnActivate(true);
		config.common().objectClass(Endereco.class).cascadeOnDelete(false);;
		config.common().objectClass(Endereco.class).cascadeOnUpdate(true);;
		config.common().objectClass(Endereco.class).cascadeOnActivate(true);
		config.common().objectClass(Funcionario.class).cascadeOnDelete(false);;
		config.common().objectClass(Funcionario.class).cascadeOnUpdate(true);;
		config.common().objectClass(Funcionario.class).cascadeOnActivate(true);
		config.common().objectClass(Avaliacao.class).cascadeOnDelete(false);;
		config.common().objectClass(Avaliacao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Avaliacao.class).cascadeOnActivate(true);		
		config.common().objectClass(Producao.class).cascadeOnDelete(false);;
		config.common().objectClass(Producao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Producao.class).cascadeOnActivate(true);
		config.common().objectClass(Insumo.class).cascadeOnDelete(false);;
		config.common().objectClass(Insumo.class).cascadeOnUpdate(true);;
		config.common().objectClass(Insumo.class).cascadeOnActivate(true);
		config.common().objectClass(Prato.class).cascadeOnDelete(false);;
		config.common().objectClass(Prato.class).cascadeOnUpdate(true);;
		config.common().objectClass(Prato.class).cascadeOnActivate(true);

		
		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
		IDControl.registrarManager(manager);
//		try {
//			String salt = PasswordUtils.getSalt(30);
//			Fachada.cadastrarFuncionario (0,"admin",null,null,null,PasswordUtils.generateSecurePassword("admin",salt),salt,null,null,null,null,null);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}			
	}
	private static void abrirBancoServidor(){
		ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
		config.common().messageLevel(0);   //0,1,2,3,4
		config.common().objectClass(ContaBancaria.class).cascadeOnDelete(false);;
		config.common().objectClass(ContaBancaria.class).cascadeOnUpdate(true);;
		config.common().objectClass(ContaBancaria.class).cascadeOnActivate(true);
		config.common().objectClass(Endereco.class).cascadeOnDelete(false);;
		config.common().objectClass(Endereco.class).cascadeOnUpdate(true);;
		config.common().objectClass(Endereco.class).cascadeOnActivate(true);
		config.common().objectClass(Funcionario.class).cascadeOnDelete(false);;
		config.common().objectClass(Funcionario.class).cascadeOnUpdate(true);;
		config.common().objectClass(Funcionario.class).cascadeOnActivate(true);
		config.common().objectClass(Avaliacao.class).cascadeOnDelete(false);;
		config.common().objectClass(Avaliacao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Avaliacao.class).cascadeOnActivate(true);		
		config.common().objectClass(Producao.class).cascadeOnDelete(false);;
		config.common().objectClass(Producao.class).cascadeOnUpdate(true);;
		config.common().objectClass(Producao.class).cascadeOnActivate(true);
		config.common().objectClass(Insumo.class).cascadeOnDelete(false);;
		config.common().objectClass(Insumo.class).cascadeOnUpdate(true);;
		config.common().objectClass(Insumo.class).cascadeOnActivate(true);
		config.common().objectClass(Prato.class).cascadeOnDelete(false);;
		config.common().objectClass(Prato.class).cascadeOnUpdate(true);;
		config.common().objectClass(Prato.class).cascadeOnActivate(true);

//		String ip = JOptionPane.showInputDialog("Digite o IP do servidor");
//		if (ip==null || ip.isEmpty())	{
//			System.out.println("ip invalido");
//			System.exit(0);
//		}
		

		try {
			dadosserver = ManipuladorProperties.getProp();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
		manager = Db4oClientServer.openClient(config,dadosserver.getProperty("prop.server.host"),34000,dadosserver.getProperty("prop.server.login"),dadosserver.getProperty("prop.server.password"));	
		IDControl.registrarManager(manager); 
//		try {
//			String salt = PasswordUtils.getSalt(30);
//			Fachada.cadastrarFuncionario (0,"admin",null,null,null,PasswordUtils.generateSecurePassword("admin",salt),salt,null,null,null,null,null);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public static void close(){
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}

	//----------CRUD-----------------------

	public void create(T obj){
		manager.store( obj );
	}
	
	@SuppressWarnings("unchecked")
	public T read(int id){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Query q = manager.query();
		q.constrain(type);
		q.descend("id").constrain(id);
		List<T> resultados = q.execute();
		if (resultados.size()>0)
			return (T) resultados.get(0);
		else
			return null;
	}

	public T update(T obj){
		manager.store(obj);
		return obj;
	}

	public void delete(T obj) {
		manager.delete(obj);
	}

	public void refresh(T obj){
		manager.ext().refresh(obj, Integer.MAX_VALUE);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll(){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Query q = manager.query();
		q.constrain(type);
		return (List<T>) q.execute();
	}
	
	public List<T> readAll(String nome){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Query q = manager.query();
		q.constrain(type);
		q.descend("nome").constrain(nome).like();
		return (List<T>) q.execute();
	}
	
//	public int getKey () {
//		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
//				.getGenericSuperclass()).getActualTypeArguments()[0];
//		Query q = manager.query();
//		q.constrain(type);
//		q.descend("id").orderAscending();
//		q.execute().
//		
//		return q.execute().size()+1;
//	}
	
	
	
	//--------transa��o---------------
	public static void begin(){	}		// tem que ser vazio
	public static void commit(){
		manager.commit();
	}
	public static void rollback(){
		manager.rollback();
	}
	
}