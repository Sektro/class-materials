<?php
session_start();

// Store family members and ideas in session for simplicity
if (!isset($_SESSION['family_members'])) {
    $_SESSION['family_members'] = [];
}

// Handle form submission to add a family member
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['name'])) {
    $_SESSION['family_members'][] = [
        'id' => count($_SESSION['family_members']) + 1,
        'name' => $_POST['name'],
        'ideas' => []
    ];
}

// Function to calculate active and ready ideas
function getIdeaStats($ideas) {
    $active = 0;
    $ready = 0;
    foreach ($ideas as $idea) {
        if ($idea['active']) {
            $active++;
            if ($idea['ready']) {
                $ready++;
            }
        }
    }
    return [$active, $ready];
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task 3: Gift List</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
    <h1>Task 3: Gift list</h1>
    <h2>My family members</h2>
    
    <form action="" method="POST">
        Name: <input type="text" name="name" required>
        <button type="submit">Add</button>
    </form>

    <ul>
        <?php foreach ($_SESSION['family_members'] as $familyMember): ?>
            <?php
                list($active, $ready) = getIdeaStats($familyMember['ideas']);
            ?>
            <li>
                <a href="member.php?id=<?= $familyMember['id'] ?>">
                    <?= $familyMember['name'] ?> (<?= $ready ?> / <?= $active ?>)
                </a>
            </li>
        <?php endforeach; ?>
    </ul>
</body>
</html>
