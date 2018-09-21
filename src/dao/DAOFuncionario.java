/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package dao;


import java.util.List;

import com.db4o.query.Query;

import modelo.Produto;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
public class DAOFuncionario  extends DAO<Produto>{

	public Produto readByNome (String nome){	
		Query q = manager.query();
		q.constrain(Produto.class);
		q.descend("nome").constrain(nome);
		List<Produto> resultados = q.execute();
		if (resultados.size()>0)
			return (Produto) resultados.get(0);
		else
			return null;
	}
	
	
	public  List<Produto> consultarProdutoSemPareteleira() {
		Query q = manager.query();
		q.constrain(Produto.class);
		q.descend("prateleira").constrain(null);
		return q.execute(); 
	}

	public int consultarTotalProdutos() {
		Query q = manager.query();
		q.constrain(Produto.class);
		int total = q.execute().size(); 
		return total;
	}

	public List<Produto> consultarProdutosDaPrateleira(int id){
		Query q = manager.query();
		q.constrain(Produto.class);
		q.descend("prateleira").descend("id").constrain(id);
		List<Produto> result = q.execute(); 
		return result;	
	}

	public List<Produto> consultarVizinhos(String nome){
		Query q = manager.query();
		q.constrain(Produto.class);
		q.descend("prateleira").descend("produtos").descend("nome").constrain(nome);
		q.descend("nome").constrain(nome).not(); // excluir o proprio nome do resultado
		List<Produto> result = q.execute(); 
		return result;	
	}

}
