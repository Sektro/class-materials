<?php
$fullname = $email = $colour = $cardnumber = "";
$fullnameErr = $emailErr = $colourErr = $cardnumberErr = "";
$formValid = true;

if ($_SERVER["REQUEST_METHOD"] == "GET") {
    // Full name validation
    if (empty($_GET["fullname"]) || count(explode(" ", $_GET["fullname"])) < 2) {
        $fullnameErr = "Full name is required and must contain at least two words.";
        $formValid = false;
    } else {
        $fullname = htmlspecialchars($_GET["fullname"]);
    }

    // Email validation
    if (empty($_GET["email"]) || !filter_var($_GET["email"], FILTER_VALIDATE_EMAIL)) {
        $emailErr = "A valid email is required.";
        $formValid = false;
    } else {
        $email = htmlspecialchars($_GET["email"]);
    }

    // Colour validation
    if (empty($_GET["colour"]) || !in_array($_GET["colour"], ['black', 'white', 'gold', 'pink', 'blue'])) {
        $colourErr = "Please choose a valid colour.";
        $formValid = false;
    } else {
        $colour = htmlspecialchars($_GET["colour"]);
    }

    // Credit card number validation
    if (empty($_GET["cardnumber"]) || strlen($_GET["cardnumber"]) != 19 || !preg_match("/^\d{4}-\d{4}-\d{4}-\d{4}$/", $_GET["cardnumber"])) {
        $cardnumberErr = "Card number must be 19 characters long and follow the format XXXX-XXXX-XXXX-XXXX.";
        $formValid = false;
    } else {
        $cardnumber = htmlspecialchars($_GET["cardnumber"]);
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
    <title>Task 2</title>
</head>
<body>
    <h1>Task 2: You've just won a new phone!</h1>
    <form action="index.php" method="get" novalidate>
        <label for="i1">Your full name:</label>
        <input type="text" name="fullname" id="i1" value="<?php echo $fullname; ?>"> <br>
        <span class="error"><?php echo $fullnameErr; ?></span><br>

        <label for="i2">Your e-mail address:</label>
        <input type="text" name="email" id="i2" value="<?php echo $email; ?>"> <br>
        <span class="error"><?php echo $emailErr; ?></span><br>

        <label>Choose colour:</label> <br>
        <input type="radio" value="black" name="colour" id="i3a" <?php echo ($colour == 'black') ? 'checked' : ''; ?>>
        <label for="i3a">black</label><br>
        <input type="radio" value="white" name="colour" id="i3b" <?php echo ($colour == 'white') ? 'checked' : ''; ?>>
        <label for="i3b">white</label><br>
        <input type="radio" value="gold" name="colour" id="i3c" <?php echo ($colour == 'gold') ? 'checked' : ''; ?>>
        <label for="i3c">gold</label><br>
        <input type="radio" value="pink" name="colour" id="i3d" <?php echo ($colour == 'pink') ? 'checked' : ''; ?>>
        <label for="i3d">pink</label><br>
        <input type="radio" value="blue" name="colour" id="i3e" <?php echo ($colour == 'blue') ? 'checked' : ''; ?>>
        <label for="i3e">blue</label><br>
        <span class="error"><?php echo $colourErr; ?></span><br>

        <label for="i4">Your credit card number:</label>
        <input type="text" name="cardnumber" id="i4" value="<?php echo $cardnumber; ?>"> <br>
        <span class="error"><?php echo $cardnumberErr; ?></span><br>

        <button type="submit">Click here to get your free phone today!</button>
    </form>

    <?php if ($formValid): ?>
        <div id="success">
            <h2>Success! Your information is complete.</h2>
            <div id="progress-bar">
                <div id="progress-bar-fill"></div>
            </div>
        </div>
    <?php endif; ?>

    <h2>Hyperlinks for testing</h2>
    <!-- Test hyperlinks here -->

    <script src="index.js"></script>
</body>
</html>
