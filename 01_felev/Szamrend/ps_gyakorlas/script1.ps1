#$teams = Get-Content "teams.dat"
#Select-String -Path .\teams.dat -Pattern $args[0] 

#Write-Host "Abban az intervallumban kérem az értékelést"
<#
1.a
try
{
    $oszt = $args[0] / $args[1]
    if ([int]::TryParse($oszt, [ref]$oszt)) {Write-Host "Egész"}
    else {Write-Host "Nem egész"}
}
catch
{
    Write-Host "..|._"
}
#>

<#
1.b
$oszt = $args[0] / $args[1]
if ([int]::TryParse($oszt, [ref]$oszt)) {Write-Host "Egész"}
else {Write-Host "Nem egész"}
Trap {„hiba szoveg”; continue}
#>

<#
2.
$grimaceshake
for ($i = 0; $i -lt $args.Length; ++$i)
{
    $grimaceshake += @($args[$i])
    Remove-Item $grimaceshake[$i] –erroraction silentlycontinue
}
#>

# $d | Foreach-Object {$_ + 5}
# Foreach ($item in $d) {$item + 5}

#$hehe = [math]::Sqrt(9)
#Write-Host $hehe

<#
$csumi = $args[0]
$csovi = $args[1]
if (Test-Path -Path $csumi)
{
    rmdir $csumi
}
mkdir $csumi
cd $csumi
"why not" > $csovi 
rm $csovi
cd..
#>

<#
$apad = $input
$anyad = $apad.Split(' ')
$sum = 0;
foreach ($i in $anyad) {$sum = $sum + $i}
Write-Host $sum
#>

<#
$csami = "Szendvics"
$nyami = "Szend"
Write-Host ([string]::Compare($csami,$nyami))
#>