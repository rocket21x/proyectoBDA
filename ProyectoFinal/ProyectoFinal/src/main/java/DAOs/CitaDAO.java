/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Cita;
import utils.Conexion;

/**
 *
 * @author thecr
 */
public class CitaDAO {
    // Insertar una nueva cita
    public boolean registrarCita(Cita cita) {
        String sql = "INSERT INTO citas (id_usuario, id_barbero, id_servicio, fecha) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cita.getIdCliente());
            stmt.setInt(2, cita.getIdBarbero());
            stmt.setInt(3, cita.getIdServicio());
            stmt.setTimestamp(4, new Timestamp(cita.getFecha().getTime()));

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Cita> listarCitas() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM citas";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
               ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cita cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setIdCliente(rs.getInt("id_usuario"));
                cita.setIdBarbero(rs.getInt("id_barbero"));
                cita.setIdServicio(rs.getInt("id_servicio"));
                cita.setFecha(rs.getTimestamp("fecha"));

                lista.add(cita);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
}
