package innerjoinsquad.modelo;

import innerjoinsquad.modelo.excepciones.PedidoNoEncontradoExcepcion;
import innerjoinsquad.modelo.excepciones.PedidoYaEnviadoExcepcion;

public class Datos {

    private Tienda tienda;

    public Datos() {
        this.tienda = new Tienda("Online Store");
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public void anadirCliente(Cliente cliente) {
        tienda.anadirCliente(cliente);
    }

    public void anadirArticulo(Articulo articulo) {
        tienda.anadirArticulo(articulo);
    }

    public void anadirPedido(Pedido pedido) {
        tienda.anadirPedido(pedido);
    }

    public java.util.ArrayList<Cliente> getClientes() {
        return tienda.getClientes();
    }

    public java.util.ArrayList<Articulo> getArticulos() {
        return tienda.getArticulos();
    }

    public Cliente buscarClientePorEmail(String email) {
        return tienda.buscarClientePorEmail(email);
    }

    public Articulo buscarArticuloPorCodigo(String codigo) {
        return tienda.buscarArticuloPorCodigo(codigo);
    }

    public java.util.ArrayList<Pedido> getPedidos() {
        return tienda.getPedidos();
    }

    public Pedido buscarPedidoPorNumero(int numeroPedido) {
        return tienda.buscarPedidoPorNumero(numeroPedido);
    }

    public void eliminarPedido(int numeroPedido) throws PedidoNoEncontradoExcepcion, PedidoYaEnviadoExcepcion {
        tienda.eliminarPedido(numeroPedido);
    }

    public java.util.ArrayList<Pedido> getPedidosPendientes() {
        return tienda.getPedidosPendientes();
    }

    public java.util.ArrayList<Pedido> getPedidosEnviados() {
        return tienda.getPedidosEnviados();
    }

    public java.util.ArrayList<Pedido> getPedidosPendientesPorCliente(String email) {
        return tienda.getPedidosPendientesPorCliente(email);
    }

    public java.util.ArrayList<Pedido> getPedidosEnviadosPorCliente(String email) {
        return tienda.getPedidosEnviadosPorCliente(email);
    }

    @Override
    public String toString() {
        return "Datos{" +
                "tienda=" + tienda +
                '}';
    }
}