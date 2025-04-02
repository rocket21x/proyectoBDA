package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/proyectobda?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CONTRA = "imperial";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: Driver de MySQL no encontrado.");
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRA);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
