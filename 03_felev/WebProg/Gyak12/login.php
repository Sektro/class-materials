<?php
    session_start();
    $failed = false;
    if ($_POST) {
        $uname = $_POST["uname"] ?? "";
        $pword = $_POST["pword"] ?? "";

        $users = json_decode(file_get_contents("users.json"), true); // true sima tömböt ad vissza, különben semmiből származó STD Classokat
        
        //javascriptben: array.find -->
        //(
        $matches = array_filter($users, fn($u) => $u("username") === $uname);

        if (count($matches) > 0) {
            $keys = array_keys($matches); //kell, mivel array_filter megtartja: indexelés
            $user = $matches[$keys[0]];

            if (password_verify($pword, $user["password"])) {
                // do login :)
                $_SESSION['user_id'] = $keys[0];
                header("location: index.php");
                exit();
            }
        } else $failed = true;
        //)
    }
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php if ($failed) : ?>
        LOGIN FAILED!!!
    <?php endif; ?>
    <form method="post" action="login.php">
        Username: <input type="text" name="uname"><br>
        Password: <input type="password" name="pword"><br>
        <button type="submit">Log me in</button>
    </form>
</body>
</html>