/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package dao;

import java.util.ArrayList;
import java.util.List;

import com.db4o.query.Query;

import modelo.Producao;

public class DAOProducao extends DAO<Producao>  {

	
//	public  Prateleira consultarPrateleiraDoProduto(String nome) {
//		Query q = manager.query();
//		q.constrain(Produto.class);
//		q.descend("nome").constrain(nome);
//		List<Prateleira> result = q.descend("prateleira").execute(); 
//		if(result.size()>0)
//			return result.get(0);
//		else
//			return null; 
//	}
//
	public List<Producao> consultarProducoesPorDiaFuncionario(String data){
		Query q = manager.query();
		q.constrain(Producao.class);
		q.descend("data").constrain(data);
		List<Producao> result = q.execute(); 
		return result;	
	}
	
	public List<Producao> consultarProducoesPorDiaFuncionario(String data, int id){
		Query q = manager.query();
		q.constrain(Producao.class);
		q.descend("data").constrain(data);
		List<Producao> result = q.execute();
		List<Producao> resultfilter = new ArrayList ();
		for (Producao producao : result) {
			if (producao.getCozinheiro().getId()==id)
				resultfilter.add(producao);
		}
		return resultfilter;	
	}
//	
//	public List<Prateleira> consultarPrateleiraComDoisProdutos(){
//		Query q = manager.query();
//		q.constrain(Prateleira.class);
//		q.constrain(new Filtro2());
//		List<Prateleira> result = q.execute(); 
//		return result;	
//	}
//	
//	
}

//**************************************************************
//@SuppressWarnings("serial")
//class Filtro  implements Evaluation {
//	public void evaluate(Candidate candidate) {
//		Prateleira p = (Prateleira) candidate.getObject();
//		candidate.include(p.getProdutos().size()==0);
//	}
//}
//
//@SuppressWarnings("serial")
//class Filtro2  implements Evaluation {
//	public void evaluate(Candidate candidate) {
//		Prateleira p = (Prateleira) candidate.getObject();
//		candidate.include(p.getProdutos().size()==2);
//	}
//}