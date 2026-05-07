package innerjoinsquad.vista;

import innerjoinsquad.controlador.Controlador;
import innerjoinsquad.modelo.*;
import innerjoinsquad.modelo.excepciones.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VistaFX extends Application {

    private Controlador controlador = new Controlador();
    private BorderPane root;
    private VBox areaContenido;

    @Override
    public void start(Stage stage) {
        root = new BorderPane();

        // Menú lateral izquierdo
        VBox menu = crearMenu();
        root.setLeft(menu);

        // Área central de contenido
        areaContenido = new VBox(10);
        areaContenido.setPadding(new Insets(20));

        // Envuelvo el areaContenido en un panel de scroll para poder deslizar abajo, cuando hayan muchas cosas
        ScrollPane scroll = new ScrollPane(areaContenido);
        scroll.setFitToWidth(true);  // Para que el contenido ocupe el ancho disponible
        scroll.setFitToHeight(false); // Permite crecer verticalmente y hacer scroll
        root.setCenter(scroll);

        // Mensaje de bienvenida
        mostrarBienvenida();

        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Online Store");
        stage.setScene(scene);
        stage.show();
    }

    private VBox crearMenu() {
        VBox menu = new VBox(5);
        menu.setPadding(new Insets(10));
        menu.setStyle("-fx-background-color: #2c3e50;");
        menu.setPrefWidth(200);

        Label titulo = new Label("MENÚ");
        titulo.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        menu.getChildren().add(titulo);

        // Botones del menú
        menu.getChildren().add(crearBotonMenu("0. Inicio", () -> mostrarBienvenida()));
        menu.getChildren().add(crearBotonMenu("1. Añadir cliente", () -> mostrarFormAnadirCliente()));
        menu.getChildren().add(crearBotonMenu("2. Mostrar clientes", () -> mostrarClientes()));
        menu.getChildren().add(crearBotonMenu("3. Borrar cliente", () -> mostrarFormBorrarCliente()));
        menu.getChildren().add(crearBotonMenu("4. Añadir artículo", () -> mostrarFormAnadirArticulo()));
        menu.getChildren().add(crearBotonMenu("5. Mostrar artículos", () -> mostrarArticulos()));
        menu.getChildren().add(crearBotonMenu("6. Borrar artículo", () -> mostrarFormBorrarArticulo()));
        menu.getChildren().add(crearBotonMenu("7. Añadir pedido", () -> mostrarFormAnadirPedido()));
        menu.getChildren().add(crearBotonMenu("8. Mostrar pedidos", () -> mostrarPedidos()));
        menu.getChildren().add(crearBotonMenu("9. Eliminar pedido", () -> mostrarFormEliminarPedido()));
        menu.getChildren().add(crearBotonMenu("10. Pedidos pendientes", () -> mostrarPedidosPendientes()));
        menu.getChildren().add(crearBotonMenu("11. Pedidos enviados", () -> mostrarPedidosEnviados()));
        menu.getChildren().add(crearBotonMenu("12. Pendientes por cliente", () -> mostrarFormPedidosPendientesPorCliente()));
        menu.getChildren().add(crearBotonMenu("13. Enviados por cliente", () -> mostrarFormPedidosEnviadosPorCliente()));
        menu.getChildren().add(crearBotonMenu("14. Salir", () -> System.exit(0)));

        return menu;
    }

    private Button crearBotonMenu(String texto, Runnable accion) {
        Button boton = new Button(texto);
        boton.setMaxWidth(Double.MAX_VALUE);
        boton.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; -fx-alignment: center-left;");
        boton.setOnAction(e -> accion.run());
        return boton;
    }

    private void mostrarBienvenida() {
        areaContenido.getChildren().clear();

        // Mensaje de bienvenida debajo de la imagen
        Label label = new Label("Bienvenido a Online Store");
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        VBox.setMargin(label, new Insets(10, 0, 0, 0));
        areaContenido.getChildren().add(label);

        // Carga de la imagen de bienvenida creada
        try {
            javafx.scene.image.Image imagen = new javafx.scene.image.Image(
                    getClass().getResourceAsStream("/recursos/OnlineStore.png")
            );
            javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(imagen);
            imageView.setFitWidth(860);
            imageView.setFitHeight(400);
            imageView.setPreserveRatio(true);
            areaContenido.getChildren().add(imageView);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + e.getMessage());
        }

    }

    private void mostrarMensaje(String mensaje) {
        Label label = new Label(mensaje);
        areaContenido.getChildren().add(label);
    }

    // ===== CLIENTES =====

    private void mostrarFormAnadirCliente() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Añadir cliente: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        TextField nombre = new TextField();
        nombre.setPromptText("Nombre");
        TextField domicilio = new TextField();
        domicilio.setPromptText("Domicilio");
        TextField nif = new TextField();
        nif.setPromptText("NIF");
        TextField email = new TextField();
        email.setPromptText("Email");

        ComboBox<String> tipo = new ComboBox<>();
        tipo.getItems().addAll("Estándar", "Premium");
        tipo.setValue("Estándar");

        Button btnAnadir = new Button("Añadir cliente");
        Label resultado = new Label();

        btnAnadir.setOnAction(e -> {
            Cliente cliente;
            if (tipo.getValue().equals("Estándar")) {
                cliente = new ClienteEstandar(nombre.getText(), domicilio.getText(), nif.getText(), email.getText());
            } else {
                cliente = new ClientePremium(nombre.getText(), domicilio.getText(), nif.getText(), email.getText());
            }
            controlador.anadirCliente(cliente);
            resultado.setText("Cliente añadido correctamente.");
        });

        areaContenido.getChildren().addAll(nombre, domicilio, nif, email, tipo, btnAnadir, resultado);
    }

    private void mostrarClientes() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Listado de clientes: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        ArrayList<Cliente> clientes = controlador.getClientes();
        if (clientes.isEmpty()) {
            mostrarMensaje("No hay clientes registrados.");
        } else {
            for (Cliente c : clientes) {
                areaContenido.getChildren().add(new Label(c.toString()));
            }
        }
    }

    private void mostrarFormBorrarCliente() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Borrar cliente: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        TextField email = new TextField();
        email.setPromptText("Email del cliente");
        Button btnBorrar = new Button("Borrar cliente");
        Label resultado = new Label();

        btnBorrar.setOnAction(e -> {
            try {
                controlador.eliminarCliente(email.getText());
                resultado.setText("Cliente borrado correctamente.");
            } catch (ClienteNoEncontradoExcepcion ex) {
                resultado.setText("Error: no existe un cliente con ese email.");
            } catch (RuntimeException ex) {
                resultado.setText("Error: no se puede borrar el cliente. Comprueba si tiene pedidos asociados.");
            }
        });

        areaContenido.getChildren().addAll(email, btnBorrar, resultado);
    }

    // ===== ARTÍCULOS =====

    private void mostrarFormAnadirArticulo() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Añadir artículo: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        TextField codigo = new TextField();
        codigo.setPromptText("Código");
        TextField descripcion = new TextField();
        descripcion.setPromptText("Descripción");
        TextField precio = new TextField();
        precio.setPromptText("Precio de venta (usar punto decimal)");
        TextField gastos = new TextField();
        gastos.setPromptText("Gastos de envío (usar punto decimal)");
        TextField tiempo = new TextField();
        tiempo.setPromptText("Tiempo de preparación (minutos)");

        Button btnAnadir = new Button("Añadir artículo");
        Label resultado = new Label();

        btnAnadir.setOnAction(e -> {
            try {
                BigDecimal precioVenta = new BigDecimal(precio.getText());
                BigDecimal gastosEnvio = new BigDecimal(gastos.getText());
                int tiempoPrep = Integer.parseInt(tiempo.getText());
                Articulo articulo = new Articulo(codigo.getText(), descripcion.getText(), precioVenta, gastosEnvio, tiempoPrep);
                controlador.anadirArticulo(articulo);
                resultado.setText("Artículo añadido correctamente.");
            } catch (NumberFormatException ex) {
                resultado.setText("Error: verifica que los valores numéricos son correctos.");
            }
        });

        areaContenido.getChildren().addAll(codigo, descripcion, precio, gastos, tiempo, btnAnadir, resultado);
    }

    private void mostrarArticulos() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Listado de artículos: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        ArrayList<Articulo> articulos = controlador.getArticulos();
        if (articulos.isEmpty()) {
            mostrarMensaje("No hay artículos registrados.");
        } else {
            for (Articulo a : articulos) {
                areaContenido.getChildren().add(new Label(a.toString()));
            }
        }
    }

    private void mostrarFormBorrarArticulo() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Borrar artículo: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        TextField codigo = new TextField();
        codigo.setPromptText("Código del artículo");
        Button btnBorrar = new Button("Borrar artículo");
        Label resultado = new Label();

        btnBorrar.setOnAction(e -> {
            try {
                controlador.eliminarArticulo(codigo.getText());
                resultado.setText("Artículo borrado correctamente.");
            } catch (ArticuloNoEncontradoExcepcion ex) {
                resultado.setText("Error: no existe un artículo con ese código.");
            } catch (RuntimeException ex) {
                resultado.setText("Error: no se puede borrar el artículo. Comprueba si tiene pedidos asociados.");
            }
        });

        areaContenido.getChildren().addAll(codigo, btnBorrar, resultado);
    }

    // ===== PEDIDOS =====

    private void mostrarFormAnadirPedido() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Añadir pedido: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        TextField emailCliente = new TextField();
        emailCliente.setPromptText("Email del cliente");
        TextField codigoArticulo = new TextField();
        codigoArticulo.setPromptText("Código del artículo");
        TextField cantidad = new TextField();
        cantidad.setPromptText("Cantidad");

        Button btnAnadir = new Button("Añadir pedido");
        Label resultado = new Label();

        btnAnadir.setOnAction(e -> {
            Cliente cliente = controlador.buscarClientePorEmail(emailCliente.getText());
            if (cliente == null) {
                resultado.setText("Error: el cliente no existe.");
                return;
            }
            Articulo articulo = controlador.buscarArticuloPorCodigo(codigoArticulo.getText());
            if (articulo == null) {
                resultado.setText("Error: el artículo no existe.");
                return;
            }
            try {
                int cant = Integer.parseInt(cantidad.getText());
                Pedido pedido = new Pedido(0, cliente, articulo, cant, LocalDateTime.now());
                controlador.anadirPedido(pedido);
                resultado.setText("Pedido añadido correctamente.");
            } catch (NumberFormatException ex) {
                resultado.setText("Error: la cantidad debe ser un número entero.");
            }
        });

        areaContenido.getChildren().addAll(emailCliente, codigoArticulo, cantidad, btnAnadir, resultado);
    }

    private void mostrarPedidos() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Listado de pedidos: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        ArrayList<Pedido> pedidos = controlador.getPedidos();
        if (pedidos.isEmpty()) {
            mostrarMensaje("No hay pedidos registrados.");
        } else {
            for (Pedido p : pedidos) {
                areaContenido.getChildren().add(new Label(p.toString()));
            }
        }
    }

    private void mostrarFormEliminarPedido() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Eliminar pedido: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        TextField numeroPedido = new TextField();
        numeroPedido.setPromptText("Número del pedido");
        Button btnEliminar = new Button("Eliminar pedido");
        Label resultado = new Label();

        btnEliminar.setOnAction(e -> {
            try {
                int numero = Integer.parseInt(numeroPedido.getText());
                controlador.eliminarPedido(numero);
                resultado.setText("Pedido eliminado correctamente.");
            } catch (NumberFormatException ex) {
                resultado.setText("Error: el número de pedido debe ser un entero.");
            } catch (PedidoYaEnviadoExcepcion ex) {
                resultado.setText("No se puede eliminar: el pedido ya ha sido enviado.");
            } catch (PedidoNoEncontradoExcepcion ex) {
                resultado.setText("Error: no existe un pedido con ese número.");
            }
        });

        areaContenido.getChildren().addAll(numeroPedido, btnEliminar, resultado);
    }

    private void mostrarPedidosPendientes() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Pedidos pendientes: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        ArrayList<Pedido> pedidos = controlador.getPedidosPendientes();
        if (pedidos.isEmpty()) {
            mostrarMensaje("No hay pedidos pendientes.");
        } else {
            for (Pedido p : pedidos) {
                areaContenido.getChildren().add(new Label(p.toString()));
            }
        }
    }

    private void mostrarPedidosEnviados() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Pedidos enviados: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        ArrayList<Pedido> pedidos = controlador.getPedidosEnviados();
        if (pedidos.isEmpty()) {
            mostrarMensaje("No hay pedidos enviados.");
        } else {
            for (Pedido p : pedidos) {
                areaContenido.getChildren().add(new Label(p.toString()));
            }
        }
    }

    private void mostrarFormPedidosPendientesPorCliente() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Pedidos pendientes por cliente: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        TextField email = new TextField();
        email.setPromptText("Email del cliente");
        Button btnBuscar = new Button("Buscar");
        VBox resultados = new VBox(5);

        btnBuscar.setOnAction(e -> {
            resultados.getChildren().clear();
            ArrayList<Pedido> pedidos = controlador.getPedidosPendientesPorCliente(email.getText());
            if (pedidos.isEmpty()) {
                resultados.getChildren().add(new Label("No hay pedidos pendientes para ese cliente."));
            } else {
                for (Pedido p : pedidos) {
                    resultados.getChildren().add(new Label(p.toString()));
                }
            }
        });

        areaContenido.getChildren().addAll(email, btnBuscar, resultados);
    }

    private void mostrarFormPedidosEnviadosPorCliente() {
        areaContenido.getChildren().clear();
        Label titulo = new Label("Pedidos enviados por cliente: ");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        areaContenido.getChildren().add(titulo);

        TextField email = new TextField();
        email.setPromptText("Email del cliente");
        Button btnBuscar = new Button("Buscar");
        VBox resultados = new VBox(5);

        btnBuscar.setOnAction(e -> {
            resultados.getChildren().clear();
            ArrayList<Pedido> pedidos = controlador.getPedidosEnviadosPorCliente(email.getText());
            if (pedidos.isEmpty()) {
                resultados.getChildren().add(new Label("No hay pedidos enviados para ese cliente."));
            } else {
                for (Pedido p : pedidos) {
                    resultados.getChildren().add(new Label(p.toString()));
                }
            }
        });

        areaContenido.getChildren().addAll(email, btnBuscar, resultados);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
