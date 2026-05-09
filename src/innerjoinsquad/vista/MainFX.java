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
import innerjoinsquad.controlador.Controlador;
import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.Pedido;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class MainFX extends Application {

    @Override
    public void start(Stage stage) {
        Controlador controlador = new Controlador();

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

        btnMostrarClientes.setOnAction(e -> {
            StringBuilder resultado = new StringBuilder();

            if (controlador.getClientes().isEmpty()) {
                resultado.append("No hay clientes registrados.");
            } else {
                resultado.append("----- LISTADO DE CLIENTES -----\n");
                for (Cliente cliente : controlador.getClientes()) {
                    resultado.append(cliente).append("\n");
                }
            }

            areaResultado.setText(resultado.toString());
        });

        btnMostrarArticulos.setOnAction(e -> {
            StringBuilder resultado = new StringBuilder();

            if (controlador.getArticulos().isEmpty()) {
                resultado.append("No hay artículos registrados.");
            } else {
                resultado.append("----- LISTADO DE ARTÍCULOS -----\n");
                for (Articulo articulo : controlador.getArticulos()) {
                    resultado.append(articulo).append("\n");
                }
            }

            areaResultado.setText(resultado.toString());
        });

        btnMostrarPedidos.setOnAction(e -> {
            StringBuilder resultado = new StringBuilder();

            if (controlador.getPedidos().isEmpty()) {
                resultado.append("No hay pedidos registrados.");
            } else {
                resultado.append("----- LISTADO DE PEDIDOS -----\n");
                for (Pedido pedido : controlador.getPedidos()) {
                    resultado.append(pedido).append("\n");
                }
            }

            areaResultado.setText(resultado.toString());
        });

        btnPedidosPendientes.setOnAction(e -> {
            StringBuilder resultado = new StringBuilder();

            if (controlador.getPedidosPendientes().isEmpty()) {
                resultado.append("No hay pedidos pendientes.");
            } else {
                resultado.append("----- PEDIDOS PENDIENTES -----\n");
                for (Pedido pedido : controlador.getPedidosPendientes()) {
                    resultado.append(pedido).append("\n");
                }
            }

            areaResultado.setText(resultado.toString());
        });

        btnPedidosEnviados.setOnAction(e -> {
            StringBuilder resultado = new StringBuilder();

            if (controlador.getPedidosEnviados().isEmpty()) {
                resultado.append("No hay pedidos enviados.");
            } else {
                resultado.append("----- PEDIDOS ENVIADOS -----\n");
                for (Pedido pedido : controlador.getPedidosEnviados()) {
                    resultado.append(pedido).append("\n");
                }
            }

            areaResultado.setText(resultado.toString());
        });

        btnPendientesPorCliente.setOnAction(e -> {
            TextInputDialog dialogo = new TextInputDialog();
            dialogo.setTitle("Pedidos pendientes por cliente");
            dialogo.setHeaderText("Consultar pedidos pendientes");
            dialogo.setContentText("Introduce el email del cliente:");

            Optional<String> resultadoDialogo = dialogo.showAndWait();

            if (resultadoDialogo.isPresent()) {
                String email = resultadoDialogo.get().trim();
                StringBuilder resultado = new StringBuilder();

                if (controlador.getPedidosPendientesPorCliente(email).isEmpty()) {
                    resultado.append("No hay pedidos pendientes para ese cliente.");
                } else {
                    resultado.append("----- PEDIDOS PENDIENTES DEL CLIENTE -----\n");
                    for (Pedido pedido : controlador.getPedidosPendientesPorCliente(email)) {
                        resultado.append(pedido).append("\n");
                    }
                }

                areaResultado.setText(resultado.toString());
            }
        });

        btnEnviadosPorCliente.setOnAction(e -> {
            TextInputDialog dialogo = new TextInputDialog();
            dialogo.setTitle("Pedidos enviados por cliente");
            dialogo.setHeaderText("Consultar pedidos enviados");
            dialogo.setContentText("Introduce el email del cliente:");

            Optional<String> resultadoDialogo = dialogo.showAndWait();

            if (resultadoDialogo.isPresent()) {
                String email = resultadoDialogo.get().trim();
                StringBuilder resultado = new StringBuilder();

                if (controlador.getPedidosEnviadosPorCliente(email).isEmpty()) {
                    resultado.append("No hay pedidos enviados para ese cliente.");
                } else {
                    resultado.append("----- PEDIDOS ENVIADOS DEL CLIENTE -----\n");
                    for (Pedido pedido : controlador.getPedidosEnviadosPorCliente(email)) {
                        resultado.append(pedido).append("\n");
                    }
                }

                areaResultado.setText(resultado.toString());
            }
        });

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
