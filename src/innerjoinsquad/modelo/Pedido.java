package innerjoinsquad.modelo;

import java.time.LocalDateTime;

public class Pedido {

    private int numeroPedido; // ID
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private LocalDateTime fechaHora;

    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora; // de tipo LocalDateTime representa fecha y hora." 2024-01-01T12:00:00"
    }

    public int getNumeroPedido() {

        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {

        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {

        return cliente;
    }

    public void setCliente(Cliente cliente) {

        this.cliente = cliente;
    }

    public Articulo getArticulo() {

        return articulo;
    }

    public void setArticulo(Articulo articulo) {

        this.articulo = articulo;
    }

    public int getCantidad() {

        return cantidad;
    }

    public void setCantidad(int cantidad) {

        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaHora() {

        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {

        this.fechaHora = fechaHora;
    }

    // Fecha del pedido + minutos de preparación = momento en el que se envía. Es True si ya está enviado.
    // plusMinutes() es un meto-do que pertenece a la clase LocalDateTime:devuelve una nueva fecha sumándole minutos
    public boolean estaEnviado() {
        LocalDateTime limite = fechaHora.plusMinutes(articulo.getTiempoPreparacionMin());
        return LocalDateTime.now().isAfter(limite) || LocalDateTime.now().isEqual(limite);
    }
    //Devuelve true → el pedido ya fue enviado. Solo se puede eliminar si NO está enviado.
    public boolean sePuedeEliminar() {
        return !estaEnviado();
    }

    public double calcularTotal() {
        double subtotal = articulo.getPrecioVenta() * cantidad;
        double envio = cliente.aplicarDescuentoEnvio(articulo.getGastosEnvio());
        return subtotal + envio;
    }

    //Operador ternario necesario porque si creamos cliente null, saldría NullPointerException
    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido=" + numeroPedido +
                ", cliente=" + (cliente != null ? cliente.getEmailCliente() : "null") + //ternario
                ", articulo=" + (articulo != null ? articulo.getCodigoArticulo() : "null") +
                ", cantidad=" + cantidad +
                ", fechaHora=" + fechaHora +
                ", total=" + calcularTotal() +
                '}';
    }
}