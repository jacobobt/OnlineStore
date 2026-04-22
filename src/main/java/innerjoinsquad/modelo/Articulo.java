package innerjoinsquad.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulos")
public class Articulo {

    @Id
    @Column(name = "codigo_articulo")
    private String codigoArticulo; // ID
    @Column(name = "descripcion")
    private String descripcionArticulo;
    @Column(name = "precio_venta")
    private double precioVenta;
    @Column(name = "gastos_envio")
    private double gastosEnvio;
    @Column(name = "tiempo_preparacion_min")
    private int tiempoPreparacionMin;

    public Articulo() {
    }

    public Articulo(String codigoArticulo, String descripcionArticulo, double precioVenta, double gastosEnvio, int tiempoPreparacionMin) {
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

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(double gastosEnvio) {
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