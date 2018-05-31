DROP TABLE GRUPO_ESTUDIANTE;
DROP TABLE GRUPO;
DROP TABLE USERS;
DROP TABLE ESTUDANTE_TUTOR;
DROP TABLE EMPRESA_ESTUDIANTE;
DROP TABLE TUTOR;
DROP TABLE EMPRESA;
DROP TABLE ESTUDIANTE;
DROP TABLE CICLO_CENTRO;
DROP TABLE CICLO;
DROP TABLE CENTRO;

CREATE TABLE CENTRO (
  COD_CENTRO       INT(10) PRIMARY KEY,
  DEN_CENTRO       VARCHAR(30) NOT NULL,
  DIRECCION_CENTRO VARCHAR(50),
  NIF_CENTRO       VARCHAR(9)  NOT NULL,
  NOMB_REPR        VARCHAR(50),
  DNI_REPR         VARCHAR(9),
  DIRECTOR_CENTRO  VARCHAR(50),
  LOC_CENTRO       VARCHAR(50),
  MUN_CENTRO       VARCHAR(50),
  PROV_CENTRO      VARCHAR(30),
  CP_CENTRO        INT(5),
  DAT_CENTRO       VARCHAR(50)
);

/*
Carga de grupos:
SELECT COD_GRUPO, NOM_GRUPO, NOM_CICLO, NOMBRE FROM GRUPO, CICLO, USERS WHERE CICLO.CLAVE_CICLO = GRUPO.CLAVE_CICLO AND GRUPO.USR = USERS.USR;

 */
CREATE TABLE CICLO (
  CLAVE_CICLO INT(10) PRIMARY KEY,
  NOM_CICLO   VARCHAR(100) NOT NULL,
  CLAVE_FAM   INT(10)      NOT NULL,
  NOM_FAM     VARCHAR(50)  NOT NULL
);

CREATE TABLE CICLO_CENTRO (
  COD_CENTRO  INT(10),
  CLAVE_CICLO INT(10),
  CONSTRAINT cicloc_fk FOREIGN KEY (CLAVE_CICLO) REFERENCES CICLO (CLAVE_CICLO),
  CONSTRAINT centro_fk FOREIGN KEY (COD_CENTRO) REFERENCES CENTRO (COD_CENTRO),
  PRIMARY KEY (COD_CENTRO, CLAVE_CICLO)
);

/*
Vista Tutor (Tabla asignados y no asignados):
SELECT NOM, APELL1, APELL2 FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE COD_GRUPO = ? AND ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND ESTUDIANTE.NUM_MAT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);
Vista Prácticas:
SELECT ESTUDIANTE.NOM, NOM_EMPR, TUT_EMPR, FECHA_INICIO, FECH_FIN, HORARIO, LOCALIZACION, ERASMUS, ESTADO FROM EMPRESA_ESTUDIANTE, ESTUDIANTE, EMPRESA WHERE ESTUDIANTE.NUM_MAT = EMPRESA_ESTUDIANTE.NUM_MAT AND EMPRESA.NUM_CONV = EMPRESA_ESTUDIANTE.NUM_CONV;
Vista alumnos de cada grupo:
SELECT ESTUDIANTE.NUM_MAT, NOM, CONCAT(APELL1, CONCAT(' ', APELL2)), DNI FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND  COD_GRUPO = ?;
Cargar alumnos desde root:
SELECT NUM_MAT, NOM, CONCAT(APELL1, CONCAT(' ', APELL2)), DNI FROM ESTUDIANTE;
Vista asignar prácticas:
SELECT CONCAT(NOM, CONCAT(' ', CONCAT(APELL1, CONCAT(' ', APELL2)))), ESTUDIANTE.NUM_MAT FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE COD_GRUPO = ? AND ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND ESTUDIANTE.NUM_MAT NOT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);
Vista asignar prácticas director:
SELECT CONCAT(NOM, CONCAT(' ', CONCAT(APELL1, CONCAT(' ', APELL2)))), ESTUDIANTE.NUM_MAT FROM ESTUDIANTE WHERE ESTUDIANTE.NUM_MAT NOT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);
*/
CREATE TABLE ESTUDIANTE (
  NUM_MAT INT(10) PRIMARY KEY,
  NOM     VARCHAR(30) NOT NULL,
  APELL1  VARCHAR(30) NOT NULL,
  APELL2  VARCHAR(30),
  DNI     VARCHAR(9)  NOT NULL UNIQUE
);

