<?php 
    session_start();
    //var_dump($_SESSION);
    if (!isset($_SESSION['user_id'])) {
        header("location: login.php");
        exit();
    }
    $users = json_decode(file_get_contents("users.json"), true);
    $user = $users[$_SESSION['user_id']];
?>

Szia, <?= $user['username'] ?>!

<a href="logout.php">Kijelentkezés</a>