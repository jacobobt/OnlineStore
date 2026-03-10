package innerjoinsquad.modelo;

public class Datos {

    private Tienda tienda;

    public Datos() {
        this.tienda = new Tienda("Online Store");
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public void anadirCliente(Cliente cliente) {
        tienda.anadirCliente(cliente);
    }

    public void anadirArticulo(Articulo articulo) {
        tienda.anadirArticulo(articulo);
    }

    public void anadirPedido(Pedido pedido) {
        tienda.anadirPedido(pedido);
    }

    @Override
    public String toString() {
        return "Datos{" +
                "tienda=" + tienda +
                '}';
    }
}