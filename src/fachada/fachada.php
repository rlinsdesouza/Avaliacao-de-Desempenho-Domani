<?php
namespace projeto\fachada;
use \projeto\DAO\DAO;
use \projeto\modelo\Funcionario;
use \projeto\modelo\Prato;
use \projeto\modelo\Insumo;
use \projeto\modelo\Avaliacao;
use \projeto\DAO\DAOInsumo;
use \Exception;

class Fachada {
    private static $daoinsumo = new DAOInsumo();
    private static $daofuncionario = new DAOFuncionario();
    private static $daoprato = new DAOPrato ();
    private static $daoavaliacao = new DAOAvaliacao ();

    static function inicializar ()  {
        DAO::open();
    }

    static function finalizar () {
        DAO::close();
    }

    static function cadastrarFuncionario (String $nome, String $matricula, String $cpf, String $email, String $dataAdmissao) : Funcionario {

        DAO::begin();
        $f = self::$daofuncionario->readByNome($nome);
        if ($f != null) {
            throw new Exception ('já cadastrado: '.$nome); 
        }
        $f = new Funcionario ($nome,$matricula,$cpf, $email, $dataAdmissao);
        self::$daofuncionario->create ($f);
        DAO::commit();
        return $f; 
    }

    public static function cadastrarPrato (String $nome, String $receita, int $dificuldade, int $tempoProduzir, bool $lactose, bool $gluten, array $insumos) : Prato {
		
		DAO::begin();			
		$i = self::$daoprato->readByNome($nome);
		if($i != null) {
			throw new Exception('produto ja cadastrado: '.$nome);
		}
		$i = new Prato($nome,$receita,$dificuldade,$tempoProduzir,$lactose,$gluten,$insumos);
		self::$daoprato.create($i);		
		DAO::commit();
		return $i;
    }
    
    public static function cadastrarInsumo (String $nome, bool $lactose, bool $gluten) : Insumo {
		
		DAO::begin();			
		$i = self::$daoinsumo->readByNome($nome);
		if($i != null) {
			throw new Exception('produto ja cadastrado: '.$nome);
		}

		$i = new Insumo($nome, $lactose, $gluten);
		self::$daoinsumo->create($i);		
		DAO::commit();
		return $i;
    }
    
    public static function cadastrarProducao (String $data, Prato $prato, Funcionario $cozinheiro) {
		
		DAO::begin();			
		$i = new Producao($data,$prato,$cozinheiro);
		self::$daoproducao->create($i);
		array_push(self::$cozinheiro->getProducoes(),$i);
		self::$daofuncionario->update($cozinheiro);
		DAO::commit();
		return $i;
	}
	
	public static function cadastrarAvaliacao (Producao $producao, int $notaSabor, int $notaAparencia, String $justificativa,
			Funcionario $avaliador) {
		
		DAO::begin();			
		$i = new Avaliacao($producao,$notaSabor,$notaAparencia,$justificativa,$avaliador);
		self::$daoavaliacao->create($i);
		array_push(self::$producao->getAvaliacoes(),$i);
		self::$daoproducao->update($producao);
		DAO::commit();
		return $i;
	}
	
	public static function removerPrato (Prato $p) {
		DAO::begin();
	    $l = self::$daoprato->ProducoesComPrato($p->getNome());
		if (count($l) == 0) {
			self::$daoprato->delete($p);			
		}else {
			throw new Exception ("Impossível excluir, prato com producao vinculada!");
		}
		DAO::commit();
		return $p;
	}
	
	public static function removerInsumo (Insumo $p) {
		DAO::begin();
		$l = self::$daoinsumo->PratosComInsumo($p.getNome());
		if (count($l)==0) {
			self::$daoinsumo.delete($p);		
		}else {
			throw new Exception ("Impossível excluir, insumo com pratos vinculados!");
		}
		DAO::commit();
		return $p;
	}
	
	public static function removerProducao (Producao $p) {
		DAO::begin();
		p.getCozinheiro().getProducoes().remove(p);
		daofuncionario.update(p.getCozinheiro());
		daoproducao.delete(p);		
		DAO.commit();
		return p;
	}
	
	public static function removerAvaliacao (Avaliacao $p) {
		DAO.begin();
		p.getProduto().getAvaliacoes().remove(p);
		daoproducao.update(p.getProduto());
		daoavaliacao.delete(p);	
		DAO.commit();
		return p;
	}
	
	public static function listarProducoes () {
		return self::$daoproducao->readAll();
	}
	
	public static function listarProducoesPorData (String $data) {
		return self::$daoproducao->consultarProducoesPorDia($data);
	}
	
	public static function listarProducoesPorDataFuncionario (String $data, int $id) {
		return self::$daoproducao->consultarProducoesPorDiaFuncionario($data,$id);
	}
	
	public static function listarProducoesPorDataFuncionario (String $datainicial, String $datafinal, int $id) {
		return self::$daoproducao->consultarProducoesPorDiaFuncionario($datainicial,$datafinal,$id);
	}
	
	public static function listarFuncionarios () {
		return self::$daofuncionario->readAll();
	}
	
	public static function listarFuncionarios (String $nome) {
		return self::$daofuncionario->readAll($nome);
	}
	
	public static function listarPratos () {
		return self::$daoprato->readAll();
	}
	
	public static function listarPratos (String $nome) {
		return self::$daoprato->readAll($nome);
	}

	public static function listarInsumo() {
		return self::$daoinsumo->readAll();
	}
	
	public static function listarInsumo(String $nome) {
		return self::$daoinsumo->readAll($nome);
	}

	public static function localizarInsumo(int $id) : Insumo {
		return self::$daoinsumo->read($id);
	}
	
	public static function localizarPrato(int $id ) : Prato{
		return self::$daoprato->read($id);
	}
	
	public static function localizarFuncionario(int $id) : Funcionario {
		return self::$daofuncionario->read($id);
	}

	public static function atualizarInsumo(int $id,String $nome,bool $lactose,bool $gluten) : Insumo {
		$p = self::$daoinsumo->read($id);
		$p->setNome($nome);
		$p->setLactose($lactose);
		$p->setGluten($gluten);
		self::$daoinsumo->update($p);
		return $p;
	}
	
	public static function atualizarPrato(int $id,String $nome,String $receita,int $dificuldade,int $tempo,bool $lactose,bool $glutem,array $insumos) : Prato {
		$p = self::$daoprato->read($id);
		$p->setNome($nome);
		$p->setReceita($receita);
		$p->setDificuldade($dificuldade);
		$p->setTempoProduzir($tempo);
		$p->setLactose($lactose);
		$p->setGluten($glutem);
		$p->setInsumos($insumos);
		self::$daoprato.update($p);
		return $p;
	}
	
	public static function atualizarFuncionario(int $id, int $matricula, String $nome, String $cpf, array $telefone, String $email, String $senha,String $salt,
			String $dataAdmissao, String $dataDemissao, array $producoes) : Funcionario {
		$p = self::$daofuncionario->read($id);
		$p->setNome($nome);
		$p->setMatricula($matricula);
		if ($senha != null) {
			$p->setSenha($senha);
			$p->setSalt($salt);
		}
		self::$daofuncionario->update($p);
		return $p;
	}
	
	public static function atualizarLactoseGluten (Prato $p) : Prato {
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
			self::$daoprato->update($p);
			return $p;	
		}else {
			throw new Exception ("Produto sem insumos cadastrados!");
		}
	}
	
	public static function calculaNotaProducoes (array $p) : double {
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