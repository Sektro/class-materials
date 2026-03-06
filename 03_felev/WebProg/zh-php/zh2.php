<?php

require_once("Storage.php");

$gameStorage = new Storage(new JsonIO('games.json'), false);
$games = $gameStorage->findAll();

$game_developers = [
    "Asobi",
    "ATLUS",
    "Bloober Team",
    "Croteam",
    "GSC Game World",
    "LocalThunk",
    "Ryu Ga Gotoku Studio",
    "Square Enix",
    "Supergiant Games",
    "Ubisoft",
    "Valve"
];

$ratings = [];
foreach ($games as $game){
    array_push($ratings, $game->rating);
}
$average = array_sum($ratings)/count($ratings);

$matchingGame = $gameStorage->findOne([
    "title"=>$_POST["title"],
]);

function validate($input, &$errors){
    if (!isset($input['rating']) || $input['rating'] > 5 || $input['rating'] < 1){
        $errors['rating'] = 'Please select a number 1 and 5!';
    }
}

$errors = [];
if (!empty($_POST)){
    validate($_POST, $errors);
}

if ($errors == []){
    if ($matchingGame) {
        $matchingGame->developer = $_POST["developer"];
        $matchingGame->rating = $_POST["rating"];
        $gameStorage->update(
            $matchingGame->id,
            $matchingGame
        );
    } else {
        $gameStorage->add([
            "title" => $_POST["title"],
            "developer" => $_POST["developer"],
            "rating" => $_POST["rating"],
        ]);
    }
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PHP ZH</title>
    <link rel="stylesheet" href="zh2.css">
</head>

<body>
    <div id="content">
        <div>
            <h2>Lista</h2>
            <h3>
                Átlagos értékelés:
                <!-- #2 -->
                <span id="average_rating"><?= $average ?></span>
            </h3>

            <table id="game_list">
                <thead>
                    <tr>
                        <th>Cím</th>
                        <th>Fejlesztő</th>
                        <th>Értékelés</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <!-- #3 -->
                    <tr class="game">
                        
                        <?php foreach ($games as $game): ?>
                            <tr>
                                <td> <?= $game->title ?></td>
                                <td> <?= $game->developer ?> </td>
                                <td class="star">
                                    <?php for ($x = 0; $x < $game->rating; $x++): ?>
                                        <img alt="star" src="./icons/star.svg" />
                                    <?php endfor; ?>
                                </td>
                            </tr>
                        <?php endforeach; ?>
                    </tr>
                </tbody>
            </table>
        </div>

        <h2>Új játék felvétele</h2>
        <!-- #4 -->
        <form novalidate method="POST" action="zh2.php">
            <!-- #7 -->
            <div class="input">
                <label for="name">Címe</label>
                <input type="text" name="title" id="title" placeholder="Half-Life Xen">
                <!-- #5 -->
            </div>
            <div class="input">
                <label for="game">Fejlesztő</label>
                <!-- #1 -->
                <select name="developer" id="developers">
                    <?php foreach ($game_developers as $developer): ?>
                        <option value="<?= $developer ?>"><?= $developer ?></option>
                    <?php endforeach; ?>
                </select>
            </div>
            <div class="input">
                <label for="desc">Értékelés</label>
                <input type="number" name="rating" id="rating" placeholder="5">
                <!-- #6 -->
                <?php if(isset($errors['rating'])): ?>
                    <div style="color: red"><?= $errors['rating'] ?></div>
                <?php endif; ?>
            </div>
            <div class="input">
                <button type="submit">Hozzáadás</button>
            </div>
        </form>
    </div>
</body>

</html>
