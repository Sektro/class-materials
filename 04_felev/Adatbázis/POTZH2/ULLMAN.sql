-- 1. feladat segéd
/
-- 1. feladat segéd teszt
/
-- 1. feladat
CREATE OR REPLACE PROCEDURE kozos_primek(n1 integer, n2 integer) IS
    seged1 integer := n1;
    seged2 integer := n2;
    talalt BOOLEAN := false;
    kiir VARCHAR2(100) := '';
BEGIN
    IF seged1 > seged2 THEN 
        LOOP
            FOR i IN 2 .. seged2 loop
                talalt := false;
                IF mod(seged2, i) = 0  AND mod(seged1, i) = 0 THEN 
                    talalt := true;
                    IF seged2 = n2 THEN 
                        kiir := kiir || i;
                    ELSE
                        kiir := kiir || ',' || i;
                    END IF;
                    seged1 := seged1 / i;
                    seged2 := seged2 / i;
                END IF;
            END loop;
        EXIT WHEN (seged2 = 1 OR NOT talalt);
        END LOOP;
    ELSE
        LOOP
            FOR i IN 2 .. seged1 loop
                talalt := false;
                IF mod(seged2, i) = 0  AND mod(seged1, i) = 0 THEN 
                    talalt := true;
                    IF seged1 = n1 THEN 
                        kiir := kiir || i;
                    ELSE
                        kiir := kiir || ',' || i;
                    END IF;
                    seged1 := seged1 / i;
                    seged2 := seged2 / i;
                END IF;
            END loop;
        EXIT WHEN (seged1 = 1 OR NOT talalt);
    END LOOP;
    END IF;
    DBMS_OUTPUT.PUT_LINE(kiir);
END kozos_primek;
/
-- 1. feladat teszt
set serveroutput on
execute kozos_primek(218790, 266475);	
/
-- 4. feladat segéd
CREATE OR REPLACE FUNCTION legregebbi(o NUMBER) RETURN DATE
IS
    CURSOR cursor IS
        SELECT *
        FROM dolgozok;
    mindate dolgozok.belepes%TYPE := SYSDATE;
BEGIN
    FOR rec IN cursor LOOP
        IF o = rec.oazon THEN
            IF rec.belepes < mindate THEN
                mindate := rec.belepes;
            END IF;
        END IF;
    END LOOP;
    RETURN mindate;
END;
/
CREATE OR REPLACE FUNCTION osztalyon_dolgozok(o NUMBER) RETURN NUMBER
IS
    CURSOR cursor IS
        SELECT *
        FROM dolgozok;
    dolgozok_szama NUMBER := 0;
BEGIN
    FOR rec IN cursor LOOP
        IF o = rec.oazon THEN
            dolgozok_szama := dolgozok_szama + 1;
        END IF;
    END LOOP;
    RETURN dolgozok_szama;
END;
/
CREATE TABLE dolgozok AS SELECT * FROM nikovits.dolgozo;
select * from dolgozok;
select * from nikovits.dolgozo;
select * from nikovits.osztaly;
/
-- 4. feladat segéd teszt
set serveroutput on
execute DBMS_OUTPUT.PUT_LINE(legregebbi(20));
execute DBMS_OUTPUT.PUT_LINE(osztalyon_dolgozok(20));
/
-- 4. feladat
CREATE OR REPLACE PROCEDURE fiz_mod IS
    CURSOR cursor IS
        SELECT * 
        FROM dolgozok
        FOR UPDATE NOWAIT;
    CURSOR cursor_avg IS
        SELECT avg(fizetes) as avg
        FROM dolgozok;
    osztaly NUMBER := 0;
BEGIN
    FOR rec IN cursor LOOP
        IF rec.belepes = legregebbi(rec.oazon) THEN
        osztaly := osztalyon_dolgozok(rec.oazon);
            UPDATE dolgozok
            SET fizetes = rec.fizetes + 100 * osztaly
            WHERE CURRENT OF cursor;
        END IF;
    END LOOP;
    FOR rec IN cursor_avg LOOP 
        DBMS_OUTPUT.PUT_LINE(round(rec.avg,2));
    END LOOP;
    rollback;
END fiz_mod;
/
-- 4. feladat teszt
set serveroutput on
execute fiz_mod();
/
select avg(fizetes) as avg from dolgozok;
/
-- 6.feladat segéd
CREATE OR REPLACE FUNCTION tartalmaz(nev VARCHAR,s VARCHAR) RETURN BOOLEAN
IS
BEGIN
    FOR i IN 1..length(nev) LOOP
        IF  substr(nev,i,length(s)) = s THEN
            RETURN true;
        END IF;
    END LOOP;
    RETURN false;
END tartalmaz;
/
CREATE OR REPLACE FUNCTION felmenok(neve VARCHAR, s VARCHAR) RETURN BOOLEAN
IS
    CURSOR cursor IS
        SELECT *
        FROM NIKOVITS.VAGYONOK;
BEGIN
    FOR rec IN cursor LOOP
        IF rec.nev = neve THEN
            IF rec.apja != 'NINCS' THEN
                IF NOT tartalmaz(neve, s) THEN
                    return felmenok(rec.apja, s);
                ELSE
                    return false;
                END IF;
            ELSE
                IF NOT tartalmaz(neve, s) THEN
                    return true;
                ELSE
                    return false;
                END IF;
            END IF;
        END IF;
    END LOOP;
END felmenok;
/
-- 6. feladat segéd tesztelése
set serveroutput on
BEGIN
    IF tartalmaz('ADAM', 'N') THEN
        DBMS_OUTPUT.PUT_LINE('VAN :D');
    ELSE
        DBMS_OUTPUT.PUT_LINE('nincs :(');
    END IF;
END;
/
set serveroutput on
BEGIN
    IF felmenok('KAIN', 'ADAM') THEN
        DBMS_OUTPUT.PUT_LINE('megfelel');
    ELSE
        DBMS_OUTPUT.PUT_LINE('nem felel meg');
    END IF;
END;
/
select * from NIKOVITS.VAGYONOK;
/ 
-- 6. feladat
CREATE OR REPLACE PROCEDURE kar_nincs(p varchar2) IS
    CURSOR cursor IS
        SELECT *
        FROM NIKOVITS.VAGYONOK;
BEGIN
    FOR rec IN cursor LOOP
        IF felmenok(rec.nev, p) THEN
            DBMS_OUTPUT.PUT_LINE(rec.nev || ' - ' || rec.vagyon || (' Ft'));
        END IF;
    END LOOP;
END kar_nincs;
/
-- 6. feladat tesztelése
set serveroutput on
execute kar_nincs('B');
execute kar_nincs('N');
/