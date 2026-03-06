<?php
    $n = $_GET["n"] ?? 0; //ha post-ot használtunk, akkor $_POST   |   ?? 0 --> alapérték legyen 0

?>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form method="get" action="1.php"> <!-- form azért kell, hogy a scriptnek elküldjük a html-es eredményeket  |  get/post  |  action lehet más php, azt jelöljük, hogy hol dolgozzuk fel az elküldött infot-->
        <input type="number" name="n" value="<?= $n ?>">
        <button>Square me!</button>
    </form>

    <?php if (isset($_GET["n"])): ?>
    Squared: <?= $n * $n?>
    <?php endif?>
</body>
</html>