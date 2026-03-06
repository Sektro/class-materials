SET AUTOCOMMIT OFF;

CREATE TABLE tr (
    a NUMBER
);

INSERT INTO tr VALUES (1);

SELECT * FROM tr;

COMMIT;

UPDATE tr SET a = a + 1;

SAVEPOINT ketto;

ROLLBACK TO ketto;

ROLLBACK;

-- read commited
-- session 1

SELECT * FROM tr;

UPDATE tr SET a = a + 1;

SELECT * FROM tr;
--itt updatelt verzió

COMMIT;
--innentől többi sessionben is updated verzió

-- serializable
-- session 2 

SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

SELECT * FROM tr;
-- ITT MÉG NEM UPDATELT (első sor miatt)

COMMIT;
-- itt már igen





SELECT * FROM v$lock;
SELECT * FROM v$session;
SELECT * FROM v$session WHERE username = 'KOTROCZO';

-- 1.feladat
SELECT se.sid, se.username, lo.type, lo.lmode, lo.ctime
FROM v$lock lo, v$session se
WHERE se.sid = lo.sid;

--2.feladat
SELECT se.sid, se.username, lo.type, lo.lmode,
lo.request, lo.ctime, block
FROM v$lock lo, v$session se
WHERE se.sid = lo.sid;

--3.feladat
SELECT lo.oracle_username, lo.session_id,
lo.locked_mode, db.object_name, db.object_type
FROM v$locked_object lo, dba_objects db
WHERE lo.object_id = db.object_id and oracle_username =
'KOTROCZO';

LOCK TABLE tr IN ROW SHARE MODE;

DROP TABLE tr2;

CREATE TABLE tr2 (a NUMBER);

INSERT INTO tr2 VALUES (1);

COMMIT;

-- session 1

UPDATE tr SET a = a + 1;

UPDATE tr2 SET a = a + 1;

-- session 2

UPDATE tr2 SET a = a + 1;

UPDATE tr SET a = a + 1;










