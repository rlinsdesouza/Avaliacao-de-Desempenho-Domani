/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package daojpa;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class DAO<T> implements DAOInterface<T> {
	protected static EntityManager manager;
	protected static EntityManagerFactory factory;
	public DAO(){}

	private static Properties dadosserver; 

	public static void open(){	
		if(manager==null){		
			abrirBancoLocal();
//			abrirBancoServidor();
		}
	}
	private static void abrirBancoLocal(){
		//propriedades do persistence.xml  que podem ser sobrescritas		
		HashMap<String,String> properties = new HashMap<String,String>();		
		//			properties.put(PersistenceUnitProperties.JDBC_DRIVER, "org.postgresql.Driver" );	
		//			properties.put(PersistenceUnitProperties.JDBC_URL, "jdbc:postgresql://localhost:5432/loja");
		//			properties.put(PersistenceUnitProperties.JDBC_USER, "postgres");
		//			properties.put(PersistenceUnitProperties.JDBC_PASSWORD, "ifpb");
		//			properties.put(PersistenceUnitProperties.LOGGING_LEVEL, "off");
		//			properties.put(PersistenceUnitProperties.LOGGING_FILE, "log.txt");
		//			properties.put(PersistenceUnitProperties.SCHEMA_GENERATION_DATABASE_ACTION, "create");
		//			properties.put(PersistenceUnitProperties.SCHEMA_GENERATION_SCRIPTS_ACTION, "create");						
		//			properties.put(PersistenceUnitProperties.SCHEMA_GENERATION_SCRIPTS_ACTION, "create");
		//			properties.put("hibernate.hbm2ddl.auto", "update");
		factory = Persistence.createEntityManagerFactory("bancopgd-eclipselink", properties);
		manager = factory.createEntityManager();
		
//		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
//		config.common().messageLevel(0);  // 0,1,2,3...
//		config.common().objectClass(ContaBancaria.class).cascadeOnDelete(false);;
//		config.common().objectClass(ContaBancaria.class).cascadeOnUpdate(true);;
//		config.common().objectClass(ContaBancaria.class).cascadeOnActivate(true);
//		config.common().objectClass(Endereco.class).cascadeOnDelete(false);;
//		config.common().objectClass(Endereco.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Endereco.class).cascadeOnActivate(true);
//		config.common().objectClass(Funcionario.class).cascadeOnDelete(false);;
//		config.common().objectClass(Funcionario.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Funcionario.class).cascadeOnActivate(true);
//		config.common().objectClass(Avaliacao.class).cascadeOnDelete(false);;
//		config.common().objectClass(Avaliacao.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Avaliacao.class).cascadeOnActivate(true);		
//		config.common().objectClass(Producao.class).cascadeOnDelete(false);;
//		config.common().objectClass(Producao.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Producao.class).cascadeOnActivate(true);
//		config.common().objectClass(Insumo.class).cascadeOnDelete(false);;
//		config.common().objectClass(Insumo.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Insumo.class).cascadeOnActivate(true);
//		config.common().objectClass(Prato.class).cascadeOnDelete(false);;
//		config.common().objectClass(Prato.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Prato.class).cascadeOnActivate(true);

//		try {
//			String salt = PasswordUtils.getSalt(30);
//			Fachada.cadastrarFuncionario (0,"admin",null,null,null,PasswordUtils.generateSecurePassword("admin",salt),salt,null,null,null,null,null);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}			
	}
//	private static void abrirBancoServidor(){
//		ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
//		config.common().messageLevel(0);   //0,1,2,3,4
//		config.common().objectClass(ContaBancaria.class).cascadeOnDelete(false);;
//		config.common().objectClass(ContaBancaria.class).cascadeOnUpdate(true);;
//		config.common().objectClass(ContaBancaria.class).cascadeOnActivate(true);
//		config.common().objectClass(Endereco.class).cascadeOnDelete(false);;
//		config.common().objectClass(Endereco.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Endereco.class).cascadeOnActivate(true);
//		config.common().objectClass(Funcionario.class).cascadeOnDelete(false);;
//		config.common().objectClass(Funcionario.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Funcionario.class).cascadeOnActivate(true);
//		config.common().objectClass(Avaliacao.class).cascadeOnDelete(false);;
//		config.common().objectClass(Avaliacao.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Avaliacao.class).cascadeOnActivate(true);		
//		config.common().objectClass(Producao.class).cascadeOnDelete(false);;
//		config.common().objectClass(Producao.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Producao.class).cascadeOnActivate(true);
//		config.common().objectClass(Insumo.class).cascadeOnDelete(false);;
//		config.common().objectClass(Insumo.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Insumo.class).cascadeOnActivate(true);
//		config.common().objectClass(Prato.class).cascadeOnDelete(false);;
//		config.common().objectClass(Prato.class).cascadeOnUpdate(true);;
//		config.common().objectClass(Prato.class).cascadeOnActivate(true);
//
////		String ip = JOptionPane.showInputDialog("Digite o IP do servidor");
////		if (ip==null || ip.isEmpty())	{
////			System.out.println("ip invalido");
////			System.exit(0);
////		}
//		
//
//		try {
//			dadosserver = ManipuladorProperties.getProp();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(null, e1.getMessage());
//		}
//		
//		manager = Db4oClientServer.openClient(config,dadosserver.getProperty("prop.server.host"),34000,dadosserver.getProperty("prop.server.login"),dadosserver.getProperty("prop.server.password"));	
//		IDControl.registrarManager(manager); 
////		try {
////			String salt = PasswordUtils.getSalt(30);
////			Fachada.cadastrarFuncionario (0,"admin",null,null,null,PasswordUtils.generateSecurePassword("admin",salt),salt,null,null,null,null,null);
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}
	public static void close(){
		if(manager!=null) {
			manager.close();
			manager=null;
			factory.close();
			factory = null;
		}
	}

	//----------CRUD-----------------------

	public void create(T obj){
		manager.persist( obj );
	}
	
	@SuppressWarnings("unchecked")
	public T read(int id){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return manager.find (type,id);
	}

	public T update(T obj){
		manager.merge(obj);
		return obj;
	}

	public void delete(T obj) {
		manager.remove(obj);
	}

//	public void refresh(T obj){
//		manager.ext().refresh(obj, Integer.MAX_VALUE);
//	}

//	@SuppressWarnings("unchecked")
//	public List<T> readAll(){
//		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
//				.getGenericSuperclass()).getActualTypeArguments()[0];
//		Query q = manager.query();
//		q.constrain(type);
//		return (List<T>) q.execute();
//	}
	
//	public List<T> readAll(String nome){
//		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
//				.getGenericSuperclass()).getActualTypeArguments()[0];
//		Query q = manager.query();
//		q.constrain(type);
//		q.descend("nome").constrain(nome).like();
//		return (List<T>) q.execute();
//	}
//	
	
	//--------transa��o---------------
	public static void begin(){
		if(!manager.getTransaction().isActive())
			manager.getTransaction().begin();
	}		
	public static void commit(){
		if(manager.getTransaction().isActive()){
			manager.getTransaction().commit();
			manager.clear();		// ---- esvaziar o cache de objetos
		}
	}
	public static void rollback(){
		if(manager.getTransaction().isActive())
			manager.getTransaction().rollback();
	}
	
}