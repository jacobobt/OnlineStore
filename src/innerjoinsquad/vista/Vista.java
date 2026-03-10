package innerjoinsquad.vista;

import innerjoinsquad.controlador.Controlador;
import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.ClienteEstandar;
import innerjoinsquad.modelo.ClientePremium;
import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.Pedido;
import java.util.Scanner;

public class Vista {

    private Controlador controlador;
    private Scanner teclado;

    public Vista() {
        this.controlador = new Controlador();
        this.teclado = new Scanner(System.in);
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void mostrarMensajeInicio() {
        System.out.println("Aplicacion Online Store iniciada correctamente.");
    }

    public void mostrarMenu() {
        System.out.println("----- MENU -----");
        System.out.println("1. Añadir cliente");
        System.out.println("2. Mostrar clientes");
        System.out.println("3. Añadir articulo");
        System.out.println("4. Mostrar articulos");
        System.out.println("5. Añadir pedido");
        System.out.println("6. Mostrar pedidos");
        System.out.println("7. Eliminar pedido");
        System.out.println("8. Mostrar pedidos pendientes");
        System.out.println("9. Mostrar pedidos enviados");
        System.out.println("10. Mostrar pedidos pendientes por cliente");
        System.out.println("11. Mostrar pedidos enviados por cliente");
        System.out.println("0. Salir");
    }

    public int leerOpcion() {
        System.out.print("Elige una opcion: ");
        return teclado.nextInt();
    }

    public void anadirClienteVista() {
        teclado.nextLine();

        System.out.print("Introduce el nombre del cliente: ");
        String nombre = teclado.nextLine();

        System.out.print("Introduce el domicilio del cliente: ");
        String domicilio = teclado.nextLine();

        System.out.print("Introduce el NIF del cliente: ");
        String nif = teclado.nextLine();

        System.out.print("Introduce el email del cliente: ");
        String email = teclado.nextLine();

        System.out.println("Tipo de cliente:");
        System.out.println("1. Cliente estandar");
        System.out.println("2. Cliente premium");
        System.out.print("Elige una opcion: ");
        int tipoCliente = teclado.nextInt();

        Cliente cliente;

        if (tipoCliente == 1) {
            cliente = new ClienteEstandar(nombre, domicilio, nif, email);
        } else {
            cliente = new ClientePremium(nombre, domicilio, nif, email);
        }

        controlador.anadirCliente(cliente);
        System.out.println("Cliente anadido correctamente.");
    }

    public void mostrarClientesVista() {
        if (controlador.getClientes().isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("----- LISTADO DE CLIENTES -----");
            for (Cliente cliente : controlador.getClientes()) {
                System.out.println(cliente);
            }
        }
    }

    public void anadirArticuloVista() {
        teclado.nextLine();

        System.out.print("Introduce el codigo del articulo: ");
        String codigo = teclado.nextLine();

        System.out.print("Introduce la descripcion del articulo: ");
        String descripcion = teclado.nextLine();

        System.out.print("Introduce el precio de venta: ");
        double precioVenta = teclado.nextDouble();

        System.out.print("Introduce los gastos de envio: ");
        double gastosEnvio = teclado.nextDouble();

        System.out.print("Introduce el tiempo de preparacion en minutos: ");
        int tiempoPreparacionMin = teclado.nextInt();

        Articulo articulo = new Articulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacionMin);

        controlador.anadirArticulo(articulo);
        System.out.println("Articulo anadido correctamente.");
    }

