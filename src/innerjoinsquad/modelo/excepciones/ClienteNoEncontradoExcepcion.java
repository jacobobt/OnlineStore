package innerjoinsquad.modelo.excepciones;

// Funciona cuando se busca un cliente que no existe en el sistema.

public class ClienteNoEncontradoExcepcion extends Exception {

    public ClienteNoEncontradoExcepcion(String email) {
        super("No se encontró ningún cliente con el email: " + email);
    }
}