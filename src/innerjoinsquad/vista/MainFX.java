package innerjoinsquad.vista;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFX extends Application {

    @Override
    public void start(Stage stage) {
        Label titulo = new Label("OnlineStore");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button btnMostrarClientes = new Button("Mostrar clientes");
        Button btnMostrarArticulos = new Button("Mostrar artículos");
        Button btnMostrarPedidos = new Button("Mostrar pedidos");
        Button btnAnadirCliente = new Button("Añadir cliente");
        Button btnAnadirArticulo = new Button("Añadir artículo");
        Button btnAnadirPedido = new Button("Añadir pedido");
        Button btnEliminarCliente = new Button("Eliminar cliente");
        Button btnEliminarArticulo = new Button("Eliminar artículo");
        Button btnEliminarPedido = new Button("Eliminar pedido");
        Button btnPedidosPendientes = new Button("Pedidos pendientes");
        Button btnPedidosEnviados = new Button("Pedidos enviados");
        Button btnPendientesPorCliente = new Button("Pendientes por cliente");
        Button btnEnviadosPorCliente = new Button("Enviados por cliente");

        TextArea areaResultado = new TextArea();
        areaResultado.setEditable(false);
        areaResultado.setWrapText(true);
        areaResultado.setPromptText("Aquí se mostrarán los resultados de las operaciones...");

        double anchoBoton = 180;
        btnMostrarClientes.setPrefWidth(anchoBoton);
        btnMostrarArticulos.setPrefWidth(anchoBoton);
        btnMostrarPedidos.setPrefWidth(anchoBoton);
        btnAnadirCliente.setPrefWidth(anchoBoton);
        btnAnadirArticulo.setPrefWidth(anchoBoton);
        btnAnadirPedido.setPrefWidth(anchoBoton);
        btnEliminarCliente.setPrefWidth(anchoBoton);
        btnEliminarArticulo.setPrefWidth(anchoBoton);
        btnEliminarPedido.setPrefWidth(anchoBoton);
        btnPedidosPendientes.setPrefWidth(anchoBoton);
        btnPedidosEnviados.setPrefWidth(anchoBoton);
        btnPendientesPorCliente.setPrefWidth(anchoBoton);
        btnEnviadosPorCliente.setPrefWidth(anchoBoton);

        VBox panelBotones = new VBox(10,
                btnMostrarClientes,
                btnMostrarArticulos,
                btnMostrarPedidos,
                btnAnadirCliente,
                btnAnadirArticulo,
                btnAnadirPedido,
                btnEliminarCliente,
                btnEliminarArticulo,
                btnEliminarPedido,
                btnPedidosPendientes,
                btnPedidosEnviados,
                btnPendientesPorCliente,
                btnEnviadosPorCliente
        );
        panelBotones.setPadding(new Insets(15));
        panelBotones.setAlignment(Pos.TOP_CENTER);

        VBox panelSuperior = new VBox(titulo);
        panelSuperior.setPadding(new Insets(15));
        panelSuperior.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(panelSuperior);
        root.setLeft(panelBotones);
        root.setCenter(areaResultado);
        root.setPadding(new Insets(10));

        BorderPane.setMargin(areaResultado, new Insets(15));

        Scene scene = new Scene(root, 1000, 650);

        stage.setTitle("OnlineStore - JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
