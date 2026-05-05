package innerjoinsquad.vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFX extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setCenter(new Label("Online Store"));

        Scene scene = new Scene(root, 900, 600);

        stage.setTitle("Online Store");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
