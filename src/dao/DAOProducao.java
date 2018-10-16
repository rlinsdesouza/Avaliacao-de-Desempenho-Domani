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

import modelo.Avaliacao;
import modelo.Prato;
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
	
	public List<Avaliacao> ProducaoComAvaliacao (Producao p){	
		Query q = manager.query();
		q.constrain(Producao.class);
		q.descend("avaliacoes");
		List<Avaliacao> resultados = q.execute();
		return resultados;
	}

//**************************************************************
@SuppressWarnings("serial")
class Filtro  implements Evaluation {
	private LocalDate datainicialf;
	private LocalDate datafinalf;
	private int idf;
	
	DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public Filtro (String datainicial, String datafinal, int id) {
		this.datainicialf = LocalDate.parse(datainicial, f);
		this.datafinalf = LocalDate.parse(datafinal, f);
		this.idf = id;
	}
	public void evaluate(Candidate candidate) {
		Producao p = (Producao) candidate.getObject();
		LocalDate dataproducao = LocalDate.parse(p.getData(), f2);
		candidate.include((dataproducao.isAfter(this.datainicialf)||dataproducao.equals(this.datainicialf))
						&& (dataproducao.isBefore(this.datafinalf)||dataproducao.equals(this.datafinalf))
						&& p.getCozinheiro().getId()==this.idf);
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