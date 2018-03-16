This is the table used in the example. 
 
CREATE TABLE PROCESSO (
ID_PROCESSO NUMBER(10, 0) NOT NULL,
TIPO_PROCESSO VARCHAR2(7) NOT NULL,
TP_ENTITA NUMBER(5, 0) NOT NULL,
ID_ENTITA NUMBER(10, 0) NOT NULL,
PROGR NUMBER(10, 0) NOT NULL,
DT_RIFERIMENTO_ORIGINALE DATE,
STATO VARCHAR2(7),
SUBSTATO VARCHAR2(7),
FL_ATTIVO VARCHAR2(1),
DT_STATO DATE,
DESCRIZIONE VARCHAR2(100),
ID_PRC_PERIF_1 VARCHAR2(250),
ID_PRC_PERIF_2 VARCHAR2(250),
ID_PRC_PERIF_3 VARCHAR2(250),
CHIAVE_1 VARCHAR2(10)
)

ALTER TABLE PROCESSO ADD CONSTRAINT PROCESSO_PK
PRIMARY KEY (ID_PROCESSO)
