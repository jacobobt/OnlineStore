package innerjoinsquad.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("ESTANDAR")
public class ClienteEstandar extends Cliente {

    public ClienteEstandar() {
    }

    public ClienteEstandar(String nombreCliente, String domicilioCliente, String nifCliente, String emailCliente) {
        super(nombreCliente, domicilioCliente, nifCliente, emailCliente);
    }
    //lo mantiene falso, como estaba por defecto
    @Override
    public boolean esPremium() {
        return false;
    }

    @Override
    public BigDecimal aplicarDescuentoEnvio(BigDecimal gastosEnvioBase) {
        return gastosEnvioBase; // sin descuento
    }

    @Override
    public String toString() {
        return "ClienteEstandar{" + super.toString() + "}";
    }
}