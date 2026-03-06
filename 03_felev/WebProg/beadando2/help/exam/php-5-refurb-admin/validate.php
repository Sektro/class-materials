<?php 
    $name = trim($_GET["name"]) ?? "";
    $stock = trim($_GET["stock"]) ?? "";
    $type = trim($_GET["type"]) ?? "";
    $specifications = trim($_GET["specifications"]) ?? "";

    $errors = [];
    if ($_GET) {
        if ($name === "") {
            $errors["name"] = "Név megadása kötelező";
        }
        if ($stock === "") {
            $errors["stock"] = "A rendelni kívánt mennyiség megadása kötelező";
        }
        if ($type === "") {
            $errors["type"] = "Termék típus megadása kötelező";
        }
        if ($specifications === "") {
            $errors["specifications"] = "Specifikáció megadása kötelező";
        }
    }
    header('location: index.php');
    exit();

?>
