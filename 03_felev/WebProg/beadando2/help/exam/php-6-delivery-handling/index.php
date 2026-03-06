<?php
// include_once("storage.php");
// $deliveryOptions = new Storage(new JsonIO("data.json"));
$deliveryOptions = json_decode(file_get_contents(filename: 'data.json'), true);
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task 6.</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <h1>Új Átvételi Lehetőség Hozzáadása</h1>
    <form id="add-form" method="post" action="add.php">
        <label for="name">Átvételi lehetőség neve:</label>
        <input type="text" id="name" name="name" placeholder="Pl.: Újhely" required>

        <label for="type">Típus:</label>
        <select id="type" name="type" required>
            <option value="pickup_point">Átvételi Pont</option>
            <option value="in_store">Bolti Átvétel</option>
            <option value="courier">Kiszállítás Futárral</option>
        </select>

        <label for="cost">Költség (Ft):</label>
        <input type="number" id="cost" name="cost" placeholder="Pl.: 500" required>

        <label for="time_estimate">Idő becslés:</label>
        <input type="text" id="time_estimate" name="time_estimate" placeholder="Pl.: 1-2 munkanap" required>

        <button type="submit" id="add-button">Hozzáadás</button>
    </form>

    <h2>Átvételi lehetőségek listája:</h2>
        <h2>Átvételi Pontok</h2>
        <ul>
                <li class="open">Teszt Helyszín - Ingyenesn (Azonnal átvehető)
                    <span class="actions"> 
                        <a href="">Delete</a>
                        <a href="">Close</a>
                    </span>
                </li>
        </ul>
</body>

</html>