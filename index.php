    <?php

    //include_once "autoload.php";
    require __DIR__ . '/vendor/autoload.php';
    use \projeto\modelo\Insumo;
    use \projeto\modelo\Prato;
    use \projeto\modelo\Funcionario;
    use \projeto\modelo\Producao;
    use \projeto\modelo\Avaliacao;

    $insumo1 =  new Insumo ('Creme de Leite',true,false);
    $insumo2 = new Insumo ('Maizena');



    $prato = new Prato ('Testando', 'Faça devagar pra não se perder',1, 365, false, false,array($insumo1,$insumo2)); 
    echo $prato.'<br>';

    $funcionario1 = new Funcionario ('Rafael Lins','1','073.975.104-26','linsdesousa@hotmail.com','2010-05-10');
    $funcionario2 = new Funcionario ('Dora','2','','','2010-05-10');
    
    $producao = new Producao ('2018-10-14',$prato,$funcionario2);
    echo $producao.'<br>';

    $avaliacao = new Avaliacao ($producao,10,5,'parecia uma gororoba',$funcionario1);
    echo $avaliacao.'<br>';

    ?>