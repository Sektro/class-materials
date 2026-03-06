<?php 

$n = $_GET["n"];
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="test.php" method="get">
        <input type="date" name="n">
        <button type="submit">add 1</button>
    </form>
    <?php if (isset($_GET["n"])) :?>
        <?=$n?><br>
        <?=date("Y") - 2?>
    <?php endif; ?>
</body>
</html>