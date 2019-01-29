/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Query;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;

import modelo.Avaliacao;
import modelo.Producao;

public class DAOProducao extends DAO<Producao>  {

	public List<Producao> consultarProducoesPorDia(String data){
		Query q = manager.createQuery(
				"select p from Producao p where p.data = :data");
		q.setParameter("data", data);
		List<Producao> result = q.getResultList(); 
		return result;	
	}
	
	public List<Producao> consultarProducoesPorDiaFuncionario(String data, int id){
		Query q = manager.createQuery(
				"select p from Producao p where p.data = :data AND p.cozinheiro.id = :id");
		q.setParameter("data", data);
		q.setParameter("id", id);		
		List<Producao> result = q.getResultList();
		return result;	
	}
	
	public List<Producao> consultarProducoesPorDiaFuncionario(String datainicial,String datafinal, int id){
		(dataproducao.isAfter(this.datainicialf)||dataproducao.equals(this.datainicialf))
		&& (dataproducao.isBefore(this.datafinalf)||dataproducao.equals(this.datafinalf))
		&& p.getCozinheiro().getId()==this.idf);
		Query q = manager.createQuery(
				"select p from Producao p where p.data > :data AND p.cozinheiro.id = :id");
		q.setParameter("data", data);
		q.setParameter("id", id);	
		q.constrain(new Filtro(datainicial,datafinal,id));
		List<Producao> result = q.execute();
		return result;	
	}
	
	public List<Avaliacao> ProducaoComAvaliacao (Producao p){	
		Query q = manager.createQuery(
				"select a from Avaliacao a where a.id = :pId");
		q.setParameter("pId", p.getId());
//		q.descend("id").constrain(p.getId());
//		q.descend("avaliacoes");
		List<Avaliacao> resultados = q.getResultList();
		return resultados;
	}

//**************************************************************
@SuppressWarnings("serial")
	class Filtro  implements Evaluation {
		private LocalDate datainicialf;
		private LocalDate datafinalf;
		private int idf;
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	//	DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		public Filtro (String datainicial, String datafinal, int id) {
			this.datainicialf = LocalDate.parse(datainicial, f);
			this.datafinalf = LocalDate.parse(datafinal, f);
			this.idf = id;
		}
		public void evaluate(Candidate candidate) {
			Producao p = (Producao) candidate.getObject();
			LocalDate dataproducao = LocalDate.parse(p.getData(), f);
			candidate.include((dataproducao.isAfter(this.datainicialf)||dataproducao.equals(this.datainicialf))
							&& (dataproducao.isBefore(this.datafinalf)||dataproducao.equals(this.datafinalf))
							&& p.getCozinheiro().getId()==this.idf);
		}
	}
}	