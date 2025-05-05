/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Servicio;
import utils.Conexion;

/**
 * Clase con los metodos para llamar a los Store Procedures del CRUD de servicio
 * @author Acer
 */
public class ServicioDAO {

    private Conexion conexion;

    public ServicioDAO() {
        conexion = new Conexion();
    }

    //CREAR SERVICIO usando Store Procedure
    public boolean crearServicio(Servicio servicio) {
        String sql = "CALL SP_CrearServicio(?, ?, ?, ?)";

        try (Connection conn = new Conexion().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, servicio.nombre);
            ps.setString(2, servicio.descripcion);
            ps.setFloat(3, servicio.precio);
            ps.setInt(4, servicio.duracion);

            return ps.executeUpdate() > 0; // Retorna true si se insertó correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // MODIFICAR SERVICIO usando Stored Procedure
    public boolean modificarServicio(Servicio servicio) {
        String sql = "CALL SP_ModificarServicio(?, ?, ?, ?, ?)";
        
        try (Connection conn = new Conexion().getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, servicio.getId());
            ps.setString(2, servicio.nombre);
            ps.setString(3, servicio.descripcion);
            ps.setFloat(4, servicio.precio);
            ps.setInt(5, servicio.duracion);

            return ps.executeUpdate() > 0; // Retorna true si se actualizó correctamente
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //OBTENER LISTA DE SERVICIOS usando Store Procedure
    public List<Servicio> listarServicios() {
        List<Servicio> listaServicios = new ArrayList<>();
        String sql = "CALL SP_MostrarServicios()"; 

        try (Connection conn = conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setId(rs.getInt("id"));
                servicio.setNombre(rs.getString("nombre"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setPrecio(rs.getFloat("precio"));
                servicio.setDuracion(rs.getInt("duracion"));

                listaServicios.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaServicios;
    }
    //ELIMINAR UN SERVICIO Utilizando Store Procedure
    public boolean eliminarServicio(int idServicio) {
        String sql = "CALL SP_EliminarServicio(?)"; 
        try (Connection conn = conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
           
            ps.setInt(1, idServicio);
            
            return ps.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean agendarCita(int idServicio, int idBarbero, Timestamp fechaHora) {
    boolean exito = false;

    String sql = "{CALL AgendarCita(?, ?, ?)}";

    try (Connection con = Conexion.getConexion();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, idServicio);
        cs.setInt(2, idBarbero);
        cs.setTimestamp(3, fechaHora);

        cs.execute();
        exito = true;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return exito;
}

}
