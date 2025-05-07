package model;

import java.util.Date;

public class Cita {
    private int id;
    private int idCliente;
    private int idBarbero;
    private int idServicio;
    private Date fecha;  // Fecha y hora de la cita

    // Constructor vacío
    public Cita() {}

    public Cita(int idCliente, int idBarbero, int idServicio, Date fecha) {
        this.idCliente = idCliente;
        this.idBarbero = idBarbero;
        this.idServicio = idServicio;
        this.fecha = fecha;
    }
    
    

    // Constructor completo
    public Cita(int id, int idCliente, int idBarbero, int idServicio, Date fecha) {
        this.id = id;
        this.idCliente = idCliente;
        this.idBarbero = idBarbero;
        this.idServicio = idServicio;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdBarbero() {
        return idBarbero;
    }

    public void setIdBarbero(int idBarbero) {
        this.idBarbero = idBarbero;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
