<?php
namespace projeto\modelo;
use \projeto\DAO\IDInterface;
use \projeto\traits\Nomeavel;
// use \projeto\modelo\Producao;

class Funcionario implements IDInterface {
    private $matricula;
    private $cpf;
    private $telefones;
    private $email;
    private $senha;
    private $dataAdmissao;
    private $dataDemissao;
    private $producoes;

    public function __construct(String $nome, String $matricula, String $cpf, String $email, String $dataAdmissao)
    {
        $this->nome = $nome;
        $this->matricula = $matricula;
        $this->cpf =  $cpf;
        $this->email = $email;
        $this->dataAdmissao = $dataAdmissao;
        $this->producoes = [];
    }

    public function getId() : int {
        return $this->id;
    }

    public function setId(int $id) {
        $this->id = $id;
    }


    public function getNome() : String {
        return $this->nome;
    }

    public function setNome(String $nome) {
        $this->nome = $nome;
    }

    /**
     * Get the value of matricula
     */ 
    public function getMatricula() : int
    {
        return $this->matricula;
    }

    /**
     * Set the value of matricula
     *
     * @return  self
     */ 
    public function setMatricula(int $matricula)
    {
        $this->matricula = $matricula;

        return $this;
    }

    /**
     * Get the value of cpf
     */ 
    public function getCpf() : String
    {
        return $this->cpf;
    }

    /**
     * Set the value of cpf
     *
     * @return  self
     */ 
    public function setCpf(String $cpf)
    {
        $this->cpf = $cpf;

        return $this;
    }

    /**
     * Get the value of telefones
     */ 
    public function getTelefones() : array
    {
        return $this->telefones;
    }

    /**
     * Set the value of telefones
     *
     * @return  self
     */ 
    public function setTelefones(array $telefones)
    {
        $this->telefones = $telefones;

        return $this;
    }

    /**
     * Get the value of email
     */ 
    public function getEmail() : String
    {
        return $this->email;
    }

    /**
     * Set the value of email
     *
     * @return  self
     */ 
    public function setEmail(String $email)
    {
        $this->email = $email;

        return $this;
    }

    /**
     * Get the value of senha
     */ 
    public function getSenha() : String
    {
        return $this->senha;
    }

    /**
     * Set the value of senha
     *
     * @return  self
     */ 
    public function setSenha(String $senha)
    {
        $this->senha = $senha;

        return $this;
    }

    /**
     * Get the value of dataAdmissao
     */ 
    public function getDataAdmissao() : String
    {
        return $this->dataAdmissao;
    }

    /**
     * Set the value of dataAdmissao
     *
     * @return  self
     */ 
    public function setDataAdmissao(String $dataAdmissao)
    {
        $this->dataAdmissao = $dataAdmissao;

        return $this;
    }

    /**
     * Get the value of dataDemissao
     */ 
    public function getDataDemissao() : String
    {
        return $this->dataDemissao;
    }

    /**
     * Set the value of dataDemissao
     *
     * @return  self
     */ 
    public function setDataDemissao(String $dataDemissao)
    {
        $this->dataDemissao = $dataDemissao;

        return $this;
    }

    /**
     * Get the value of producoes
     */ 
    public function getProducoes() : array
    {
        return $this->producoes;
    }

    /**
     * Set the value of producoes
     *
     * @return  self
     */ 
    public function setProducoes(array $producoes)
    {
        $this->producoes = $producoes;

        return $this;
    }

    public function __toString()
    {
        return 'Nome Funcionário: '.$this->getNome();
    }
} 


?>