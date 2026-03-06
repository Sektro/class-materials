select table_name, tablespace_name from user_tables;
-- nem látjuk, mivel user prefixen használjuk, nekünk meg még nincs táblánk

select * from user_tables;

create sequence SEQ1
MINVALUE 1 MAXVALUE 100 INCREMENT BY 5
START WITH 50 CYCLE;

SELECT * FROM DBA_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ1';

CREATE TABLE dolgozo AS SELECT * FROM kotroczo.dolgozo;

CREATE OR REPLACE VIEW v1 AS SELECT oazon, AVG(fizetes) avg_fiz FROM dolgozo GROUP BY oazon; 
--  view: nem tábla ==> folyamatosan változik a táblával együtt
SELECT * FROM dolgozo;
SELECT * FROM v1;

SELECT view_name, text
FROM dba_views
WHERE owner ='KOTROCZO' and view_name = 'V1';

CREATE OR REPLACE SYNONYM syn1 FOR v1;

SELECT * FROM syn1 WHERE oazon > 10;

SELECT *
FROM dba_synonyms
WHERE owner = 'KOTROCZO' AND synonym_name = 'SYN1';

SELECT owner, object_name, object_id, object_type
FROM dba_objects
WHERE owner = 'KOTROCZO';

SELECT * FROM dba_indexes;
DESCRIBE dba_indexes;

SELECT * FROM dba_tab_columns
WHERE owner = 'KOTROCZO' AND table_name = 'DOLGOZO';

SELECT * FROM dba_tables 
WHERE owner = 'KOTROCZO' AND table_name = 'DOLGOZO';

-- statisztikai adatok folyton változnak, a rendszer csak időközönként számolja ki őket
ANALYZE TABLE dolgozo COMPUTE STATISTICS;
ANALYZE TABLE dolgozo DELETE STATISTICS;
-- ezzel kikényszerithető ez a számolás

-- 1.feladat
-- Milyen tipusú objektumai vannak a NIKOVITS nevű felhasználónak az adatbázisban?
SELECT DISTINCT object_type FROM dba_objects WHERE owner = 'NIKOVITS';

-- 2.feladat
-- Hány különböző tipusú objektum van nyilvántartva az adatbázisban?
with asd as (SELECT DISTINCT object_type FROM dba_objects)
SELECT count(object_type) FROM asd;

-- SELECT count(DISTINCT object_type) FROM dba_objects;

--3.feladat
-- Kik azok a felhasználók, akiknek van nézete, de nincs triggere?
SELECT DISTINCT owner FROM dba_objects WHERE owner NOT IN (SELECT owner FROM dba_objects WHERE object_type = 'TRIGGER') AND owner IN (SELECT owner FROM dba_objects WHERE object_type = 'VIEW');

SELECT owner FROM dba_objects WHERE object_type = 'VIEW'
MINUS
SELECT owner FROM dba_objects WHERE object_type = 'TRIGGER';

-- katalógus
CREATE TABLE gyak1_20250911(d DATE);
INSERT INTO gyak1_20250911 VALUES (sysdate);
COMMIT;
SELECT owner FROM dba_tables WHERE table_name = 'GYAK1_20250911';





