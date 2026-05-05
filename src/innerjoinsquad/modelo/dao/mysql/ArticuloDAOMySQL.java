package innerjoinsquad.modelo.dao.mysql;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.ArticuloDAO;
import innerjoinsquad.modelo.util.ConexionBD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArticuloDAOMySQL implements ArticuloDAO {

    @Override
    public void insertarArticulo(Articulo articulo) throws SQLException {

        String sql = "INSERT INTO articulos (codigo_articulo, descripcion, precio_venta, gastos_envio, tiempo_preparacion_min) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, articulo.getCodigoArticulo());
            ps.setString(2, articulo.getDescripcionArticulo());
            ps.setBigDecimal(3, articulo.getPrecioVenta());
            ps.setBigDecimal(4, articulo.getGastosEnvio());
            ps.setInt(5, articulo.getTiempoPreparacionMin());

            ps.executeUpdate();
        }
    }

    @Override
    public Articulo obtenerArticuloPorCodigo(String codigo) throws SQLException {

        String sql = "SELECT * FROM articulos WHERE codigo_articulo = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, codigo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String codigoArticulo = rs.getString("codigo_articulo");
                String descripcionArticulo = rs.getString("descripcion");
                BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
                BigDecimal gastosEnvio = rs.getBigDecimal("gastos_envio");
                int tiempoPreparacionMin = rs.getInt("tiempo_preparacion_min");

                return new Articulo(
                        codigoArticulo,
                        descripcionArticulo,
                        precioVenta,
                        gastosEnvio,
                        tiempoPreparacionMin
                );
            }
        }

        return null;
    }

    @Override
    public List<Articulo> listarArticulos() throws SQLException {

        List<Articulo> listaArticulos = new java.util.ArrayList<>();

        String sql = "SELECT * FROM articulos";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String codigoArticulo = rs.getString("codigo_articulo");
                String descripcionArticulo = rs.getString("descripcion");
                BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
                BigDecimal gastosEnvio = rs.getBigDecimal("gastos_envio");
                int tiempoPreparacionMin = rs.getInt("tiempo_preparacion_min");

                Articulo articulo = new Articulo(
                        codigoArticulo,
                        descripcionArticulo,
                        precioVenta,
                        gastosEnvio,
                        tiempoPreparacionMin
                );

                listaArticulos.add(articulo);
            }
        }

        return listaArticulos;
    }

    @Override
    public void eliminarArticulo(String codigo) throws SQLException {
        String sql = "DELETE FROM articulos WHERE codigo_articulo = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, codigo);
            ps.executeUpdate();
        }
    }
}
