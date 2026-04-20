package innerjoinsquad;

import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.mysql.PedidoDAOMySQL;

import java.sql.SQLException;
import java.util.List;

public class PruebaListarPedidos {

    public static void main(String[] args) {

        try {
            PedidoDAOMySQL pedidoDAO = new PedidoDAOMySQL();

            List<Pedido> pedidos = pedidoDAO.listarPedidos();

            if (pedidos.isEmpty()) {
                System.out.println("No hay pedidos en la base de datos.");
            } else {
                System.out.println("Lista de pedidos:");
                for (Pedido pedido : pedidos) {
                    System.out.println(pedido);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos.");
            System.out.println(e.getMessage());
        }
    }
}