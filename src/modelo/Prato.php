<?php
namespace projeto\modelo;
use \projeto\DAO\IDInterface;
// use modelo\Insumo;

class Prato implements IDInterface {
    private $receita;
    private $dificuldade;
    private $tempoProduzir;
    private $lactose;
    private $gluten;
    private $insumos;
    use \projeto\traits\Nomeavel;

    function __construct (String $nome, String $receita, int $dificuldade, int $tempoProduzir, bool $lactose, bool $gluten, array $insumos = []) {
        $this->nome =  strtoupper($nome);
        $this->receita = $receita;
        $this->dificuldade = $dificuldade;
        $this->tempoProduzir = $tempoProduzir;
        $this->lactose = $lactose;
        $this->gluten = $gluten;
        $this->insumos = $insumos;

    }

    public function getId() : int {
        return $this->id;
    }

    public function setId(int $id) {
        $this->id =  $id;
    }

    public function getNome() : String {
        return $this->nome;
    }

    public function setNome (String $nome) {
        $this->nome = strtoupper($nome);
    }

    public function getReceita () : String {
        return $this->receita;
    }

    public function setReceita (String $receita) {
        $this->receita = $receita;
    }

    public function getDificuldade () : int {
        return $this->dificuldade;
    }

    public function setDificuldade (int $dificuldade) {
        $this->dificuldade =  $dificuldade;
    }

    public function getTempoProduzir () : int {
        return $this->tempoProduzir;
    }

    public function setTempoProduzir (int $tempoProduzir) {
        $this->tempoProduzir = $tempoProduzir;
    }

    public function isLactose () : bool {
        return $this->lactose;
    }

    public function setLactose (bool $lactose) {
        $this->lactose = $lactose;
    }

    public function isGluten () : bool {
        return $this->gluten;
    }

    public function setGluten (bool $gluten) {
        $this->gluten = $gluten;
    }

    public function getInsumos () : array {
        return $this->insumos;
    }

    public function setInsumos (array $insumos) {
        $this->insumos = $insumos;
    }

    public function __toString () {
        $insumos='';
        foreach ($this->insumos as $item) {
            $insumos.= $item.', ';
        }
        return 'Prato: '.$this->getNome().' / Receita: '.$this->getReceita().' / Dificuldade: '.$this->dificuldade.' / Tempo para produzir: '.$this->tempoProduzir.' / Lactose: '.($this->lactose ? 'SIM' : 'NÃO').' / Glúten: '.($this->gluten ? 'SIM' : 'NÃO').'/ Insumos: '.$insumos;
    }

}

?>