/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import modelo.Avaliacao;
import modelo.Producao;

public class DAOAvaliacao extends DAO<Avaliacao>  {

	public List<Producao> consultarProducoesPorDiaFuncionario(String data){
		Query q = manager.createQuery(
				"select p from Producao p where p.data = :data");
		q.setParameter("data", data);
		List<Producao> result = q.getResultList(); 
		return result;	
	}
	
	public List<Producao> consultarProducoesPorDiaFuncionario(String data, int id){
		Query q = manager.createQuery(
				"select p from Producao p where p.data = :data");
		q.setParameter("data", data);
		List<Producao> result = q.getResultList();
		List<Producao> resultfilter = new ArrayList ();
		for (Producao producao : result) {
			if (producao.getCozinheiro().getId()==id)
				resultfilter.add(producao);
		}
		return resultfilter;	
	}	
}