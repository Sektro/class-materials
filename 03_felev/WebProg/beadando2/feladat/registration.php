<?php
    $users = json_decode(file_get_contents("users.json"), true);

    $success = false;

    $username = $_GET["username"] ?? "";
    $email = $_GET["email"] ?? "";
    $password = $_GET["password"] ?? "";
    $password_again = $_GET["password_again"] ?? "";

    $errors = [];
    if ($_GET) {
        if ($username === "") {
            $errors["username"] = "Please enter a username!";
        }
        foreach ($users as $u) {
            if ($username == $u["username"]) {
                $errors["username"] = "Username already taken!";
            }
        }
        if ($email === "") {
            $errors["email"] = "Please enter an email address!";
        } else if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $errors["email"] = "Please enter a valid email address!";
        }
        foreach ($users as $u) {
            if ($email == $u["email"]) {
                $errors["email"] = "Email already in use!";
            }
        }
        if ($password === "") {
            $errors["password"] = "Please enter a password!";
        }
        if ($password_again != $password) {
            $errors["password_again"] = "The passwords do not match!";
        }

        if ($errors === []) {
            $users[$username] = [
                "username" => $username,
                "email" => $email,
                "password" => $password,
                "reservations" => []
            ];
            $file_content = json_encode($users, JSON_PRETTY_PRINT);
            file_put_contents("users.json", $file_content);

            $success = true;
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
        </div>
    </div>
    <div class="nav-replacer"></div>
    <main>
        <h1>Registration</h1>
        <form method="get" action="registration.php" id="registration-form" novalidate>
            <div>
                <label for="username">Username: </label>
                <input type="text" name="username" id="username" value=<?=$username?>>
                <div class="error-message"><?=$errors["username"] ?? "";?></div>
            </div>
            <div>
                <label for="email">Email: </label>
                <input type="email" name="email" id="email" value=<?=$email?>>
                <div class="error-message"><?=$errors["email"] ?? "";?></div>
            </div>
            <div>
                <label for="password">Password: </label>
                <input type="password" name="password" id="password" value=<?=$password?>>
                <div class="error-message"><?=$errors["password"] ?? "";?></div>
            </div>
            <div>
                <label for="password_again">Password again: </label>
                <input type="password" name="password_again" id="password_again" value=<?=$password_again?>>
                <div class="error-message"><?=$errors["password_again"] ?? "";?></div>
            </div>
            <button type="submit">Sign up</button>
        </form>
        <?php if ($success) :?>
            <div class="login-message">
                <div class="login-message-list">
                    <div>Account created!</div>
                    <img src="pics/checkmark.png" alt="">
                    <a href="index.php"><button>Back to main menu</button></a>
                </div>
            </div>
        <?php endif;?>
    </main>
</body>
</html>