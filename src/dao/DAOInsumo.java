/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Funcionario;
import modelo.Insumo;

public class DAOInsumo extends DAO<Insumo>  {
	public Insumo readByNome (String nome){	
		Query q = manager.query();
		q.constrain(Insumo.class);
		q.descend("nome").constrain(nome);
		List<Insumo> resultados = q.execute();
		if (resultados.size()>0)
			return (Insumo) resultados.get(0);
		else
			return null;
	}
	
}