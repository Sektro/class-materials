<?php
    $a = $_GET["a"] ?? 0;
    $b = $_GET["b"] ?? 0;
    $c = $_GET["c"] ?? 0;

    if ($_GET) { // igy csak akkor dolgozzuk fel, ha kaptunk értéket 

    }
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form method="get" action="2.php">
        a = <input type="number" name="a"><br>
        b = <input type="number" name="b"><br>
        c = <input type="number" name="c"><br>
        <button type="submit">Solve quadratic equation</button>
    </form>
    <?php if (isset($_GET["a"])&&isset($_GET["b"])&&isset($_GET["c"])): ?>
    <?=$a?>x^2 + <?=$b?>x + <?=$c?><br>
    <?php if ($b * $b - 4*$a*$c >= 0): ?>
    x1: <?= ((-1)*$b + sqrt($b * $b - 4*$a*$c)) / 2*$a?><br>
    x2: <?= ((-1)*$b - sqrt($b * $b - 4*$a*$c)) / 2*$a?><br>
    <?php else: ?>
        x1: <?= -$b / 2*$a . ' + ' . sqrt(-($b * $b - 4*$a*$c)) / 2*$a . 'i'?><br>
        x2: <?= -$b / 2*$a . ' - ' . sqrt(-($b * $b - 4*$a*$c)) / 2*$a   . 'i'?><br>
    <?php endif;?>
    <?php endif;?>
</body>
</html>