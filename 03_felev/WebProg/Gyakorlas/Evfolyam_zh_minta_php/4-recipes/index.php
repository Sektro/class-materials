<?php
session_start();

// Load recipes from JSON file
$recipes = json_decode(file_get_contents('recipes.json'), true);

// Initialize fridge if it doesn't exist
if (!isset($_SESSION['fridge'])) {
    $_SESSION['fridge'] = [];
}

// Function to check if a recipe can be made
function canMakeRecipe($ingredients) {
    global $_SESSION;
    return empty(array_diff($ingredients, $_SESSION['fridge']));
}

// Handle removing ingredients when a recipe is made
if (isset($_GET['make_recipe']) && isset($recipes[$_GET['make_recipe']])) {
    $recipeName = $_GET['make_recipe'];
    $ingredients = $recipes[$recipeName];
    $_SESSION['fridge'] = array_diff($_SESSION['fridge'], $ingredients);
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="index.css">
    <title>Task 4: Recipe tracker</title>
</head>
<body>
    <h1>Task 4: Recipe tracker</h1>
    
    <h2>List of recipes</h2>
    <ul>
        <?php foreach ($recipes as $recipeName => $ingredients): ?>
            <li>
                <a href="details.php?recipe=<?= urlencode($recipeName) ?>"><?= $recipeName ?></a>
                <?php if (canMakeRecipe($ingredients)): ?>
                    <span> - Elkészíthető!</span>
                    <a href="?make_recipe=<?= urlencode($recipeName) ?>">Make</a>
                <?php endif; ?>
            </li>
        <?php endforeach; ?>
    </ul>

    <h2>Fridge contents</h2>
    <ul>
        <?php foreach ($_SESSION['fridge'] as $item): ?>
            <li><?= $item ?></li>
        <?php endforeach; ?>
    </ul>
</body>
</html>
