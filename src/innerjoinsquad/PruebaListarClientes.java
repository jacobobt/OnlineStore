package innerjoinsquad;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.dao.mysql.ClienteDAOMySQL;

import java.sql.SQLException;
import java.util.List;

public class PruebaListarClientes {

    public static void main(String[] args) {

        try {
            ClienteDAOMySQL clienteDAO = new ClienteDAOMySQL();

            List<Cliente> clientes = clienteDAO.listarClientes();

            if (clientes.isEmpty()) {
                System.out.println("No hay clientes en la base de datos.");
            } else {
                System.out.println("Lista de clientes:");
                for (Cliente cliente : clientes) {
                    System.out.println(cliente);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes.");
            System.out.println(e.getMessage());
        }
    }
}