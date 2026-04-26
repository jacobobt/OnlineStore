package innerjoinsquad;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.ClienteEstandar;
import innerjoinsquad.modelo.dao.jpa.ClienteDAOJPA;

public class PruebaInsertarClienteJPA {

    public static void main(String[] args) {

        // 1. Crear cliente
        Cliente cliente = new ClienteEstandar(
                "Juan Pérez",
                "Calle Falsa 123",
                "12345678A",
                "juan@email.com"
        );

        // 2. Crear DAO
        ClienteDAOJPA clienteDAO = new ClienteDAOJPA();

        // 3. Insertar en BD
        clienteDAO.insertarCliente(cliente);

        System.out.println("Cliente insertado correctamente en la base de datos.");
    }
}