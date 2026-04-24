package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.jpa.ArticuloDAOJPA;

import java.util.List;

public class PruebaListarArticulosJPA {
    public static void main(String[] args) {
        try {
            ArticuloDAOJPA dao = new ArticuloDAOJPA();
            List<Articulo> articulos = dao.listarArticulos();

            if (articulos.isEmpty()) {
                System.out.println("No hay articulos en la base de datos.");
            } else {
                System.out.println("Listado de articulos:");
                for (Articulo articulo : articulos) {
                    System.out.println(articulo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
