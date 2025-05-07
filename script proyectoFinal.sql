create database proyectobda;
use proyectobda;
-- Tabla de Usuarios (clientes, barberos, administradores)
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100) UNIQUE,
    contrasena VARCHAR(100),
    rol ENUM('cliente', 'barbero', 'administrador') NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

select * from usuarios;
select * from servicios;
INSERT INTO servicios (nombre, descripcion, precio, duracion)
VALUES ('Corte de Cabello', 'Corte de cabello clásico con tijera y máquina', 150.00, 30);



-- Tabla de Servicios
CREATE TABLE IF NOT EXISTS servicios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion TEXT,
    precio DECIMAL(10, 2),
    duracion INT -- Duración en minutos
);

-- Tabla de Citas
CREATE TABLE IF NOT EXISTS citas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    id_barbero INT,
    id_servicio INT,
    fecha DATETIME,
    estado ENUM('pendiente', 'confirmada', 'cancelada', 'completada') DEFAULT 'pendiente',
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_servicio) REFERENCES servicios(id),
    FOREIGN KEY (id_barbero) REFERENCES usuarios(id)
);




-- Tabla de Pagos
CREATE TABLE IF NOT EXISTS pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cita INT,
    id_usuario INT, -- Para saber quién hizo el pago
    monto DECIMAL(10,2),
    metodo_pago ENUM('efectivo', 'tarjeta', 'transferencia') NOT NULL,
    estado ENUM('pendiente', 'completado', 'cancelado') DEFAULT 'pendiente',
    fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cita) REFERENCES citas(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

-- 📌 SP para registrar un usuario
DELIMITER //
CREATE PROCEDURE sp_registrar_usuario(
    IN p_nombre VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_contrasena VARCHAR(100),
    IN p_rol ENUM('cliente', 'barbero', 'administrador')
)
BEGIN
    INSERT INTO usuarios (nombre, correo, contrasena, rol) VALUES (p_nombre, p_correo, p_contrasena, p_rol);
END //
DELIMITER ;

-- 📌 SP para modificar un usuario
DELIMITER //
CREATE PROCEDURE sp_modificar_usuario(
    IN p_id INT,
    IN p_nombre VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_contrasena VARCHAR(100),
    IN p_rol ENUM('cliente', 'barbero', 'administrador')
)
BEGIN
    UPDATE usuarios SET nombre = p_nombre, correo = p_correo, contrasena = p_contrasena, rol = p_rol WHERE id = p_id;
END //
DELIMITER ;

-- 📌 SP para validar login
DELIMITER //
CREATE PROCEDURE sp_validar_login(
    IN p_correo VARCHAR(100),
    IN p_contrasena VARCHAR(100)
)
BEGIN
    SELECT * FROM usuarios WHERE correo = p_correo AND contrasena = p_contrasena;
END //
DELIMITER ;


-- SP para obtener usuarios por correo
DELIMITER $$

CREATE PROCEDURE obtener_usuario_por_correo(IN correoUsuario VARCHAR(100))
BEGIN
    SELECT * FROM usuarios WHERE correo = correoUsuario;
END$$

DELIMITER ;

INSERT INTO usuarios (nombre, correo, contrasena, rol)
VALUES ('Jesus', 'jesus@example.com', '12345', 'cliente');

DELIMITER $$

CREATE PROCEDURE sp_verificar_disponibilidad(IN fecha_hora DATETIME)
BEGIN
    DECLARE count_citas INT;

    -- Contar cuántas citas existen en la misma fecha y hora
    SELECT COUNT(*) INTO count_citas
    FROM citas
    WHERE fecha = fecha_hora;

    -- Devolver 0 si está disponible, 1 si ya está ocupada
    SELECT count_citas;
END $$

DELIMITER ;



