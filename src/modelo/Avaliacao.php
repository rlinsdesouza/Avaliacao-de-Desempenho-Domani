<?php
namespace projeto\modelo;
use \projeto\DAO\IDInterface;
// use modelo\Producao;
// use modelo\Funcionario;

class Avaliacao implements IDInterface {
  private $produto;
  private $notaSabor;
  private $notaAparencia;
  private $justificativa;
  private $avaliador;

  public function __construct (Producao $produto, int $notaSabor, int $notaAparencia, String $justificativa, Funcionario $avaliador) {
    $this->produto = $produto;
    $this->notaSabor = $notaSabor;
    $this->notaAparencia = $notaAparencia;
    $this->justificativa = $justificativa;
    $this->avaliador = $avaliador;
  }

  public function getId () : int {
    return $this->id;
  }

  public function setId(int $id) {
    $this->id = $id;
  }

  public function getNotaSabor () : int {
    return $this->notaSabor;
  }

  public function setNotaSabor (int $notaSabor) {
    $this->notaSabor = $notaSabor;
  }

  public function getNotaAparencia () : int {
    return $this->notaAparencia;
  }

  public function setNotaAparencia (int $notaAparencia) {
    $this->notaAparencia = $notaAparencia;
  }

  public function getJustificativa () : String {
    return $this->justificativa;
  }

  public function setJustificativa (String $justificativa) {
    $this->justificativa = $justificativa;
  }

  public function getAvaliador () : Funcionario {
    return $this->avaliador;
  } 

  public function setAvaliador (Funcionario $avaliador) {
    $this->avaliador = $avaliador;
  }

  public function __toString () {
    return 'Produção: '.$this->produto->getPrato()->getNome().'/Cozinheiro: '.$this->produto->getCozinheiro()->getNome().'/'.'Nota sabor: '.$this->notaSabor.'/Nota aparência: '.$this->notaAparencia.'/Justificativa: '.$this->justificativa.'/Avaliador: '.$this->avaliador->getNome();
  }

}

 ?>