/*SELECT ESTUDIANTE.NOM, NOM_EMPR, TUT_EMPR, FECHA_INICIO, FECH_FIN, HORARIO, LOCALIZACION, ERASMUS, ESTADO FROM EMPRESA_ESTUDIANTE, ESTUDIANTE, EMPRESA;*/
CREATE TABLE EMPRESA (
  NUM_CONV    INT(10) PRIMARY KEY,
  NOM_EMPR    VARCHAR(30)  NOT NULL,
  F_FIRMA     DATE,
  LOCALIDAD   VARCHAR(100) NOT NULL,
  DIRECCION   VARCHAR(500) NOT NULL,
  REPR_EMPR   VARCHAR(50),
  CORREO_EMPR VARCHAR(50)
);

CREATE TABLE TUTOR (
  NIF_TUTOR VARCHAR(9) PRIMARY KEY,
  NOM       VARCHAR(30) NOT NULL,
  APELL1    VARCHAR(30) NOT NULL,
  APELL2    VARCHAR(30)
);

/*
Vista Prácticas:
SELECT ESTUDIANTE.NOM, NOM_EMPR, TUT_EMPR, FECHA_INICIO, FECH_FIN, HORARIO, LOCALIZACION, ERASMUS, ESTADO FROM EMPRESA_ESTUDIANTE, ESTUDIANTE, EMPRESA WHERE ESTUDIANTE.NUM_MAT = EMPRESA_ESTUDIANTE.NUM_MAT AND EMPRESA.NUM_CONV = EMPRESA_ESTUDIANTE.NUM_CONV;
Vista Tutor (Tabla asignados y no asignados)
SELECT NOM, APELL1, APELL2 FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE COD_GRUPO = ? AND ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND ESTUDIANTE.NUM_MAT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);
Asignación de prácticas:
INSERT INTO EMPRESA_ESTUDIANTE (NUM_MAT, NUM_CONV, TUT_EMPR, FECHA_INICIO, FECH_FIN, HORARIO, LOCALIZACION, ERASMUS, ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
Eliminación:
DELETE FROM EMPRESA_ESTUDIANTE WHERE NUM_MAT = ? AND NUM_CONV = ?;
Modificación:
UPDATE EMPRESA_ESTUDIANTE SET FECHA_INICIO = ?, FECH_FIN = ?, TUT_EMPR = ?, HORARIO = ?, LOCALIZACION = ?, ERASMUS = ?, ESTADO = ? WHERE NUM_MAT = ? AND NUM_CONV = ?;

*/
CREATE TABLE EMPRESA_ESTUDIANTE (
  NUM_MAT      INT(10) NOT NULL,
  NUM_CONV     INT(10) NOT NULL,
  TUT_EMPR     VARCHAR(50),
  FECHA_INICIO DATE,
  FECH_FIN     DATE,
  HORARIO      VARCHAR(50),
  LOCALIZACION VARCHAR(100),
  ERASMUS      BOOLEAN,
  ESTADO       VARCHAR(200),
  CONSTRAINT mat_fk FOREIGN KEY (NUM_MAT) REFERENCES ESTUDIANTE (NUM_MAT),
  CONSTRAINT conv_fk FOREIGN KEY (NUM_CONV) REFERENCES EMPRESA (NUM_CONV),
  PRIMARY KEY (NUM_MAT, NUM_CONV)
);

CREATE TABLE ESTUDANTE_TUTOR (
  NUM_MAT   INT(10)    NOT NULL,
  NIF_TUTOR VARCHAR(9) NOT NULL,
  CONSTRAINT est_fk FOREIGN KEY (NUM_MAT) REFERENCES ESTUDIANTE (NUM_MAT),
  CONSTRAINT tutor_fk FOREIGN KEY (NIF_TUTOR) REFERENCES TUTOR (NIF_TUTOR),
  PRIMARY KEY (NUM_MAT, NIF_TUTOR)
);

/*
Inicio de sesión:
SELECT PWD, ROLE, NOMBRE FROM USERS WHERE USR = ?;
Insertar usuario:
INSERT INTO USERS (USR, PWD, ROLE, MAIL) VALUES (?, ?, ?, ?);
Recuperar mail del usuario:
SELECT MAIL FROM USERS WHERE USR = ?;
Cambiar contraseña:
UPDATE USERS SET PWD = ? WHERE USR = ?;
Carga de tutores:
SELECT NOMBRE, USR, MAIL,NIF FROM USERS WHERE ROLE = 0;"
Carga de grupos:
SELECT COD_GRUPO, NOM_GRUPO, NOM_CICLO, NOMBRE FROM GRUPO, CICLO, USERS WHERE CICLO.CLAVE_CICLO = GRUPO.CLAVE_CICLO AND GRUPO.USR = USERS.USR;
Cargar administrativos:
SELECT NOMBRE, USR, MAIL,NIF FROM USERS WHERE ROLE = 1;
Registro de tutor:
INSERT INTO USERS (NOMBRE, USR, PWD, ROLE, MAIL, NIF) VALUES (?, ?, ?, ?, ?, ?);
 */
