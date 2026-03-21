package innerjoinsquad.modelo.dao;

import innerjoinsquad.modelo.Cliente;
import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

    void insertarCliente(Cliente cliente) throws SQLException;

    Cliente obtenerClientePorEmail(String email) throws SQLException;

    List<Cliente> listarClientes() throws SQLException;
}