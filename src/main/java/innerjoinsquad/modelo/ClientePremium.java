package innerjoinsquad.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREMIUM")
public class ClientePremium extends Cliente {

    private static final double CUOTA_ANUAL = 30.0;
    private static final double DESCUENTO_ENVIO = 0.20; // 20%

    public ClientePremium() {
    }

    public ClientePremium(String nombreCliente, String domicilioCliente, String nifCliente, String emailCliente) {
        super(nombreCliente, domicilioCliente, nifCliente, emailCliente);
    }

    public double getCuotaAnual() {
        return CUOTA_ANUAL;
    }

    public double getDescuentoEnvio() {
        return DESCUENTO_ENVIO;
    }

    @Override
    public boolean esPremium() {
        return true;
    }

    @Override
    public double aplicarDescuentoEnvio(double gastosEnvioBase) {
        return gastosEnvioBase * (1.0 - DESCUENTO_ENVIO);
    }

    @Override
    public String toString() {
        return "ClientePremium{" +
                "cuotaAnual=" + CUOTA_ANUAL +
                ", descuentoEnvio=" + DESCUENTO_ENVIO +
                ", " + super.toString() +
                "}";
    }
}