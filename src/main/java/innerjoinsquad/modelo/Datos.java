package innerjoinsquad.modelo;

import innerjoinsquad.modelo.dao.ArticuloDAO;
import innerjoinsquad.modelo.dao.ClienteDAO;
import innerjoinsquad.modelo.dao.PedidoDAO;
import innerjoinsquad.modelo.excepciones.PedidoNoEncontradoExcepcion;
import innerjoinsquad.modelo.excepciones.PedidoYaEnviadoExcepcion;
import innerjoinsquad.modelo.factory.DAOFactory;
import innerjoinsquad.modelo.factory.MySQLDAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class Datos {

    private DAOFactory factory;
    private ClienteDAO clienteDAO;
    private ArticuloDAO articuloDAO;
    private PedidoDAO pedidoDAO;

    public Datos() {
        this.factory = new MySQLDAOFactory();
        this.clienteDAO = factory.getClienteDAO();
        this.articuloDAO = factory.getArticuloDAO();
        this.pedidoDAO = factory.getPedidoDAO();
    }

    public void anadirCliente(Cliente cliente) {
        try {
            clienteDAO.insertarCliente(cliente);
        } catch (SQLException e) {
            throw new RuntimeException("Error al añadir cliente en la base de datos.", e);
        }
    }

    public void anadirArticulo(Articulo articulo) {
        try {
            articuloDAO.insertarArticulo(articulo);
        } catch (SQLException e) {
            throw new RuntimeException("Error al añadir artículo en la base de datos.", e);
        }
    }

    public void anadirPedido(Pedido pedido) {
        try {
            pedidoDAO.insertarPedido(pedido);
        } catch (SQLException e) {
            throw new RuntimeException("Error al añadir pedido en la base de datos.", e);
        }
    }

    public ArrayList<Cliente> getClientes() {
        try {
            return new ArrayList<>(clienteDAO.listarClientes());
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener clientes de la base de datos.", e);
        }
    }

    public ArrayList<Articulo> getArticulos() {
        try {
            return new ArrayList<>(articuloDAO.listarArticulos());
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener artículos de la base de datos.", e);
        }
    }

    public Cliente buscarClientePorEmail(String email) {
        try {
            return clienteDAO.obtenerClientePorEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cliente en la base de datos.", e);
        }
    }

    public Articulo buscarArticuloPorCodigo(String codigo) {
        try {
            return articuloDAO.obtenerArticuloPorCodigo(codigo);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar artículo en la base de datos.", e);
        }
    }

    public ArrayList<Pedido> getPedidos() {
        try {
            return new ArrayList<>(pedidoDAO.listarPedidos());
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener pedidos de la base de datos.", e);
        }
    }

    public Pedido buscarPedidoPorNumero(int numeroPedido) {
        try {
            return pedidoDAO.obtenerPedidoPorNumero(numeroPedido);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar pedido en la base de datos.", e);
        }
    }

    public void eliminarPedido(int numeroPedido) throws PedidoNoEncontradoExcepcion, PedidoYaEnviadoExcepcion {
        try {
            Pedido pedido = pedidoDAO.obtenerPedidoPorNumero(numeroPedido);

            if (pedido == null) {
                throw new PedidoNoEncontradoExcepcion(numeroPedido);
            }

            if (!pedido.sePuedeEliminar()) {
                throw new PedidoYaEnviadoExcepcion(numeroPedido);
            }

            pedidoDAO.eliminarPedido(numeroPedido);

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar pedido en la base de datos.", e);
        }
    }

    public ArrayList<Pedido> getPedidosPendientes() {
        try {
            return new ArrayList<>(pedidoDAO.listarPedidosPendientes());
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener pedidos pendientes.", e);
        }
    }

    public ArrayList<Pedido> getPedidosEnviados() {
        try {
            return new ArrayList<>(pedidoDAO.listarPedidosEnviados());
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener pedidos enviados.", e);
        }
    }

    public ArrayList<Pedido> getPedidosPendientesPorCliente(String email) {
        try {
            return new ArrayList<>(pedidoDAO.listarPedidosPendientesPorCliente(email));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener pedidos pendientes por cliente.", e);
        }
    }

    public ArrayList<Pedido> getPedidosEnviadosPorCliente(String email) {
        try {
            return new ArrayList<>(pedidoDAO.listarPedidosEnviadosPorCliente(email));
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener pedidos enviados por cliente.", e);
        }
    }

    @Override
    public String toString() {
        return "Datos{" +
                "factory=" + factory.getClass().getSimpleName() +
                '}';
    }
}