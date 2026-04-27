package innerjoinsquad.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("ESTANDAR")

public class ClienteEstandar extends Cliente {

    public ClienteEstandar(String nombreCliente, String domicilioCliente, String nifCliente, String emailCliente) {
        super(nombreCliente, domicilioCliente, nifCliente, emailCliente);
    }

    // Constructor vació que añado porque es obligatorio para JPA
    public ClienteEstandar() {}

    //lo mantiene falso, como estaba por defecto
    @Override
    public boolean esPremium() {
        return false;
    }

    @Override
    public double aplicarDescuentoEnvio(double gastosEnvioBase) {
        return gastosEnvioBase; // sin descuento
    }

    @Override
    public String toString() {
        return "ClienteEstandar{" + super.toString() + "}";
    }
}