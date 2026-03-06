-- Rowid
SELECT rowid, kotroczo.dolgozo.* FROM kotroczo.dolgozo;

SELECT  rowid, 
        dbms_rowid.rowid_object(rowid) adatobj,
        dbms_rowid.rowid_relative_fno(rowid) fajl,
        dbms_rowid.rowid_block_number(rowid) blokk,
        dbms_rowid.rowid_row_number(rowid) sor,
        kotroczo.dolgozo.*
FROM kotroczo.dolgozo;

SELECT * FROM dba_objects WHERE object_id = 78322;
SELECT * FROM dba_data_files; --itteni relative_fno-t adja vissza a 2. fgv

-- Indexelés
SELECT * FROM dba_indexes WHERE owner = 'NIKOVITS' AND index_name = 'EMP2';
SELECT * FROM nikovits.emp;
SELECT * FROM dba_ind_columns WHERE index_owner = 'NIKOVITS' AND index_name = 'EMP2';
SELECT * FROM dba_ind_expressions WHERE index_owner = 'NIKOVITS';

-- Particionálás
-- EZ NEM MŰKÖDIK ARAMISON (ullmant kell használni)
-- Feature nekűnk nem elérhető, de a rendszer magának megengedi ==> CSAK system által készitett particiók
SELECT * FROM dba_part_tables;
SELECT * FROM dba_tab_partitions;
SELECT * FROM dba_part_indexes;

-- Adjuk meg azoknak a tábláknak a neveit, amelyeknek van csökkenő sorrendben indexelt oszlopa.
SELECT DISTINCT table_name FROM dba_ind_columns WHERE descend = 'DESC';
--^^ nem jó, több embernek lehet ugyanolyan nevű táblája
--ˇˇ igy jó
SELECT table_owner, table_name, column_name 
FROM dba_ind_columns 
WHERE descend = 'DESC' 
GROUP BY table_owner, table_name, column_name;

-- Adjuk meg azoknak az indexeknek a neveit, amelyek legalább 9 oszloposak.
-- (vagyis a táblának legalább 9 oszlopát vagy egyéb kifejezését indexelik.)
SELECT index_owner, index_name 
FROM dba_ind_columns 
WHERE column_position >= 9 
GROUP BY index_owner, index_name;

SELECT index_owner, index_name, COUNT(column_name) db
FROM dba_ind_columns 
GROUP BY index_owner, index_name
HAVING COUNT(column_name) >= 9;

-- Írjunk meg egy plsql procedúrát, amelyik a paraméterül kapott táblára vonatkozóan 
-- kiírja a tábla indexeit és azok méretét bájtban. Az indexeket abc sorrendben, külön sorokban
-- kell kiírni, a méretet szóközzel elválasztva az index nevétől így:
-- INDEX1: 1234  
-- INDEX2: 5678
-- CREATE OR REPLACE PROCEDURE list_indexes(p_owner VARCHAR2, p_table VARCHAR2) IS
-- Tesztelés:
-- SET SERVEROUTPUT ON
-- EXECUTE list_indexes('nikovits', 'customers');
-- EXECUTE list_indexes('nikovits', 'emp');
SELECT * FROM dba_indexes;
SELECT * FROM dba_segments;
SELECT segment_name, bytes FROM dba_segments WHERE segment_type = 'INDEX' AND owner = 'NIKOVITS' AND 
segment_name IN (
SELECT index_name FROM dba_indexes WHERE owner = 'NIKOVITS' AND table_name = 'EMP')
ORDER BY segment_name;
/
CREATE OR REPLACE PROCEDURE list_indexes(p_owner VARCHAR2, p_table VARCHAR2) IS
    CURSOR cursor IS
        SELECT segment_name, bytes 
        FROM dba_segments 
        WHERE segment_type = 'INDEX' AND 
            UPPER(owner) = UPPER(p_owner) AND 
            segment_name IN (
                SELECT index_name 
                FROM dba_indexes 
                WHERE UPPER(owner) = UPPER(p_owner) AND UPPER(table_name) = UPPER(p_table))
        ORDER BY segment_name;
    rec cursor%rowtype;
BEGIN
    FOR rec IN cursor LOOP
        DBMS_OUTPUT.PUT_LINE(rec.segment_name || ': ' || rec.bytes);
    END LOOP;
END list_indexes;
/
SET SERVEROUTPUT ON
EXECUTE list_indexes('nikovits', 'customers');
EXECUTE list_indexes('nikovits', 'emp');
/

-- Hozzunk létre egy indexet a dolgozó tábla oazon oszlopára dolgozo_oazon néven.
SELECT * FROM dolgozo;
CREATE INDEX dolgozo_oazon ON dolgozo(oazon);

SELECT * FROM dba_indexes WHERE index_name = 'DOLGOZO_OAZON';
