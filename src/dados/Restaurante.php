<?php
namespace projeto\dados;
use \projeto\modelo\Funcionario;
use \projeto\modelo\Prato;
use \projeto\modelo\Insumo;
use \projeto\modelo\Avaliacao;
use \projeto\modelo\Producao;

class Restaurante {

    private static $avaliacaos =[];
    private static $idavaliacaos = 0;
    private static $funcionarios =[];
    private static $idfuncionarios = 0;
    private static $insumos =[];
    private static $idinsumos =0;
    private static $pratos=[];
    private static $idpratos=0;
    private static $producaos=[];
    private static $idproducaos=0;




    public static function addAvaliacao (Avaliacao $ava) {
        array_push(self::$avaliacaos, $ava);
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
        array_push(self::$producaos, $ava);
    }


    /**
     * Get the value of avaliacaoes
     */ 
    public static function getAvaliacaos()
    {
        return self::$avaliacaos;
    }

    /**
     * Set the value of avaliacaoes
     *
     * @return  self
     */ 
    public static function setAvaliacaos($avaliacaos)
    {
        array_ṕush(self::$avaliacaos, $avaliacaos);
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
     * Get the value of producaos
     */ 
    public static function getProducaos()
    {
        return self::$producaos;
    }

    /**
     * Set the value of producaos
     *
     * @return  self
     */ 
    public static function setProducaos($producaos)
    {
        array_push(self::$producaos , $producaos);

    }

    /**
     * Get the value of idavaliacaoes
     */ 
    public static function getIdavaliacaos()
    {
        return ++self::$idavaliacaos;
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
     * Get the value of idproducaos
     */ 
    public static function getIdproducaos()
    {
        return ++self::$idproducaos;
    }

    /**
     * Get the value of funcionarios
     */ 
    public static function getFuncionarios()
    {
        return self::$funcionarios;
    }
}

?>