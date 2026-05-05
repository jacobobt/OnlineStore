package innerjoinsquad.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.math.BigDecimal;

@Entity
@Table(name = "articulos")
public class Articulo {

    @Id
    @Column(name = "codigo_articulo")
    private String codigoArticulo;

    @Column(name = "descripcion")
    private String descripcionArticulo;

    @Column(name = "precio_venta", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @Column(name = "gastos_envio", nullable = false, precision = 10, scale = 2)
    private BigDecimal gastosEnvio;

    @Column(name = "tiempo_preparacion_min")
    private int tiempoPreparacionMin;

    // Constructor vació que añado porque es obligatorio para JPA
    public Articulo() {}

    public Articulo(String codigoArticulo, String descripcionArticulo, BigDecimal precioVenta, BigDecimal gastosEnvio, int tiempoPreparacionMin) {
        this.codigoArticulo = codigoArticulo;
        this.descripcionArticulo = descripcionArticulo;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPreparacionMin = tiempoPreparacionMin;
    }

    public String getCodigoArticulo() {

        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {

        this.codigoArticulo = codigoArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(BigDecimal gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public int getTiempoPreparacionMin() {
        return tiempoPreparacionMin;
    }

    public void setTiempoPreparacionMin(int tiempoPreparacionMin) {
        this.tiempoPreparacionMin = tiempoPreparacionMin;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "codigoArticulo='" + codigoArticulo + '\'' +
                ", descripcionArticulo='" + descripcionArticulo + '\'' +
                ", precioVenta=" + precioVenta +
                ", gastosEnvio=" + gastosEnvio +
                ", tiempoPreparacionMin=" + tiempoPreparacionMin +
                '}';
    }
}
