package innerjoinsquad;

import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.jpa.PedidoDAOJPA;

import java.util.List;

public class PruebaListarPedidosJPA {

    public static void main(String[] args) {

        PedidoDAOJPA pedidoDAO = new PedidoDAOJPA();

        List<Pedido> pedidos = pedidoDAO.listarPedidos();

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en la base de datos.");
        } else {
            System.out.println("Lista de pedidos:");
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }
}