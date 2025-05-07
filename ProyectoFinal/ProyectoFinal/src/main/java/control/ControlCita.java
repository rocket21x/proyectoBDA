/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DAOs.CitaDAO;
import model.Cita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class ControlCita {

    private CitaDAO citaDAO;

    public ControlCita() {
        this.citaDAO = new CitaDAO();
    }

    public boolean registrarCita(int idCliente, int idServicio, int idBarbero, Date fecha) {
        Cita cita = new Cita();
        cita.setIdCliente(idCliente);
        cita.setIdServicio(idServicio);
        cita.setIdBarbero(idBarbero);
        cita.setFecha(fecha);
        

        return citaDAO.registrarCita(cita); // Devuelve true si la inserción fue exitosa
    }
}

