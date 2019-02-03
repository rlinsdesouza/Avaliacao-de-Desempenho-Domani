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
		q.setParameter("name", nome.toUpperCase());
		List<Funcionario> resultados = q.getResultList();
		if (resultados.size()>0)
			return (Funcionario) resultados.get(0);
		else
			return null;
	}

}
