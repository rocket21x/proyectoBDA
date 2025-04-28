/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author thecr
 */
public class Cita {
   private int id;
    private int idUsuario;
    private int idServicio;
    private Timestamp fecha; // Usamos Timestamp para manejar fecha y hora
    private String estado; // "pendiente", "confirmada", "cancelada", "completada"
    private String estadoPago; // "pendiente", "pagado", "cancelado"

    public Cita() {
    }

    public Cita(int idServicio, Timestamp fecha, String estado, String estadoPago) {
        
        this.idServicio = idServicio;
        this.fecha = fecha;
        this.estado = estado;
        this.estadoPago = estadoPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    @Override
    public String toString() {
        return "Cita{" + "id=" + id + ", idUsuario=" + idUsuario + ", idServicio=" + idServicio + ", fecha=" + fecha + ", estado=" + estado + ", estadoPago=" + estadoPago + '}';
    }
    
    
    
    

    
}
