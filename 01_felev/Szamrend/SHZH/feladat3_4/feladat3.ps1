Write-Host "Sándor Kolos Barnabás - bhyuha - Shellscript Zh - 3.feladat" -ForegroundColor Cyan

$dogak = Get-Content "zh.txt"
$counter = 0
$nevek

foreach ($i in $dogak)
{
    $nevek += @($i.Split(':')[0])
    ++$counter
}
Write-Host $counter
$nevek | Sort-Object
