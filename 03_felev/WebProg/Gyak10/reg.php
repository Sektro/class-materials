<?php
    $email = trim($_POST["email"]) ?? "";
    $name = trim($_POST["full_name"]) ?? "";
    $taj = trim($_POST["taj"]) ?? "";
    $children = trim($_POST["children"]) ?? "";
    $shirtsize = trim($_POST["shirtsize"]) ?? "";
    $tos = filter_var($_POST["tos"] ?? false, FILTER_VALIDATE_BOOLEAN); //

    $errors = [];
    if ($_POST) {
        if ($email === "") {
            $errors["email"] = "Az email cimet ki kell tölteni!";
        } else if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $errors["email"] = "Az email cime nem valid!";
        }
        if ($name === "") {
            $errors["name"] = "Kérjük add meg a neved!";
        } else if (count(explode(" ",$name)) < 2) {
            $errors["name"] = "A név formátuma nem megfelelő!";
        }
        if ($taj === "") {
            $errors["taj"] = "Kérjük add meg a TAJ számod!";
        } else if (count(array_filter(str_split($taj), fn($x) => $x >= '0' && $x <= '9')) !== 9) {
            $errors["taj"] = "A TAJ szám formátuma nem megfelelő!";
        }
        if ($children === "") {
            $errors["children"] = "Kérjük adja meg gyerekeinek számát!";
        } else if (filter_var($children, FILTER_VALIDATE_INT) === false) { //itt ! nem működik, 0-ra false-t ad (a false az 0)
            $errors["children"] = "A szám formátuma nem megfelelő! (egész számnak kell lennie)";
        } else if ($children < 0) {
            $errors["children"] = "A szám formátuma nem megfelelő! (a szám nem lehet negativ)";
        }
        if ($shirtsize === "") {
            $errors["shirtsize"] = "Kérjük add meg a pólód méretét!";
        } else if (!in_array($shirtsize, ['xs', 's', 'm', 'l', 'xl', 'xll'])) {
            $errors["shirtsize"] = "A pólóméret formátuma nem megfelelő!";
        }
        if (!$tos) {
            $errors["tos"] = "Kérjük fogadja el a ToS-t!";
        }
        
        /*
        array_map(fn($e) => "<span style='color: red'>$e</span>", $errors);

        if (count($errors) == 0) {
            //helyes minden!
            $reg = json_decode(file_get_contents('data.json'), true);

            if (!isset($reg[$taj])) {
                * (ha nem szeretném, hogy felülíródjon)
            }
            *$reg[$taj] = [
                'fullname' => $fullname,
                'email' => $email,
                'taj' => $taj,
                'age' => $age,
                'gender' => $gender,
                'regdate' => $regdate,
                'notes' => $notes
            ];

            file_put_contents('data.json', json_encode($reg, JSON_PRETTY_PRINT));
        }
            */
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

    <form method="post" action="3.php">
        Teljes név: <input type="text" name="full_name" value= <?=$name;?>><br>
        <?= $errors["name"] ?? "";?><br>
        <!-- legyen kitöltve, legalább 2 szó -->
        E-mail: <input type="text" name="email" value= <?=$email;?>>
        <?= $errors["email"] ?? "";?><br>
        <!-- legyen kitöltve, formailag valid -->
        TAJ szám: <input type="text" name="taj" value=<?=$taj;?>><br>
        <?= $errors["taj"] ?? "";?><br>
        <!-- legyen kitöltve, 9 hosszú, karakterek számok -->
        Gyerekek száma <input type="text" name="children"  value=<?=$children;?>><br>
        <?= $errors["children"] ?? "";?><br>
        <!-- legyen kitöltve, egész szám, nem negativ -->
        Pólóméret: <select name="shirtsize">
            <!-- legyen kitöltve, a valid értékek közül egy -->
            <option value="xs" <?= $shirtsize == "xs" ? "selected" : "" ?>>XS</option>
            <option value="s" <?= $shirtsize == "s" ? "selected" : "" ?>>S</option>
            <option value="m" <?= $shirtsize == "m" ? "selected" : "" ?>>M</option>
            <option value="l" <?= $shirtsize == "l" ? "selected" : "" ?>>L</option>
            <option value="xl" <?= $shirtsize == "xl" ? "selected" : "" ?>>XL</option>
            <option value="xxl" <?= $shirtsize == "xll" ? "selected" : "" ?>>XXL</option>
        </select><?= $errors["shirtsize"] ?? "";?><br>
        <input type="checkbox" name="tos" <?= $tos ? "checked" : "" ?>>Elfogadom a ToS-t
        <?= $errors["tos"] ?? "";?><br>
        <button type="submit">Submit</button>

        <?php  if ($_POST && count($errors) == 0) :?>
        <b>Adatok megfelelőek!</b>
        <?php endif; ?>

        <!--
        <ul>
             //foreach(($errors ?? []) as $e): 
                <li>  //$e  </li>
             //endforeach; 
        </ul>
        -->
    </form>
</body>
</html>