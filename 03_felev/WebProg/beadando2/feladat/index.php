<?php
    $cars = json_decode(file_get_contents("cars.json"), true);
    $users = json_decode(file_get_contents("users.json"), true);

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



    //search
    $seats = $_GET["seats"] ?? "";
    $transmission = $_GET["transmission"] ?? "";
    $price_from = $_GET["price_from"] ?? -1;
    $price_to = $_GET["price_to"] ?? -1;

    $found = true;
?>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>iKarRental</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    <div class="nav">
        <div id="name">iKarRental</div>
        <div class="nothing"></div>
        <div class="logreg">
            <?php if ($valid_user) :?>
                <a href="new_car.php?user=<?=$user["username"]?>" class="new-button-link"><button class="new-button">New car</button></a>
            <?php else: ?>
                <a href="new_car.php" class="new-button-link"><button class="new-button">New car</button></a>
            <?php endif;?>
            <?php if (!$valid_user) :?>
                <a href="login.php"><button class="login-button">Login</button></a>
                <a href="registration.php"><button class="register-button">Register</button></a>
            <?php endif;?>
            <?php if ($valid_user) :?>
                <a href="profile.php?user=<?=$user["username"]?>"><img src="pics/account_icon.png" alt=""></a>
                <a href="index.php"><button class="login-button">Logout</button></a>
            <?php endif;?>
        </div>
    </div>
    <div class="nav-replacer"></div>
    <div class="search">
        <div class="search-cover">
            <span>Follow your<br>dreams!</span>
        </div>
    </div>
    <div class="actual-search">
        <form method="get" action="index.php" class="actual-search-form" novalidate>
            <input type="number" name="seats" placeholder="greater than 0" id="seats">
            <label for="seats">seats, </label>
            <label for="from">from</label>
            <input type="date" id="from" name="from" placeholder="2025.01.01">
            <label for="to">to</label>
            <input type="date" id="to" name="to" placeholder="2025.01.01">
            <select name="transmission" id="transmission">
                <option value="">Choose</option>
                <option value="Manual">Manual</option>
                <option value="Automatic">Automatic</option>
            </select>
            <input type="number" name="price_from" placeholder="10 000 Ft">
             - 
            <input type="number" name="price_to" placeholder="20 000 Ft">
            <?php if ($valid_user) :?><input type="hidden" value=<?=$user['username']?> name="user"><?php endif; ?>
            <button type="submit">Search</button>
        </form>
    </div>
    <div class="container">
        <?php foreach ($cars as $car) :?>
            <?php
                if (isset($_GET["seats"]) && $seats > 0) {
                    if ($car["passengers"] != $seats) {$found = false;}
                }
                if (isset($_GET["seats"]) && $transmission != "") {
                    if ($car["transmission"] != $transmission) {$found = false;}
                }
                if (isset($_GET["price_from"]) && $price_from >= 0) {
                    if ($car["daily_price_huf"] < $price_from) {$found = false;}
                }
                if (isset($_GET["price_to"]) && $price_to >= 0) {
                    if ($car["daily_price_huf"] > $price_to) {$found = false;}
                }
            ?>
            <?php if ($found) :?>
                <div>
                    <a <?php if ($valid_user) :?>href="car.php?id=<?= $car['id']?>&user=<?= $user['username']?><?php else: ?>href="car.php?id=<?= $car['id']?><?php endif; ?>" class="car-subside">
                        <img src=<?=$car["image"]?>>
                        <div class="title"><?=$car["brand"]?> <?=$car["model"]?></div>
                        <div class="extra-info"><?=$car["passengers"]?> seats - <?=$car["transmission"]?></div>
                        <div class="price"><?=number_format($car["daily_price_huf"], 0, ',', ' ')?> Ft</div>
                    </a>
                    <a href="" class="reserve-button-link"><button class="reserve-button">Reserve</button></a>
                </div>
            <?php endif; ?>
            <?php $found = true; ?>
        <?php endforeach; ?>
    </div>
</body>
</html>