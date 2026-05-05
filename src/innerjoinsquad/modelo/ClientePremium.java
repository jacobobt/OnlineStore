package innerjoinsquad.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("PREMIUM")

public class ClientePremium extends Cliente {

    private static final BigDecimal CUOTA_ANUAL = new BigDecimal("30.00");
    private static final BigDecimal DESCUENTO_ENVIO = new BigDecimal("0.20"); // 20%

    public ClientePremium(String nombreCliente, String domicilioCliente, String nifCliente, String emailCliente) {
        super(nombreCliente, domicilioCliente, nifCliente, emailCliente);
    }

    // Constructor vacio que añado porque es obligatorio para JPA
    public ClientePremium() {}

    public BigDecimal getCuotaAnual() {
        return CUOTA_ANUAL;
    }

    public BigDecimal getDescuentoEnvio() {
        return DESCUENTO_ENVIO;
    }

    @Override
    public boolean esPremium() {
        return true;
    }

    @Override
    public BigDecimal aplicarDescuentoEnvio(BigDecimal gastosEnvioBase) {
        return gastosEnvioBase.multiply(BigDecimal.ONE.subtract(DESCUENTO_ENVIO));
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
