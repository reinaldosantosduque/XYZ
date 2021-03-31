--------------------------------------------------------
-- Archivo creado  - mi�rcoles-marzo-31-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table TARJETA
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."TARJETA" 
   (	"NUMERO" VARCHAR2(50 BYTE), 
	"EXPIRA" VARCHAR2(10 BYTE), 
	"CODIGO" NUMBER, 
	"MARCA" VARCHAR2(20 BYTE), 
	"CLIENTE" VARCHAR2(100 BYTE), 
	"DEFECTO" NUMBER, 
	"TITULAR" VARCHAR2(50 BYTE), 
	"ID" NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 1000 INCREMENT BY 1 START WITH 1 NOCACHE  ORDER  CYCLE  NOKEEP  NOSCALE 
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table CLIENTE
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."CLIENTE" 
   (	"NOMBRE" VARCHAR2(50 BYTE), 
	"EMAIL" VARCHAR2(100 BYTE), 
	"NIT" VARCHAR2(20 BYTE), 
	"REGIMEN" NUMBER, 
	"PASSWORD" VARCHAR2(100 BYTE), 
	"TELEFONO" VARCHAR2(20 BYTE), 
	"DIRECCION" VARCHAR2(20 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
