package innerjoinsquad.modelo.factory;

import innerjoinsquad.modelo.dao.ArticuloDAO;
import innerjoinsquad.modelo.dao.ClienteDAO;
import innerjoinsquad.modelo.dao.PedidoDAO;
import innerjoinsquad.modelo.dao.jpa.ArticuloDAOJPA;
import innerjoinsquad.modelo.dao.jpa.ClienteDAOJPA;
import innerjoinsquad.modelo.dao.jpa.PedidoDAOJPA;

public class JPADAOFactory extends DAOFactory {

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAOJPA();
    }

    @Override
    public ArticuloDAO getArticuloDAO() {
        return new ArticuloDAOJPA();
    }

    @Override
    public PedidoDAO getPedidoDAO() {
        return new PedidoDAOJPA();
    }
}
