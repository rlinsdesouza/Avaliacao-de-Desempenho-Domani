<?php
namespace projeto\fachada;
use \projeto\DAO\DAO;
use \projeto\modelo\Funcionario;
use \projeto\modelo\Prato;
use \projeto\modelo\Insumo;
use \projeto\modelo\Avaliacao;
use \projeto\modelo\Producao;
use \projeto\DAO\DAOFuncionario;
use \projeto\DAO\DAOPrato;
use \projeto\DAO\DAOAvaliacao;
use \projeto\DAO\DAOInsumo;
use \projeto\DAO\DAOProducao;

use \Exception;

class Fachada {
	
	private $daoinsumo;
    private $daofuncionario;
    private $daoprato;
	private $daoavaliacao;
	private $daoproducao;
	private $dao;
	
	function __construct () {
		$this->daoinsumo = new DAOInsumo();
		$this->daofuncionario = new DAOFuncionario();
		$this->daoprato = new DAOPrato ();
		$this->daoavaliacao = new DAOAvaliacao();
		$this->daoproducao = new DAOProducao();
		$this->dao = new DAO();	
	}

    function inicializar ()  {
		$this->dao->open();
    }

    function finalizar () {
        $this->dao->close();
    }

    function cadastrarFuncionario (String $nome, String $matricula, String $cpf, String $email, String $dataAdmissao) : Funcionario {

        $this->dao->begin();
        $f = $this->daofuncionario->readByNome($nome);
        if ($f != null) {
            throw new Exception ('já cadastrado: '.$nome); 
        }
        $f = new Funcionario ($nome,$matricula,$cpf, $email, $dataAdmissao);
        $this->daofuncionario->create ($f);
        $this->dao->commit();
        return $f; 
    }

    public function cadastrarPrato (String $nome, String $receita, int $dificuldade, int $tempoProduzir, bool $lactose, bool $gluten, array $insumos) : Prato {
		
		$this->dao->begin();			
		$i = $this->daoprato->readByNome($nome);
		if($i != null) {
			throw new Exception('produto ja cadastrado: '.$nome);
		}
		$i = new Prato($nome,$receita,$dificuldade,$tempoProduzir,$lactose,$gluten,$insumos);
		$this->daoprato->create($i);		
		$this->dao->commit();
		return $i;
    }
    
    public function cadastrarInsumo (String $nome, bool $lactose = false, bool $gluten = false) : Insumo {
		
		$this->dao->begin();			
		$i = $this->daoinsumo->readByNome($nome);
		if($i != null) {
			throw new Exception('produto ja cadastrado: '.$nome);
		}

		$i = new Insumo($nome, $lactose, $gluten);
		$this->daoinsumo->create($i);		
		$this->dao->commit();
		return $i;
    }
    
    public function cadastrarProducao (String $data, Prato $prato, Funcionario $cozinheiro) {
		
		$this->dao->begin();			
		$i = new Producao($data,$prato,$cozinheiro);
		$this->daoproducao->create($i);
		// var_dump($cozinheiro);
		debug_zval_dump($cozinheiro);
		array_push($cozinheiro->getProducoes(),$i);
		debug_zval_dump($cozinheiro);
		// var_dump($cozinheiro);
		$this->daofuncionario->update($cozinheiro);
		$this->dao->commit();
		return $i;
	}
	
	public function cadastrarAvaliacao (Producao &$producao, int $notaSabor, int $notaAparencia, String $justificativa,
			Funcionario $avaliador) {
		
		$this->dao->begin();			
		$i = new Avaliacao($producao,$notaSabor,$notaAparencia,$justificativa,$avaliador);
		$this->daoavaliacao->create($i);
		array_push($producao->getAvaliacoes(),$i);
		$this->daoproducao->update($producao);
		$this->dao->commit();
		return $i;
	}
	
	public function removerPrato (Prato $p) {
		$this->dao->begin();
	    $l = $this->daoprato->ProducoesComPrato($p->getNome());
		if (count($l) == 0) {
			$this->daoprato->delete($p);			
		}else {
			throw new Exception ("Impossível excluir, prato com producao vinculada!");
		}
		$this->dao->commit();
		return $p;
	}
	
	public function removerInsumo (Insumo $p) {
		$this->dao->begin();
		$l = $this->daoinsumo->PratosComInsumo($p.getNome());
		if (count($l)==0) {
			$this->daoinsumo.delete($p);		
		}else {
			throw new Exception ("Impossível excluir, insumo com pratos vinculados!");
		}
		$this->dao->commit();
		return $p;
	}
	
