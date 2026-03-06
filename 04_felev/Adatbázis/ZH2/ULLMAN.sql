-- 1. feladat
set serveroutput on
CREATE OR REPLACE PROCEDURE felbontas(p1 integer)
IS
    seged integer := p1;
    kiir VARCHAR2(100) := '';
BEGIN
    LOOP
        FOR i IN 2 .. seged loop
            IF mod(seged, i) = 0 THEN 
                IF seged = p1 THEN 
                    kiir := kiir || i;
                ELSE
                    kiir := kiir || ',' || i;
                END IF;
                seged := seged / i;
            END IF;
        END loop;
    EXIT WHEN seged = 1;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(kiir);
END felbontas;
/
-- 1. feladat teszt
set serveroutput on
EXECUTE felbontas(26068);
/



-- el�k�sz�let
CREATE TABLE sajatdolgozo AS SELECT * FROM nikovits.dolgozo;
select * from sajatdolgozo;
select * from nikovits.fiz_kategoria;
select * from nikovits.osztaly;
/
-- 2. feladat seged
set serveroutput on
CREATE OR REPLACE FUNCTION oszt_szam(o integer) RETURN NUMBER
IS
    num NUMBER := 0;
    CURSOR oszt_cursor IS
        SELECT *
        FROM nikovits.dolgozo
        WHERE oazon = o;
BEGIN
    FOR rec IN oszt_cursor LOOP
        num := num + 1;
    END LOOP;
    RETURN num;
END;
/
CREATE OR REPLACE FUNCTION fiz_kat_felso(k integer) RETURN NUMBER
IS
    CURSOR fiz_cursor IS
        SELECT *
        FROM nikovits.fiz_kategoria;
BEGIN
    FOR rec IN fiz_cursor LOOP
        IF rec.kategoria = k then
            return rec.felso;
        end if;
    END LOOP;
    RETURN 0;
END;
/
CREATE OR REPLACE FUNCTION fiz_kat_also(k integer) RETURN NUMBER
IS
    CURSOR fiz_cursor IS
        SELECT *
        FROM nikovits.fiz_kategoria;
BEGIN
    FOR rec IN fiz_cursor LOOP
        IF rec.kategoria = k then
            return rec.also;
        end if;
    END LOOP;
    RETURN 0;
END;
/
-- 2. feladat seged teszt
set serveroutput on
EXECUTE   DBMS_OUTPUT.PUT_LINE(oszt_szam(10));
EXECUTE   DBMS_OUTPUT.PUT_LINE(fiz_kat_felso(4));
EXECUTE   DBMS_OUTPUT.PUT_LINE(fiz_kat_also(4));
/
-- 2. feladat
set serveroutput on
CREATE OR REPLACE PROCEDURE upd_kat(p integer) 
    atl NUMBER;
IS
    CURSOR upd_cursor IS
        SELECT *
        FROM sajatdolgozo
        WHERE fizetes >= fiz_kat_also(p) and fizetes <= fiz_kat_felso(p)
        FOR UPDATE NOWAIT;
    CURSOR cursor IS
        SELECT *
        FROM sajatdolgozo
BEGIN
    FOR rec IN upd_cursor LOOP
        UPDATE sajatdolgozo
        SET fizetes = fizetes + oszt_szam(rec.oazon) * 100
        WHERE CURRENT OF upd_cursor;
        atl = avg(upd_cursor.fizetes);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(avg(sajatdolgozo.fizetes));
END upd_kat;
/
set serveroutput on
execute upd_kat(2);
/
ROLLBACK;
/
select * from sajatdolgozo;
/
-- 3. feladat segéd
CREATE OR REPLACE FUNCTION van2ugyanolyan(s VARCHAR) RETURN BOOLEAN
IS
BEGIN
    FOR i IN 1..length(s) LOOP
        FOR j IN 1..length(s) LOOP
            IF j != i AND substr(s,i,1) = substr(s,j,1) THEN
                RETURN true;
            END IF;
        END LOOP;
    END LOOP;
    RETURN false;
END van2ugyanolyan;
/
-- 3. feladat segéd teszt
set serveroutput on
BEGIN
    IF van2ugyanolyan('asdghjkd') THEN
        DBMS_OUTPUT.PUT_LINE('VAN :D');
    ELSE
        DBMS_OUTPUT.PUT_LINE('nincs :(');
    END IF;
END;
/
-- 3. feladat
CREATE OR REPLACE PROCEDURE betu2 IS
    CURSOR cursor IS
        select *
        from sajatdolgozo;
BEGIN
    FOR rec IN cursor LOOP
        IF rec.fizetes > 1200 AND van2ugyanolyan(rec.dnev) THEN
            DBMS_OUTPUT.PUT_LINE(rec.dnev || ' - ' || rec.fizetes || ' Ft');
        END IF;
    END LOOP;
