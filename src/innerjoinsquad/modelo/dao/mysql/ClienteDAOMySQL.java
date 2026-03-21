package innerjoinsquad.modelo.dao.mysql;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.dao.ClienteDAO;
import innerjoinsquad.modelo.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAOMySQL implements ClienteDAO {

    @Override
    public void insertarCliente(Cliente cliente) throws SQLException {

        String sql = "INSERT INTO clientes (email_cliente, nombre_cliente, domicilio_cliente, nif_cliente, tipo_cliente) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getEmailCliente());
            ps.setString(2, cliente.getNombreCliente());
            ps.setString(3, cliente.getDomicilioCliente());
            ps.setString(4, cliente.getNifCliente());

            // tipo cliente (simple)
            if (cliente instanceof innerjoinsquad.modelo.ClientePremium) {
                ps.setString(5, "PREMIUM");
            } else {
                ps.setString(5, "ESTANDAR");
            }

            ps.executeUpdate();
        }
    }

    @Override
    public Cliente obtenerClientePorEmail(String email) throws SQLException {
        return null;
    }

    @Override
    public List<Cliente> listarClientes() throws SQLException {
        return null;
    }
}