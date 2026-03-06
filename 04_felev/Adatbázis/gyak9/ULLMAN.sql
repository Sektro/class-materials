create or replace FUNCTION prim(n integer) RETURN number IS
 ret_val NUMBER(1) := 1;
 v_limit NUMBER(38);
BEGIN
 IF n < 2 OR trunc(n) <> n THEN RETURN 0; END IF;
  v_limit := trunc(sqrt(n));
  FOR i IN 2 .. v_limit loop
  IF mod(n, i) = 0 THEN ret_val := 0; exit; END IF;
 END loop;
 RETURN ret_val;    
END;
/
create or replace PROCEDURE fib(db integer) IS
 v1  integer := 0;
 v2  integer := 1;
 v_next integer;
BEGIN
  IF db <= 1 THEN v_next:=v1; ELSIF db=2 THEN v_next:=v2; END IF; 
  FOR i IN 3 .. db LOOP
    v_next := v1+v2;
    v1 := v2; v2 := v_next; 
  END LOOP;
  DBMS_OUTPUT. PUT_LINE(TO_CHAR(v_next));
END;
/
-- segtség kötelezõhöz
CREATE OR REPLACE FUNCTION get_jobs(o_nev varchar2) RETURN varchar2 IS
    jobs_chain VARCHAR2(100);
    first NUMBER(1) := 1;
    CURSOR work_cursor (o_nev VARCHAR2) IS
        with oszt_dolg as (select dnev as nev, foglalkozas, onev as osztaly from nikovits.dolgozo d, nikovits.osztaly o where d.oazon = o.oazon order by nev)
        SELECT foglalkozas
        FROM oszt_dolg
        WHERE osztaly = o_nev;
BEGIN
    FOR foglalkozasok IN work_cursor(o_nev) LOOP
        if first = 0 then jobs_chain := to_char(foglalkozasok.foglalkozas); first := 0; else
        jobs_chain := jobs_chain||'-'||to_char(foglalkozasok.foglalkozas);
        end if;
    END LOOP;
    RETURN jobs_chain;
END;
/
-- kötelezõ feladat
DECLARE
    CURSOR fogl_cursor IS
        SELECT onev
        FROM nikovits.osztaly
        WHERE deptno = 30
        FOR UPDATE NOWAIT;
BEGIN
    FOR emp_record IN sal_cursor LOOP
        UPDATE emp
        SET sal = emp_record.sal * 1.10
        WHERE CURRENT OF sal_cursor;
    END LOOP;
    COMMIT;
END;
/
select prim(10) from dual;

create table gyak8 as select dkod, dnev from nikovits.dolgozo where prim(dkod) = 1;
select * from gyak8;

set serveroutput on;
execute fib(10);
call fib(10);

select * from nikovits.dolgozo;
select * from nikovits.osztaly;
select * from nikovits.emp;
SELECT get_jobs('RESEARCH') FROM dual;
select dnev as nev, foglalkozas, onev as osztaly from nikovits.dolgozo d, nikovits.osztaly o where d.oazon = o.oazon order by nev;
with oszt_dolg as (select dnev as nev, foglalkozas, onev as osztaly from nikovits.dolgozo d, nikovits.osztaly o where d.oazon = o.oazon order by nev)
SELECT foglalkozas FROM oszt_dolg WHERE osztaly = 'RESEARCH';

create table gyak9(osztaly VARCHAR2(100), foglalkozasok VARCHAR2(100));


