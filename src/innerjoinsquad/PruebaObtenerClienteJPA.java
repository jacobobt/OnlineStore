package innerjoinsquad;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.dao.jpa.ClienteDAOJPA;

public class PruebaObtenerClienteJPA {

    public static void main(String[] args) {

        ClienteDAOJPA clienteDAO = new ClienteDAOJPA();

        Cliente cliente = clienteDAO.obtenerClientePorEmail("jo@girona.com");

        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("No se ha encontrado ningún cliente con ese email.");
        }
    }
}