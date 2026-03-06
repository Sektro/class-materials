EXPLAIN PLAN FOR
SELECT *
FROM kotroczo.dolgozo
WHERE dnev = 'KING';

SELECT * FROM plan_table;

SELECT plan_table_output FROM TABLE(dbms_xplan.display());


EXPLAIN PLAN FOR
SELECT SUM(fizetes)
FROM kotroczo.dolgozo
WHERE oazon = 10;

SELECT plan_table_output FROM TABLE(dbms_xplan.display());


SELECT * FROM kotroczo.dolgozo;
SELECT * FROM kotroczo.fiz_kategoria;
SELECT * FROM kotroczo.osztaly;

-- Adjuk meg azoknak az osztályoknak a neveit, amelyeknek van olyan dolgozója, 
-- aki az 1.es fizetési kategóriába esik.

EXPLAIN PLAN FOR
SELECT onev
FROM kotroczo.osztaly
WHERE oazon IN
(
SELECT oazon
FROM kotroczo.dolgozo
WHERE fizetes >= 700 AND fizetes <= 1200);
SELECT plan_table_output FROM TABLE(dbms_xplan.display());

EXPLAIN PLAN FOR
SELECT DISTINCT onev
FROM kotroczo.dolgozo NATURAL JOIN kotroczo.osztaly CROSS JOIN kotroczo.fiz_kategoria
WHERE fizetes >= also AND fizetes <= felso AND kategoria = 1;
SELECT plan_table_output FROM TABLE(dbms_xplan.display());


--HINTES VERZIÓ
EXPLAIN PLAN FOR
SELECT /*+ full(d) full(o) full(f) ordered use_nl(o) use_nl(f) */ DISTINCT onev
FROM kotroczo.dolgozo d NATURAL JOIN kotroczo.osztaly o CROSS JOIN kotroczo.fiz_kategoria f
WHERE fizetes >= also AND fizetes <= felso AND kategoria = 1;
SELECT plan_table_output FROM TABLE(dbms_xplan.display());

--hibák esetén nem szól, egyszzerűen nem aplikálja a hintet


/*
SELECT STATEMENT +  + 
  SORT + AGGREGATE + 
    TABLE ACCESS + FULL + NIKOVITS.CIKK
*/
EXPLAIN PLAN FOR
SELECT SUM(suly)
FROM nikovits.cikk;
SELECT plan_table_output FROM TABLE(dbms_xplan.display());

/*
SELECT STATEMENT +  + 
  SORT + AGGREGATE + 
    TABLE ACCESS + BY INDEX ROWID + NIKOVITS.CIKK
      INDEX + UNIQUE SCAN + NIKOVITS.C_CKOD
*/
EXPLAIN PLAN FOR
SELECT sum(suly)
FROM nikovits.cikk
WHERE ckod = 1;
SELECT plan_table_output FROM TABLE(dbms_xplan.display());

SELECT * FROM dba_ind_columns WHERE index_owner = 'NIKOVITS' AND index_name = 'C_KOD';


