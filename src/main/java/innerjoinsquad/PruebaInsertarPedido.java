package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.ClienteEstandar;
import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.mysql.PedidoDAOMySQL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class PruebaInsertarPedido {

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
                    2,
                    LocalDateTime.now()
            );

            PedidoDAOMySQL pedidoDAO = new PedidoDAOMySQL();
            pedidoDAO.insertarPedido(pedido);

            System.out.println("Pedido insertado correctamente en la base de datos.");

        } catch (SQLException e) {
            System.out.println("Error al insertar pedido.");
            System.out.println(e.getMessage());
        }
    }
}