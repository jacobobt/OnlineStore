package innerjoinsquad;

import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.jpa.PedidoDAOJPA;

public class PruebaObtenerPedidoJPA {

    public static void main(String[] args) {

        PedidoDAOJPA pedidoDAO = new PedidoDAOJPA();

        Pedido pedido = pedidoDAO.obtenerPedidoPorNumero(4);

        if (pedido != null) {
            System.out.println("Pedido encontrado:");
            System.out.println(pedido);
        } else {
            System.out.println("No se ha encontrado ningún pedido con ese número.");
        }
    }
}