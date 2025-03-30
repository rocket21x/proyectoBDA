/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author jesus
 */
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    String url ="jdbc:mysql://localhost:3306";
    String nombreBD = "barberia";
    String usuario = "root";
    String contra = "imperial";
    String driver = "com.mysql.jdbc.Driver";
    
    Connection conexion = null;
    
    // Metodo para establecer la conexion con la BD
    public Connection conexion(){
        // Establecemos el intento de la conexion
        try {
            // Cargar los driver de la base de datos en tiempo real o dinámico
            Class.forName(driver);
            // Establecer la conexion
            conexion =  DriverManager.getConnection(url+"/"+nombreBD, usuario, contra);
            
            //Mostrar un mensaje en dado caso que la conexion sea correcta
            System.out.println("Conexion exitosa");
            
            
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexión fallida: " + e);
        }
         return conexion;   
        
    }
    public void desconectar(){
        try {
            if(conexion!= null && !conexion.isClosed()){
                
                conexion.close();
                System.out.println("se cerro la conexion con la bd");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

