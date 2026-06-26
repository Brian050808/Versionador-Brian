-- SCRIPT COMPLETO: ControlEscolar con SP y Triggers (sufijo _uriel)
CREATE DATABASE IF NOT EXISTS ControlEscolar;
USE ControlEscolar;

-- TABLA BITACORA
CREATE TABLE IF NOT EXISTS Bitacora_Operaciones (
    ID_Bitacora INT AUTO_INCREMENT PRIMARY KEY,
    Usuario_DB VARCHAR(100),
    Fecha_Hora DATETIME,
    Operacion VARCHAR(10),
    Tabla_Afectada VARCHAR(50),
    Detalle VARCHAR(255)
);

-- TABLA ESTUDIANTE
CREATE TABLE IF NOT EXISTS Estudiante (
    ID_Estudiante INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellido_Paterno VARCHAR(100) NOT NULL,
    Apellido_Materno VARCHAR(100),
    Matricula VARCHAR(20) NOT NULL
);

-- TABLA DOCENTE
CREATE TABLE IF NOT EXISTS Docente (
    ID_Docente INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellido_Paterno VARCHAR(100) NOT NULL,
    Apellido_Materno VARCHAR(100),
    RFC VARCHAR(13) NOT NULL
);

-- TABLA MATERIA
CREATE TABLE IF NOT EXISTS Materia (
    ID_Materia INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Materia VARCHAR(150) NOT NULL,
    Creditos INT NOT NULL
);

-- TABLA REGISTRA (asignaciones estudiante-materia-docente)
CREATE TABLE IF NOT EXISTS Registra (
    ID_Registra INT AUTO_INCREMENT PRIMARY KEY,
    ID_Estudiante INT NOT NULL,
    ID_Docente INT NOT NULL,
    ID_Materia INT NOT NULL,
    Fecha_Inscripcion DATETIME,
    FOREIGN KEY (ID_Estudiante) REFERENCES Estudiante(ID_Estudiante),
    FOREIGN KEY (ID_Docente) REFERENCES Docente(ID_Docente),
    FOREIGN KEY (ID_Materia) REFERENCES Materia(ID_Materia)
);

-- ------------------------------------------------------------------
-- USERS (ajusta contraseña antes de ejecutar en producción)
-- ------------------------------------------------------------------
-- Usuario admin propio para la práctica
CREATE USER IF NOT EXISTS 'admin'@'%' IDENTIFIED BY '123';
GRANT ALL PRIVILEGES ON ControlEscolar.* TO 'admin'@'%';
FLUSH PRIVILEGES;

-- Usuario restringido (SELECT, INSERT, UPDATE)
CREATE USER IF NOT EXISTS 'user'@'%' IDENTIFIED BY '123';
GRANT SELECT, INSERT, UPDATE ON ControlEscolar.* TO 'user'@'%';
FLUSH PRIVILEGES;

-- ------------------------------------------------------------------
-- PROCEDIMIENTOS ALMACENADOS (todos con sufijo _uriel)
-- ------------------------------------------------------------------
DELIMITER $$

-- ESTUDIANTE: INSERT
CREATE PROCEDURE sp_ins_estudiante_brian(
    IN pNombre VARCHAR(100),
    IN pAPaterno VARCHAR(100),
    IN pAMaterno VARCHAR(100),
    IN pMatricula VARCHAR(20)
)
BEGIN
    INSERT INTO Estudiante (Nombre, Apellido_Paterno, Apellido_Materno, Matricula)
    VALUES (pNombre, pAPaterno, pAMaterno, pMatricula);
END$$

-- ESTUDIANTE: UPDATE
CREATE PROCEDURE sp_upd_estudiante_brian(
    IN pID INT,
    IN pNombre VARCHAR(100),
    IN pAPaterno VARCHAR(100),
    IN pAMaterno VARCHAR(100),
    IN pMatricula VARCHAR(20)
)
BEGIN
    UPDATE Estudiante
    SET Nombre = pNombre,
        Apellido_Paterno = pAPaterno,
        Apellido_Materno = pAMaterno,
        Matricula = pMatricula
    WHERE ID_Estudiante = pID;