CREATE TABLE USERS (
  NOMBRE VARCHAR(50),
  USR    VARCHAR(50) PRIMARY KEY,
  PWD    VARCHAR(260) NOT NULL,
  ROLE   TINYINT(1)   NOT NULL,
  MAIL   VARCHAR(50)  NOT NULL,
  NIF    VARCHAR(9) UNIQUE
);

/*
Conseguir códigos de grupos:
SELECT COD_GRUPO FROM GRUPO;
Dashboard Director:
SELECT NOM_GRUPO, NOMBRE FROM GRUPO,USERS WHERE GRUPO.USR=USERS.USR AND ROLE=0 AND COD_GRUPO=?;
SELECT COUNT(*) FROM (SELECT ESTUDIANTE.NUM_MAT FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE ESTUDIANTE.NUM_MAT NOT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE) AND COD_GRUPO=? AND ESTUDIANTE.NUM_MAT=GRUPO_ESTUDIANTE.NUM_MAT) AS CONSULTA;
Obtener grupos de cada tutor:
SELECT NOM_GRUPO, COD_GRUPO FROM GRUPO WHERE USR = ?;
Carga de grupos:
SELECT COD_GRUPO, NOM_GRUPO, NOM_CICLO, NOMBRE FROM GRUPO, CICLO, USERS WHERE CICLO.CLAVE_CICLO = GRUPO.CLAVE_CICLO AND GRUPO.USR = USERS.USR;

 */
CREATE TABLE GRUPO (
  NOM_GRUPO   VARCHAR(50) NOT NULL,
  COD_GRUPO   INT(10) PRIMARY KEY,
  CLAVE_CICLO INT(10)     NOT NULL,
  USR         VARCHAR(50),
  CONSTRAINT ciclo_fk FOREIGN KEY (CLAVE_CICLO) REFERENCES CICLO (CLAVE_CICLO),
  CONSTRAINT tutor_grupo_fk FOREIGN KEY (USR) REFERENCES USERS (USR)
);
/*
Vista Tutor (Tabla asignados y no asignados):
SELECT NOM, APELL1, APELL2 FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE COD_GRUPO = ? AND ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND ESTUDIANTE.NUM_MAT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);
Vista alumnos de cada grupo:
SELECT ESTUDIANTE.NUM_MAT, NOM, CONCAT(APELL1, CONCAT(' ', APELL2)), DNI FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND  COD_GRUPO = ?;
Vista asignar prácticas tutor:
SELECT CONCAT(NOM, CONCAT(' ', CONCAT(APELL1, CONCAT(' ', APELL2)))), ESTUDIANTE.NUM_MAT FROM ESTUDIANTE, GRUPO_ESTUDIANTE WHERE COD_GRUPO = ? AND ESTUDIANTE.NUM_MAT = GRUPO_ESTUDIANTE.NUM_MAT AND ESTUDIANTE.NUM_MAT NOT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);
Vista asignar prácticas director:
SELECT CONCAT(NOM, CONCAT(' ', CONCAT(APELL1, CONCAT(' ', APELL2)))), ESTUDIANTE.NUM_MAT FROM ESTUDIANTE WHERE ESTUDIANTE.NUM_MAT NOT IN (SELECT NUM_MAT FROM EMPRESA_ESTUDIANTE);
 */
CREATE TABLE GRUPO_ESTUDIANTE (
  COD_GRUPO  INT(10) NOT NULL,
  NUM_MAT    INT(10) NOT NULL,
  ANO_ACADEM INT(4)  NOT NULL,
  CONSTRAINT grupo_fk FOREIGN KEY (COD_GRUPO) REFERENCES GRUPO (COD_GRUPO),
  CONSTRAINT matricula_fk FOREIGN KEY (NUM_MAT) REFERENCES ESTUDIANTE (NUM_MAT),
  PRIMARY KEY (COD_GRUPO, NUM_MAT)
);


INSERT INTO CENTRO VALUES
  (1, 'UEM VILLAVICIOSA', 'C/ TAJO S/N', '89452147B', 'REPRESENTANTE 1', '70081495A', 'DIRECTOR1',
      'VILLAVICIOSA DE ODÓN', 'VILLAVICIOSA DE ODÓN', 'MADRID', 28670, 'DAT SUR');

INSERT INTO CICLO VALUES (84, 'DESARROLLO DE APLICACIONES MULTIPLATAFORMA', 486644, 'INFORMÁTICA');

