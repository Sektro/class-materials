<?php

$shop = [
  [ "brand" => "Homemade", "type"  => "Dark chocolate", "price" => 2000 ],
  [ "brand" => "Grandma's", "type"  => "Milk chocolate", "price" => 1500 ],
  [ "brand" => "Worldsweet", "type"  => "Milk chocolate", "price" => 3000 ],
  [ "brand" => "Worldsweet", "type"  => "Dark chocolate", "price" => 4000 ],
  [ "brand" => "Worldsweet", "type"  => "Orange essence", "price" => 4000 ],
  [ "brand" => "Homemade", "type"  => "Milk chocolate", "price" => 1000 ],
  [ "brand" => "Speziale", "type"  => "Apple & Cinnamon", "price" => 1000 ]
];
$typesHelper = [];
for($i = 0; $i < count($shop); $i++) {
  $typesHelper[] = $shop[$i]["type"];
}
$typesHelper2 = array_unique($typesHelper);
$types = [];
foreach ($typesHelper2 as $typeHelp) {
    $types[] = $typeHelp;
}
sort($types);

$brandsHelper = [];
for($i = 0; $i < count($shop); $i++) {
  $brandsHelper[] = $shop[$i]["brand"];
}
$brandsHelper2 = array_unique($brandsHelper);
$brands = [];
foreach ($brandsHelper2 as $brandHelp) {
    $brands[] = $brandHelp;
}
sort($brands);

$prices = [];
for($i = 0; $i < count($shop); $i++) {
  $prices[] = $shop[$i]["price"];
}
$maxPrice=max($prices);
$minPrice=min($prices);


$averageHelperSum = 0;
$averageHelperDB = 0;
$actualPrice = 0;

$found = false;
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="index.css">
  <title>Task 1</title>
</head>
<body>
  <h1>Task 1: Candies</h1>
  <table>
    <tr>
      <?php for($i = 0; $i < count($types) + 2; $i++) :?>
        <?php if($i === 0) :?>
          <td></td>
        <?php endif; ?>
        <?php if($i >= 1 && $i <= count($types)) :?>
          <th><?=$types[$i-1]?></th>
        <?php endif; ?>
        <?php if($i === count($types) + 1) :?>
          <th>Average price</th>
        <?php endif; ?>
      <?php endfor; ?>
    </tr>
    <?php for($i = 0; $i < count($brands); $i++) :?>
        <tr>
        <?php for($j = 0; $j < count($types) + 2; $j++) :?>
          <?php if($j === 0) :?>
            <th><?=$brands[$i]?></th>
          <?php endif; ?>
          <?php if($j >= 1 && $j <= count($types)) :?>
            <?php for($l = 0; $l < count($shop); $l++) :?>
                <?php if($shop[$l]["brand"] === $brands[$i] && $shop[$l]["type"] === $types[$j-1]) :?>
                  <?php $actualPrice = $shop[$l]["price"]; $found = true;?>
                  <?php $averageHelperSum += $shop[$l]["price"];$averageHelperDB += 1;?>
                <?php endif; ?>
              <?php endfor; ?>
            <td class=<?php if ($actualPrice === $minPrice && $found) :?> <?="lowest"?> <?php endif;?> <?php if ($actualPrice === $maxPrice && $found) :?> <?="largest"?> <?php endif;?>>
              <?php if ($found) :?>
                <?=$actualPrice?>
                <?php $found = false;?>
              <?php endif; ?>
              <?php$actualPrice=0;?>
            </td>
          <?php endif; ?>
          <?php if($j === count($types) + 1) :?>
            <td><?=round($averageHelperSum/$averageHelperDB)?></td>
            <?php $averageHelperSum = 0;$averageHelperDB = 0;?>
          <?php endif; ?>
        <?php endfor; ?>
        </tr>
      <?php endfor; ?>
  </table>
</body>
</html>