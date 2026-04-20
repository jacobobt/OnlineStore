package innerjoinsquad.modelo.factory;

import innerjoinsquad.modelo.dao.ArticuloDAO;
import innerjoinsquad.modelo.dao.ClienteDAO;
import innerjoinsquad.modelo.dao.PedidoDAO;
import innerjoinsquad.modelo.dao.mysql.ArticuloDAOMySQL;
import innerjoinsquad.modelo.dao.mysql.ClienteDAOMySQL;
import innerjoinsquad.modelo.dao.mysql.PedidoDAOMySQL;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAOMySQL();
    }

    @Override
    public ArticuloDAO getArticuloDAO() {
        return new ArticuloDAOMySQL();
    }

    @Override
    public PedidoDAO getPedidoDAO() {
        return new PedidoDAOMySQL();
    }
}