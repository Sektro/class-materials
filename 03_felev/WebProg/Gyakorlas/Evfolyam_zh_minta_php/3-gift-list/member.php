<?php
session_start();

// Retrieve the family member ID from the URL
$familyMemberId = $_GET['id'] ?? null;
$familyMember = null;

// Find the selected family member based on the ID
if ($familyMemberId !== null) {
    foreach ($_SESSION['family_members'] as &$member) {
        if ($member['id'] == $familyMemberId) {
            $familyMember = &$member;
            break;
        }
    }
}

// Handle adding a new idea
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['idea'])) {
    $familyMember['ideas'][] = [
        'idea' => $_POST['idea'],
        'active' => true,
        'ready' => false,
        'comments' => []
    ];
}

// Handle adding a comment to an idea
if (isset($_POST['add-comment']) && isset($_POST['comment'])) {
    $ideaId = $_POST['idea-id'];
    $comment = $_POST['comment'];

    // Find the idea and add the comment
    if (isset($familyMember['ideas'][$ideaId])) {
        $familyMember['ideas'][$ideaId]['comments'][] = $comment;
    }
}

// Handle marking an idea as complete or hide
if (isset($_POST['complete']) || isset($_POST['hide'])) {
    $ideaId = $_POST['idea-id'];
    if (isset($familyMember['ideas'][$ideaId])) {
        if (isset($_POST['complete'])) {
            $familyMember['ideas'][$ideaId]['ready'] = true;
        }
        if (isset($_POST['hide'])) {
            $familyMember['ideas'][$ideaId]['active'] = false;
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
    <title>Task 3: Gift List</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>
    <h1>Task 3: Gift list</h1>
    <a href="index.php">Back to main page</a>

    <?php if ($familyMember): ?>
        <h2>Ideas for <?= $familyMember['name'] ?></h2>

        <!-- New idea form -->
        <form action="" method="POST">
            <fieldset>
                <legend>New idea</legend>
                Idea: <input type="text" name="idea" required>
                <button name="function-add" type="submit">Add new idea</button>
            </fieldset>
        </form>

        <!-- Display ideas -->
        <?php foreach ($familyMember['ideas'] as $index => $idea): ?>
            <?php if ($idea['active']): ?>
                <details>
                    <summary>
                        <?= $idea['idea'] ?> <?= $idea['ready'] ? '✓' : '' ?>
                    </summary>

                    <!-- Comment form -->
                    <form action="" method="POST">
                        <input type="hidden" name="idea-id" value="<?= $index ?>">
                        Comment: <input type="text" name="comment" required>
                        <button type="submit" name="add-comment">Add comment</button> <br>
                    </form>

                    <!-- Complete/Hide form -->
                    <form action="" method="POST">
                        <input type="hidden" name="idea-id" value="<?= $index ?>">
                        <button type="submit" name="complete">Complete</button>
                        <button type="submit" name="hide">Hide</button>
                    </form>

                    <ul>
                        <?php foreach ($idea['comments'] as $comment): ?>
                            <li><?= $comment ?></li>
                        <?php endforeach; ?>
                    </ul>
                </details>
            <?php endif; ?>
        <?php endforeach; ?>

    <?php else: ?>
        <p>Family member not found.</p>
    <?php endif; ?>
</body>
</html>
