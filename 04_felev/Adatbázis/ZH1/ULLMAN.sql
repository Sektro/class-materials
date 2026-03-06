select * from nikovits.r;
select * from nikovits.s;

-- 1.feladat a)
SELEct avg(B) from nikovits.r r , (select distinct C from nikovits.s) s WHERE r.c = s.c;
-- 1. feladat b)
select sum(D) as SM from (select B-C as DF , D from nikovits.r r natural join nikovits.s s where B > 1) group by DF order by DF;
-- 1. feladat c)
SELECT B,SM FROM (select A, B, avg(c) as AV, sum(d) as SM from nikovits.r r natural join nikovits.s s group by A, B) where AV < 4 and SM > 23 order by SM; 


select * from nikovits.dolgozo;
select * from nikovits.osztaly;
select * from nikovits.fiz_kategoria;

-- 3. feladat
with atlag as (select avg(fizetes) as atlagfiz from (select beo.fizetes as fizetes from nikovits.dolgozo beo, nikovits.dolgozo fon where beo.fonoke = fon.dkod and fon.dnev = 'BLAKE' order by beo.fizetes fetch  FIRST 3 ROWS ONLY))
select dnev, fizetes from atlag, nikovits.dolgozo where fizetes < atlag.atlagfiz;


-- 6.feladat
with katatlag as (select avg(fizetes) as Kat_atlag, kategoria from nikovits.dolgozo, nikovits.fiz_kategoria where fizetes between also and felso group by kategoria)
select dnev, fizetes, Kat_atlag from nikovits.dolgozo d, nikovits.fiz_kategoria natural join katatlag f where fizetes between also and felso and fizetes < Kat_atlag;

-- 5.feladat
create table dolgozo2 as select * from nikovits.dolgozo;
select * from dolgozo2; 
SELECT sum(fizetes) FROM dolgozo2;  -- eredmeny: 36825
select * from dolgozo2, nikovits.fiz_kategoria where fizetes between also and felso order by fizetes;
delete from dolgozo2 
where dkod = (select dkod from dolgozo2, nikovits.fiz_kategoria where fizetes between also and felso  and kategoria = 1 order by fizetes fetch first 1 rows only) or 
dkod = (select dkod from dolgozo2, nikovits.fiz_kategoria where fizetes between also and felso  and kategoria = 2 order by fizetes fetch first 1 rows only) or 
dkod = (select dkod from dolgozo2, nikovits.fiz_kategoria where fizetes between also and felso  and kategoria = 3 order by fizetes fetch first 1 rows only) or 
dkod = (select dkod from dolgozo2, nikovits.fiz_kategoria where fizetes between also and felso  and kategoria = 4 order by fizetes fetch first 1 rows only) or 
dkod = (select dkod from dolgozo2, nikovits.fiz_kategoria where fizetes between also and felso  and kategoria = 5 order by fizetes fetch first 1 rows only);
SELECT sum(fizetes) FROM dolgozo2;  -- eredmeny: 27025

-- 4.feladat
with belepesszamok as (select belepes, count(*) as cnt from nikovits.dolgozo group by belepes)
select d.dnev, d.belepes from belepesszamok b, nikovits.dolgozo d where cnt >= 2 and d.belepes = b.belepes;






