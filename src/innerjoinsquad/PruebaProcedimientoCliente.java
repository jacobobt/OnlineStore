package innerjoinsquad;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.ClienteEstandar;
import innerjoinsquad.modelo.dao.mysql.ClienteDAOMySQL;

import java.sql.SQLException;

public class PruebaProcedimientoCliente {

    public static void main(String[] args) {

        try {
            Cliente cliente = new ClienteEstandar(
                    "Ana López",
                    "Calle Nueva 45",
                    "98765432B",
                    "ana@email.com"
            );

            ClienteDAOMySQL clienteDAO = new ClienteDAOMySQL();

            clienteDAO.insertarClienteConProcedimiento(cliente);

            System.out.println("Cliente insertado usando procedimiento almacenado.");

        } catch (SQLException e) {
            System.out.println("Error al ejecutar el procedimiento.");
            System.out.println(e.getMessage());
        }
    }
}