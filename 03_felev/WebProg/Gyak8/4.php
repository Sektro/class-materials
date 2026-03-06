<!-- 5. --> 
 <ul>
<?php
    $errors = ["TypeError", "IllegalArgumentException", "IOError", "Out of coffee"];
    foreach($errors as $e) {
        echo "<li>$e</li>";
    }
?>
</ul>
