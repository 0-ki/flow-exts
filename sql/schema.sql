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
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'BAT', 1,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'CMD', 1,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'COM', 1,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'CPL', 1,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'EXE', 1,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'SCR', 1,1);
INSERT INTO EXTENSION (NAME, FLAG_USE , FLAG_FIXED ) VALUES( 'JS', 1,1);

COMMIT;