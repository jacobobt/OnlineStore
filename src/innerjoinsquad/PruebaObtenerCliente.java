package innerjoinsquad;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.dao.mysql.ClienteDAOMySQL;

import java.sql.SQLException;

public class PruebaObtenerCliente {

    public static void main(String[] args) {

        try {
            ClienteDAOMySQL clienteDAO = new ClienteDAOMySQL();

            Cliente cliente = clienteDAO.obtenerClientePorEmail("juan@email.com");

            if (cliente != null) {
                System.out.println("Cliente encontrado:");
                System.out.println(cliente);
            } else {
                System.out.println("No se ha encontrado ningún cliente con ese email.");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener cliente.");
            System.out.println(e.getMessage());
        }
    }
}