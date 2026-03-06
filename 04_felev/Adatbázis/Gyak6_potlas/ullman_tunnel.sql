-- gyak6
create table gyak6 as select * from nikovits.osztaly;
drop table gyak6;

select * from gyak6;
select * from nikovits.dolgozo;
select * from nikovits.fiz_kategoria;

select oazon as Oazon, telephely as Telephely, round(avg(fizetes)) as AtlagFiz from nikovits.dolgozo natural join nikovits.osztaly group by oazon, telephely;
with o2s as (select distinct oazon from nikovits.dolgozo, nikovits.fiz_kategoria where fizetes between (select also from nikovits.fiz_kategoria where kategoria = 2) and (select felso from nikovits.fiz_kategoria where kategoria = 2))
select * from gyak6 where gyak6.oazon not in (select distinct oazon from nikovits.dolgozo, nikovits.fiz_kategoria where fizetes between (select also from nikovits.fiz_kategoria where kategoria = 2) and (select felso from nikovits.fiz_kategoria where kategoria = 2));

delete from gyak6 where gyak6.oazon in (select distinct oazon from nikovits.dolgozo, nikovits.fiz_kategoria where fizetes between (select also from nikovits.fiz_kategoria where kategoria = 2) and (select felso from nikovits.fiz_kategoria where kategoria = 2));
commit;
rollback; -- ellenőrzés

-- zh gyakorlas 
-- NIKOVITS.CIKK     (ckod, cnev, szin, suly)              -- cikkek kódja, neve, színe, súlya
-- NIKOVITS.PROJEKT  (pkod, pnev, helyszin)                -- projektek kódja, neve, helyszíne
-- NIKOVITS.SZALLITO (szkod, sznev, statusz, telephely)    -- szállítók kódja, neve, státusza, telephelye
-- NIKOVITS.SZALLIT  (szkod, ckod, pkod, mennyiseg, datum) -- a szallító a cikket a projekthez szállítja 

select * from NIKOVITS.CIKK;
select * from NIKOVITS.projekt;
select * from NIKOVITS.szallito;
select * from NIKOVITS.szallit;

-- 1. 
select distinct cikk.ckod, cikk.cnev from nikovits.cikk cikk, nikovits.szallit szallit, nikovits.szallito sz;allito where cikk.ckod = szallit.ckod and szallit.szkod = szallito.szkod and szallito.telephely = 'Pecs' order by cikk.ckod;
-- 2. 
with cikkek as (select distinct cikk.ckod as cikkkod, cikk.cnev as cikknev from nikovits.cikk cikk, nikovits.szallit szallit, nikovits.szallito szallito where cikk.ckod = szallit.ckod and szallit.szkod = szallito.szkod and szallito.telephely = 'Pecs')
select count(cikkkod) from cikkek;
-- 3.
SELECT distinct ckod, cnev from nikovits.cikk
minus
select distinct cikk.ckod, cikk.cnev from nikovits.cikk cikk, nikovits.szallit szallit, nikovits.szallito szallito where cikk.ckod = szallit.ckod and szallit.szkod = szallito.szkod and szallito.telephely = 'Pecs';
-- 4. 
with cikkek as (
SELECT distinct ckod as cikkod, cnev as cikknev from nikovits.cikk
minus
select distinct cikk.ckod, cikk.cnev from nikovits.cikk cikk, nikovits.szallit szallit, nikovits.szallito szallito where cikk.ckod = szallit.ckod and szallit.szkod = szallito.szkod and szallito.telephely = 'Pecs'
)
select count(cikkod) from cikkek;
-- sablon osszes joinjára:
select * from nikovits.szallito natural join nikovits.szallit natural join nikovits.cikk natural join nikovits.projekt;
-- 5.
select distinct sznev from nikovits.szallito natural join nikovits.szallit natural join nikovits.cikk natural join nikovits.projekt where statusz > 10 and helyszin = 'Budapest' and cnev = 'szek';
-- 6.
select distinct pkod from nikovits.projekt natural join nikovits.szallito natural join nikovits.szallit natural join nikovits.cikk where szin = 'barna' order by pkod;



