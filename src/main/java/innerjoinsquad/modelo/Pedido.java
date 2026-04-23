package innerjoinsquad.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_pedido")
    private int numeroPedido;

    @ManyToOne
    @JoinColumn(name = "email_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "codigo_articulo")
    private Articulo articulo;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    public Pedido() {
    }

    public Pedido(int numeroPedido, Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
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

    public boolean estaEnviado() {
        LocalDateTime limite = fechaHora.plusMinutes(articulo.getTiempoPreparacionMin());
        return LocalDateTime.now().isAfter(limite) || LocalDateTime.now().isEqual(limite);
    }

    public boolean sePuedeEliminar() {
        return !estaEnviado();
    }

    public BigDecimal calcularTotal() {
        BigDecimal subtotal = articulo.getPrecioVenta().multiply(BigDecimal.valueOf(cantidad));
        BigDecimal envio = cliente.aplicarDescuentoEnvio(articulo.getGastosEnvio());
        return subtotal.add(envio);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido=" + numeroPedido +
                ", cliente=" + (cliente != null ? cliente.getEmailCliente() : "null") +
                ", articulo=" + (articulo != null ? articulo.getCodigoArticulo() : "null") +
                ", cantidad=" + cantidad +
                ", fechaHora=" + fechaHora +
                ", total=" + calcularTotal() +
                '}';
    }
}
