package innerjoinsquad.modelo.dao;

import innerjoinsquad.modelo.Articulo;

import java.sql.SQLException;
import java.util.List;

public interface ArticuloDAO {

    void insertarArticulo(Articulo articulo) throws SQLException;

    Articulo obtenerArticuloPorCodigo(String codigo) throws SQLException;

    List<Articulo> listarArticulos() throws SQLException;
}