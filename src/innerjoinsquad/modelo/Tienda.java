package innerjoinsquad.modelo;

import java.util.ArrayList;

public class
Tienda {

    private String nombreTienda;
    private ArrayList<Cliente> clientes;
    private ArrayList<Articulo> articulos;
    private ArrayList<Pedido> pedidos;

    public Tienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
        this.clientes = new ArrayList<>();
        this.articulos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void anadirCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void anadirArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    public void anadirPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public Cliente buscarClientePorEmail(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmailCliente().equalsIgnoreCase(email)) {
                return cliente;
            }
        }
        return null;
    }

    public Articulo buscarArticuloPorCodigo(String codigo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigoArticulo().equalsIgnoreCase(codigo)) {
                return articulo;
            }
        }
        return null;
    }
    public Pedido buscarPedidoPorNumero(int numeroPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumeroPedido() == numeroPedido) {
                return pedido;
            }
        }
        return null;
    }
    public boolean eliminarPedido(int numeroPedido) {
        Pedido pedido = buscarPedidoPorNumero(numeroPedido);

        if (pedido != null && pedido.sePuedeEliminar()) {
            pedidos.remove(pedido);
            return true;
        }

        return false;
    }
    public java.util.ArrayList<Pedido> getPedidosPendientes() {
        java.util.ArrayList<Pedido> pedidosPendientes = new java.util.ArrayList<>();

        for (Pedido pedido : pedidos) {
            if (!pedido.estaEnviado()) {
                pedidosPendientes.add(pedido);
            }
        }

        return pedidosPendientes;
    }
    public java.util.ArrayList<Pedido> getPedidosEnviados() {
        java.util.ArrayList<Pedido> pedidosEnviados = new java.util.ArrayList<>();

        for (Pedido pedido : pedidos) {
            if (pedido.estaEnviado()) {
                pedidosEnviados.add(pedido);
            }
        }

        return pedidosEnviados;
    }
    public java.util.ArrayList<Pedido> getPedidosPendientesPorCliente(String email) {
        java.util.ArrayList<Pedido> pedidosPendientesCliente = new java.util.ArrayList<>();

        for (Pedido pedido : pedidos) {
            if (!pedido.estaEnviado() &&
                    pedido.getCliente().getEmailCliente().equalsIgnoreCase(email)) {
                pedidosPendientesCliente.add(pedido);
            }
        }

        return pedidosPendientesCliente;
    }
    public java.util.ArrayList<Pedido> getPedidosEnviadosPorCliente(String email) {
        java.util.ArrayList<Pedido> pedidosEnviadosCliente = new java.util.ArrayList<>();

        for (Pedido pedido : pedidos) {
            if (pedido.estaEnviado() &&
                    pedido.getCliente().getEmailCliente().equalsIgnoreCase(email)) {
                pedidosEnviadosCliente.add(pedido);
            }
        }

        return pedidosEnviadosCliente;
    }


    @Override
    public String toString() {
        return "Tienda{" +
                "nombreTienda='" + nombreTienda + '\'' +
                ", clientes=" + clientes.size() +
                ", articulos=" + articulos.size() +
                ", pedidos=" + pedidos.size() +
                '}';
    }
}