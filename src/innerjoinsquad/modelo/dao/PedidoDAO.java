package innerjoinsquad.modelo.dao;

import innerjoinsquad.modelo.Pedido;

import java.sql.SQLException;
import java.util.List;

public interface PedidoDAO {

    void insertarPedido(Pedido pedido) throws SQLException;

    Pedido obtenerPedidoPorNumero(int numeroPedido) throws SQLException;

    List<Pedido> listarPedidos() throws SQLException;
}