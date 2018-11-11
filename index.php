    <?php

    //include_once "autoload.php";
    require __DIR__ . '/vendor/autoload.php';
    use \projeto\modelo\Insumo;
    use \projeto\modelo\Prato;
    use \projeto\modelo\Funcionario;
    use \projeto\modelo\Producao;
    use \projeto\modelo\Avaliacao;
    use \projeto\fachada\Fachada;
    use \projeto\dados\Restaurante;

    $fachada = new Fachada ();

    $insumo1 = $fachada->cadastrarInsumo('Creme de Leite',true,false);
    $insumo2 = $fachada->cadastrarInsumo('Maizena');

    

    $prato = $fachada->cadastrarPrato('Testando', 'Faça devagar pra não se perder',1, 365, false, false,array($insumo1,$insumo2)); 
    echo $prato.'<br>';

    $funcionario1 = $fachada->cadastrarFuncionario('Rafael Lins','1','073.975.104-26','linsdesousa@hotmail.com','2010-05-10');
    $funcionario2 = $fachada->cadastrarFuncionario('Dora','2','','','2010-05-10');
    
    $producao = $fachada->cadastrarProducao('2018-10-14',$prato,$funcionario2);
    echo $producao.'<br>';

    $avaliacao = new Avaliacao ($producao,10,5,'parecia uma gororoba',$funcionario1);
    echo $avaliacao.'<br>';

    ?>