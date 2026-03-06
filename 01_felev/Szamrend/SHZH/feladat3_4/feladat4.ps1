Write-Host "Sándor Kolos Barnabás - bhyuha - Shellscript Zh - 4.feladat" -ForegroundColor Cyan

$dogak = Get-Content "zh.txt"

Write-Host "Pótzh-t ír:"
foreach ($i in $dogak)
{
    $sor = $i.Split(':')
    $szamok = $sor[1].Split(',')
    $sum = 0
    foreach ($j in $szamok) {$sum = $sum + $j}
    if ($sum -lt 10) {Write-Host $sor[0]}
}