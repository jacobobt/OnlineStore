package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.jpa.ArticuloDAOJPA;

import java.util.List;

public class PruebaListarArticulosJPA {

    public static void main(String[] args) {

        ArticuloDAOJPA articuloDAO = new ArticuloDAOJPA();

        List<Articulo> articulos = articuloDAO.listarArticulos();

        if (articulos.isEmpty()) {
            System.out.println("No hay artículos en la base de datos.");
        } else {
            System.out.println("Lista de artículos:");
            for (Articulo articulo : articulos) {
                System.out.println(articulo);
            }
        }
    }
}