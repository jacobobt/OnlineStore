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
import innerjoinsquad.modelo.ClienteEstandar;
import innerjoinsquad.modelo.ClientePremium;
import innerjoinsquad.modelo.Pedido;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;
import java.util.Arrays;
import java.math.BigDecimal;


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

        btnAnadirCliente.setOnAction(e -> {
            TextInputDialog dialogNombre = new TextInputDialog();
            dialogNombre.setTitle("Añadir cliente");
            dialogNombre.setHeaderText("Nuevo cliente");
            dialogNombre.setContentText("Introduce el nombre:");

            Optional<String> resultadoNombre = dialogNombre.showAndWait();
            if (resultadoNombre.isEmpty()) {
                return;
            }
            String nombre = resultadoNombre.get().trim();

            TextInputDialog dialogDomicilio = new TextInputDialog();
            dialogDomicilio.setTitle("Añadir cliente");
            dialogDomicilio.setHeaderText("Nuevo cliente");
            dialogDomicilio.setContentText("Introduce el domicilio:");

            Optional<String> resultadoDomicilio = dialogDomicilio.showAndWait();
            if (resultadoDomicilio.isEmpty()) {
                return;
            }
            String domicilio = resultadoDomicilio.get().trim();

            TextInputDialog dialogNif = new TextInputDialog();
            dialogNif.setTitle("Añadir cliente");
            dialogNif.setHeaderText("Nuevo cliente");
            dialogNif.setContentText("Introduce el NIF:");

            Optional<String> resultadoNif = dialogNif.showAndWait();
            if (resultadoNif.isEmpty()) {
                return;
            }
            String nif = resultadoNif.get().trim();

            TextInputDialog dialogEmail = new TextInputDialog();
            dialogEmail.setTitle("Añadir cliente");
            dialogEmail.setHeaderText("Nuevo cliente");
            dialogEmail.setContentText("Introduce el email:");

            Optional<String> resultadoEmail = dialogEmail.showAndWait();
            if (resultadoEmail.isEmpty()) {
                return;
            }
            String email = resultadoEmail.get().trim();

            ChoiceDialog<String> dialogTipo = new ChoiceDialog<>("ESTANDAR", Arrays.asList("ESTANDAR", "PREMIUM"));
            dialogTipo.setTitle("Añadir cliente");
            dialogTipo.setHeaderText("Tipo de cliente");
            dialogTipo.setContentText("Selecciona el tipo:");

            Optional<String> resultadoTipo = dialogTipo.showAndWait();
            if (resultadoTipo.isEmpty()) {
                return;
            }

            Cliente cliente;
            if (resultadoTipo.get().equals("PREMIUM")) {
                cliente = new ClientePremium(nombre, domicilio, nif, email);
            } else {
                cliente = new ClienteEstandar(nombre, domicilio, nif, email);
            }

            try {
                controlador.anadirCliente(cliente);
                areaResultado.setText("Cliente añadido correctamente:\n" + cliente);
            } catch (RuntimeException ex) {
                areaResultado.setText("Error al añadir el cliente:\n" + ex.getMessage());
            }
        });

        btnAnadirArticulo.setOnAction(e -> {
            TextInputDialog dialogCodigo = new TextInputDialog();
            dialogCodigo.setTitle("Añadir artículo");
            dialogCodigo.setHeaderText("Nuevo artículo");
            dialogCodigo.setContentText("Introduce el código:");

            Optional<String> resultadoCodigo = dialogCodigo.showAndWait();
            if (resultadoCodigo.isEmpty()) {
                return;
            }
            String codigo = resultadoCodigo.get().trim();

            TextInputDialog dialogDescripcion = new TextInputDialog();
            dialogDescripcion.setTitle("Añadir artículo");
            dialogDescripcion.setHeaderText("Nuevo artículo");
            dialogDescripcion.setContentText("Introduce la descripción:");

            Optional<String> resultadoDescripcion = dialogDescripcion.showAndWait();
            if (resultadoDescripcion.isEmpty()) {
                return;
            }
            String descripcion = resultadoDescripcion.get().trim();

            TextInputDialog dialogPrecio = new TextInputDialog();
            dialogPrecio.setTitle("Añadir artículo");
            dialogPrecio.setHeaderText("Nuevo artículo");
            dialogPrecio.setContentText("Introduce el precio de venta:");

            Optional<String> resultadoPrecio = dialogPrecio.showAndWait();
            if (resultadoPrecio.isEmpty()) {
                return;
            }

            TextInputDialog dialogGastos = new TextInputDialog();
            dialogGastos.setTitle("Añadir artículo");
            dialogGastos.setHeaderText("Nuevo artículo");
            dialogGastos.setContentText("Introduce los gastos de envío:");

            Optional<String> resultadoGastos = dialogGastos.showAndWait();
            if (resultadoGastos.isEmpty()) {
                return;
            }

            TextInputDialog dialogTiempo = new TextInputDialog();
            dialogTiempo.setTitle("Añadir artículo");
            dialogTiempo.setHeaderText("Nuevo artículo");
            dialogTiempo.setContentText("Introduce el tiempo de preparación en minutos:");

            Optional<String> resultadoTiempo = dialogTiempo.showAndWait();
            if (resultadoTiempo.isEmpty()) {
                return;
            }

            try {
                BigDecimal precioVenta = new BigDecimal(resultadoPrecio.get().trim());
                BigDecimal gastosEnvio = new BigDecimal(resultadoGastos.get().trim());
                int tiempoPreparacionMin = Integer.parseInt(resultadoTiempo.get().trim());

                Articulo articulo = new Articulo(
                        codigo,
                        descripcion,
                        precioVenta,
                        gastosEnvio,
                        tiempoPreparacionMin
                );

                controlador.anadirArticulo(articulo);
                areaResultado.setText("Artículo añadido correctamente:\n" + articulo);
            } catch (NumberFormatException ex) {
                areaResultado.setText("Error: precio, gastos de envío o tiempo de preparación no tienen un formato válido.");
            } catch (RuntimeException ex) {
                areaResultado.setText("Error al añadir el artículo:\n" + ex.getMessage());
            }
        });

        btnAnadirPedido.setOnAction(e -> {
            TextInputDialog dialogEmail = new TextInputDialog();
            dialogEmail.setTitle("Añadir pedido");
            dialogEmail.setHeaderText("Nuevo pedido");
            dialogEmail.setContentText("Introduce el email del cliente:");

            Optional<String> resultadoEmail = dialogEmail.showAndWait();
            if (resultadoEmail.isEmpty()) {
                return;
            }
            String emailCliente = resultadoEmail.get().trim();

            Cliente cliente = controlador.buscarClientePorEmail(emailCliente);

            if (cliente == null) {
                TextInputDialog dialogNombre = new TextInputDialog();
                dialogNombre.setTitle("Nuevo cliente");
                dialogNombre.setHeaderText("El cliente no existe");
                dialogNombre.setContentText("Introduce el nombre del cliente:");

                Optional<String> resultadoNombre = dialogNombre.showAndWait();
                if (resultadoNombre.isEmpty()) {
                    return;
                }
                String nombre = resultadoNombre.get().trim();

                TextInputDialog dialogDomicilio = new TextInputDialog();
                dialogDomicilio.setTitle("Nuevo cliente");
                dialogDomicilio.setHeaderText("El cliente no existe");
                dialogDomicilio.setContentText("Introduce el domicilio del cliente:");

                Optional<String> resultadoDomicilio = dialogDomicilio.showAndWait();
                if (resultadoDomicilio.isEmpty()) {
                    return;
                }
                String domicilio = resultadoDomicilio.get().trim();

                TextInputDialog dialogNif = new TextInputDialog();
                dialogNif.setTitle("Nuevo cliente");
                dialogNif.setHeaderText("El cliente no existe");
                dialogNif.setContentText("Introduce el NIF del cliente:");

                Optional<String> resultadoNif = dialogNif.showAndWait();
                if (resultadoNif.isEmpty()) {
                    return;
                }
                String nif = resultadoNif.get().trim();

                ChoiceDialog<String> dialogTipo = new ChoiceDialog<>("ESTANDAR", Arrays.asList("ESTANDAR", "PREMIUM"));
                dialogTipo.setTitle("Nuevo cliente");
                dialogTipo.setHeaderText("Tipo de cliente");
                dialogTipo.setContentText("Selecciona el tipo:");

                Optional<String> resultadoTipo = dialogTipo.showAndWait();
                if (resultadoTipo.isEmpty()) {
                    return;
                }

                if (resultadoTipo.get().equals("PREMIUM")) {
                    cliente = new ClientePremium(nombre, domicilio, nif, emailCliente);
                } else {
                    cliente = new ClienteEstandar(nombre, domicilio, nif, emailCliente);
                }

                controlador.anadirCliente(cliente);
            }

            TextInputDialog dialogCodigo = new TextInputDialog();
            dialogCodigo.setTitle("Añadir pedido");
            dialogCodigo.setHeaderText("Nuevo pedido");
            dialogCodigo.setContentText("Introduce el código del artículo:");

            Optional<String> resultadoCodigo = dialogCodigo.showAndWait();
            if (resultadoCodigo.isEmpty()) {
                return;
            }
            String codigoArticulo = resultadoCodigo.get().trim();

            Articulo articulo = controlador.buscarArticuloPorCodigo(codigoArticulo);
            if (articulo == null) {
                areaResultado.setText("Error: el artículo no existe.");
                return;
            }

            TextInputDialog dialogCantidad = new TextInputDialog();
            dialogCantidad.setTitle("Añadir pedido");
            dialogCantidad.setHeaderText("Nuevo pedido");
            dialogCantidad.setContentText("Introduce la cantidad:");

            Optional<String> resultadoCantidad = dialogCantidad.showAndWait();
            if (resultadoCantidad.isEmpty()) {
                return;
            }

            try {
                int cantidad = Integer.parseInt(resultadoCantidad.get().trim());

                Pedido pedido = new Pedido(
                        0,
                        cliente,
                        articulo,
                        cantidad,
                        java.time.LocalDateTime.now()
                );

                controlador.anadirPedido(pedido);
                areaResultado.setText("Pedido añadido correctamente:\n" + pedido);
            } catch (NumberFormatException ex) {
                areaResultado.setText("Error: la cantidad debe ser un número entero.");
            } catch (RuntimeException ex) {
                areaResultado.setText("Error al añadir el pedido:\n" + ex.getMessage());
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
