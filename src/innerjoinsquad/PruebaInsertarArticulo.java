package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.mysql.ArticuloDAOMySQL;

import java.math.BigDecimal;
import java.sql.SQLException;

public class PruebaInsertarArticulo {

    public static void main(String[] args) {

        try {
            Articulo articulo = new Articulo(
                    "A001",
                    "Teclado mecánico",
                    new BigDecimal("79.99"),
                    new BigDecimal("5.50"),
                    30
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
