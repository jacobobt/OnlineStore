package innerjoinsquad.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;

import java.math.BigDecimal;

@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@DiscriminatorColumn(name = "tipo_cliente")
@DiscriminatorValue("CLIENTE")

public abstract class Cliente {

    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "domicilio_cliente")
    private String domicilioCliente;
    @Column(name = "nif_cliente")
    private String nifCliente;
    @Id
    @Column(name = "email_cliente")
    private String emailCliente; // ID

    // Constructor vacio que añado porque es obligatorio para JPA
    public Cliente() {}

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

    // Estos métodos los dejamos porque estaban en el diagrama.
    // En ClienteEstandar y ClientePremium los implementamos diferente.
    public boolean esPremium() {
        return false;
    }

    public BigDecimal aplicarDescuentoEnvio(BigDecimal gastosEnvioBase) {
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
