/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;

import java.util.List;

import javax.persistence.Query;

import modelo.Funcionario;
import modelo.Insumo;
import modelo.Prato;

public class DAOInsumo extends DAO<Insumo>  {
	public Insumo readByNome (String nome){	
		Query q = manager.createQuery(
				"select i from Insumo i where i.nome = :name");
		q.setParameter("name", nome);
		List<Insumo> resultados = q.getResultList();
		if (resultados.size()>0)
			return (Insumo) resultados.get(0);
		else
			return null;
	}
	
	public List<Prato> PratosComInsumo (String nome){	
		Query q = manager.createQuery(
				"select p from Insumo i JOIN Prato p where i.nome = :name");
		q.setParameter("name", nome);
		List<Prato> resultados = q.getResultList();
		return resultados;
	}
	
}