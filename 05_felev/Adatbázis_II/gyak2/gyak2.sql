SELECT * FROM sz1;
SELECT * FROM dba_objects WHERE object_name = 'SZ1';
SELECT * FROM dba_synonyms WHERE synonym_name = 'SZ1';
SELECT * FROM dba_objects WHERE object_name = 'ATLAG';
SELECT * FROM dba_views WHERE view_name = 'ATLAG';
SELECT 'ATLAG' FROM nikovits.ATLAG_KOLTSEG;

SELECT * FROM dba_data_files;
SELECT * FROM DBA_EXTENTS;

-- 2 szerver között létrehozott kapcsolat (itt könnyű, mivel ELTE domainen belül vagyunk)
-- otthonról becsatlakozás az ELTE domainre a ceasar linkről, ilyenkor a link ugyanúgy megmarad, nem zavar bele
CREATE DATABASE LINK ullman_db_link CONNECT TO bhyuha IDENTIFIED BY Befott99
USING 'ullman.inf.elte.hu:1521/ullman';

SELECT * FROM kotroczo.dolgozo@ullman_db_link d, kotroczo.osztaly o WHERE d.oazon = o.oazon;

--
SELECT DISTINCT owner FROM dba_objects WHERE object_type = 'TABLE' GROUP BY owner HAVING count(*) > 40
MINUS
SELECT DISTINCT owner FROM dba_objects WHERE object_type = 'INDEX' GROUP BY owner HAVING count(*) > 37;

-- táblák neve és tulajdonosa amiknek legalább 8 db dátum tipusú oszlopa van
SELECT owner, table_name FROM DBA_TAB_COLUMNS WHERE data_type = 'DATE' GROUP BY owner, table_name HAVING count(*) >= 8;

-- PLSQL procedúra, amely paraméterul kaptt karakterlanc alapjan kiirja azoknak a tablaknak a nevet es tulajdonosat, amelyek az adott karakterlanccal kezdodnek
-- ha a parameter kisbetus, akkor is mukodjon
-- EXECUTE tableprint()

-- még hibás!!
SELECT * FROM dba_tables;
/
SET serveroutput ON;
CREATE OR REPLACE PROCEDURE tableprint(asd varchar2) IS
    CURSOR table_cursor (asd varchar2) IS
        SELECT table_name
        FROM dba_tables;
BEGIN
    FOR t IN table_cursor(asd) LOOP
        IF  substr(t,1,length(asd)) = asd THEN
            DBMS_OUTPUT.PUT_LINE('VAN :D');
        END IF;
    END LOOP;
END;
/