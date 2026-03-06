$tanar = Get-Content "teams.dat"
$tanulo = Get-Content "hallgato.dat"

$vizsgal # = New System.Collections.ArrayList

Write-Host "Sándor Kolos Barnabás - BHYUHA" -ForegroundColor Cyan  "2.Beadandó"  "4.feladat"
if ($args.Length -lt 1)
{
    Write-Host "Nincs elég megadott paraméter!"
}
else
{
    if ($args[0] -eq "-lista")
    {
        if ($args.Length -lt 2)
        {
            Write-Host "Nincs elég megadott paraméter!"
        }
        else
        {
            $hossz = $tanar.Length
            for ($i = 0; $i -lt $hossz; ++$i)
            {
                $vizsgalt = $tanar[$i].Split(',')
                if ($vizsgalt[2] -eq $args[1]) #([string]::Compare($vizsgalt[2],$args[1]) -eq 0)
                {
                    $j = 0
                    for (; $j -lt $vizsgal.Length -and $vizsgal[$j] -ne $vizsgalt[0]; ++$j)
                    {
                        
                    }
                    if ($j -eq $vizsgal.Length)
                    {
                        Write-Host $vizsgalt[0]
                        $vizsgal += @($vizsgalt[0])
                        #$vizsgal.Add($vizsgalt[0])
                    }
                }
            }
        }
    }
    
    if ($args[0] -eq "-hallgato")
    {
        if ($args.Length -lt 2)
        {
            Write-Host "Nincs elég megadott paraméter!"
        }
        else
        {
            $hosszt = $tanar.Length
            $hosszh = $tanulo.Length
            [boolean]$vantan = $false
            $vizsgalth
            $vizsgaltt
            for ($i = 0; $i -lt $hosszh -and !($vantan); ++$i)
            {
                $vizsgalth = $tanulo[$i].Split(',')
                if ($vizsgalth[0] -eq $args[1]) {$vantan = $true}
            }
            if (!($vantan)) {Write-Host "Nincs ilyen tanuló!"}
            else
            {
                for ($i = 1; $i -lt $vizsgalth.Count; ++$i)
                {
                    for ($j = 0; $j -lt $hosszt; ++$j) 
                    {
                        $vizsgaltt = $tanar[$j].Split(',')
                        if ($vizsgalth[$i] -eq $vizsgaltt[1])
                        {
                            
                            $l = 0
                            for (; $l -lt $vizsgal.Length -and $vizsgal[$l] -ne $vizsgaltt[2]; ++$l)
                            {
                                
                            }
                            if ($l -eq $vizsgal.Length)
                            {
                                Write-Host $vizsgaltt[2]
                                $vizsgal += @($vizsgaltt[2])
                            }
                        }
                    }
                }
            }
        }
    }

    if ($args[0] -eq "-sok")
    {       
        #nevek
        for ($j = 0; $j -lt $tanar.Length; ++$j) 
        {
            $vizsgalt = $tanar[$j].Split(',')
            for ($l = 0; $l -lt $vizsgal.Length -and $vizsgal[$l] -ne $vizsgalt[2]; ++$l)
            {
                
            }
            if ($l -eq $vizsgal.Length)
            {
                #Write-Host $vizsgalt[2]
                $vizsgal += @($vizsgalt[2])
            }
        }

        #max
        $maxertek = 0
        $maxind
        for ($j = 0; $j -lt $vizsgal.Length; ++$j)
        {
            $counter = 0
            for ($l = 0; $l -lt $tanar.Length; ++$l)
            {
                $vizsgalt = $tanar[$j].Split(',')
                if ($vizsgalt[2] -eq $vizsgal[$j]) {++$counter}
            }
            if ($counter -gt $maxertek) 
            {
                $maxertek = $counter
                $maxind = $j
            }
        }
        Write-Host $vizsgal[$maxind]
    }
}