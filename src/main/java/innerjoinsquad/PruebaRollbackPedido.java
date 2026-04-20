package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.ClienteEstandar;
import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.mysql.PedidoDAOMySQL;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class PruebaRollbackPedido {

    public static void main(String[] args) {

        try {
            Cliente cliente = new ClienteEstandar(
                    "Juan Pérez",
                    "Calle Falsa 123",
                    "12345678A",
                    "juan@email.com"
            );

            // Artículo que NO existe en la BD
            Articulo articulo = new Articulo(
                    "NO_EXISTE",
                    "Artículo inventado",
                    10.0,
                    2.0,
                    5
            );

            Pedido pedido = new Pedido(
                    0,
                    cliente,
                    articulo,
                    1,
                    LocalDateTime.now()
            );

            PedidoDAOMySQL pedidoDAO = new PedidoDAOMySQL();
            pedidoDAO.insertarPedidoTransaccion(pedido);

            System.out.println("Esto no debería imprimirse si el rollback funciona.");

        } catch (SQLException e) {
            System.out.println("Se produjo un error y la transacción hizo rollback.");
            System.out.println(e.getMessage());
        }
    }
}