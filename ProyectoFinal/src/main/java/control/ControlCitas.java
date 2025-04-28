/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DAOs.CitaDAO;
import java.sql.Time;
import java.sql.Timestamp;
import model.Cita;


/**
 *
 * @author thecr
 */
public class ControlCitas {
    private CitaDAO citaDAO;

    public ControlCitas(CitaDAO citaDAO) {
        this.citaDAO = citaDAO;
    }
    
//    public boolean agendarCita(int idUsuario,int idServicio, Timestamp fecha, Time hora) {
//        // Crear objeto Cita
//        
//        Cita nuevaCita = new Cita(idUsuario, idServicio, fecha, hora, "pendiente", "pendiente");
//
//
//        // Verificar disponibilidad antes de registrar
//        if (!citaDAO.verificarDisponibi lidad(fecha)) {
//            System.out.println("❌ La fecha y hora seleccionadas ya están ocupadas.");
//            return false;
//        }
//
//        // Intentar registrar la cita
//        if (citaDAO.registrarCita(nuevaCita)) {
//            System.out.println("✅ Cita agendada con éxito.");
//            return true;
//        } else {
//            System.out.println("❌ Error al registrar la cita.");
//            return false;
//        }
//    }
}
