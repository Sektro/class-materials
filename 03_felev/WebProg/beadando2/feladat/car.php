<?php
    $cars = json_decode(file_get_contents("cars.json"), true);
    $users = json_decode(file_get_contents("users.json"), true);

    //id
    if (!isset($_GET['id'])) {
        header('location: index.php');
        exit();
    }
    $id = $_GET['id'];
    $found = false;
    $car;
    foreach ($cars as $c) {
        if ($c["id"]  == $id) {$found = true; $car = $c;}
    }
    if (!$found) {
        header('location: index.php');
        exit();
    }

    //login
    $name = "";
    $user;
    $valid_user = true;
    if (!isset($_GET['user'])) {
        $valid_user = false;
    }
    else {
        $name = $_GET['user'];
        $valid = false;
        foreach ($users as $u) {
            if ($u["username"]  == $name) {$valid = true; $user = $u;}
        }
        if (!$valid) {
            $valid_user = false;
        }
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
            <?php if (!$valid_user) :?>
                <a href="index.php" class="new-button-link"><button class="new-button">Main page</button></a>
                <a href="login.php"><button class="login-button">Login</button></a>
                <a href="registration.php"><button class="register-button">Register</button></a>
            <?php endif;?>
            <?php if ($valid_user) :?>
                <a href="index.php?user=<?=$user["username"]?>" class="new-button-link"><button class="new-button">Main page</button></a>
                <a href="profile.php?user=<?=$user["username"]?>"><img src="pics/account_icon.png" alt=""></a>
                <a href="car.php?id=<?=$id?>"><button class="login-button">Logout</button></a>
            <?php endif;?>
        </div>
    </div>
    <div class="nav-replacer"></div>
    <div class="car-display">
        <img src=<?=$car["image"]?>>
        <div class="information">
            <div class="properties">
                <div class="car-title"><?=$car["brand"]?> <?=$car["model"]?></div>
                <div class="car-seats">Seats: <?=$car["passengers"]?></div>
                <div class="car-transmission">Transmission: <?=$car["transmission"]?></div>
                <div class="car-fuel">Fuel type: <?=$car["fuel_type"]?></div>
                <div class="car-year">Year of manufacture: <?=$car["year"]?></div>
                <div class="car-price">Price: <?=number_format($car["daily_price_huf"], 0, ',', ' ')?> Ft/day</div>
            </div>
            <div class="buttons">
                <button class="date-button">Choose Date</button><button class="reserve-button">Reserve</button>
            </div>
        </div>
    </div>
</body>
</html>