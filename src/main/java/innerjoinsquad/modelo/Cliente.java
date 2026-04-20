package innerjoinsquad.modelo;

public abstract class Cliente {

    private String nombreCliente;
    private String domicilioCliente;
    private String nifCliente;
    private String emailCliente; // ID

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