//szeret
SELECT * FROM nikovits.szeret;
SELECT nev FROM nikovits.szeret WHERE gyumolcs like 'alma'
INTERSECT
SELECT nev FROM nikovits.szeret WHERE gyumolcs like 'körte';
CREATE TABLE GYAK2 AS SELECT distinct sz1.nev FROM nikovits.szeret sz1, nikovits.szeret sz2 where sz1.nev = sz2.nev and sz1.gyumolcs != sz2.gyumolcs;
SELECT distinct sz1.nev FROM nikovits.szeret sz1, nikovits.szeret sz2, nikovits.szeret sz3 where sz1.nev = sz2.nev and sz1.nev = sz3.nev and sz1.gyumolcs != sz2.gyumolcs and sz1.gyumolcs != sz3.gyumolcs and sz2.gyumolcs != sz3.gyumolcs;
SELECT distinct sz1.nev FROM nikovits.szeret sz1, nikovits.szeret sz2, nikovits.szeret sz3 where sz1.nev = sz2.nev and sz1.nev = sz3.nev and sz1.gyumolcs != sz2.gyumolcs and sz1.gyumolcs != sz3.gyumolcs and sz2.gyumolcs = sz3.gyumolcs;

//dolgozo
SELECT * FROM nikovits.dolgozo;
SELECT dnev FROM nikovits.dolgozo;
SELECT dkod FROM nikovits.dolgozo;

//feladat2
//1
SELECT dnev FROM nikovits.dolgozo where fonoke = (select dkod from nikovits.dolgozo where dnev = 'KING');
SELECT beo.dnev FROM nikovits.dolgozo fon, nikovits.dolgozo beo where beo.fonoke = fon.dkod and fon.dnev = 'KING';
