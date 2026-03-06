<?php
    session_start(); //FONTOS!!! (nincs: "headers already sent" hiba)

    //$_SESSION['x'] - adott felhasználóhoz lesz társitva x
    if (!isset($_SESSION['x'])) {
        $_SESSION['x'] = 0;
    }
    $_SESSION['x']++;
    echo $_SESSION['x'];

    /*
        LOGIN rendszer: SESSION ID alapján (User ID-t tárolunk a sessionben)
    */
?>