package innerjoinsquad;

import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.mysql.PedidoDAOMySQL;

import java.sql.SQLException;

public class PruebaObtenerPedido {

    public static void main(String[] args) {

        try {
            PedidoDAOMySQL pedidoDAO = new PedidoDAOMySQL();

            Pedido pedido = pedidoDAO.obtenerPedidoPorNumero(1);

            if (pedido != null) {
                System.out.println("Pedido encontrado:");
                System.out.println(pedido);
            } else {
                System.out.println("No se ha encontrado ningún pedido con ese número.");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener pedido.");
            System.out.println(e.getMessage());
        }
    }
}