/* Constructora */
DROP DATABASE IF EXISTS constructoraH;
CREATE DATABASE constructoraH;
USE constructoraH;

/*obras ejecutadas*/
CREATE TABLE obra (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    direccion VARCHAR(50) NOT NULL,
    entrega DATE
);


/* empleados de la constructora */
CREATE TABLE empleado (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(9) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    sueldo FLOAT,
    idObra INTEGER,
    CONSTRAINT fk_obraEmp FOREIGN KEY (idObra) REFERENCES obra(id)
);



/* maquinaria de la empresa para realizar las obras*/
CREATE TABLE maquinaria (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(7) NOT NULL UNIQUE,
    modelo VARCHAR(20) NOT NULL,
    idEmpleado INTEGER,
    idObra INTEGER,
    CONSTRAINT fk_empleado FOREIGN KEY (idEmpleado) REFERENCES empleado(id),
    CONSTRAINT fk_obraMaq FOREIGN KEY (idObra) REFERENCES obra(id)
);

INSERT INTO obra (nombre, direccion, entrega) VALUES ('URBANIZACIÓN BUENAVISTA','C/MAYOR 3', '2023/03/02');
INSERT INTO obra (nombre, direccion, entrega) VALUES ('RESIDENCIAL MIRAFLORES','C/MENOR 2', '2025/05/02');
INSERT INTO obra (nombre, direccion, entrega) VALUES ('BAR EL PEDREGAL','C/OSA 12', '2024/05/02');
INSERT INTO obra (nombre, direccion, entrega) VALUES ('POLIDEPORTIVO MUNICIPAL','C/FRONTÓN 25', '2024/10/20');


INSERT INTO empleado (dni, nombre, sueldo, idObra) VALUES ('17123456A', 'PEDRO PICAPIEDRA', 3000.0, 2);
INSERT INTO empleado (dni, nombre, sueldo, idObra) VALUES ('19123456B', 'PABLO MARMOL', 2000.0, 2);

INSERT INTO maquinaria (matricula, modelo, idEmpleado, idObra) VALUES ('1234BCD', 'EXCAVADORA 2000', 1,2);
INSERT INTO maquinaria (matricula, modelo, idEmpleado, idObra) VALUES ('4567XYZ', 'MANITOU 3000', 2, 2);