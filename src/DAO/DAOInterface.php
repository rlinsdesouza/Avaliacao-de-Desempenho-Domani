<?php
namespace projeto\DAO;

interface DAOInterface  {
    public function create ( $obj);
    public function read (int $id);
    public function update ( $obj);
    public function delete ( $obj);
    public function readAll();
}
?>