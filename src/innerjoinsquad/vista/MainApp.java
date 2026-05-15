package innerjoinsquad.vista;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        Vista vista = new Vista(stage);
        vista.mostrarVentanaPrincipal();
    }

    public static void main(String[] args) {
        launch();
    }
}