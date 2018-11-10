<?php
namespace projeto\modelo;
use \projeto\DAO\IDInterface;

class Insumo implements IDInterface {
    private $lactose;
	private $gluten;
	use \projeto\traits\Nomeavel;
	
    public function __construct (String $nome, bool $lactose = false, bool $gluten = false) {
        $this->nome = strtoupper($nome);
        $this->lactose = $lactose;
        $this->gluten = $gluten;
    }

	public function getId() : int {
		return $id;
	}

	public function setId(int $id) {
		$this->id = $id;
	}

	public function getNome() : String{
		return $this->nome;
	}

	public function setNome(String $nome) {
		$this->nome = strtoupper($nome);
	}

	public function isLactose() : bool {
		return $lactose;
	}

	public function setLactose(bool $lactose) {
		$this->lactose = $lactose;
	}

	public function isGluten() : bool {
		return $this->gluten;
	}

	public function setGluten (bool $gluten) {
		$this->gluten = $gluten;
	}

	public function __toString()
	{
		return 'Nome: '.$this->getNome().'Possui lactose? '.($this->lactose ? 'SIM' : 'NÃO').'Possui glútem? '.($this->gluten ? 'SIM' : 'NÃO');
	}
}


?>