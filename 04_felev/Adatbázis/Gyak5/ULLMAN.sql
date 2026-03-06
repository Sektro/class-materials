-- 5-ös gyakorlati feladatsortól már hasonlitanak a feladatok a ZH-ban találhatóakra
SELECT DISTINCT onev, AVG(fizetes) + 100 AS fiz_plus
FROM nikovits.Dolgozo d, nikovits.Osztaly o
WHERE d.oazon = o.oazon
GROUP BY onev
HAVING COUNT(dkod) > 3
ORDER BY onev;

select * from nikovits.dolgozo;
select * from nikovits.fiz_kategoria;

-- 14. (4.feladatsor)
select kategoria, count(distinct oazon), count(*) from nikovits.dolgozo, nikovits.fiz_kategoria
where fizetes >= also and fizetes <= felso
group by kategoria; -- van-e olyan ahol 1 osztály van (nincs, count(distinct oazon) sehol nem 1)
-- 2-vel:
/*
select kategoria from nikovits.dolgozo join nikovits.fiz_kategoria
on fizetes >= also and fizetes <= felso
group by kategoria;
*/
select kategoria from nikovits.dolgozo, nikovits.fiz_kategoria
where fizetes >= also and fizetes <= felso
group by kategoria having count(distinct oazon) = 2;

-- 19. (4.feladatsor)
select o.oazon, onev, count(dkod), nvl(sum(fizetes),0) -- null helyére 0
from nikovits.dolgozo d join nikovits.osztaly o on (d.oazon = o.oazon)
group by o.oazon, onev;
select o.oazon, onev, count(dkod), sum(fizetes)
from nikovits.dolgozo d right outer join nikovits.osztaly o on (d.oazon = o.oazon)
group by o.oazon, onev;
-- külsõ join: megõrzi valamelyik oldal értékeit amik elvesznének (left, vagy right)
-- pl.: 40-es oazon eltûnne, mivel nincs dolgozója, de megmarad

-- 17. (4.feladatsor)
-- select decode(mod(dkod, 2),0, 'páros', 1, 'páratlan') paritas, count(dkod) szam
-- 

-- create or replace
-- Allekérdezések definiálása:
-- with
--  osztaly_osszfiz as (select ...)
-- select * from osztaly_osszfiz where ...

CREATE TABLE R(A VARCHAR(10), B INTEGER, C INTEGER);
INSERT INTO R VALUES('X',1, 2);
INSERT INTO R VALUES('Y',2, 3);
INSERT INTO R VALUES('Y',3, 4);
INSERT INTO R VALUES('X',1, 5);
INSERT INTO R VALUES('Y',3, 5);
INSERT INTO R VALUES('X',4, 2);
INSERT INTO R VALUES('X',4, 4);
CREATE TABLE S(C INTEGER, D INTEGER);
INSERT INTO S VALUES(2, 8);
INSERT INTO S VALUES(2, 15);
INSERT INTO S VALUES(3, 9);
INSERT INTO S VALUES(3, 14);
INSERT INTO S VALUES(4, 11);
INSERT INTO S VALUES(4, 17);
INSERT INTO S VALUES(2, 1);
INSERT INTO S VALUES(6, 20);
-- DELETE FROM R where A = 'Y' and B = 3 and C = 4; 

select * from s;
select * from r;

create table gyak5 as select r.A, AVG(D) as av from r, s where B >= 2 group by A;
select * from gyak5;


