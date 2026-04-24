package innerjoinsquad.modelo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/online_store";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "010493j@";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}