/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DAOs.ServicioDAO;
import java.util.List;
import model.Servicio;

/**
 *
 * @author Acer
 */
public class ControlServicio {
    private ServicioDAO servicioDAO;

    public ControlServicio() {
        servicioDAO = new ServicioDAO();
    }
    
     //Crear un servicio
    public String crearServicio(String nombre, String descripcion, float precio, int duracion) {
        if (nombre == null || nombre.trim().isEmpty() ||
            descripcion == null || descripcion.trim().isEmpty() ||
            precio <= 0 || duracion <= 0) {
            return "Todos los campos son obligatorios y deben tener valores válidos.";
        }

        Servicio nuevoServicio = new Servicio(nombre, descripcion, precio, duracion);
        boolean exito = servicioDAO.crearServicio(nuevoServicio);

        return exito ? "Servicio registrado con éxito." : "Error al registrar el servicio.";
    }

    // Método para obtener la lista de todos los servicios
    public List<Servicio> obtenerServicios() {
        return servicioDAO.listarServicios();
    }

    // Método para modificar un servicio
    public String modificarServicio(int id, String nombre, String descripcion, float precio, int duracion) {
        if (id <= 0 || nombre == null || nombre.trim().isEmpty() ||
            descripcion == null || descripcion.trim().isEmpty() ||
            precio <= 0 || duracion <= 0) {
            return "Todos los campos son obligatorios y deben tener valores válidos.";
        }

        Servicio modificarServicio = new Servicio(id, nombre, descripcion, precio, duracion);
        boolean exito = servicioDAO.modificarServicio(modificarServicio);

        return exito ? "Servicio actualizado con éxito." : "Error al actualizar el servicio.";
    }

    // Método para eliminar un servicio
    public String eliminarServicio(int id) {
        if (id <= 0) {
            return "ID de servicio no válido.";
        }

        boolean exito = servicioDAO.eliminarServicio(id);
        return exito ? "Servicio eliminado con éxito." : "Error al eliminar el servicio.";
    }

   
    
}
