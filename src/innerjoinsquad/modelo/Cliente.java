package innerjoinsquad.modelo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente")
public abstract class Cliente {

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "domicilio_cliente", nullable = false)
    private String domicilioCliente;

    @Column(name = "nif_cliente", nullable = false)
    private String nifCliente;

    @Id
    @Column(name = "email_cliente", nullable = false)
    private String emailCliente;

    public Cliente() {
        // Constructor vacío obligatorio para JPA
    }

    public Cliente(String nombreCliente, String domicilioCliente, String nifCliente, String emailCliente) {
        this.nombreCliente = nombreCliente;
        this.domicilioCliente = domicilioCliente;
        this.nifCliente = nifCliente;
        this.emailCliente = emailCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDomicilioCliente() {
        return domicilioCliente;
    }

    public void setDomicilioCliente(String domicilioCliente) {
        this.domicilioCliente = domicilioCliente;
    }

    public String getNifCliente() {
        return nifCliente;
    }

    public void setNifCliente(String nifCliente) {
        this.nifCliente = nifCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public boolean esPremium() {
        return false;
    }

    public double aplicarDescuentoEnvio(double gastosEnvioBase) {
        return gastosEnvioBase;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", domicilioCliente='" + domicilioCliente + '\'' +
                ", nifCliente='" + nifCliente + '\'' +
                ", emailCliente='" + emailCliente + '\'' +
                '}';
    }
}