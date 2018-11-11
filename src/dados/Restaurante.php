<?php
namespace projeto\dados;
use \projeto\modelo\Funcionario;
use \projeto\modelo\Prato;
use \projeto\modelo\Insumo;
use \projeto\modelo\Avaliacao;
use \projeto\modelo\Producao;

class Restaurante {

    private static $avaliacaoes =[];
    private static $idavaliacaoes = 0;
    private static $funcionarios =[];
    private static $idfuncionarios = 0;
    private static $insumos =[];
    private static $idinsumos =0;
    private static $pratos=[];
    private static $idpratos=0;
    private static $producoes=[];
    private static $idproducoes=0;




    public static function addAvaliacao (Avaliacao $ava) {
        array_push(self::$avaliacoes, $ava);
    }


    public static function addFuncionario (Funcionario $ava) {
        array_push(self::$funcionarios, $ava);
    }


    public static function addInsumo (Insumo $ava) {
        array_push(self::$insumos, $ava);
    }


    public static function addPrato (Prato $ava) {
        array_push(self::$pratos, $ava);
    }

    public static function addProducao (Producao $ava) {
        array_push(self::$producoes, $ava);
    }


    /**
     * Get the value of avaliacaoes
     */ 
    public static function getAvaliacaoes()
    {
        return self::$avaliacaoes;
    }

    /**
     * Set the value of avaliacaoes
     *
     * @return  self
     */ 
    public static function setAvaliacaoes($avaliacaoes)
    {
        array_ṕush(self::$avaliacaoes, $avaliacaoes);
    }

    /**
     * Get the value of insumos
     */ 
    public static function getInsumos()
    {
        return self::$insumos;
    }

    /**
     * Set the value of insumos
     *
     * @return  self
     */ 
    public static function setInsumos($insumos)
    {
        array_push(self::$insumos,$insumos);

    }

    /**
     * Get the value of pratos
     */ 
    public static function getPratos()
    {
        return self::$pratos;
    }

    /**
     * Set the value of pratos
     *
     * @return  self
     */ 
    public static function setPratos($pratos)
    {
        array_push(self::$pratos,$pratos);

    }

    /**
     * Get the value of producoes
     */ 
    public function getProducoes()
    {
        return self::$producoes;
    }

    /**
     * Set the value of producoes
     *
     * @return  self
     */ 
    public static function setProducoes($producoes)
    {
        array_push(self::$producoes , $producoes);

    }

    /**
     * Get the value of idavaliacaoes
     */ 
    public static function getIdavaliacaoes()
    {
        return ++self::$idavaliacaoes;
    }

    /**
     * Get the value of idfuncionarios
     */ 
    public static function getIdfuncionarios()
    {
        return ++self::$idfuncionarios;
    }

    /**
     * Get the value of idinsumos
     */ 
    public static function getIdinsumos()
    {
        return ++self::$idinsumos;
    }

    /**
     * Get the value of idpratos
     */ 
    public static function getIdpratos()
    {
        return ++self::$idpratos;
    }

    /**
     * Get the value of idproducoes
     */ 
    public static function getIdproducoes()
    {
        return ++self::$idproducoes;
    }
}

?>