package innerjoinsquad.vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// Clase MainFX: punto de entrada de la interfaz gráfica con JavaFX. Sustituye a Main.java que usaba la consola.

public class MainFX extends Application {

    @Override
    public void start(Stage stage) {
        // BorderPane es el layout principal de la ventana.
        BorderPane root = new BorderPane();
        // Añado un label en el centro como placeholder.
        root.setCenter(new Label("Online Store"));
        // Creo la escena con un tamaño de 900x600 píxeles.
        Scene scene = new Scene(root, 900, 600);
        // Configuro el título de la ventana.
        stage.setTitle("Online Store");
        stage.setScene(scene);
        // Mostrar la ventana.
        stage.show();
    }

    public static void main(String[] args) {
        // Lanza la aplicación JavaFX.
        launch(args);
    }
}