END$$

-- ESTUDIANTE: DELETE
CREATE PROCEDURE sp_del_estudiante_brian(IN pID INT)
BEGIN
    DELETE FROM Estudiante WHERE ID_Estudiante = pID;
END$$

-- ESTUDIANTE: SELECT ALL
CREATE PROCEDURE sp_sel_estudiante_brian()
BEGIN
    SELECT * FROM Estudiante;
END$$

-- MATERIA: INSERT / UPDATE / DELETE / SELECT
CREATE PROCEDURE sp_ins_materia_brian(IN pNombre VARCHAR(150), IN pCreditos INT)
BEGIN
    INSERT INTO Materia (Nombre_Materia, Creditos) VALUES (pNombre, pCreditos);
END$$

CREATE PROCEDURE sp_upd_materia_brian(IN pID INT, IN pNombre VARCHAR(150), IN pCreditos INT)
BEGIN
    UPDATE Materia SET Nombre_Materia = pNombre, Creditos = pCreditos WHERE ID_Materia = pID;
END$$

CREATE PROCEDURE sp_del_materia_brian(IN pID INT)
BEGIN
    DELETE FROM Materia WHERE ID_Materia = pID;
END$$

CREATE PROCEDURE sp_sel_materia_brian()
BEGIN
    SELECT * FROM Materia;
END$$

-- DOCENTE: INSERT / UPDATE / DELETE / SELECT
CREATE PROCEDURE sp_ins_docente_brian(IN pNombre VARCHAR(100), IN pAPaterno VARCHAR(100), IN pAMaterno VARCHAR(100), IN pRFC VARCHAR(13))
BEGIN
    INSERT INTO Docente (Nombre, Apellido_Paterno, Apellido_Materno, RFC) VALUES (pNombre, pAPaterno, pAMaterno, pRFC);
END$$

CREATE PROCEDURE sp_upd_docente_brian(IN pID INT, IN pNombre VARCHAR(100), IN pAPaterno VARCHAR(100), IN pAMaterno VARCHAR(100), IN pRFC VARCHAR(13))
BEGIN
    UPDATE Docente SET Nombre = pNombre, Apellido_Paterno = pAPaterno, Apellido_Materno = pAMaterno, RFC = pRFC WHERE ID_Docente = pID;
END$$

CREATE PROCEDURE sp_del_docente_brian(IN pID INT)
BEGIN
    DELETE FROM Docente WHERE ID_Docente = pID;
END$$

CREATE PROCEDURE sp_sel_docente_brian()
BEGIN
    SELECT * FROM Docente;
END$$

-- REGISTRA (asignación): INSERT / UPDATE / SELECT
CREATE PROCEDURE sp_ins_registro_brian(IN pID_Estudiante INT, IN pID_Docente INT, IN pID_Materia INT, IN pFecha DATETIME)
BEGIN
    INSERT INTO Registra (ID_Estudiante, ID_Docente, ID_Materia, Fecha_Inscripcion) VALUES (pID_Estudiante, pID_Docente, pID_Materia, pFecha);
END$$

CREATE PROCEDURE sp_upd_registro_brian(IN pID INT, IN pID_Estudiante INT, IN pID_Docente INT, IN pID_Materia INT, IN pFecha DATETIME)
BEGIN
    UPDATE Registra SET ID_Estudiante = pID_Estudiante, ID_Docente = pID_Docente, ID_Materia = pID_Materia, Fecha_Inscripcion = pFecha WHERE ID_Registra = pID;
END$$

CREATE PROCEDURE sp_sel_registro_brian()
BEGIN
    SELECT r.ID_Registra, r.Fecha_Inscripcion, e.Nombre AS Estudiante, CONCAT(d.Nombre, ' ', d.Apellido_Paterno) AS Docente, m.Nombre_Materia
    FROM Registra r
    JOIN Estudiante e ON e.ID_Estudiante = r.ID_Estudiante
    JOIN Docente d ON d.ID_Docente = r.ID_Docente
    JOIN Materia m ON m.ID_Materia = r.ID_Materia;
