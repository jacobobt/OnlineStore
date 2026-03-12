package innerjoinsquad.modelo.excepciones;

// Funciona cuando se intenta añadir un cliente con un email que ya existe.

public class ClienteYaExisteExcepcion extends Exception {

    public ClienteYaExisteExcepcion(String email) {
        super("Ya existe un cliente con el email: " + email);
    }
}
