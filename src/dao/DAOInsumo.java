/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Insumo;
import modelo.Prato;

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
	
	public List<Prato> PratosComInsumo (String nome){	
		Query q = manager.query();
		q.constrain(Prato.class);
		q.descend("insumos").descend("nome").constrain(nome);
		List<Prato> resultados = q.execute();
		return resultados;
	}
	
}