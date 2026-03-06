Write-Host "Sándor Kolos Barnabás - bhyuha - Shellscript Zh - 2.feladat" -ForegroundColor Cyan

if ($args.Count -lt 1)
{
    Write-Host "Nincs elég paraméter!"
}
else
{
    $p = $args[0]
    $prim = $true
    $test = 0;
    $i = 2
    
    for (;$i -le ($p/2) -and $prim; ++$i)
    {
        $test = $p / $i
        if ([int]::TryParse($test, [ref]$test)) {$prim = $false}
    }
    if ($prim -and $p -gt 1) {Write-Host "A megadott szám prím"}
    else {Write-Host "A megadott szám NEM prím"}
}