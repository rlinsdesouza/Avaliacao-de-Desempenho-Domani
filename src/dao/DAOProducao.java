/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Producao;

public class DAOProducao extends DAO<Producao>  {

	public List<Producao> consultarProducoesPorDia(String data){
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
		q.descend("cozinheiro").descend("id").constrain(id);
		List<Producao> result = q.execute();
		return result;	
	}
	
	public List<Producao> consultarProducoesPorDiaFuncionario(String datainicial,String datafinal, int id){
		Query q = manager.query();
		q.constrain(Producao.class);
		q.constrain(new Filtro(datainicial,datafinal,id));
		List<Producao> result = q.execute();
		return result;	
	}

//**************************************************************
@SuppressWarnings("serial")
class Filtro  implements Evaluation {
	private LocalDate datainicial;
	private LocalDate datafinal;
	private int id;
	
	DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Filtro (String datainicial, String datafinal, int id) {
		this.datainicial = LocalDate.parse(datainicial, f);
		this.datafinal = LocalDate.parse(datafinal, f);
		this.id = id;
	}
	public void evaluate(Candidate candidate) {
		Producao p = (Producao) candidate.getObject();
		LocalDate dataproducao = LocalDate.parse(p.getData(), f);
		candidate.include((dataproducao.isAfter(this.datainicial)||dataproducao.equals(this.datainicial))
						&& (dataproducao.isBefore(this.datafinal)||dataproducao.equals(this.datafinal))
						&& p.getCozinheiro().getId()==this.id);
	}
}

//@SuppressWarnings("serial")
//class Filtro2  implements Evaluation {
//	public void evaluate(Candidate candidate) {
//		Prateleira p = (Prateleira) candidate.getObject();
//		candidate.include(p.getProdutos().size()==2);
//	}
//}
}	