<?php
    $cars = json_decode(file_get_contents("cars.json"), true);
    $users = json_decode(file_get_contents("users.json"), true);

    $img = $_GET["img_source"] ?? "pics/image_placeholder.png";
    $brand = $_GET["brand"] ?? "";
    $model = $_GET["model"] ?? "";
    $seats = $_GET["seats"] ?? "";
    $transmission = $_GET["transmission"] ?? "";
    $fuel = $_GET["fuel"] ?? "";
    $year = $_GET["year"] ?? 0;
    $price = $_GET["price"] ?? 0;

    $errors = [];
    if ($_GET) {
        if ($brand === "") {
            $errors["brand"] = "Please enter a brand name!";
        }
        if ($model === "") {
            $errors["model"] = "Please enter a model name!";
        }
        if ($seats === "") {
            $errors["seats"] = "Please enter the number of seats!";
        } else if (filter_var($seats, FILTER_VALIDATE_INT) === false) {
            $errors["seats"] = "The given number isn't valid!";
        } else if ($seats <= 0) {
            $errors["seats"] = "Please enter a valid number of seats!";
        }
        if ($transmission === "") {
            $errors["transmission"] = "Please choose a transmission!";
        } else if (!in_array($transmission, ['Manual', 'Automatic'])) {
            $errors["transmission"] = "Not an appropriate transmission!";
        }
        if ($fuel === "") {
            $errors["fuel"] = "Please choose a fuel type!";
        } else if (!in_array($fuel, ['Petrol', 'Diesel', 'Electric'])) {
            $errors["fuel"] = "Not an appropriate fuel type!";
        }
        if ($year === "") {
            $errors["year"] = "Please enter the year of manufacture!";
        } else if (filter_var($year, FILTER_VALIDATE_INT) === false) {
            $errors["year"] = "The given number isn't valid!";
        } else if ($year < 1885 || $year > date("Y")) {
            $errors["year"] = "Please enter a valid year of manufacture!";
        }
        if ($price === "") {
            $errors["price"] = "Please enter the price of the car!";
        } else if (filter_var($price, FILTER_VALIDATE_INT) === false) {
            $errors["price"] = "The given number isn't valid!";
        } else if ($price <= 0) {
            $errors["price"] = "Please enter a valid price!";
        }

        if ($errors === []) {
            $id = uniqid();
            $cars[$id] = [
                "id" => $id,
                "brand" => $brand,
                "model" => $model,
                "year" => $year,
                "transmission" => $transmission,
                "fuel_type" => $fuel,
                "passengers" => $seats,
                "daily_price_huf" => $price,
                "image" => $img
            ];
            $file_content = json_encode($cars, JSON_PRETTY_PRINT);
            file_put_contents('cars.json', $file_content);
        }
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
    <title>iKarRental - Add New Car</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body class="car-body">
    <div class="nav">
        <div id="name">iKarRental</div>
        <div class="nothing"></div>
        <div class="logreg">
            <?php if ($valid_user) :?>
                <a href="index.php?user=<?=$user["username"]?>" class="new-button-link"><button class="new-button">Main page</button></a>
            <?php else: ?>
                <a href="index.php" class="new-button-link"><button class="new-button">Main page</button></a>
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
    <form method="get" action="new_car.php" class="new-car-display" novalidate>
        <div>
            <img src=<?=$img?>>
            <input type="url" name="img_source" id="imgsource" value=<?=$img?>>
            <label for="imgsource">Type in image url</label>
        </div>
        <div class="information">
            <div class="properties">
                <div class="new-car-title"><?=$brand?> <?=$model?></div>
                <div class="new-car-seats">Seats: <?=$seats?></div>
                <div class="new-car-transmission">Transmission: <?=$transmission?></div>
                <div class="new-car-fuel">Fuel type: <?=$fuel?></div>
                <div class="new-car-year">Year of manufacture: <?=$year?></div>
                <div class="new-car-price">Price: <?=$price?> Ft/day</div>
            </div>
        </div>
        <div class="new-car-inputs">
            <div>
                <label for="new-car-brand">Brand:</label>
                <input type="text" name="brand" id="new-car-brand" value=<?=$brand?>>
                <?= $errors["brand"] ?? "";?><br>
            </div>
            <div>
                <label for="new-car-model">Model:</label>
                <input type="text" name="model" id="new-car-model" value=<?=$model?>>
                <?= $errors["model"] ?? "";?><br>
            </div>
            <div>
                <label for="new-car-seats">Seats:</label>
                <input type="number" name="seats" id="new-car-seats" value=<?=$seats?>>
                <?= $errors["seats"] ?? "";?><br>
            </div>
            <div>
                <label for="new-car-transmission">Transmission:</label>
                <select name="transmission" id="new-car-transmission">
                    <option value="">Choose</option>
                    <option value="Manual">Manual</option>
                    <option value="Automatic">Automatic</option>
                </select>
                <?= $errors["transmission"] ?? "";?><br>
            </div>
            <div>
                <label for="new-car-fuel">Fuel type:</label>
                <select name="fuel" id="new-car-fuel">
                    <option value="">Choose</option>
                    <option value="Petrol">Petrol</option>
                    <option value="Diesel">Diesel</option>
                    <option value="Electric">Electric</option>
                </select>
                <?= $errors["fuel"] ?? "";?><br>
            </div>
            <div>
                <label for="new-car-year">Year of manufacture:</label>
                <input type="number" name="year" id="new-car-year" value=<?=$year?>>
                <?= $errors["year"] ?? "";?><br>
            </div>
            <div>
                <label for="new-car-price">Daily price:</label>
                <input type="number" name="price" id="new-car-price" value=<?=$price?>>
                <label for="new-car-price"> Ft</label>
                <?= $errors["price"] ?? "";?><br>
            </div>
        </div>
        <button type="submit" class="add-car-button">Add car</button>
</form>
</body>
</html>