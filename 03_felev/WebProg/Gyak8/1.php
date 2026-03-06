<!-- 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
</body>
</html>   valid php kód -->
<!-- madfmnajdnfjknjhb askdjfhjk b -- ez is valid php kód-->
<?php

?> <!-- csak ezzel foglalkozik -->

<?php
    $x = 3; // $ kell változónevek elé
    echo $x . "<br>"; // . = stringek összekötése
    echo($x."<br>"); // x.a helyett: x -> a
    print $x."<br>";
    print ($x . "<br>");

    $t = [6, 0, 8, -1, -5];
    // echo $t; --> nem múködik
    print_r($t);
    echo "<br>";
    for ($i = 0; $i < count($t); $i++) {
        echo $t[$i] . " ";
    }
    echo "<br>";

    // split = explode
    // join = implode
    echo implode(", ", $t);
    
    function is_even($n) {
        return $n % 2 === 0; //== ugyanolyan mint jsben
    }
    $f1 = array_filter($t, 'is_even');
    print_r($f1);
    echo "Count: " . count($f1);
    echo "<br>";

    $zero = 0;
    $f2 = array_filter($t, function($n) use ($zero){
        return $n % 2 === $zero; // nem működik (változókat nem látják a függvények ==> át kell adni paraméterként) csak use-al
        //return $n % 2 === 0;
    });
    print_r($f2);
    echo "<br>";

    $f3 = array_filter($t, fn($n) => $n % 2 === 0);
    print_r($f3);
    echo "<br>";

    $car = [
        "model" => "Ford",
        "year" => 2024,
        "broken" => false,
        "works" => true
    ]; // ezek indexek, igy ez egy tömb, nem egy objektum (a dictionary-t helyettesiti)
    print_r($car);
    echo "Broken: " . $car["broken"] ? "true" : "false";
    echo "<br>";

    foreach($t as $elem) {
        echo $elem;
    }
    echo "<br>";
    foreach($car as $key => $value) {
        echo $key . " is " . $value . " ";
    }
    echo "<br>";

    echo 'sima string x-e: $x <br>';
    echo "dupla idézőjeles string x-e: $x <br>";
?>

