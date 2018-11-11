<?php
namespace projeto\DAO;
use \projeto\DAO\DAO;
use projeto\dados\Restaurante;

class DAOInsumo extends DAO {

    public function readByNome (String $nome) {
        $insumos = Restaurante::getInsumos();

        foreach ($insumos as $value) {
            if ($value->getNome()==$nome){
                return $value;
            } 
        }
        return null;
    }
}

?>