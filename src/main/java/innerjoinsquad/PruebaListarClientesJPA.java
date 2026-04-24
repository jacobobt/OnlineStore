package innerjoinsquad;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.dao.jpa.ClienteDAOJPA;
import java.util.List;

public class PruebaListarClientesJPA {
    public static void main(String[] args) {
        try {
            ClienteDAOJPA dao = new ClienteDAOJPA();
            List<Cliente> clientes = dao.listarClientes();

            if (clientes.isEmpty()) {
                System.out.println("No hay clientes en la base de datos.");
            } else {
                System.out.println("Listado de clientes:");
                for (Cliente cliente : clientes) {
                    System.out.println(cliente.getClass().getSimpleName() + " -> " + cliente);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
