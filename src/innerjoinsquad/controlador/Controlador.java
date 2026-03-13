package innerjoinsquad.controlador;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.Datos;
import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.excepciones.PedidoNoEncontradoExcepcion;
import innerjoinsquad.modelo.excepciones.PedidoYaEnviadoExcepcion;

public class Controlador {

    private Datos datos;

    public Controlador() {
        this.datos = new Datos();
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    public void anadirCliente(Cliente cliente) {
        datos.anadirCliente(cliente);
    }

    public void anadirArticulo(Articulo articulo) {
        datos.anadirArticulo(articulo);
    }

    public void anadirPedido(Pedido pedido) {
        datos.anadirPedido(pedido);
    }

    public java.util.ArrayList<Cliente> getClientes() {
        return datos.getClientes();
    }

    public java.util.ArrayList<Articulo> getArticulos() {
        return datos.getArticulos();
    }

    public Cliente buscarClientePorEmail(String email) {
        return datos.buscarClientePorEmail(email);
    }

    public Articulo buscarArticuloPorCodigo(String codigo) {
        return datos.buscarArticuloPorCodigo(codigo);
    }

    public java.util.ArrayList<Pedido> getPedidos() {
        return datos.getPedidos();
    }

    public Pedido buscarPedidoPorNumero(int numeroPedido) {
        return datos.buscarPedidoPorNumero(numeroPedido);
    }

    public void eliminarPedido(int numeroPedido) throws PedidoNoEncontradoExcepcion, PedidoYaEnviadoExcepcion {
        datos.eliminarPedido(numeroPedido);
    }

    public java.util.ArrayList<Pedido> getPedidosPendientes() {
        return datos.getPedidosPendientes();
    }

    public java.util.ArrayList<Pedido> getPedidosEnviados() {
        return datos.getPedidosEnviados();
    }

    public java.util.ArrayList<Pedido> getPedidosPendientesPorCliente(String email) {
        return datos.getPedidosPendientesPorCliente(email);
    }

    public java.util.ArrayList<Pedido> getPedidosEnviadosPorCliente(String email) {
        return datos.getPedidosEnviadosPorCliente(email);
    }
}