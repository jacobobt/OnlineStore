package innerjoinsquad.modelo.dao.mysql;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.ClienteEstandar;
import innerjoinsquad.modelo.ClientePremium;
import innerjoinsquad.modelo.dao.ClienteDAO;
import innerjoinsquad.modelo.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;

public class ClienteDAOMySQL implements ClienteDAO {
    //Transacción
    @Override
    public void insertarCliente(Cliente cliente) throws SQLException {
        Connection conexion = null;

        try {
            // Abrimos la conexión
            conexion = ConexionBD.getConexion();

            // Desactivamos autocommit e iniciamos transacción
            conexion.setAutoCommit(false);

            // Preparar y ejecutar INSERT
            String sql = "INSERT INTO clientes (email_cliente, nombre_cliente, domicilio_cliente, nif_cliente, tipo_cliente) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, cliente.getEmailCliente());
                ps.setString(2, cliente.getNombreCliente());
                ps.setString(3, cliente.getDomicilioCliente());
                ps.setString(4, cliente.getNifCliente());
                ps.setString(5, (cliente instanceof ClientePremium) ? "PREMIUM" : "ESTANDAR");

                ps.executeUpdate();
            }

            // Si funciona commit
            conexion.commit();
            System.out.println("Cliente insertado correctamente con transacción.");

        } catch (SQLException e) {
            // Si falla algo rollback
            if (conexion != null) {
                conexion.rollback();
                System.out.println("Error al insertar cliente: ROLLBACK ejecutado");
            }
            throw e;

        } finally {
            // Volvemos a activar autocommit y cerramos conexión
            if (conexion != null) {
                conexion.setAutoCommit(true);
                conexion.close();
            }
        }
    }
    @Override
    public Cliente obtenerClientePorEmail(String email) throws SQLException {

        String sql = "SELECT * FROM clientes WHERE email_cliente = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String emailCliente = rs.getString("email_cliente");
                String nombreCliente = rs.getString("nombre_cliente");
                String domicilioCliente = rs.getString("domicilio_cliente");
                String nifCliente = rs.getString("nif_cliente");
                String tipoCliente = rs.getString("tipo_cliente");

                if (tipoCliente.equalsIgnoreCase("PREMIUM")) {
                    return new ClientePremium(
                            nombreCliente,
                            domicilioCliente,
                            nifCliente,
                            emailCliente
                    );
                } else {
                    return new ClienteEstandar(
                            nombreCliente,
                            domicilioCliente,
                            nifCliente,
                            emailCliente
                    );
                }
            }
        }

        return null;
    }

    @Override
    public List<Cliente> listarClientes() throws SQLException {

        List<Cliente> listaClientes = new java.util.ArrayList<>();

        String sql = "SELECT * FROM clientes";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String emailCliente = rs.getString("email_cliente");
                String nombreCliente = rs.getString("nombre_cliente");
                String domicilioCliente = rs.getString("domicilio_cliente");
                String nifCliente = rs.getString("nif_cliente");
                String tipoCliente = rs.getString("tipo_cliente");

                Cliente cliente;

                if (tipoCliente.equalsIgnoreCase("PREMIUM")) {
                    cliente = new ClientePremium(
                            nombreCliente,
                            domicilioCliente,
                            nifCliente,
                            emailCliente
                    );
                } else {
                    cliente = new ClienteEstandar(
                            nombreCliente,
                            domicilioCliente,
                            nifCliente,
                            emailCliente
                    );
                }

                listaClientes.add(cliente);
            }
        }

        return listaClientes;
    }
    public void insertarClienteConProcedimiento(Cliente cliente) throws SQLException {

        String sql = "{CALL insertar_cliente_proc(?, ?, ?, ?, ?)}";

        try (Connection conexion = ConexionBD.getConexion();
             java.sql.CallableStatement cs = conexion.prepareCall(sql)) {

            cs.setString(1, cliente.getEmailCliente());
            cs.setString(2, cliente.getNombreCliente());
            cs.setString(3, cliente.getDomicilioCliente());
            cs.setString(4, cliente.getNifCliente());

            if (cliente instanceof ClientePremium) {
                cs.setString(5, "PREMIUM");
            } else {
                cs.setString(5, "ESTANDAR");
            }

            cs.execute();
        }
    }
}