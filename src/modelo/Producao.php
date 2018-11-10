<?php
namespace projeto\modelo;
use \projeto\DAO\IDInterface;
// use modelo\Prato;
// use modelo\Funcionario;

class Producao implements IDInterface {
  private $data;
  private $prato;
  private $cozinheiro;
  private $avaliacoes;

  public function __construct (String $data,Prato $prato, Funcionario $cozinheiro ) {
    $this->data=$data;
    $this->prato=$prato;
    $this->cozinheiro = $cozinheiro;
    $this->avaliacoes= [];
  }

  public function getId () : int {
    return $this->id;
  }

  public function setId (int $id) {
    $this->id = $id;
  }

  public function getData () : String {
    return $this->data;
  }

  public function setData (String $data) {
    $this->data = $data;
  }

  public function getPrato () : Prato {
    return $this->prato;
  }

  public function setPrato (Prato $prato) {
    $this->prato = $prato;
  }

  public function getCozinheiro () : Funcionario {
    return $this->cozinheiro;
  }

  public function setCozinheiro (Funcionario $cozinheiro) {
    $this->cozinheiro = $cozinheiro;
  }

  public function getAvaliacoes () : array {
    return $this->avaliacoes;
  }

  public function setAvaliacoes (array $avaliacoes) {
    $this->avaliacoes = $avaliacoes;
  }

  public function __toString()
  {
    return 'Data: '.$this->data.'/Prato: '.$this->prato->getNome().'/Cozinheiro: '.$this->cozinheiro->getNome ().'/Quant avaliações: '.count($this->avaliacoes);
  }

}

 ?>
