Write-Host "Sándor Kolos Barnabás - bhyuha - Shellscript Zh - 1.feladat" -ForegroundColor Cyan

if ($args.Count -lt 3)
{
    Write-Host "Nincs elég paraméter!"
}
else
{
    $a = $args[0]
    $d = $args[1]
    $db = $args[2]
    
    Write-Host $a
    for ($i = 1; $i -lt $db; ++$i)
    {
        $a = $a + $d
        Write-Host $a
    }
}