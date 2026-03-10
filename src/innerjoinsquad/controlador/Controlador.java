package innerjoinsquad.controlador;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.Datos;
import innerjoinsquad.modelo.Pedido;

public class Controlador {

    private Datos datos;

    public Controlador() {
        this.datos = new Datos();
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    public void anadirCliente(Cliente cliente) {
        datos.anadirCliente(cliente);
    }

    public void anadirArticulo(Articulo articulo) {
        datos.anadirArticulo(articulo);
    }

    public void anadirPedido(Pedido pedido) {
        datos.anadirPedido(pedido);
    }
}