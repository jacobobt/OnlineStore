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
        // devuelve la implementación JPA del DAO de Cliente
        return new ClienteDAOJPA();
    }

    @Override
    public ArticuloDAO getArticuloDAO() {
        // devuelve la implementación JPA del DAO de Articulo
        return new ArticuloDAOJPA();
    }

    @Override
    public PedidoDAO getPedidoDAO() {
        // devuelve la implementación JPA del DAO de Pedido
        return new PedidoDAOJPA();
    }
}