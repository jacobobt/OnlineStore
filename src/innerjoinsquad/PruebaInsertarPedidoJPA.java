package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.ClienteEstandar;
import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.jpa.PedidoDAOJPA;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PruebaInsertarPedidoJPA {

    public static void main(String[] args) {

        Cliente cliente = new ClienteEstandar(
                "Juan Pérez",
                "Calle Falsa 123",
                "12345678A",
                "juan@email.com"
        );

        Articulo articulo = new Articulo(
                "A002",
                "Teclado mecánico",
                new BigDecimal("79.99"),
                new BigDecimal("5.50"),
                30
        );

        Pedido pedido = new Pedido(
                0,
                cliente,
                articulo,
                2,
                LocalDateTime.now()
        );

        PedidoDAOJPA pedidoDAO = new PedidoDAOJPA();
        pedidoDAO.insertarPedido(pedido);

        System.out.println("Pedido insertado correctamente en la base de datos.");
    }
}
