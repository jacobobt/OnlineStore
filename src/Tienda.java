// esto es clase tienda
import java.util.ArrayList;

public class
Tienda {

    private String nombreTienda;
    private ArrayList<Cliente> clientes;
    private ArrayList<Articulo> articulos;
    private ArrayList<Pedido> pedidos;

    public Tienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
        this.clientes = new ArrayList<>();
        this.articulos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void anadirCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void anadirArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    public void anadirPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public String toString() {
        return "Tienda{" +
                "nombreTienda='" + nombreTienda + '\'' +
                ", clientes=" + clientes.size() +
                ", articulos=" + articulos.size() +
                ", pedidos=" + pedidos.size() +
                '}';
    }
}