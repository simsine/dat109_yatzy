
DROP SCHEMA IF EXISTS yatzydb CASCADE;
CREATE SCHEMA yatzydb;
SET search_path TO yatzydb;

CREATE TABLE spiller (
    brukernavn VARCHAR(35),
    email VARCHAR(35) NOT NULL,
    fornavn VARCHAR(35),
    etternavn VARCHAR(35),
    hashetpassord VARCHAR(255),
    salt VARCHAR(255),

    PRIMARY KEY (brukernavn)
);

CREATE TABLE poengtabell (
    brukernavn VARCHAR(255) NOT NULL,
    spillNr INTEGER NOT NULL,
    poeng JSONB NOT NULL,

    PRIMARY KEY (spillNr, brukernavn),
    FOREIGN KEY (brukernavn) REFERENCES spiller(brukernavn)
);

-- brukere
INSERT INTO spiller (brukernavn, email, fornavn, etternavn, hashetpassord, salt)
VALUES
('A', 'a@eksempel.no', 'Ale', 'Anders', 'hashetpassord_a', 'salt_a')
('B', 'a@eksempel.no', 'Bleb', 'Benders', 'hashetpassord_b', 'salt_b')
('C', 'a@eksempel.no', 'Cleb', 'Cinders', 'hashetpassord_a', 'salt_c')


-- spill 1

INSERT INTO poengtabell(spillNr, brukernavn, poeng)
VALUES (

  'A', 1 
  '{
    "ENERE": 3,
    "TOERE": 6,
    "TREERE": 9,
    "FIRERE": 12,
    "FEMMERE": 15,
    "SEKSERE": 18,
    "ETT_PAR": 10,
    "TO_PAR": 20,
    "TRE_PAR": 30,
    "FIRE_LIKE": 40,
    "LITEN_STRAIGHT": 50,
    "STOR_STRAIGHT": 60,
    "HUS": 25,
    "YATZY: 20
    
    }'::JSONB
), 
(
    'B', 
    1,
  '{
    "ENERE": 3,
    "TOERE": 6,
    "TREERE": 9,
    "FIRERE": 12,
    "FEMMERE": 15,
    "SEKSERE": 18,
    "ETT_PAR": 10,
    "TO_PAR": 20,
    "TRE_PAR": 30,
    "FIRE_LIKE": 40,
    "LITEN_STRAIGHT": 50,
    "STOR_STRAIGHT": 60,
    "HUS": 25,
    "YATZY: 20
    
    }'::JSONB

),
(

    'C', 
    1,
  '{
    "ENERE": 3,
    "TOERE": 6,
    "TREERE": 9,
    "FIRERE": 12,
    "FEMMERE": 15,
    "SEKSERE": 18,
    "ETT_PAR": 10,
    "TO_PAR": 20,
    "TRE_PAR": 30,
    "FIRE_LIKE": 40,
    "LITEN_STRAIGHT": 50,
    "STOR_STRAIGHT": 60,
    "HUS": 25,
    "YATZY: 20
    
    }'::JSONB
);

SELECT * FROM poengtabell;

