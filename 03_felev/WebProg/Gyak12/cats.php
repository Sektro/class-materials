<?php 
    $filter = $_GET['filter'] ?? "";

    $pdo = new PDO('sqlite:db.sqlite');

    $q = $pdo -> prepare('SELECT * FROM cats WHERE name LIKE :likeCond');
    $q -> execute(['likeCond' => '%'.$filter.'%']);
    $res = $q -> fetchAll(PDO::FETCH_ASSOC);
    
    echo json_encode($res, JSON_PRETTY_PRINT);
?> 