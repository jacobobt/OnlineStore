package innerjoinsquad;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.dao.jpa.ClienteDAOJPA;

import java.util.List;

public class PruebaListarClientesJPA {

    public static void main(String[] args) {

        ClienteDAOJPA clienteDAO = new ClienteDAOJPA();

        List<Cliente> clientes = clienteDAO.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes en la base de datos.");
        } else {
            System.out.println("Lista de clientes:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }
}