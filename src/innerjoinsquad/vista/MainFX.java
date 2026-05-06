package innerjoinsquad.vista;

import javafx.application.Application;
import javafx.stage.Stage;

// Clase MainFX: punto de entrada de la interfaz gráfica con JavaFX. Sustituye a Main.java que usaba la consola.

public class MainFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Lanzo VistaFX que contiene toda la interfaz gráfica
        new VistaFX().start(stage);
    }

    public static void main(String[] args) {
        // Lanza la aplicación JavaFX
        launch(args);
    }
}
