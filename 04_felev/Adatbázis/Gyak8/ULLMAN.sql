select * from nikovits.ab1_jegyek;

-- PL/SQL
set serveroutput on
begin
    dbms_output.put_line('hello');
end;
/
set serveroutput on
DECLARE                 
  v NUMBER := 0;
BEGIN
  DBMS_OUTPUT.PUT_LINE('Ez még lefutott ...');
  v := 1/v;
  DBMS_OUTPUT.PUT_LINE('Ez már nem fog ...');
EXCEPTION
  WHEN ZERO_DIVIDE THEN
    DBMS_OUTPUT.PUT_LINE('Nullával osztás történt');
END;
/
CREATE OR REPLACE FUNCTION func_plus_2 (num number) RETURN number IS
    v NUMBER(6);
BEGIN
    v := num + 2;
    return(v);
END;
/
set serveroutput on
    execute list_my_sessions;
execute kill_session(1472,41768);
select * from nikovits.emp;
/
CREATE OR REPLACE FUNCTION prim(n integer) RETURN number IS
    v NUMBER(38,0);
BEGIN
    FOR i IN 2 .. v-1 LOOP
        IF mod(v,i)=0 THEN RETURN 0; END IF;
    END LOOP;
    return 1;
END;
/
    
SELECT prim(26388279066623) from dual;
    
    
    
    