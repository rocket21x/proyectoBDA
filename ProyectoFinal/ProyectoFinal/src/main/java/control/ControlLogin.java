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
    public String registrarUsuario(String nombre, String correo, String contrasena, String rol){
    // Validar que ningún campo esté vacío
    if (nombre == null || nombre.trim().isEmpty() ||
        correo == null || correo.trim().isEmpty() ||
        contrasena == null || contrasena.trim().isEmpty() ||
        rol == null || rol.trim().isEmpty()) {
        return "Todos los campos son obligatorios.";
    }

    // Validar que el correo tenga un formato válido
    if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
        return "Formato de correo inválido.";
    }

    // Validar que la contraseña tenga mínimo 6 caracteres
    if (contrasena.length() < 6) {
        return "La contraseña debe tener al menos 6 caracteres.";
    }
    // Crear objeto usuario y llamar al DAO
    
    Usuario nuevoUsuario = new Usuario(nombre, correo, contrasena, rol);
    
    boolean exito = usuarioDAO.registrarUsuario(nuevoUsuario);

    return exito ? "Usuario registrado con éxito." : "Error al registrar el usuario.";
    

    }
}