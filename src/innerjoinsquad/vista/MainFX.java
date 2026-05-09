package innerjoinsquad.vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainFX extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("OnlineStore - Interfaz gráfica en construcción");

        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("OnlineStore");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
