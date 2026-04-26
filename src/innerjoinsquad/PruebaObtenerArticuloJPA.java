package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.jpa.ArticuloDAOJPA;

public class PruebaObtenerArticuloJPA {

    public static void main(String[] args) {

        ArticuloDAOJPA articuloDAO = new ArticuloDAOJPA();

        Articulo articulo = articuloDAO.obtenerArticuloPorCodigo("A001");

        if (articulo != null) {
            System.out.println("Artículo encontrado:");
            System.out.println(articulo);
        } else {
            System.out.println("No se ha encontrado ningún artículo con ese código.");
        }
    }
}