/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

/**
 *
 * @author thecr
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;
import utils.Conexion;

public class UsuarioDAO {
    private Conexion conexion;

    // 🔹 Constructor que recibe la conexión
    public UsuarioDAO() {
        conexion = new Conexion();
    }

    // 🔹 REGISTRAR USUARIO usando Stored Procedure
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "CALL sp_registrar_usuario(?, ?, ?, ?)";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getContra());  // ⚠️ Recuerda cifrar la contraseña antes de guardarla
            ps.setString(4, usuario.getRol());

            return ps.executeUpdate() > 0; // Retorna true si se insertó correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 MODIFICAR USUARIO usando Stored Procedure
    public boolean modificarUsuario(Usuario usuario) {
        String sql = "CALL sp_modificar_usuario(?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContra());
            ps.setString(5, usuario.getRol());

            return ps.executeUpdate() > 0; // Retorna true si se actualizó correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 VALIDAR LOGIN usando Stored Procedure
    public Usuario validarLogin(String correo, String contrasena) {
        String sql = "CALL sp_validar_login(?, ?)";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    rs.getString("rol"),
                    rs.getString("fecha_registro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null; // Si no encuentra usuario, retorna null
    }
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        Usuario usuario = null;
        String sql = "CALL obtener_usuario_por_correo(?)"; // Llamada al stored procedure
        
        try (Connection conn = conexion.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContra(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    public String obtenerRol(String correo) {
        String rol = null;
        String query = "SELECT rol FROM usuarios WHERE correo = ?";

        try (Connection conn = conexion.conexion(); 
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rol = rs.getString("rol");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }
}
