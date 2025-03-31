/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import DAOs.UsuarioDAO;
import model.Usuario;

/**
 *
 * @author thecr
 */
public class ControlLogin {
    private UsuarioDAO usuarioDAO;

    public ControlLogin() {
        usuarioDAO = new UsuarioDAO();
    }

    // Método para validar el login
    public boolean validarLogin(String correo, String contrasena) {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreo(correo);
        
        if (usuario != null && usuario.getContra().equals(contrasena)) {
            return true; // Login exitoso
        }
        return false; // Login fallido
    }
    
    public String obtenerTipo(String correo) {
        return usuarioDAO.obtenerRol(correo);  // Llamamos al método del DAO para obtener el rol
    }
    
}
