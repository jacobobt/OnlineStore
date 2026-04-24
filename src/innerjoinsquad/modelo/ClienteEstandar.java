package innerjoinsquad.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ESTANDAR")
public class ClienteEstandar extends Cliente {

    public ClienteEstandar() {
        super();
    }

    public ClienteEstandar(String nombreCliente, String domicilioCliente, String nifCliente, String emailCliente) {
        super(nombreCliente, domicilioCliente, nifCliente, emailCliente);
    }

    @Override
    public boolean esPremium() {
        return false;
    }

    @Override
    public double aplicarDescuentoEnvio(double gastosEnvioBase) {
        return gastosEnvioBase;
    }

    @Override
    public String toString() {
        return "ClienteEstandar{" + super.toString() + "}";
    }
}