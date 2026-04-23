package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.mysql.ArticuloDAOMySQL;
import java.math.BigDecimal;

import java.sql.SQLException;

public class PruebaInsertarArticulo {

    public static void main(String[] args) {

        try {
            Articulo articulo = new Articulo(
                    "A1",
                    "Articulo 1",
                    BigDecimal.valueOf(89.90),
                    BigDecimal.valueOf(4.90),
                    60
            );

            ArticuloDAOMySQL articuloDAO = new ArticuloDAOMySQL();

            articuloDAO.insertarArticulo(articulo);

            System.out.println("Artículo insertado correctamente en la base de datos.");

        } catch (SQLException e) {
            System.out.println("Error al insertar artículo.");
            System.out.println(e.getMessage());
        }
    }
}