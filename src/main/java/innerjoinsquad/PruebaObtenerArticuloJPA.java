package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.jpa.ArticuloDAOJPA;

public class PruebaObtenerArticuloJPA {
    public static void main(String[] args) {
        try {
            ArticuloDAOJPA dao = new ArticuloDAOJPA();
            Articulo articulo = dao.obtenerArticuloPorCodigo("S207");

            if (articulo != null) {
                System.out.println("Articulo encontrado:");
                System.out.println(articulo);
            } else {
                System.out.println("No se ha encontrado el articulo.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
