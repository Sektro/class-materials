select * from nikovits.szeret;
select distinct sz1.nev from nikovits.szeret sz1, nikovits.szeret sz2 where sz1.nev = sz2.nev and sz1.gyumolcs <> sz2.gyumolcs;

select * from nikovits.dolgozo where dnev in ('KING');

select * from nikovits.dolgozo where length(dnev) = 4;
select dnev from nikovits.dolgozo where length(dnev) = 4;
delete from gyak3;
insert into GYAK3 (select beo.dnev as beosztott,fon.dnev as fonok from nikovits.dolgozo beo, nikovits.dolgozo fon where (length(beo.dnev) = length(fon.dnev))  and (beo.fonoke = fon.dkod));
select * from gyak3;
select beo.dnev as beosztott,fon.dnev as fonok from nikovits.dolgozo beo, nikovits.dolgozo fon where (length(beo.dnev) = length(fon.dnev)) and (beo.fonoke = fon.dkod);
select * from nikovits.dolgozo where 'str' = 'str '; // mindig igaz: eltekint a sorvégi szóköztõl

//Kisbetû-nagybetûkezelõ:
//LOWER
//UPPER
//INITCAP
//Karakterkezelõ függvények:
//CONCAT
//SUBSTR
//LENGTH
//INSTR
//LPAD | RPAD
//TRIM
//REPLACE

select NVL(jutalek,0) from nikovits.dolgozo;    // ha érték null, kiad: 2.paraméter
select NVL2(jutalek,1000,0)  from nikovits.dolgozo;
select TO_CHAR(NVL2(jutalek,'kap pénzt','nem kap pénzt')) as jutalék  from nikovits.dolgozo;

select decode(to_char(dkod), 'thisaintitchief', '7499',
                    'blud', '7369',
                to_char(dkod)) from nikovits.dolgozo;



