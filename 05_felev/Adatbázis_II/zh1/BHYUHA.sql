-- 5. Feladat

SELECT table_name
FROM dba_tab_columns
WHERE data_type = 'NUMBER' AND owner = 'NIKOVITS'
GROUP BY owner, table_name
HAVING COUNT(*) = 3
INTERSECT
SELECT table_name
FROM dba_tab_columns
WHERE data_type = 'VARCHAR2' AND owner = 'NIKOVITS'
GROUP BY owner, table_name
HAVING COUNT(*) = 3;

-- 6. Feladat

SELECT tablespace_name
FROM dba_tables
GROUP BY tablespace_name
ORDER BY count(*)
FETCH FIRST 1 ROWS ONLY;


-- 7. Feladat

SELECT owner FROM dba_extents WHERE segment_name IN (
SELECT segment_name
FROM dba_extents
WHERE segment_type = 'TABLE'
GROUP BY segment_name
HAVING COUNT(DISTINCT file_id) = 3);

-- 8. Feladat

SELECT index_owner as owner, index_name
FROM dba_ind_columns
GROUP BY index_owner, index_name
HAVING COUNT(column_name) = 3
INTERSECT
SELECT table_owner, index_name
FROM dba_indexes
WHERE index_type = 'FUNCTION-BASED NORMAL'
GROUP BY table_owner, index_name;


-- 9. Feladat

SELECT owner, object_name, dbms_rowid.rowid_relative_fno('AAAUs1AAFAAAU8TAAD') as relative_fno FROM dba_objects WHERE object_id = dbms_rowid.rowid_object('AAAUs1AAFAAAU8TAAD');
SELECT owner, table_name, dbms_rowid.rowid_relative_fno('AAAUs1AAFAAAU8TAAD') as relative_fno FROM dba_tables;
select * from DBA_TABLES;
-- 10. Feladat
CREATE OR REPLACE PROCEDURE list_views(p_owner VARCHAR2, p_table VARCHAR2) IS
    CURSOR curs IS     
            SELECT * 
            FROM dba_views 
            WHERE UPPER(owner) = UPPER(p_owner);
    rec curs%rowtype;
BEGIN
    FOR rec IN curs LOOP
        dbms_output.put_line(rec.view_name);
    END LOOP;
END;
/