END betu2;
/
-- 3. feladat tesztelés
set serveroutput on
execute betu2();
/
-- 4. feladat segédek
CREATE OR REPLACE FUNCTION vanbenneT(s VARCHAR) RETURN BOOLEAN
IS
BEGIN
    FOR i IN 1..length(s) LOOP
        IF  substr(s,i,1) = 'T' THEN
            RETURN true;
        END IF;
    END LOOP;
    RETURN false;
END vanbenneT;
/
CREATE OR REPLACE FUNCTION fiz_kat(fizetes integer) RETURN NUMBER
IS
    CURSOR fiz_cursor IS
        SELECT *
        FROM nikovits.fiz_kategoria;
BEGIN
    FOR rec IN fiz_cursor LOOP
        IF fizetes >= rec.also and fizetes <= rec.felso then
            return rec.kategoria;
        end if;
    END LOOP;
    RETURN 0;
END;
/
-- 4. feladat segédek tesztelés
set serveroutput on
BEGIN
    IF vanbenneT('asdghjkdT') THEN
        DBMS_OUTPUT.PUT_LINE('VAN :D');
    ELSE
        DBMS_OUTPUT.PUT_LINE('nincs :(');
    END IF;
END;
/
set serveroutput on
BEGIN
    DBMS_OUTPUT.PUT_LINE(fiz_kat(2000));
END;
/
-- 4. feladat
CREATE OR REPLACE PROCEDURE fiz_novel(p_oazon number) IS
    CURSOR cursor IS
        SELECT * 
        FROM sajatdolgozo
        FOR UPDATE NOWAIT;
    CURSOR cursorKiiras IS
        SELECT * 
        FROM sajatdolgozo;
BEGIN
    FOR rec IN cursor LOOP
        IF vanbenneT(rec.dnev) THEN
            UPDATE sajatdolgozo
            SET fizetes = rec.fizetes + 10000
            WHERE CURRENT OF cursor;
        ELSE
            UPDATE sajatdolgozo
            SET fizetes = rec.fizetes + 10000 * fiz_kat(rec.fizetes)
            WHERE CURRENT OF cursor;
        END IF;
    END LOOP;
    FOR rectum IN cursorKiiras LOOP
        DBMS_OUTPUT.PUT_LINE(rectum.dnev || ' - ' || rectum.fizetes || ' Ft');
    END LOOP;
    ROLLBACK;
END fiz_novel;
/ 
-- 4. feladat tesztelés
set serveroutput on
execute fiz_novel(20);
select * from nikovits.fiz_kategoria;
select * from sajatdolgozo;
/
-- 5. feladat segéd
CREATE OR REPLACE FUNCTION faktorialis(n NUMBER) RETURN NUMBER
IS
    fakt NUMBER := 1;
BEGIN
    FOR i IN 1..n LOOP
        fakt := i * fakt;
    END LOOP;
    RETURN fakt;
END faktorialis;
/
-- 5. feladat segéd tesztelés
set serveroutput on
BEGIN
    DBMS_OUTPUT.PUT_LINE(faktorialis(4));
END;
/
-- 5. feladat
CREATE OR REPLACE PROCEDURE szamok(n number) IS
    reciprok NUMBER;
    negyzetgyok NUMBER;
BEGIN
        IF n = 0 THEN 
            reciprok := 0;
            DBMS_OUTPUT.PUT_LINE(reciprok);
        ELSE
            reciprok := 1 / n;
            DBMS_OUTPUT.PUT_LINE(reciprok);
        END IF;
        IF n < 0 THEN
            negyzetgyok := -1;
            DBMS_OUTPUT.PUT_LINE('nincs négyzetgyöke');
        ELSE 
            negyzetgyok := sqrt(n);
            DBMS_OUTPUT.PUT_LINE(negyzetgyok);
        END IF;
        IF n < 0 THEN
            DBMS_OUTPUT.PUT_LINE('nincs faktoriálisa');
        ELSE
            DBMS_OUTPUT.PUT_LINE(faktorialis(n));
        END IF;
END szamok;
/
-- 5. feladat tesztelés
set serveroutput on
execute szamok(0);
execute szamok(-2);
execute szamok(40);
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
    IF tartalmaz('asdghjkdT', 'dT') THEN
        DBMS_OUTPUT.PUT_LINE('VAN :D');
    ELSE
        DBMS_OUTPUT.PUT_LINE('nincs :(');
    END IF;
END;
/
set serveroutput on
BEGIN
    IF felmenok('asdghjkdT', 'dT') THEN
        DBMS_OUTPUT.PUT_LINE('VAN :D');
    ELSE
        DBMS_OUTPUT.PUT_LINE('nincs :(');
    END IF;
END;
/
select * from NIKOVITS.VAGYONOK;
/ 
-- 6. feladat
CREATE OR REPLACE PROCEDURE kar_nincs(p varchar2) IS
BEGIN
END kar_nincs;
/
-- 6. feladat tesztelése
set serveroutput on
execute kar_nincs('B');
execute kar_nincs('N');
/
-- 1.feladat segéd
/
-- 1. feladat segéd tesztelése
/ 
-- 1. feladat
/
-- 1. feladat tesztelése
/

