<?php 
    $pdo = new PDO('sqlite:db.sqlite'); // adatbázis elérése

    $q = $pdo -> query('SELECT * FROM cats');
    $res = $q -> fetchAll(PDO::FETCH_ASSOC); // fetchAll() is jó, ekkor 2szer irja ki (2 féleképpen lehet rá hivatkozni)

    var_dump($res);

    echo "<h1>Másik példa:</h1>";


    $limit = 60;
    // $q = $pdo -> query('SELECT * FROM cats WHERE limit > ' .$limit); //nem kéne, userben nem szabad bizni
    $q = $pdo -> query('SELECT * FROM cats WHERE age > :limit'); 
    $q -> execute(['limit' => $limit]);
    $res = $q -> fetchAll(PDO::FETCH_ASSOC);
    
    var_dump($res);
?>