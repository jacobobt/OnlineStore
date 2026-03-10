package innerjoinsquad;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Tienda tienda = new Tienda("Online Store");

        Articulo art1 = new Articulo("A001", "Teclado mecanico", 79.99, 5.99, 60);

        Cliente cliente1 = new ClienteEstandar("Ana", "Calle 1", "12345678A", "ana@mail.com");
        Cliente cliente2 = new ClientePremium("Luis", "Calle 2", "87654321B", "luis@mail.com");

        Pedido pedido1 = new Pedido(1, cliente1, art1, 2, LocalDateTime.now().minusMinutes(10));
        Pedido pedido2 = new Pedido(2, cliente2, art1, 1, LocalDateTime.now().minusMinutes(120));

        tienda.anadirArticulo(art1);
        tienda.anadirCliente(cliente1);
        tienda.anadirCliente(cliente2);
        tienda.anadirPedido(pedido1);
        tienda.anadirPedido(pedido2);

        System.out.println(tienda);
        System.out.println(cliente1);
        System.out.println(cliente2);
        System.out.println(art1);
        System.out.println(pedido1);
        System.out.println(pedido2);
    }
}