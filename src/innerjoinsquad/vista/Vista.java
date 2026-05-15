package innerjoinsquad.vista;

import innerjoinsquad.controlador.Controlador;
import innerjoinsquad.modelo.*;
import innerjoinsquad.modelo.excepciones.*;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Vista {

    private final Controlador controlador;
    private final Stage stagePrincipal;

    public Vista(Stage stagePrincipal) {
        this.controlador = new Controlador();
        this.stagePrincipal = stagePrincipal;
    }

    // ─────────────────────────────────────────────
    //  VENTANA PRINCIPAL
    // ─────────────────────────────────────────────

    public void mostrarVentanaPrincipal() {
        stagePrincipal.setTitle("Online Store - InnerJoinSquad");

        // Tres botones de sección
        Button btnClientes  = crearBotonMenu("👤 Clientes");
        Button btnArticulos = crearBotonMenu("📦 Artículos");
        Button btnPedidos   = crearBotonMenu("🛒 Pedidos");

        btnClientes.setOnAction(e  -> mostrarVentanaClientes());
        btnArticulos.setOnAction(e -> mostrarVentanaArticulos());
        btnPedidos.setOnAction(e   -> mostrarVentanaPedidos());

        VBox menu = new VBox(20, btnClientes, btnArticulos, btnPedidos);
        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(40));

        Label titulo = new Label("Online Store");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        VBox root = new VBox(30, titulo, menu);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f0f4f8;");

        stagePrincipal.setScene(new Scene(root, 420, 380));
        stagePrincipal.show();
    }

    private Button crearBotonMenu(String texto) {
        Button btn = new Button(texto);
        btn.setPrefWidth(220);
        btn.setPrefHeight(50);
        btn.setStyle("-fx-font-size: 16px; -fx-background-color: #2563eb; " +
                "-fx-text-fill: white; -fx-background-radius: 8;");
        return btn;
    }

    // ─────────────────────────────────────────────
    //  VENTANA CLIENTES
    // ─────────────────────────────────────────────

    private void mostrarVentanaClientes() {
        Stage stage = new Stage();
        stage.setTitle("Gestión de Clientes");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(stagePrincipal);

        // Tabla
        TableView<Cliente> tabla = new TableView<>();
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Cliente, String> colNombre    = new TableColumn<>("Nombre");
        TableColumn<Cliente, String> colEmail     = new TableColumn<>("Email");
        TableColumn<Cliente, String> colNif       = new TableColumn<>("NIF");
        TableColumn<Cliente, String> colDomicilio = new TableColumn<>("Domicilio");
        TableColumn<Cliente, String> colTipo      = new TableColumn<>("Tipo");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("emailCliente"));
        colNif.setCellValueFactory(new PropertyValueFactory<>("nifCliente"));
        colDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilioCliente"));
        colTipo.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().esPremium() ? "Premium" : "Estándar"));

        tabla.getColumns().addAll(colNombre, colEmail, colNif, colDomicilio, colTipo);
        refrescarTablaClientes(tabla);

        // Botones
        Button btnAnadir   = new Button("➕ Añadir cliente");
        Button btnEliminar = new Button("🗑 Eliminar cliente");
        Button btnRefrescar = new Button("🔄 Refrescar");

        btnAnadir.setOnAction(e -> {
            mostrarDialogoAnadirCliente();
            refrescarTablaClientes(tabla);
        });

        btnEliminar.setOnAction(e -> {
            Cliente seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlerta("Selecciona un cliente de la tabla.", Alert.AlertType.WARNING);
                return;
            }
            mostrarDialogoEliminarCliente(seleccionado.getEmailCliente());
            refrescarTablaClientes(tabla);
        });

        btnRefrescar.setOnAction(e -> refrescarTablaClientes(tabla));

        HBox botones = new HBox(10, btnAnadir, btnEliminar, btnRefrescar);
        botones.setAlignment(Pos.CENTER_LEFT);
        botones.setPadding(new Insets(10, 0, 0, 0));

        VBox root = new VBox(10, tabla, botones);
        root.setPadding(new Insets(20));
        VBox.setVgrow(tabla, Priority.ALWAYS);

        stage.setScene(new Scene(root, 750, 400));
        stage.show();
    }

    private void refrescarTablaClientes(TableView<Cliente> tabla) {
        tabla.setItems(FXCollections.observableArrayList(controlador.getClientes()));
    }

    private void mostrarDialogoAnadirCliente() {
        Stage dialog = new Stage();
        dialog.setTitle("Añadir Cliente");
        dialog.initModality(Modality.APPLICATION_MODAL);

        TextField tfNombre    = new TextField(); tfNombre.setPromptText("Nombre");
        TextField tfDomicilio = new TextField(); tfDomicilio.setPromptText("Domicilio");
        TextField tfNif       = new TextField(); tfNif.setPromptText("NIF");
        TextField tfEmail     = new TextField(); tfEmail.setPromptText("Email");

        ToggleGroup grupo = new ToggleGroup();
        RadioButton rbEstandar = new RadioButton("Estándar"); rbEstandar.setToggleGroup(grupo); rbEstandar.setSelected(true);
        RadioButton rbPremium  = new RadioButton("Premium");  rbPremium.setToggleGroup(grupo);
        HBox tipoBox = new HBox(15, new Label("Tipo:"), rbEstandar, rbPremium);
        tipoBox.setAlignment(Pos.CENTER_LEFT);

        Button btnGuardar  = new Button("Guardar");
        Button btnCancelar = new Button("Cancelar");

        btnCancelar.setOnAction(e -> dialog.close());
        btnGuardar.setOnAction(e -> {
            String nombre    = tfNombre.getText().trim();
            String domicilio = tfDomicilio.getText().trim();
            String nif       = tfNif.getText().trim();
            String email     = tfEmail.getText().trim();

            if (nombre.isEmpty() || domicilio.isEmpty() || nif.isEmpty() || email.isEmpty()) {
                mostrarAlerta("Rellena todos los campos.", Alert.AlertType.WARNING);
                return;
            }

            Cliente cliente = rbPremium.isSelected()
                    ? new ClientePremium(nombre, domicilio, nif, email)
                    : new ClienteEstandar(nombre, domicilio, nif, email);

            try {
                controlador.anadirCliente(cliente);
                mostrarAlerta("Cliente añadido correctamente.", Alert.AlertType.INFORMATION);
                dialog.close();
            } catch (Exception ex) {
                mostrarAlerta("Error al añadir cliente: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        HBox botonesDialog = new HBox(10, btnGuardar, btnCancelar);
        botonesDialog.setAlignment(Pos.CENTER_RIGHT);

        GridPane grid = new GridPane();
        grid.setVgap(10); grid.setHgap(10); grid.setPadding(new Insets(20));
        grid.addRow(0, new Label("Nombre:"),    tfNombre);
        grid.addRow(1, new Label("Domicilio:"), tfDomicilio);
        grid.addRow(2, new Label("NIF:"),       tfNif);
        grid.addRow(3, new Label("Email:"),     tfEmail);
        grid.addRow(4, tipoBox);
        grid.add(botonesDialog, 0, 5, 2, 1);

        dialog.setScene(new Scene(grid, 380, 280));
        dialog.showAndWait();
    }

    private void mostrarDialogoEliminarCliente(String email) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar el cliente con email: " + email + "?",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirmar eliminación");
        confirm.showAndWait().ifPresent(resp -> {
            if (resp == ButtonType.YES) {
                try {
                    controlador.eliminarCliente(email);
                    mostrarAlerta("Cliente eliminado correctamente.", Alert.AlertType.INFORMATION);
                } catch (ClienteNoEncontradoExcepcion ex) {
                    mostrarAlerta("Error: cliente no encontrado.", Alert.AlertType.ERROR);
                } catch (RuntimeException ex) {
                    mostrarAlerta("No se puede eliminar: el cliente tiene pedidos asociados.", Alert.AlertType.ERROR);
                }
            }
        });
    }

    // ─────────────────────────────────────────────
    //  VENTANA ARTÍCULOS
    // ─────────────────────────────────────────────

    private void mostrarVentanaArticulos() {
        Stage stage = new Stage();
        stage.setTitle("Gestión de Artículos");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(stagePrincipal);

        TableView<Articulo> tabla = new TableView<>();
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Articulo, String>     colCodigo      = new TableColumn<>("Código");
        TableColumn<Articulo, String>     colDescripcion = new TableColumn<>("Descripción");
        TableColumn<Articulo, BigDecimal> colPrecio      = new TableColumn<>("Precio (€)");
        TableColumn<Articulo, BigDecimal> colEnvio       = new TableColumn<>("Gastos envío (€)");
        TableColumn<Articulo, Integer>    colTiempo      = new TableColumn<>("Preparación (min)");

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoArticulo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionArticulo"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        colEnvio.setCellValueFactory(new PropertyValueFactory<>("gastosEnvio"));
        colTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempoPreparacionMin"));

        tabla.getColumns().addAll(colCodigo, colDescripcion, colPrecio, colEnvio, colTiempo);
        refrescarTablaArticulos(tabla);

        Button btnAnadir    = new Button("➕ Añadir artículo");
        Button btnEliminar  = new Button("🗑 Eliminar artículo");
        Button btnRefrescar = new Button("🔄 Refrescar");

        btnAnadir.setOnAction(e -> {
            mostrarDialogoAnadirArticulo();
            refrescarTablaArticulos(tabla);
        });

        btnEliminar.setOnAction(e -> {
            Articulo seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlerta("Selecciona un artículo de la tabla.", Alert.AlertType.WARNING);
                return;
            }
            mostrarDialogoEliminarArticulo(seleccionado.getCodigoArticulo());
            refrescarTablaArticulos(tabla);
        });

        btnRefrescar.setOnAction(e -> refrescarTablaArticulos(tabla));

        HBox botones = new HBox(10, btnAnadir, btnEliminar, btnRefrescar);
        botones.setAlignment(Pos.CENTER_LEFT);
        botones.setPadding(new Insets(10, 0, 0, 0));

        VBox root = new VBox(10, tabla, botones);
        root.setPadding(new Insets(20));
        VBox.setVgrow(tabla, Priority.ALWAYS);

        stage.setScene(new Scene(root, 750, 400));
        stage.show();
    }

    private void refrescarTablaArticulos(TableView<Articulo> tabla) {
        tabla.setItems(FXCollections.observableArrayList(controlador.getArticulos()));
    }

    private void mostrarDialogoAnadirArticulo() {
        Stage dialog = new Stage();
        dialog.setTitle("Añadir Artículo");
        dialog.initModality(Modality.APPLICATION_MODAL);

        TextField tfCodigo      = new TextField(); tfCodigo.setPromptText("Código");
        TextField tfDescripcion = new TextField(); tfDescripcion.setPromptText("Descripción");
        TextField tfPrecio      = new TextField(); tfPrecio.setPromptText("Precio (ej: 9.99)");
        TextField tfEnvio       = new TextField(); tfEnvio.setPromptText("Gastos envío (ej: 2.50)");
        TextField tfTiempo      = new TextField(); tfTiempo.setPromptText("Minutos preparación");

        Button btnGuardar  = new Button("Guardar");
        Button btnCancelar = new Button("Cancelar");

        btnCancelar.setOnAction(e -> dialog.close());
        btnGuardar.setOnAction(e -> {
            try {
                String codigo      = tfCodigo.getText().trim();
                String descripcion = tfDescripcion.getText().trim();
                BigDecimal precio  = new BigDecimal(tfPrecio.getText().trim().replace(",", "."));
                BigDecimal envio   = new BigDecimal(tfEnvio.getText().trim().replace(",", "."));
                int tiempo         = Integer.parseInt(tfTiempo.getText().trim());

                if (codigo.isEmpty() || descripcion.isEmpty()) {
                    mostrarAlerta("Rellena todos los campos.", Alert.AlertType.WARNING);
                    return;
                }

                Articulo articulo = new Articulo(codigo, descripcion, precio, envio, tiempo);
                controlador.anadirArticulo(articulo);
                mostrarAlerta("Artículo añadido correctamente.", Alert.AlertType.INFORMATION);
                dialog.close();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Precio, gastos y tiempo deben ser numéricos.", Alert.AlertType.ERROR);
            } catch (Exception ex) {
                mostrarAlerta("Error al añadir artículo: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        HBox botonesDialog = new HBox(10, btnGuardar, btnCancelar);
        botonesDialog.setAlignment(Pos.CENTER_RIGHT);

        GridPane grid = new GridPane();
        grid.setVgap(10); grid.setHgap(10); grid.setPadding(new Insets(20));
        grid.addRow(0, new Label("Código:"),       tfCodigo);
        grid.addRow(1, new Label("Descripción:"),  tfDescripcion);
        grid.addRow(2, new Label("Precio (€):"),   tfPrecio);
        grid.addRow(3, new Label("Gastos envío:"), tfEnvio);
        grid.addRow(4, new Label("Prep. (min):"),  tfTiempo);
        grid.add(botonesDialog, 0, 5, 2, 1);

        dialog.setScene(new Scene(grid, 380, 280));
        dialog.showAndWait();
    }

    private void mostrarDialogoEliminarArticulo(String codigo) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar el artículo con código: " + codigo + "?",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirmar eliminación");
        confirm.showAndWait().ifPresent(resp -> {
            if (resp == ButtonType.YES) {
                try {
                    controlador.eliminarArticulo(codigo);
                    mostrarAlerta("Artículo eliminado correctamente.", Alert.AlertType.INFORMATION);
                } catch (ArticuloNoEncontradoExcepcion ex) {
                    mostrarAlerta("Error: artículo no encontrado.", Alert.AlertType.ERROR);
                } catch (RuntimeException ex) {
                    mostrarAlerta("No se puede eliminar: el artículo tiene pedidos asociados.", Alert.AlertType.ERROR);
                }
            }
        });
    }

    // ─────────────────────────────────────────────
    //  VENTANA PEDIDOS
    // ─────────────────────────────────────────────

    private void mostrarVentanaPedidos() {
        Stage stage = new Stage();
        stage.setTitle("Gestión de Pedidos");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(stagePrincipal);

        TableView<Pedido> tabla = new TableView<>();
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Pedido, Integer> colNum      = new TableColumn<>("Nº Pedido");
        TableColumn<Pedido, String>  colCliente  = new TableColumn<>("Cliente");
        TableColumn<Pedido, String>  colArticulo = new TableColumn<>("Artículo");
        TableColumn<Pedido, Integer> colCantidad = new TableColumn<>("Cantidad");
        TableColumn<Pedido, String>  colFecha    = new TableColumn<>("Fecha/Hora");
        TableColumn<Pedido, String>  colEstado   = new TableColumn<>("Estado");
        TableColumn<Pedido, String>  colTotal    = new TableColumn<>("Total (€)");

        colNum.setCellValueFactory(new PropertyValueFactory<>("numeroPedido"));
        colCliente.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getCliente() != null
                                ? data.getValue().getCliente().getEmailCliente() : ""));
        colArticulo.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getArticulo() != null
                                ? data.getValue().getArticulo().getDescripcionArticulo() : ""));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colFecha.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getFechaHora() != null
                                ? data.getValue().getFechaHora().toString().replace("T", " ") : ""));
        colEstado.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().estaEnviado() ? "✅ Enviado" : "⏳ Pendiente"));
        colTotal.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().calcularTotal().toPlainString() + " €"));

        tabla.getColumns().addAll(colNum, colCliente, colArticulo, colCantidad, colFecha, colEstado, colTotal);

        // Filtros
        ToggleGroup filtroGrupo = new ToggleGroup();
        RadioButton rbTodos     = new RadioButton("Todos");     rbTodos.setToggleGroup(filtroGrupo);     rbTodos.setSelected(true);
        RadioButton rbPendientes = new RadioButton("Pendientes"); rbPendientes.setToggleGroup(filtroGrupo);
        RadioButton rbEnviados  = new RadioButton("Enviados");  rbEnviados.setToggleGroup(filtroGrupo);

        TextField tfEmailFiltro = new TextField(); tfEmailFiltro.setPromptText("Email cliente (opcional)");
        tfEmailFiltro.setPrefWidth(220);

        Button btnFiltrar = new Button("🔍 Filtrar");
        btnFiltrar.setOnAction(e -> aplicarFiltro(tabla, rbTodos, rbPendientes, rbEnviados, tfEmailFiltro.getText().trim()));

        HBox filtros = new HBox(10, rbTodos, rbPendientes, rbEnviados,
                new Label("  Por cliente:"), tfEmailFiltro, btnFiltrar);
        filtros.setAlignment(Pos.CENTER_LEFT);
        filtros.setPadding(new Insets(5, 0, 5, 0));

        // Botones acción
        Button btnAnadir    = new Button("➕ Añadir pedido");
        Button btnEliminar  = new Button("🗑 Eliminar pedido");
        Button btnRefrescar = new Button("🔄 Refrescar");

        btnAnadir.setOnAction(e -> {
            mostrarDialogoAnadirPedido();
            aplicarFiltro(tabla, rbTodos, rbPendientes, rbEnviados, tfEmailFiltro.getText().trim());
        });

        btnEliminar.setOnAction(e -> {
            Pedido seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                mostrarAlerta("Selecciona un pedido de la tabla.", Alert.AlertType.WARNING);
                return;
            }
            mostrarDialogoEliminarPedido(seleccionado.getNumeroPedido());
            aplicarFiltro(tabla, rbTodos, rbPendientes, rbEnviados, tfEmailFiltro.getText().trim());
        });

        btnRefrescar.setOnAction(e ->
                aplicarFiltro(tabla, rbTodos, rbPendientes, rbEnviados, tfEmailFiltro.getText().trim()));

        HBox botones = new HBox(10, btnAnadir, btnEliminar, btnRefrescar);
        botones.setAlignment(Pos.CENTER_LEFT);
        botones.setPadding(new Insets(10, 0, 0, 0));

        // Cargar datos iniciales
        tabla.setItems(FXCollections.observableArrayList(controlador.getPedidos()));

        VBox root = new VBox(10, filtros, tabla, botones);
        root.setPadding(new Insets(20));
        VBox.setVgrow(tabla, Priority.ALWAYS);

        stage.setScene(new Scene(root, 900, 480));
        stage.show();
    }

    private void aplicarFiltro(TableView<Pedido> tabla,
                               RadioButton rbTodos,
                               RadioButton rbPendientes,
                               RadioButton rbEnviados,
                               String email) {
        ArrayList<Pedido> resultado;

        boolean tieneEmail = !email.isEmpty();

        if (rbPendientes.isSelected()) {
            resultado = tieneEmail
                    ? controlador.getPedidosPendientesPorCliente(email)
                    : controlador.getPedidosPendientes();
        } else if (rbEnviados.isSelected()) {
            resultado = tieneEmail
                    ? controlador.getPedidosEnviadosPorCliente(email)
                    : controlador.getPedidosEnviados();
        } else {
            resultado = tieneEmail
                    ? filtrarPedidosPorCliente(controlador.getPedidos(), email)
                    : controlador.getPedidos();
        }

        tabla.setItems(FXCollections.observableArrayList(resultado));
    }

    private ArrayList<Pedido> filtrarPedidosPorCliente(ArrayList<Pedido> pedidos, String email) {
        ArrayList<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getCliente() != null && p.getCliente().getEmailCliente().equalsIgnoreCase(email)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    private void mostrarDialogoAnadirPedido() {
        Stage dialog = new Stage();
        dialog.setTitle("Añadir Pedido");
        dialog.initModality(Modality.APPLICATION_MODAL);

        // ComboBoxes con los datos existentes
        ComboBox<Cliente>  cbCliente  = new ComboBox<>(FXCollections.observableArrayList(controlador.getClientes()));
        ComboBox<Articulo> cbArticulo = new ComboBox<>(FXCollections.observableArrayList(controlador.getArticulos()));

        // Mostrar email en el combo de clientes
        cbCliente.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Cliente c, boolean empty) {
                super.updateItem(c, empty);
                setText(empty || c == null ? null : c.getEmailCliente() + " (" + c.getNombreCliente() + ")");
            }
        });
        cbCliente.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Cliente c, boolean empty) {
                super.updateItem(c, empty);
                setText(empty || c == null ? null : c.getEmailCliente() + " (" + c.getNombreCliente() + ")");
            }
        });

        // Mostrar descripción en el combo de artículos
        cbArticulo.setCellFactory(lv -> new ListCell<>() {
            @Override protected void updateItem(Articulo a, boolean empty) {
                super.updateItem(a, empty);
                setText(empty || a == null ? null : a.getCodigoArticulo() + " - " + a.getDescripcionArticulo());
            }
        });
        cbArticulo.setButtonCell(new ListCell<>() {
            @Override protected void updateItem(Articulo a, boolean empty) {
                super.updateItem(a, empty);
                setText(empty || a == null ? null : a.getCodigoArticulo() + " - " + a.getDescripcionArticulo());
            }
        });

        cbCliente.setPrefWidth(300);
        cbArticulo.setPrefWidth(300);

        TextField tfCantidad = new TextField(); tfCantidad.setPromptText("Cantidad");

        Button btnGuardar  = new Button("Guardar");
        Button btnCancelar = new Button("Cancelar");

        btnCancelar.setOnAction(e -> dialog.close());
        btnGuardar.setOnAction(e -> {
            Cliente  cliente  = cbCliente.getValue();
            Articulo articulo = cbArticulo.getValue();

            if (cliente == null || articulo == null) {
                mostrarAlerta("Selecciona un cliente y un artículo.", Alert.AlertType.WARNING);
                return;
            }

            try {
                int cantidad = Integer.parseInt(tfCantidad.getText().trim());
                if (cantidad <= 0) {
                    mostrarAlerta("La cantidad debe ser mayor que 0.", Alert.AlertType.WARNING);
                    return;
                }

                Pedido pedido = new Pedido(0, cliente, articulo, cantidad, LocalDateTime.now());
                controlador.anadirPedido(pedido);
                mostrarAlerta("Pedido añadido correctamente.", Alert.AlertType.INFORMATION);
                dialog.close();
            } catch (NumberFormatException ex) {
                mostrarAlerta("La cantidad debe ser un número entero.", Alert.AlertType.ERROR);
            } catch (Exception ex) {
                mostrarAlerta("Error al añadir pedido: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        HBox botonesDialog = new HBox(10, btnGuardar, btnCancelar);
        botonesDialog.setAlignment(Pos.CENTER_RIGHT);

        GridPane grid = new GridPane();
        grid.setVgap(10); grid.setHgap(10); grid.setPadding(new Insets(20));
        grid.addRow(0, new Label("Cliente:"),  cbCliente);
        grid.addRow(1, new Label("Artículo:"), cbArticulo);
        grid.addRow(2, new Label("Cantidad:"), tfCantidad);
        grid.add(botonesDialog, 0, 3, 2, 1);

        dialog.setScene(new Scene(grid, 430, 200));
        dialog.showAndWait();
    }

    private void mostrarDialogoEliminarPedido(int numeroPedido) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Eliminar el pedido nº " + numeroPedido + "?",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirmar eliminación");
        confirm.showAndWait().ifPresent(resp -> {
            if (resp == ButtonType.YES) {
                try {
                    controlador.eliminarPedido(numeroPedido);
                    mostrarAlerta("Pedido eliminado correctamente.", Alert.AlertType.INFORMATION);
                } catch (PedidoYaEnviadoExcepcion ex) {
                    mostrarAlerta("No se puede eliminar: el pedido ya fue enviado.", Alert.AlertType.ERROR);
                } catch (PedidoNoEncontradoExcepcion ex) {
                    mostrarAlerta("Error: pedido no encontrado.", Alert.AlertType.ERROR);
                }
            }
        });
    }


    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo, mensaje, ButtonType.OK);
        alert.showAndWait();
    }
}