package innerjoinsquad.modelo.excepciones;

// Funciona cuando se intenta eliminar un pedido que ya ha sido enviado.

public class PedidoYaEnviadoExcepcion extends Exception {

    public PedidoYaEnviadoExcepcion(int numeroPedido) {
        super("El pedido número " + numeroPedido + " ya ha sido enviado y no puede eliminarse.");
    }
}