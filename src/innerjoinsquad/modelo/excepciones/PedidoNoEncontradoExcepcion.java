package innerjoinsquad.modelo.excepciones;

// Funciona cuando se busca un pedido que no existe en el sistema.

public class PedidoNoEncontradoExcepcion extends Exception {

    public PedidoNoEncontradoExcepcion(int numeroPedido) {
        super("No se encontró ningún pedido con el número: " + numeroPedido);
    }
}