END$$

DELIMITER ;

-- ------------------------------------------------------------------
-- TRIGGERS: Poblan la Bitacora_Operaciones (nombres con estilo solicitado)
-- Nota: normalmente sp_ prefijo es para procedimientos; el pedido fue de estas formas.
-- Crear triggers para Estudiante, Materia, Docente y Registra
-- ------------------------------------------------------------------

DELIMITER $$

-- ESTUDIANTE: INSERT
CREATE TRIGGER sp_ins_estudiante_brian
AFTER INSERT ON Estudiante
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'INSERT', 'Estudiante', CONCAT('ID=', NEW.ID_Estudiante, ' Matricula=', NEW.Matricula));
END$$

CREATE TRIGGER sp_upd_estudiante_brian
AFTER UPDATE ON Estudiante
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'UPDATE', 'Estudiante', CONCAT('ID=', NEW.ID_Estudiante, ' Matricula=', NEW.Matricula));
END$$

CREATE TRIGGER sp_del_estudiante_brian
AFTER DELETE ON Estudiante
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'DELETE', 'Estudiante', CONCAT('ID=', OLD.ID_Estudiante));
END$$

-- MATERIA triggers
CREATE TRIGGER sp_ins_materia_brian AFTER INSERT ON Materia FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'INSERT', 'Materia', CONCAT('ID=', NEW.ID_Materia));
END$$

CREATE TRIGGER sp_upd_materia_brian AFTER UPDATE ON Materia FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'UPDATE', 'Materia', CONCAT('ID=', NEW.ID_Materia));
END$$

CREATE TRIGGER sp_del_materia_brian AFTER DELETE ON Materia FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'DELETE', 'Materia', CONCAT('ID=', OLD.ID_Materia));
END$$

-- DOCENTE triggers
CREATE TRIGGER sp_ins_docente_brian AFTER INSERT ON Docente FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'INSERT', 'Docente', CONCAT('ID=', NEW.ID_Docente));
END$$

CREATE TRIGGER sp_upd_docente_brian AFTER UPDATE ON Docente FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'UPDATE', 'Docente', CONCAT('ID=', NEW.ID_Docente));
END$$

CREATE TRIGGER sp_del_docente_brian AFTER DELETE ON Docente FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'DELETE', 'Docente', CONCAT('ID=', OLD.ID_Docente));
END$$

-- REGISTRA triggers
CREATE TRIGGER sp_ins_registra_brian AFTER INSERT ON Registra FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'INSERT', 'Registra', CONCAT('ID=', NEW.ID_Registra));
END$$

CREATE TRIGGER sp_upd_registra_brian AFTER UPDATE ON Registra FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'UPDATE', 'Registra', CONCAT('ID=', NEW.ID_Registra));
END$$

CREATE TRIGGER sp_del_registra_brian AFTER DELETE ON Registra FOR EACH ROW
BEGIN
    INSERT INTO Bitacora_Operaciones (Usuario_DB, Fecha_Hora, Operacion, Tabla_Afectada, Detalle)
    VALUES (USER(), NOW(), 'DELETE', 'Registra', CONCAT('ID=', OLD.ID_Registra));
END$$

CREATE PROCEDURE sp_del_registro_brian(IN pID INT)
BEGIN
    DELETE FROM Registra WHERE ID_Registra = pID;
END$$

DELIMITER ;


GRANT EXECUTE ON PROCEDURE ControlEscolar.sp_sel_estudiante_brian TO 'user'@'%';
GRANT EXECUTE ON PROCEDURE ControlEscolar.sp_ins_estudiante_brian TO 'user'@'%';
GRANT EXECUTE ON PROCEDURE ControlEscolar.sp_upd_estudiante_brian TO 'user'@'%';
GRANT EXECUTE ON PROCEDURE ControlEscolar.sp_del_estudiante_brian TO 'user'@'%';

FLUSH PRIVILEGES;
