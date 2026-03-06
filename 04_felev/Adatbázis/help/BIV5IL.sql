-- 1. Feladat:

    CREATE TABLE Autok(
        id          NUMBER NOT NULL PRIMARY KEY,
        rendszam    VARCHAR2(10),
        vetel       DATE DEFAULT TO_DATE('2025-01-01', 'YYYY-MM-DD')
    );

    --DROP TABLE Autok;

-- 2. Feladat:

    CREATE OR REPLACE VIEW o_chicago AS
    SELECT d.dnev, kat.kategoria
    FROM dolgozo d, fiz_kategoria kat, osztaly oszt
    WHERE d.fizetes BETWEEN kat.also AND kat.felso
    AND oszt.telephely = 'CHICAGO'
    AND d.oazon = oszt.oazon;

    --SELECT * FROM o_chicago;

-- 3. Feladat:

    CREATE OR REPLACE PROCEDURE tavolsag(x1 NUMBER, y1 NUMBER, x2 NUMBER, y2 NUMBER) IS
        azonos  EXCEPTION;
        d       REAL;
    BEGIN
        IF x1 = x2 AND y1 = y2 THEN
            RAISE azonos;
        END IF;
        d := SQRT((x2 - x1)*(x2 - x1) + (y2 - y1) * (y2 - y1));
        
        dbms_output.put_line('d=' || ROUND(d,4));
    EXCEPTION
        WHEN azonos THEN
            dbms_output.put_line('KÉT PONT AZONOS!');
    END;
    /

    --execute tavolsag(1,2,10,8);
    --execute tavolsag(0,0,0,0);

-- 4. Feladat:

    --SELECT * FROM dolgozo2;
    
    CREATE OR REPLACE PROCEDURE update_fiz_by_napok IS
        workman     VARCHAR(10);
        fizu_most   INTEGER;
        fizu_majd   INTEGER;
        CURSOR curs1 IS
            SELECT
            worker.dkod,
            worker.dnev AS workman,
            worker.fizetes AS fizu_most,
            worker.fizetes + worker.belepes - boss.belepes AS fizu_majd
            FROM dolgozo2 worker, dolgozo2 boss
            WHERE worker.fonoke = boss.dkod;
        rec curs1%rowtype;
    BEGIN
        FOR rec IN curs1 LOOP
            workman := rec.workman;
            fizu_most := rec.fizu_most;
            fizu_majd := rec.fizu_majd;
            dbms_output.put_line(workman || ':' || fizu_most || '->' || fizu_majd);
            
            UPDATE dolgozo2
            SET fizetes = fizu_majd
            WHERE dkod = rec.dkod;
        END LOOP;
        ROLLBACK;
    END;
    /
    
    --CALL update_fiz_by_napok();

-- 5. Feladat:
    --SELECT * FROM dolgozo WHERE oazon IS NULL;

    CREATE OR REPLACE FUNCTION telephely_atlag_elteres(my_dkod dolgozo.dkod%type) RETURN REAL IS
        szoras          REAL;
        oszt_atlag_fiz  REAL;
        dolgozo_fiz     dolgozo.fizetes%type;
        dolgozo_oazon   dolgozo.oazon%type;
        nincs_osztaly   EXCEPTION;
        CURSOR dolgozo_osztaly IS
            SELECT AVG(d.fizetes) AS atlag
            FROM dolgozo d, osztaly oszt
            WHERE d.oazon = oszt.oazon
            AND d.oazon = (
                SELECT oazon
                FROM dolgozo
                WHERE dkod = my_dkod
            );
        rec dolgozo_osztaly%rowtype;
    BEGIN
        SELECT fizetes, oazon
        INTO dolgozo_fiz, dolgozo_oazon
        FROM dolgozo
        WHERE dkod = my_dkod;
        
        IF dolgozo_oazon IS NULL THEN
            RAISE nincs_osztaly;
        END IF;
        
        FOR rec IN dolgozo_osztaly LOOP
            oszt_atlag_fiz := rec.atlag;
        END LOOP;
        
        szoras := ((dolgozo_fiz - oszt_atlag_fiz) / oszt_atlag_fiz) * 100;
        
        RETURN ROUND(szoras,2);
    EXCEPTION
        WHEN no_data_found OR nincs_osztaly THEN
            return -1;
    END;
    /

--SELECT telephely_atlag_elteres(742424877) FROM dual;    ---nincs
--SELECT telephely_atlag_elteres(7877) FROM dual;         ---nincs osztaly
--SELECT telephely_atlag_elteres(7839) FROM dual;
--SELECT telephely_atlag_elteres(7369) FROM dual;