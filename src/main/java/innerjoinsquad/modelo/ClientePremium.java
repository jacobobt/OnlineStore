package innerjoinsquad.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@DiscriminatorValue("PREMIUM")
public class ClientePremium extends Cliente {

    private static final BigDecimal CUOTA_ANUAL = BigDecimal.valueOf(30.0);
    private static final BigDecimal DESCUENTO_ENVIO = BigDecimal.valueOf(0.20); // 20%

    public ClientePremium() {
    }

    public ClientePremium(String nombreCliente, String domicilioCliente, String nifCliente, String emailCliente) {
        super(nombreCliente, domicilioCliente, nifCliente, emailCliente);
    }

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
        return gastosEnvioBase.multiply(BigDecimal.ONE.subtract(DESCUENTO_ENVIO))
                .setScale(2, RoundingMode.HALF_UP);
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