    public void mostrarArticulosVista() {
        if (controlador.getArticulos().isEmpty()) {
            System.out.println("No hay articulos registrados.");
        } else {
            System.out.println("----- LISTADO DE ARTICULOS -----");
            for (Articulo articulo : controlador.getArticulos()) {
                System.out.println(articulo);
            }
        }
    }
    public void anadirPedidoVista() {
        teclado.nextLine();

        System.out.print("Introduce el numero del pedido: ");
        int numeroPedido = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Introduce el email del cliente: ");
        String emailCliente = teclado.nextLine();

        Cliente cliente = controlador.buscarClientePorEmail(emailCliente);

        if (cliente == null) {
            System.out.println("El cliente no existe. Se procedera a darlo de alta.");

            System.out.print("Introduce el nombre del cliente: ");
            String nombre = teclado.nextLine();

            System.out.print("Introduce el domicilio del cliente: ");
            String domicilio = teclado.nextLine();

            System.out.print("Introduce el NIF del cliente: ");
            String nif = teclado.nextLine();

            System.out.println("Tipo de cliente:");
            System.out.println("1. Cliente estandar");
            System.out.println("2. Cliente premium");
            System.out.print("Elige una opcion: ");
            int tipoCliente = teclado.nextInt();
            teclado.nextLine();

            if (tipoCliente == 1) {
                cliente = new ClienteEstandar(nombre, domicilio, nif, emailCliente);
            } else {
                cliente = new ClientePremium(nombre, domicilio, nif, emailCliente);
            }

            controlador.anadirCliente(cliente);
            System.out.println("Cliente anadido correctamente.");
        }

        System.out.print("Introduce el codigo del articulo: ");
        String codigoArticulo = teclado.nextLine();

        Articulo articulo = controlador.buscarArticuloPorCodigo(codigoArticulo);

        if (articulo == null) {
            System.out.println("Error: el articulo no existe.");
            return;
        }

        System.out.print("Introduce la cantidad: ");
        int cantidad = teclado.nextInt();

        Pedido pedido = new Pedido(
                numeroPedido,
                cliente,
                articulo,
                cantidad,
                java.time.LocalDateTime.now()
        );

        controlador.anadirPedido(pedido);
        System.out.println("Pedido anadido correctamente.");
    }
    public void mostrarPedidosVista() {
        if (controlador.getPedidos().isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            System.out.println("----- LISTADO DE PEDIDOS -----");
            for (Pedido pedido : controlador.getPedidos()) {
                System.out.println(pedido);
            }
        }
    }
    public void eliminarPedidoVista() {
        System.out.print("Introduce el numero del pedido que quieres eliminar: ");
        int numeroPedido = teclado.nextInt();

        Pedido pedido = controlador.buscarPedidoPorNumero(numeroPedido);

        if (pedido == null) {
            System.out.println("Error: no existe un pedido con ese numero.");
        } else {
            boolean eliminado = controlador.eliminarPedido(numeroPedido);

            if (eliminado) {
                System.out.println("Pedido eliminado correctamente.");
            } else {
                System.out.println("No se puede eliminar el pedido porque ya ha sido enviado.");
            }
        }
    }
    public void mostrarPedidosPendientesVista() {
        if (controlador.getPedidosPendientes().isEmpty()) {
            System.out.println("No hay pedidos pendientes.");
        } else {
            System.out.println("----- PEDIDOS PENDIENTES -----");
            for (Pedido pedido : controlador.getPedidosPendientes()) {
                System.out.println(pedido);
            }
        }
    }
    public void mostrarPedidosEnviadosVista() {
        if (controlador.getPedidosEnviados().isEmpty()) {
            System.out.println("No hay pedidos enviados.");
        } else {
            System.out.println("----- PEDIDOS ENVIADOS -----");
            for (Pedido pedido : controlador.getPedidosEnviados()) {
                System.out.println(pedido);
            }
        }
    }
    public void mostrarPedidosPendientesPorClienteVista() {
        teclado.nextLine();

        System.out.print("Introduce el email del cliente: ");
        String email = teclado.nextLine();

        if (controlador.getPedidosPendientesPorCliente(email).isEmpty()) {
            System.out.println("No hay pedidos pendientes para ese cliente.");
        } else {
            System.out.println("----- PEDIDOS PENDIENTES DEL CLIENTE -----");
            for (Pedido pedido : controlador.getPedidosPendientesPorCliente(email)) {
                System.out.println(pedido);
            }
        }
    }
    public void mostrarPedidosEnviadosPorClienteVista() {
        teclado.nextLine();

        System.out.print("Introduce el email del cliente: ");
        String email = teclado.nextLine();

        if (controlador.getPedidosEnviadosPorCliente(email).isEmpty()) {
            System.out.println("No hay pedidos enviados para ese cliente.");
        } else {
            System.out.println("----- PEDIDOS ENVIADOS DEL CLIENTE -----");
            for (Pedido pedido : controlador.getPedidosEnviadosPorCliente(email)) {
                System.out.println(pedido);
            }
        }
    }

}