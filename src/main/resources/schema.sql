drop table if exists extension CASCADE;
create table extension
(
    id        bigint generated by default as identity,
    name varchar(20),
    flag_use     integer,
    flag_fixed integer,
    primary key (id)
);
-- 고정 확장자
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'BAT', 0,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'CMD', 0,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'COM', 0,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'CPL', 0,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'EXE', 0,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'SCR', 0,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'JS', 0,1);

COMMIT;
