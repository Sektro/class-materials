--Melyek azok az objektum tipusok, amelyek tényleges tárolást igényelnek, vagyis tartoznak hozzájuk adatblokkok?
SELECT * FROM DBA_OBJECTS where data_object_id IS NOT NULL;

--Melyek azok az objektum tipusok, amelyek tényleges tárolást nem igényelnek, vagyis tartoznak hozzájuk adatblokkok?
SELECT DISTINCT object_type FROM DBA_OBJECTS where data_object_id IS NULL
INTERSECT
SELECT DISTINCT object_type FROM DBA_OBJECTS where data_object_id IS NOT NULL;

--Adjuk meg azoknak a tábláknak a nevét, amiknek az 1. és a 4. oszlopa is VARCHAR2 tipusú
SELECT owner, table_name FROM dba_tab_columns WHERE column_id = 1 AND data_type = 'VARCHAR2'
INTERSECT
SELECT owner, table_name FROM dba_tab_columns WHERE column_id = 4 AND data_type = 'VARCHAR2';

--Hol vannak a NIKOVITS felhasználó SZALLIT táblájának az adatai tárolva?
SELECT * FROM dba_segments WHERE owner = 'NIKOVITS' AND segment_name = 'SZALLIT'; --nem jó, itt csak a header_file-t kapjuk meg, a többit nem

SELECT * FROM dba_data_files WHERE file_id IN (SELECT file_id FROM dba_extents WHERE owner = 'NIKOVITS' AND segment_name = 'SZALLIT');
--minden szegmenshez tartozik objektum, de nem minden objektumhoz tartozik szegmens

--Adjuk meg adatfájlonként, hogy az egyes adatfájlokban mennyi a foglalt hely összesen. (fájlnév, fájl_méret, foglalt_hely)
with free_spaces as (SELECT file_id as f_id, bytes as free_space FROM dba_free_space)
SELECT file_name, (bytes-free_spaces.free_space) as fájl_méret, bytes as foglalt_hely FROM dba_data_files, free_spaces WHERE file_id = free_spaces.f_id;
--nem jó

SELECT file_id, SUM(bytes) FROM dba_extents GROUP BY file_id;

SELECT file_name, f.bytes, e.ex_size
FROM dba_data_files f, (SELECT file_id, SUM(bytes) ex_size
                        FROM dba_extents
                        GROUP BY file_id) e
WHERE f.file_id = e.file_id;

--Melyik két felhasználó objektumai összesen foglalják a legtöbb helyet az adatbázisban?
SELECT * FROM dba_segments s, dba_objects o WHERE o.data_object_id = s.header_block;

SELECT owner, SUM(bytes)
FROM dba_segments
GROUP BY owner
ORDER BY 2 DESC
FETCH FIRST 2 ROWS ONLY;

--Van-e NIKOVITS felhasználónak olyan táblája, amelyik több adatfájlban is helyet foglal?
SELECT * FROM dba_data_files WHERE file_id IN (SELECT file_id FROM dba_extents WHERE owner = 'NIKOVITS');

SELECT * FROM dba_extents e, (SELECT count(*) as dbszam, extent_id as o_id FROM dba_extents WHERE owner = 'NIKOVITS' AND segment_type = 'TABLE') o WHERE owner = 'NIKOVITS' AND segment_type = 'TABLE' AND ;
SELECT count(*) as dbszam, extent_id as o_id FROM dba_extents WHERE owner = 'NIKOVITS' AND segment_type = 'TABLE' GROUP BY segment_name;


select segment_name, COUNT(DISTINCT file_id) as files FROM dba_extents WHERE owner = 'NIKOVITS' AND segment_type = 'TABLE' GROUP BY segment_name HAVING COUNT(DISTINCT file_id) > 1;


--Hány összefüggő szabad terület van a 'users01.dbf' adatfájlban? Mekkora ezek mérete?
SELECT COUNT(*), SUM(fr.bytes)
FROM dba_data_files f, dba_free_space fr
WHERE file_name LIKE '%users01.dbf%' AND f.file_id = fr.file_id;

--Hány százalékban foglaltak az adatfájlok?
SELECT * FROM dba_free_space;
SELECT * FROM dba_data_files;

SELECT DISTINCT f.file_id, ((f.bytes-fr.bytes) / f.bytes) as PERCENTAGE FROM dba_data_files f, dba_free_space fr WHERE f.file_id = fr.file_id;

SELECT d.file_id, SUM(e.bytes) / d.bytes * 100 as percentage
FROM dba_data_files d, dba_extents e
WHERE d.file_id = e.file_id
GROUP BY d.file_id, d.bytes;

--Melyik táblatéren van az ORAUSER felhasználó DOLGOZO táblája?
SELECT tablespace_name FROM dba_segments WHERE segment_name = 'DOLGOZO' AND segment_type = 'TABLE' AND owner = 'ORAUSER';
--YEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA



