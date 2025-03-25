
DROP SCHEMA IF EXISTS YatzyDB CASCADE;
CREATE SCHEMA yatzydb;
SET search_path TO yatzydb;

CREATE TABLE poengtabell (
    poengtabell_Id SERIAL,
    enere INTEGER,
    toere INTEGER, 
    treere INTEGER,
    firere INTEGER,
    femmere INTEGER,
    seksere INTEGER,
    ettpar INTEGER,
    topar INTEGER,
    trelike INTEGER,
    firelike INTEGER,
    litenstraight INTEGER,
    storstraight INTEGER,
    hus INTEGER,
    sjanse INTEGER,
    yatzy INTEGER,
    
    PRIMARY KEY (poengtabell_Id), 
    FOREGIN KEY (spiller_Id)
);

CREATE TABLE spiller (
    spiller_Id SERIAL,
    email VARCHAR(35),
    fornavn VARCHAR(35),
    etternavn VARCHAR(35),

    PRIMARY KEY (spiller_Id)
);