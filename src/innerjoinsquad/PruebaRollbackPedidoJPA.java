package innerjoinsquad;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.ClienteEstandar;
import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.jpa.PedidoDAOJPA;

import java.time.LocalDateTime;

public class PruebaRollbackPedidoJPA {

    public static void main(String[] args) {

        Cliente cliente = new ClienteEstandar(
                "Jo",
                "Girona",
                "45454545A",
                "jo@girona.com"
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

        PedidoDAOJPA pedidoDAO = new PedidoDAOJPA();
        pedidoDAO.insertarPedido(pedido);

        System.out.println("El rollback funcionó correctamente. El pedido no se insertó.");
    }
}