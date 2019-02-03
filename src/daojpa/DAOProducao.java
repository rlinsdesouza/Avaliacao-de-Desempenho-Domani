/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daojpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
		Query q = manager.createQuery(
				"select p from Producao p where "
				+ "(p.data > :datainicial OR p.data = :datainicial)"
				+ "AND"
				+ "(p.data < :datafinal OR p.data = :datafinal)"
				+ "AND"
				+ "(p.cozinheiro.id = :id)");
		q.setParameter("datainicial", datainicial);
		q.setParameter("datafinal", datafinal);
		q.setParameter("id", id);	
		List<Producao> result = q.getResultList();
		return result;	
	}
	
}
//**************************************************************