INSERT INTO CICLO_CENTRO VALUES (1, 84);

INSERT INTO ESTUDIANTE VALUES (28756432, 'Adrián', 'García', 'García', '84265175I');
INSERT INTO ESTUDIANTE VALUES (28756434, 'Julen', 'Bujanda', 'Blanco', '84265275I');
INSERT INTO ESTUDIANTE VALUES (28756436, 'Iván', 'Hernández', '', '84267175I');
INSERT INTO ESTUDIANTE VALUES (28756438, 'Víctor', 'Jiménez', '', '88265175I');
INSERT INTO ESTUDIANTE VALUES (28756440, 'Andrés', 'Murillas', '', '84265155I');
INSERT INTO ESTUDIANTE VALUES (28756442, 'Juan', 'García', '', '84265155U');
INSERT INTO ESTUDIANTE VALUES (28756444, 'Michael', 'Brown', '', '88265175E');

INSERT INTO EMPRESA VALUES
  (4656, 'Accenture', STR_TO_DATE('12-12-2017', '%d-%m-%Y'), 'Alcobendas', 'Calle Principal 1',
   'Theresa May', 'ASDADSASD@GMAIL.COM');
INSERT INTO EMPRESA VALUES
  (4657, 'Apple España S.A.', STR_TO_DATE('12-12-2015', '%d-%m-%Y'), 'Madrid', 'Paseo de la Castellana 92',
   'Alexander Brown', 'abrown@apple.com');
INSERT INTO EMPRESA VALUES
  (4658, 'Google Inc.', STR_TO_DATE('12-12-2017', '%d-%m-%Y'), 'Mountain view', 'Main Street 80',
   'John Smith', 'jsmith@google.com');

INSERT INTO TUTOR VALUES ('45612378A', 'Pedro', 'Camacho', 'Ortega');

INSERT INTO EMPRESA_ESTUDIANTE VALUES
  (28756432, 4656, 'Jane Doe', STR_TO_DATE('01-06-2018', '%d-%m-%Y'), STR_TO_DATE('01-09-2018', '%d-%m-%Y'),
   '8AM-5PM', 'Brighton', TRUE, 'Completado');

INSERT INTO EMPRESA_ESTUDIANTE VALUES
  (28756436, 4657, 'Jane Doe', STR_TO_DATE('15-07-2018', '%d-%m-%Y'), STR_TO_DATE('20-10-2018', '%d-%m-%Y'),
   '8AM-5PM', 'Madrid', FALSE, 'Completado');

INSERT INTO ESTUDANTE_TUTOR VALUES (28756432, '45612378A');

/*Usuarios de prueba, la contraseña es "contraseña", se inserta el hash que desde la aplicación se inserta al generar usuarios.*/
INSERT INTO USERS VALUES
  ('Pedro Camacho', 'tutor', 'edf9cf90718610ee7de53c0dcc250739239044de9ba115bb0ca6026c3e4958a5', 0,
   'julenwhite@icloud.com', '00000000A');
INSERT INTO USERS VALUES
  ('Raúl Rodríguez', 'raul', 'edf9cf90718610ee7de53c0dcc250739239044de9ba115bb0ca6026c3e4958a5', 0,
   'julenwhite@icloud.com', '00000000B');
INSERT INTO USERS VALUES
  ('Ana Manzanero', 'director', 'edf9cf90718610ee7de53c0dcc250739239044de9ba115bb0ca6026c3e4958a5', 1,
   'julenwhite@icloud.com', '00000000C');
INSERT INTO USERS VALUES ('Superusuario', 'root', 'edf9cf90718610ee7de53c0dcc250739239044de9ba115bb0ca6026c3e4958a5', 2,
                          'julenwhite@icloud.com', NULL);

INSERT INTO GRUPO VALUES ('DAM2-1718', 10, 84, 'tutor');
INSERT INTO GRUPO VALUES ('ASIR2-1718', 11, 84, 'tutor');
INSERT INTO GRUPO VALUES ('DAM2-1617', 20, 84, 'raul');

INSERT INTO GRUPO_ESTUDIANTE VALUES (10, 28756432, 2018);
INSERT INTO GRUPO_ESTUDIANTE VALUES (10, 28756434, 2018);
INSERT INTO GRUPO_ESTUDIANTE VALUES (10, 28756436, 2018);
INSERT INTO GRUPO_ESTUDIANTE VALUES (20, 28756438, 2017);
INSERT INTO GRUPO_ESTUDIANTE VALUES (20, 28756440, 2017);
INSERT INTO GRUPO_ESTUDIANTE VALUES (11, 28756442, 2018);
INSERT INTO GRUPO_ESTUDIANTE VALUES (11, 28756444, 2018);
