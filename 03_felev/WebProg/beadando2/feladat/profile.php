<?php
    $cars = json_decode(file_get_contents("cars.json"), true);
    $users = json_decode(file_get_contents("users.json"), true);

    //login
    $name = "";
    if (!isset($_GET['user'])) {
        header('location: index.php');
        exit();
    }
    $name = $_GET['user'];
    $valid = false;
    $user;
    foreach ($users as $u) {
        if ($u["username"]  == $name) {$valid = true; $user = $u;}
    }
    if (!$valid) {
        header('location: index.php');
        exit();
    }
?>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>iKarRental</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body class="car-body">
    <div class="nav">
        <div id="name">iKarRental</div>
        <div class="nothing"></div>
        <div class="logreg">
            <a href="index.php?user=<?=$user["username"]?>" class="new-button-link"><button class="new-button">Main page</button></a>
            <a href="index.php"><button class="login-button">Logout</button></a>
        </div>
    </div>
    <div class="nav-replacer"></div>
    <div class="car-display">
        <img src="pics/account_icon.png" alt="">
        <div class="profile-title"><?=$user["username"]?></div>
    </div>
    <div class="container">
        <?php foreach($user["reservations"] as $r) :?>
            <div>
                
            </div>
        <?php endforeach;?>
    </div>
</body>
</html>