<?php 
    $reg = json_decode(file_get_contents('data.json'), true);

    if (!isset($_GET['taj'])) {
        header('location: index.php');
        exit();
    }

    $taj = $_GET['taj'];

    if (!isset($reg[$taj])) {
        header('location: index.php');
        exit();
    }

    $r = $reg[$taj];
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    Teljes név: <?= $r['fullname'] ?><br>
    E-mail: <br>
    ... <br>
    <a href="delete.php?taj=<?= $taj ?>">Törlés</a>
</body>
</html>