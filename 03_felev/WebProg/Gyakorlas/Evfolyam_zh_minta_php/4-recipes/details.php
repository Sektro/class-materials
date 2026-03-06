<?php
session_start();

// Load recipes from JSON file
$recipes = json_decode(file_get_contents('recipes.json'), true);

// Get the recipe name from the URL
$recipeName = $_GET['recipe'] ?? null;

if (!$recipeName || !isset($recipes[$recipeName])) {
    die("Recipe not found.");
}

$ingredients = $recipes[$recipeName];

// Handle form submission to add ingredients to the fridge
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    foreach ($ingredients as $index => $ingredient) {
        if (isset($_POST["ingredient{$index}"])) {
            $_SESSION['fridge'][] = $ingredient;
        }
    }
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

    <a href="index.php">← Back to recipes</a>
    
    <h2><?= $recipeName ?></h2>

    <form action="" method="POST">
        <?php foreach ($ingredients as $index => $ingredient): ?>
            <input type="checkbox" id="ingredient<?= $index ?>" name="ingredient<?= $index ?>"
                <?php if (in_array($ingredient, $_SESSION['fridge'])): ?>checked disabled<?php endif; ?>>
            <label for="ingredient<?= $index ?>"><?= $ingredient ?></label><br>
        <?php endforeach; ?>

        <button type="submit">Save</button>
    </form>
</body>
</html>
