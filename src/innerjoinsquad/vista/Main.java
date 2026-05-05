package innerjoinsquad.vista;

public class Main {
    public static void main(String[] args) {
        innerjoinsquad.vista.Vista vista = new innerjoinsquad.vista.Vista();
        int opcion = -1;

        vista.mostrarMensajeInicio();

        while (opcion != 0) {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();

            if (opcion == 1) {
                vista.anadirClienteVista();
            } else if (opcion == 2) {
                vista.mostrarClientesVista();
            } else if (opcion == 3) {
                vista.eliminarClienteVista();
            } else if (opcion == 4) {
                vista.anadirArticuloVista();
            } else if (opcion == 5) {
                vista.mostrarArticulosVista();
            } else if (opcion == 6) {
                vista.eliminarArticuloVista();
            } else if (opcion == 7) {
                vista.anadirPedidoVista();
            } else if (opcion == 8) {
                vista.mostrarPedidosVista();
            } else if (opcion == 9) {
                vista.eliminarPedidoVista();
            } else if (opcion == 10) {
                vista.mostrarPedidosPendientesVista();
            } else if (opcion == 11) {
                vista.mostrarPedidosEnviadosVista();
            } else if (opcion == 12) {
                vista.mostrarPedidosPendientesPorClienteVista();
            } else if (opcion == 13) {
                vista.mostrarPedidosEnviadosPorClienteVista();
            } else if (opcion == 0) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opcion no valida.");
            }
        }
    }
}
