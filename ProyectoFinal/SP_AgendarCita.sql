CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_AgendarCita`(
	IN p_id_usuario INT,
    IN p_id_servicio INT,
    IN p_fecha DATETIME
)
BEGIN
-- Verificar que el usuario existe
    IF NOT EXISTS (SELECT 1 FROM usuarios WHERE id = p_id_usuario) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error usuario inexistente';
    END IF;

    -- Verificar que el servicio existe
    IF NOT EXISTS (SELECT 1 FROM servicios WHERE id = p_id_servicio) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error servicio inexistente';
    END IF;

    -- Insertar la nueva cita
    INSERT INTO citas (id_usuario, id_servicio, fecha, estado, estado_pago)
    VALUES (p_id_usuario, p_id_servicio, p_fecha, 'pendiente', 'pendiente');

    SELECT 'La cita se agendo correctamente' AS mensaje;
END