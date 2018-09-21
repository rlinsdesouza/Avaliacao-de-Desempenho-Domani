/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package dao;

import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Prateleira;
import modelo.Produto;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
public class DAOPrato  extends DAO<Prateleira>{

	
	public  Prateleira consultarPrateleiraDoProduto(String nome) {
		Query q = manager.query();
		q.constrain(Produto.class);
		q.descend("nome").constrain(nome);
		List<Prateleira> result = q.descend("prateleira").execute(); 
		if(result.size()>0)
			return result.get(0);
		else
			return null; 
	}

	public List<Prateleira> consultarPrateleirasVazias(){
		Query q = manager.query();
		q.constrain(Prateleira.class);
		q.constrain(new Filtro());
		List<Prateleira> result = q.execute(); 
		return result;	
	}
	
	public List<Prateleira> consultarPrateleiraComDoisProdutos(){
		Query q = manager.query();
		q.constrain(Prateleira.class);
		q.constrain(new Filtro2());
		List<Prateleira> result = q.execute(); 
		return result;	
	}
	
	
}

//**************************************************************
@SuppressWarnings("serial")
class Filtro  implements Evaluation {
	public void evaluate(Candidate candidate) {
		Prateleira p = (Prateleira) candidate.getObject();
		candidate.include(p.getProdutos().size()==0);
	}
}

@SuppressWarnings("serial")
class Filtro2  implements Evaluation {
	public void evaluate(Candidate candidate) {
		Prateleira p = (Prateleira) candidate.getObject();
		candidate.include(p.getProdutos().size()==2);
	}
}