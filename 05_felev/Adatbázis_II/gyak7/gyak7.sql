-- Ullman 
-- Vegyük 2012. februárjának első hétfőjét és adjuk meg, hogy összesen
-- hány darab telefonhyvást kezdeményeztek a megelőző naőpon a Szentendrei
-- primer központból

SELECT SUM(darab)
FROM nikovits.hivas, nikovits.kozpont, nikovits.primer
WHERE hivas.kozp_azon_hivo = kozpont.kozp_azon
    AND kozpont.primer = primer.korzet
    AND primer.varos = 'Szentendre'
    AND hivas.datum = next_day(to_date('2012.01.31', 'YYYY.MM.DD'), 'hétfő') - 1;
    
SELECT * FROM dba_clusters;
SELECT * FROM dba_clu_columns WHERE owner = 'NIKOVITS'; -- mely oszlop lett felhasználva a cluster szervezéshez
SELECT * FROM dba_cluster_hash_expressions WHERE owner = 'NIKOVITS';

-- Adjuk meg azokat a clustereket az adatbázisban, amelyeken még nincs egy tábla sem!
-- (owner, cluster_name)
SELECT COUNT(*) as db FROM (
SELECT owner, cluster_name FROM dba_clusters
MINUS
SELECT owner, cluster_name FROM dba_tables group by owner, cluster_name);

SELECT COUNT(*) as db FROM (
SELECT owner, cluster_name FROM dba_clusters
MINUS
SELECT owner, cluster_name FROM dba_clu_columns group by owner, cluster_name);

-- Adjuk meg azokat a clustereket, amelyeken legalább 2 tábla van!
SELECT owner, cluster_name 
FROM dba_tables
WHERE cluster_name IS NOT NULL
group by owner, cluster_name
HAVING count(*) >= 2;



