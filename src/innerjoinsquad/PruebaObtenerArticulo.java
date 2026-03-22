package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.mysql.ArticuloDAOMySQL;

import java.sql.SQLException;

public class PruebaObtenerArticulo {

    public static void main(String[] args) {

        try {
            ArticuloDAOMySQL articuloDAO = new ArticuloDAOMySQL();

            Articulo articulo = articuloDAO.obtenerArticuloPorCodigo("A001");

            if (articulo != null) {
                System.out.println("Artículo encontrado:");
                System.out.println(articulo);
            } else {
                System.out.println("No se ha encontrado ningún artículo con ese código.");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener artículo.");
            System.out.println(e.getMessage());
        }
    }
}