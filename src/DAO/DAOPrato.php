<?php
namespace projeto\DAO;
use \projeto\DAO\DAO;
use \projeto\dados\Restaurante;

class DAOPrato extends DAO {

    public function readByNome (String $nome) {
        $insumos = Restaurante::getPratos();

        foreach ($insumos as $value) {
            if ($value->getNome()==$nome){
                return $value;
            } 
        }
        return null;
    }

}


?>