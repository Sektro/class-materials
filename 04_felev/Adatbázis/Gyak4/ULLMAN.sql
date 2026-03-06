create table GYAK4 as select o.oazon as Oazon, o.telephely as Telephely, avg(d.fizetes) as AtlagFiz from nikovits.dolgozo d, nikovits.osztaly o where d.oazon = o.oazon group by o.oazon, o.telephely;
select * from GYAK4;

SELECT DISTINCT onev, AVG(fizetes) + 100 AS fiz_plus
FROM nikovits.Dolgozo d, nikovits.Osztaly o
WHERE d.oazon = o.oazon
GROUP BY onev
HAVING COUNT(dkod) > 3
ORDER BY onev;

/*
Fontos függvények:
• AVG
• COUNT
• MAX
• MIN
• SUM
*/

-- kötelezõ feladat natural joinnal
select oazon as Oazon, telephely as Telephely, round(avg(fizetes)) as AtlagFiz from nikovits.dolgozo natural join nikovits.osztaly group by oazon, telephely;