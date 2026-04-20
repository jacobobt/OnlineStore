package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.mysql.ArticuloDAOMySQL;

import java.sql.SQLException;
import java.util.List;

public class PruebaListarArticulos {

    public static void main(String[] args) {

        try {
            ArticuloDAOMySQL articuloDAO = new ArticuloDAOMySQL();

            List<Articulo> articulos = articuloDAO.listarArticulos();

            if (articulos.isEmpty()) {
                System.out.println("No hay artículos en la base de datos.");
            } else {
                System.out.println("Lista de artículos:");
                for (Articulo articulo : articulos) {
                    System.out.println(articulo);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar artículos.");
            System.out.println(e.getMessage());
        }
    }
}