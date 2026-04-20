package innerjoinsquad.modelo;

import innerjoinsquad.modelo.*;
import innerjoinsquad.modelo.excepciones.ArticuloYaExisteExcepcion;
import innerjoinsquad.modelo.excepciones.ClienteYaExisteExcepcion;
import innerjoinsquad.modelo.excepciones.PedidoYaEnviadoExcepcion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OnlineStoreTest {

    private Articulo articulo;
    private Cliente clientePremium;
    private Datos datos;

    @BeforeEach
    void setUp() throws ClienteYaExisteExcepcion, ArticuloYaExisteExcepcion {
        articulo = new Articulo("A1", "Artículo1", 89.90, 04.90, 60);
        clientePremium = new ClientePremium("Yo mismo", "Calle peatonal", "12345678A", "yomismo@gmail.com");

        datos = new Datos();
        datos.anadirArticulo(articulo);
        datos.anadirCliente(clientePremium);
    }

    // Test 1: ver si el cliente premium recibe un 20% de descuento en el envio
    @Test
    void testCalcularTotalClientePremium() {
        Pedido pedido = new Pedido(1, clientePremium, articulo, 2, LocalDateTime.now());
        double envioConDescuento = 4.90 * (1 - 0.20);
        double esperado = (89.90 * 2) + envioConDescuento;
        assertEquals(esperado, pedido.calcularTotal(), 0.001);
    }

    // Test 2: ver que no se puede eliminar un pedido que ya ha sido enviado
    @Test
    void testEliminarPedidoYaEnviado() {
        Pedido pedidoAntiguo = new Pedido(2, clientePremium, articulo, 1,
                LocalDateTime.now().minusMinutes(120));
        datos.anadirPedido(pedidoAntiguo);

        assertThrows(PedidoYaEnviadoExcepcion.class, () -> datos.eliminarPedido(2));
    }
}