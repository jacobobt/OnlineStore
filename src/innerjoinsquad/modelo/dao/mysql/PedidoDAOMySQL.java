package innerjoinsquad.modelo.dao.mysql;

import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.PedidoDAO;
import innerjoinsquad.modelo.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import innerjoinsquad.modelo.dao.ClienteDAO;
import innerjoinsquad.modelo.dao.ArticuloDAO;

public class PedidoDAOMySQL implements PedidoDAO {

    private Pedido construirPedidoDesdeResultSet(ResultSet rs, ClienteDAO clienteDAO, ArticuloDAO articuloDAO) throws SQLException {

        int numeroPedido = rs.getInt("numero_pedido");
        String emailCliente = rs.getString("email_cliente");
        String codigoArticulo = rs.getString("codigo_articulo");
        int cantidad = rs.getInt("cantidad");
        java.time.LocalDateTime fechaHora = rs.getTimestamp("fecha_hora").toLocalDateTime();

        innerjoinsquad.modelo.Cliente cliente = clienteDAO.obtenerClientePorEmail(emailCliente);
        innerjoinsquad.modelo.Articulo articulo = articuloDAO.obtenerArticuloPorCodigo(codigoArticulo);

        return new Pedido(
                numeroPedido,
                cliente,
                articulo,
                cantidad,
                fechaHora
        );
    }

    @Override
    public void insertarPedido(Pedido pedido) throws SQLException {

        String sql = "INSERT INTO pedidos (email_cliente, codigo_articulo, cantidad, fecha_hora) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pedido.getCliente().getEmailCliente());
            ps.setString(2, pedido.getArticulo().getCodigoArticulo());
            ps.setInt(3, pedido.getCantidad());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(pedido.getFechaHora()));

            ps.executeUpdate();
        }
    }

    @Override
    public Pedido obtenerPedidoPorNumero(int numeroPedido) throws SQLException {

        String sql = "SELECT * FROM pedidos WHERE numero_pedido = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, numeroPedido);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String emailCliente = rs.getString("email_cliente");
                String codigoArticulo = rs.getString("codigo_articulo");
                int cantidad = rs.getInt("cantidad");
                java.time.LocalDateTime fechaHora = rs.getTimestamp("fecha_hora").toLocalDateTime();

                // DAO auxiliares
                ClienteDAO clienteDAO = new ClienteDAOMySQL();
                ArticuloDAO articuloDAO = new ArticuloDAOMySQL();

                // obtener objetos completos
                innerjoinsquad.modelo.Cliente cliente = clienteDAO.obtenerClientePorEmail(emailCliente);
                innerjoinsquad.modelo.Articulo articulo = articuloDAO.obtenerArticuloPorCodigo(codigoArticulo);

                return new Pedido(
                        numeroPedido,
                        cliente,
                        articulo,
                        cantidad,
                        fechaHora
                );
            }
        }

        return null;
    }

    @Override
    public List<Pedido> listarPedidos() throws SQLException {

        List<Pedido> listaPedidos = new java.util.ArrayList<>();

        String sql = "SELECT * FROM pedidos";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ClienteDAO clienteDAO = new ClienteDAOMySQL();
            ArticuloDAO articuloDAO = new ArticuloDAOMySQL();

            while (rs.next()) {

                int numeroPedido = rs.getInt("numero_pedido");
                String emailCliente = rs.getString("email_cliente");
                String codigoArticulo = rs.getString("codigo_articulo");
                int cantidad = rs.getInt("cantidad");
                java.time.LocalDateTime fechaHora = rs.getTimestamp("fecha_hora").toLocalDateTime();

                innerjoinsquad.modelo.Cliente cliente = clienteDAO.obtenerClientePorEmail(emailCliente);
                innerjoinsquad.modelo.Articulo articulo = articuloDAO.obtenerArticuloPorCodigo(codigoArticulo);

                Pedido pedido = new Pedido(
                        numeroPedido,
                        cliente,
                        articulo,
                        cantidad,
                        fechaHora
                );

                listaPedidos.add(pedido);
            }
        }

        return listaPedidos;
    }
    @Override
    public void eliminarPedido(int numeroPedido) throws SQLException {

        String sql = "DELETE FROM pedidos WHERE numero_pedido = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, numeroPedido);

            ps.executeUpdate();
        }
    }
    @Override
    public List<Pedido> listarPedidosPendientes() throws SQLException {

        List<Pedido> todosLosPedidos = listarPedidos();
        List<Pedido> pedidosPendientes = new java.util.ArrayList<>();

        for (Pedido pedido : todosLosPedidos) {
            if (!pedido.estaEnviado()) {
                pedidosPendientes.add(pedido);
            }
        }

        return pedidosPendientes;
    }
    @Override
    public List<Pedido> listarPedidosEnviados() throws SQLException {

        List<Pedido> todosLosPedidos = listarPedidos();
        List<Pedido> pedidosEnviados = new java.util.ArrayList<>();

        for (Pedido pedido : todosLosPedidos) {
            if (pedido.estaEnviado()) {
                pedidosEnviados.add(pedido);
            }
        }

        return pedidosEnviados;
    }
    @Override
    public List<Pedido> listarPedidosPendientesPorCliente(String emailCliente) throws SQLException {

        List<Pedido> pedidosPendientes = listarPedidosPendientes();
        List<Pedido> pedidosPendientesCliente = new java.util.ArrayList<>();

        for (Pedido pedido : pedidosPendientes) {
            if (pedido.getCliente().getEmailCliente().equalsIgnoreCase(emailCliente)) {
                pedidosPendientesCliente.add(pedido);
            }
        }

        return pedidosPendientesCliente;
    }
    @Override
    public List<Pedido> listarPedidosEnviadosPorCliente(String emailCliente) throws SQLException {

        List<Pedido> pedidosEnviados = listarPedidosEnviados();
        List<Pedido> pedidosEnviadosCliente = new java.util.ArrayList<>();

        for (Pedido pedido : pedidosEnviados) {
            if (pedido.getCliente().getEmailCliente().equalsIgnoreCase(emailCliente)) {
                pedidosEnviadosCliente.add(pedido);
            }
        }

        return pedidosEnviadosCliente;
    }
    public void insertarPedidoTransaccion(Pedido pedido) throws SQLException {

        String sql = "INSERT INTO pedidos (email_cliente, codigo_articulo, cantidad, fecha_hora) VALUES (?, ?, ?, ?)";

        Connection conexion = null;

        try {
            conexion = ConexionBD.getConexion();

            // 🔥 INICIO TRANSACCIÓN
            conexion.setAutoCommit(false);

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, pedido.getCliente().getEmailCliente());
            ps.setString(2, pedido.getArticulo().getCodigoArticulo());
            ps.setInt(3, pedido.getCantidad());
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(pedido.getFechaHora()));

            ps.executeUpdate();

            // 🔥 TODO OK → COMMIT
            conexion.commit();

            System.out.println("Pedido insertado con transacción.");

        } catch (SQLException e) {

            if (conexion != null) {
                // 🔥 ERROR → ROLLBACK
                conexion.rollback();
            }

            throw e;

        } finally {
            if (conexion != null) {
                conexion.setAutoCommit(true);
                conexion.close();
            }
        }
    }
}