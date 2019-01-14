/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;

import java.util.List;

import javax.persistence.Query;

import modelo.Prato;
import modelo.Producao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
public class DAOPrato  extends DAO<Prato>{
	
	public Prato readByNome (String nome){	
		Query q = manager.createQuery(
				"select p from Prato p where p.nome = :name");
		q.setParameter("name", nome);
		List<Prato> resultados = q.getResultList();
		if (resultados.size()>0)
			return (Prato) resultados.get(0);
		else
			return null;
	}
	
//	public List<Producao> ProducoesComPrato (String nome){	
//		Query q = manager.query();
//		q.constrain(Producao.class);
//		q.descend("prato").descend("nome").constrain(nome);
//		List<Producao> resultados = q.execute();
//		return resultados;
//	}

	
}

//**************************************************************
//@SuppressWarnings("serial")
//class Filtro  implements Evaluation {
//	public void evaluate(Candidate candidate) {
//		Prato p = (Prato) candidate.getObject();
//		candidate.include(p.getProdutos().size()==0);
//	}
//}
//
//@SuppressWarnings("serial")
//class Filtro2  implements Evaluation {
//	public void evaluate(Candidate candidate) {
//		Prato p = (Prato) candidate.getObject();
//		candidate.include(p.getProdutos().size()==2);
//	}
//}