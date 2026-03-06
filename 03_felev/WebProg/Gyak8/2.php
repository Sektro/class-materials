<!-- Feladatok: -->

<!-- 4. -->
 <?php
    echo "<style> \n";
    for ($i = 0; $i < 10; $i++) {
        $x = ($i+1) * 5;
        echo("#sizes$i {\nfont-size: $x" . "px;\n}\n");
    }
    echo "</style> \n";
    for ($i = 0; $i < 10; $i++) {
        echo('<span id="sizes'.$i.'">Hello világ!</span><br>');
    }

    //orai:
    for ($i = 10; $i < 20; $i++) {
        echo "<p style=\"font-size: {$i}pt\">Hello világ!</p>";
    }
?>

