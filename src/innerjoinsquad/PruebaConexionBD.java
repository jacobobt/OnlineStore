package innerjoinsquad;

import innerjoinsquad.modelo.util.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexionBD {

    public static void main(String[] args) {
        try {
            Connection conexion = ConexionBD.getConexion();
            System.out.println("Conexion realizada correctamente con la base de datos.");
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            System.out.println(e.getMessage());
        }
    }
}