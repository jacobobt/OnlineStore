package innerjoinsquad.vista;

import innerjoinsquad.controlador.Controlador;

public class Vista {

    private Controlador controlador;

    public Vista() {
        this.controlador = new Controlador();
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void mostrarMensajeInicio() {
        System.out.println("Aplicacion Online Store iniciada correctamente.");
    }
}