package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.ClienteEstandar;
import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.mysql.PedidoDAOMySQL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class PruebaInsertarPedidoTransaccion {

    public static void main(String[] args) {

        try {
            Cliente cliente = new ClienteEstandar(
                    "Juan Pérez",
                    "Calle Falsa 123",
                    "12345678A",
                    "juan@email.com"
            );

            Articulo articulo = new Articulo(
                    "A1",
                    "Articulo 1",
                    BigDecimal.valueOf(89.90),
                    BigDecimal.valueOf(4.90),
                    60
            );

            Pedido pedido = new Pedido(
                    0,
                    cliente,
                    articulo,
                    3,
                    LocalDateTime.now()
            );

            PedidoDAOMySQL pedidoDAO = new PedidoDAOMySQL();
            pedidoDAO.insertarPedidoTransaccion(pedido);

            System.out.println("Prueba de transacción completada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error en la prueba de transacción.");
            System.out.println(e.getMessage());
        }
    }
}