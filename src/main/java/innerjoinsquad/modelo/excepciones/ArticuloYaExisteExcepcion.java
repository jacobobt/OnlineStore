package innerjoinsquad.modelo.excepciones;

// Funciona cuando se intenta añadir un artículo con un código que ya existe.

public class ArticuloYaExisteExcepcion extends Exception {

    public ArticuloYaExisteExcepcion(String codigo) {
        super("Ya existe un artículo con el código: " + codigo);
    }
}