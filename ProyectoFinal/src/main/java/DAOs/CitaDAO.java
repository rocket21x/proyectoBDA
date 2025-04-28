package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import model.Cita;
import utils.Conexion;

public class CitaDAO {
    private Conexion conexion;

    public CitaDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    // Verificar disponibilidad de una fecha y hora
    public boolean verificarDisponibilidad(Timestamp fecha) {
        String sql = "CALL sp_verificar_disponibilidad(?)"; // Solo necesita fecha con hora
        try (Connection conn = conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, fecha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0; // 0 significa que no hay citas en ese horario
                }
            }
        } catch (SQLException e) {
            System.out.println("Error verificando disponibilidad: " + e.getMessage());
        }
        return false; // En caso de error, asumimos que no está disponible
    }

    // Registrar nueva cita
    public boolean registrarCita(Cita cita) {
        if (!verificarDisponibilidad(cita.getFecha())) {
            System.out.println("No se puede registrar la cita, ya está ocupada.");
            return false;
        }

        String sql = "INSERT INTO citas (id_usuario, id_servicio, fecha, estado, estado_pago) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cita.getIdUsuario());
            ps.setInt(2, cita.getIdServicio());
            ps.setTimestamp(3, cita.getFecha());
            ps.setString(4, cita.getEstado());
            ps.setString(5, cita.getEstadoPago());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar la cita: " + e.getMessage());
            return false;
        }
    }
}