	public function removerProducao (Producao $p) {
		$this->dao->begin();
		p.getCozinheiro().getProducoes().remove(p);
		daofuncionario.update(p.getCozinheiro());
		daoproducao.delete(p);		
		DAO.commit();
		return p;
	}
	
	public function removerAvaliacao (Avaliacao $p) {
		DAO.begin();
		p.getProduto().getAvaliacoes().remove(p);
		daoproducao.update(p.getProduto());
		daoavaliacao.delete(p);	
		DAO.commit();
		return p;
	}
	
	public function listarProducoes () {
		return $this->daoproducao->readAll();
	}
	
	public function listarProducoesPorData (String $data) {
		return $this->daoproducao->consultarProducoesPorDia($data);
	}
	
	public function listarProducoesPorDataFuncionario (String $data, int $id) {
		return $this->daoproducao->consultarProducoesPorDiaFuncionario($data,$id);
	}
	
	public function listarProducoesPorDataFuncionario2 (String $datainicial, String $datafinal, int $id) {
		return $this->daoproducao->consultarProducoesPorDiaFuncionario($datainicial,$datafinal,$id);
	}
	
	public function listarFuncionarios () {
		return $this->daofuncionario->readAll();
	}
	
	public function listarFuncionarios2 (String $nome) {
		return $this->daofuncionario->readAll($nome);
	}
	
	public function listarPratos () {
		return $this->daoprato->readAll();
	}
	
	public function listarPratos2 (String $nome) {
		return $this->daoprato->readAll($nome);
	}

	public function listarInsumo() {
		return $this->daoinsumo->readAll();
	}
	
	public function listarInsumo2(String $nome) {
		return $this->daoinsumo->readAll($nome);
	}

	public function localizarInsumo(int $id) : Insumo {
		return $this->daoinsumo->read($id);
	}
	
	public function localizarPrato(int $id ) : Prato{
		return $this->daoprato->read($id);
	}
	
	public function localizarFuncionario(int $id) : Funcionario {
		return $this->daofuncionario->read($id);
	}

	public function atualizarInsumo(int $id,String $nome,bool $lactose,bool $gluten) : Insumo {
		$p = $this->daoinsumo->read($id);
		$p->setNome($nome);
		$p->setLactose($lactose);
		$p->setGluten($gluten);
		$this->daoinsumo->update($p);
		return $p;
	}
	
	public function atualizarPrato(int $id,String $nome,String $receita,int $dificuldade,int $tempo,bool $lactose,bool $glutem,array $insumos) : Prato {
		$p = $this->daoprato->read($id);
		$p->setNome($nome);
		$p->setReceita($receita);
		$p->setDificuldade($dificuldade);
		$p->setTempoProduzir($tempo);
		$p->setLactose($lactose);
		$p->setGluten($glutem);
		$p->setInsumos($insumos);
		$this->daoprato.update($p);
		return $p;
	}
	
	public function atualizarFuncionario(int $id, int $matricula, String $nome, String $cpf, array $telefone, String $email, String $senha,String $salt,
			String $dataAdmissao, String $dataDemissao, array $producoes) : Funcionario {
		$p = $this->daofuncionario->read($id);
		$p->setNome($nome);
		$p->setMatricula($matricula);
		if ($senha != null) {
			$p->setSenha($senha);
			$p->setSalt($salt);
		}
		$this->daofuncionario->update($p);
		return $p;
	}
	
	public function atualizarLactoseGluten (Prato $p) : Prato {
		$insumos = $p->getInsumos();
		if ($insumos != null && !(count($insumos)==0)) {
			$p->setLactose(false);
			$p->setGluten(false);
			foreach ($insumos as $insumo) {
				if ($insumo->isGluten())
					$p->setGluten(true);
				if ($insumo.isLactose())
					$p->setLactose(true);
			}
			$this->daoprato->update($p);
			return $p;	
		}else {
			throw new Exception ("Produto sem insumos cadastrados!");
		}
	}
	
	public function calculaNotaProducoes (array $p) : double {
		$nota = 0.0;
		$numerador=0.0;
		$denominador=0.0;
		
		foreach ($p as $producao) {
			if (count(!$producao->getAvaliacoes())==0) {
				foreach ($producao->getAvaliacoes() as $a) {
					$numerador = $numerador + (((($a->getNotaAparencia()+$a->getNotaSabor())/2)*$producao->getPrato()->getDificuldade()));
					$denominador = $denominador + $producao->getPrato()->getDificuldade();
				}
			}
		}
		$nota = $numerador/$denominador;		
		return $nota;
	}

}


?>