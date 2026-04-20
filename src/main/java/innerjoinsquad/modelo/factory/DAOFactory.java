package innerjoinsquad.modelo.factory;

import innerjoinsquad.modelo.dao.ArticuloDAO;
import innerjoinsquad.modelo.dao.ClienteDAO;
import innerjoinsquad.modelo.dao.PedidoDAO;

public abstract class DAOFactory {

    public abstract ClienteDAO getClienteDAO();
    public abstract ArticuloDAO getArticuloDAO();
    public abstract PedidoDAO getPedidoDAO();
}