package innerjoinsquad;

import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.jpa.PedidoDAOJPA;
import java.util.List;

public class PruebaListarPedidosJPA {
    public static void main(String[] args) {
        try {
            PedidoDAOJPA dao = new PedidoDAOJPA();
            List<Pedido> pedidos = dao.listarPedidos();

            if (pedidos.isEmpty()) {
                System.out.println("No hay pedidos en la base de datos.");
            } else {
                System.out.println("Listado de pedidos:");
                for (Pedido pedido : pedidos) {
                    System.out.println(pedido);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
