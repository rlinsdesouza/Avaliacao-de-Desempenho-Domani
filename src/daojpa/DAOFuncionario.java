/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;


import java.util.List;

import javax.persistence.Query;

import modelo.Funcionario;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
public class DAOFuncionario  extends DAO<Funcionario>{

	public Funcionario readByNome (String nome){	
		Query q = manager.createQuery(
				"select f from Funcionario f where f.nome = :name");
		q.setParameter("name", nome);
		List<Funcionario> resultados = q.getResultList();
		if (resultados.size()>0)
			return (Funcionario) resultados.get(0);
		else
			return null;
	}
	
	
//	public  List<Funcionario> consultarFuncionarioSemPareteleira() {
//		Query q = manager.query();
//		q.constrain(Funcionario.class);
//		q.descend("prateleira").constrain(null);
//		return q.execute(); 
//	}
//
//	public int consultarTotalFuncionarios() {
//		Query q = manager.query();
//		q.constrain(Funcionario.class);
//		int total = q.execute().size(); 
//		return total;
//	}
//
//	public List<Funcionario> consultarFuncionariosDaPrateleira(int id){
//		Query q = manager.query();
//		q.constrain(Funcionario.class);
//		q.descend("prateleira").descend("id").constrain(id);
//		List<Funcionario> result = q.execute(); 
//		return result;	
//	}
//
//	public List<Funcionario> consultarVizinhos(String nome){
//		Query q = manager.query();
//		q.constrain(Funcionario.class);
//		q.descend("prateleira").descend("Funcionarios").descend("nome").constrain(nome);
//		q.descend("nome").constrain(nome).not(); // excluir o proprio nome do resultado
//		List<Funcionario> result = q.execute(); 
//		return result;	
//	}

}
