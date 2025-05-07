package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import utils.Conexion;

public class BarberosDAO {

    public List<Usuario> listarBarberos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM usuarios WHERE rol = 'barbero'";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario barbero = new Usuario();
                barbero.setId(rs.getInt("id"));
                barbero.setNombre(rs.getString("nombre"));
                lista.add(barbero);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
