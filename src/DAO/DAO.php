<?php
namespace projeto\DAO;
use \projeto\dados\Restaurante;

class DAO implements \projeto\DAO\DAOInterface {

    public function create ($obj) {
        $classe = 'add'.$this->get_class_name(get_class($obj));
        Restaurante::$classe($obj);
    }

    public function read (int $id) {

    }

    public function update ($obj) {
        // $classe = 'get'.$this->get_class_name(get_class($obj)).'s';
        // $producoes = Restaurante::$classe;
        // foreach ($producoes as $key => $value) {
        //     if 
        // }
    }

    public function delete ($obj) {

    }

    public function refresh ($obj) {

    } 

    public function readAll()
    {
        
    }


    public static function begin () {

    }

    public static function commit() {

    }

    public static function rollback(){

    }

    function get_class_name($classname)
{
    if ($pos = strrpos($classname, '\\')) return substr($classname, $pos + 1);
    return $pos;
}

}

?>