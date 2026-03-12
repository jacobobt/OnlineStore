package innerjoinsquad.modelo.excepciones;

// Funciona cuando se busca un artículo que no existe en el sistema.

public class ArticuloNoEncontradoExcepcion extends Exception {

    public ArticuloNoEncontradoExcepcion(String codigo) {
        super("No se encontró ningún artículo con el código: " + codigo);
    }
}