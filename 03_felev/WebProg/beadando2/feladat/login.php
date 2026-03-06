<?php
    $users = json_decode(file_get_contents("users.json"), true);

    $username = $_GET["username"] ?? "";
    $password = $_GET["password"] ?? "";

    $logged_in = false;

    $errors = [];
    if ($_GET) {
        $found = false;
        if ($password === "") {
            $errors["password"] = "Please enter a password!";
        }
        if ($username === "") {
            $errors["username"] = "Please enter a username!";
        } else {
            foreach ($users as $u) {
                if ($username == $u["username"]) {
                    $found = true;
                    if ($password != $u["password"]) {
                        $errors["password"] = "Incorrect password!";
                    }
                }
            }
            if (!$found) {
                $errors["username"] = "Username doesn't exist!";
            }
        }
        if ($errors === []) {
            $logged_in = true;
        }
    }
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>iKarRental - Registration</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    <div class="nav">
        <div id="name">iKarRental</div>
        <div class="nothing"></div>
        <div class="logreg">
            <a href="index.php" class="new-button-link"><button class="new-button">Main page</button></a>
            <a href="registration.php"><button class="register-button">Register</button></a>
        </div>
    </div>
    <div class="nav-replacer"></div>
    <main>
        <h1>Login</h1>
        <form method="get" action="login.php" id="registration-form" novalidate>
            <div>
                <label for="username">Username: </label>
                <input type="text" name="username" id="username" value=<?=$username?>>
                <div class="error-message"><?=$errors["username"] ?? "";?></div>
            </div>
            <div>
                <label for="password">Password: </label>
                <input type="password" name="password" id="password" value=<?=$password?>>
                <div class="error-message"><?=$errors["password"] ?? "";?></div>
            </div>
            <button type="submit">Log in</button>
        </form>
        <?php if ($logged_in) :?>
            <div class="login-message">
                <div class="login-message-list">
                    <div>Log in successful!</div>
                    <img src="pics/checkmark.png" alt="">
                    <a href="index.php?user=<?=$username?>"><button>Back to main menu</button></a>
                </div>
            </div>
        <?php endif;?>
    </main>
</body>
</html>