package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.jpa.ArticuloDAOJPA;

public class PruebaInsertarArticuloJPA {

    public static void main(String[] args) {

        Articulo articulo = new Articulo(
                "A002",
                "Teclado mecánico",
                79.99,
                5.50,
                30
        );

        ArticuloDAOJPA articuloDAO = new ArticuloDAOJPA();
        articuloDAO.insertarArticulo(articulo);

        System.out.println("Artículo insertado correctamente en la base de datos.");
    }
}
