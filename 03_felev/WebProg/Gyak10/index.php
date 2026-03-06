<?php 
    $reg = json_decode(file_get_contents('data.json'), true);

    usort($reg, fn($a, $b) => strcmp($a['fullname'], $b['fullname']));
?>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php foreach($reg as $r): ?>
        <li><a href="show.php?taj=<?= $r['taj'] ?>"> $r[$fullname]; </a> </li>
    <?php endforeach;?>
</body>
</html>


<!--
*****RENDEZŐ FÜGGVÉNYEK*****

sort() - sort arrays in ascending order
rsort() - sort arrays in descending order
asort() - sort associative arrays in ascending order, according to the value
ksort() - sort associative arrays in ascending order, according to the key
arsort() - sort associative arrays in descending order, according to the value
krsort() - sort associative arrays in descending order, according to the key

+usort()
-->