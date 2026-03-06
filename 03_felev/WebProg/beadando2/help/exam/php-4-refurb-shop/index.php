<?php
    $data = json_decode(file_get_contents("data.json"), true);
    // print_r($data);
    //1

    //2
    function giveClass($stock) {
        if ($stock === 0) {
            return "sold-out";
        }
        else if ($stock > 120) {
            return "high-stock";
        }
        else {
            return "";
        }
    }
    function salePrice($price,$lowestprice) {
        if ($price === $lowestprice) {
            return ($price * 85) / 100;
        }
        else {
            return ($price * 90) / 100;
        }
    }
    function lastSale($sales) {
        $salesInNumbers = [];
        foreach ($sales as $s) {
            $date = explode("-",$s);
            $salesInNumbers[]= $date[2];
        }
        return max($salesInNumbers);
    }

?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>PHP-1</title>
    <link rel="stylesheet" href="index.css" />
</head>

<body>
    <h1>1. Termék lista oldal</h1>
    <div class="container">


    <?php foreach($data as $d) :?>
        <?php
            $sales = [];
            foreach ($d["sales"] as $key => $value) {
                $sales[] = $key;
            }
        ?>
        <div class=<?= giveClass($d["stock"]);?>>
            <?php 
                $name = str_replace(" ","_",$d["name"]);
                $imgname = "assets/$name.png";
            ?>
            <img src=<?=$imgname?>>
            <div class="title"><?=$d["name"]?></div>
            <?php if ($d["stock"] === 0) :?>
                Utoljára elérhető: 2024-11-<?= lastSale($sales)?>
            <?php elseif ($d["stock"] > 120) :?>
                <div class="price-container">
                    <div class="discounted-price"><?=number_format(salePrice($d["price"],$d["lowest_price"]), 0, ',', ' ')?> Ft</div>
                    <div class="original-price"><?=number_format($d["price"], 0, ',', ' ')?> Ft</div>
                </div>
                <div class="stock-info">Raktárkészlet: <?=$d["stock"]?></div>
            <?php else :?>
                <div class="price"><?=number_format($d["price"], 0, ',', ' ')?> Ft</div>
                <div class="stock-info">Raktárkészlet: <?=$d["stock"]?></div>
            <?php endif;?>
        </div>
    <?php endforeach;?>

    
    <!--
        <div class="">
            <img src="assets/USB-C_Charger_1.png">
            <div class="title">USB-C Charger 1</div>
            <div class="price">7 990 Ft</div>
            <div class="stock-info">Raktárkészlet: 100</div>
        </div>
        <div class="sold-out">
            <img src="assets/Wireless_Earbuds_2.png">
            <div class="title">Wireless Earbuds 2</div>
            Utoljára elérhető: 2024-11-28
        </div>
        <div class="">
            <img src="assets/Portable_Power_Bank_3.png">
            <div class="title">Portable Power Bank 3</div>
            <div class="price">15 190 Ft</div>
            <div class="stock-info">Raktárkészlet: 116</div>
        </div>
        <div class="high-stock">
            <img src="assets/Smartphone_Screen_Protector_4.png">
            <div class="title">Smartphone Screen Protector 4</div>
            <div class="price-container">
                <div class="original-price">5 290 Ft</div>
                <div class="discounted-price">4 497 Ft</div>
            </div>
            <div class="stock-info">Raktárkészlet: 194</div>
        </div>
    -->
    
    </div>
</body>

</html>