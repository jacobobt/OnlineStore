package innerjoinsquad.modelo.dao.jpa;

import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.PedidoDAO;
import innerjoinsquad.modelo.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOJPA implements PedidoDAO {

    @Override
    public void insertarPedido(Pedido pedido) throws SQLException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();

            tx.begin();
            em.persist(pedido);
            tx.commit();

            System.out.println("Pedido insertado correctamente con JPA.");

        } catch (PersistenceException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new SQLException("Error al insertar pedido con JPA.", e);

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Pedido obtenerPedidoPorNumero(int numeroPedido) throws SQLException {
        EntityManager em = null;

        try {
            em = JPAUtil.getEntityManager();
            return em.find(Pedido.class, numeroPedido);

        } catch (PersistenceException e) {
            throw new SQLException("Error al obtener pedido por número con JPA.", e);

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Pedido> listarPedidos() throws SQLException {
        EntityManager em = null;

        try {
            em = JPAUtil.getEntityManager();

            TypedQuery<Pedido> query =
                    em.createQuery("SELECT p FROM Pedido p ORDER BY p.numeroPedido", Pedido.class);

            return query.getResultList();

        } catch (PersistenceException e) {
            throw new SQLException("Error al listar pedidos con JPA.", e);

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarPedido(int numeroPedido) throws SQLException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();

            tx.begin();

            Pedido pedido = em.find(Pedido.class, numeroPedido);
            if (pedido != null) {
                em.remove(pedido);
            }

            tx.commit();

            System.out.println("Pedido eliminado correctamente con JPA.");

        } catch (PersistenceException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new SQLException("Error al eliminar pedido con JPA.", e);

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Pedido> listarPedidosPendientes() throws SQLException {
        List<Pedido> todosLosPedidos = listarPedidos();
        List<Pedido> pedidosPendientes = new ArrayList<>();

        for (Pedido pedido : todosLosPedidos) {
            if (!pedido.estaEnviado()) {
                pedidosPendientes.add(pedido);
            }
        }

        return pedidosPendientes;
    }

    @Override
    public List<Pedido> listarPedidosEnviados() throws SQLException {
        List<Pedido> todosLosPedidos = listarPedidos();
        List<Pedido> pedidosEnviados = new ArrayList<>();

        for (Pedido pedido : todosLosPedidos) {
            if (pedido.estaEnviado()) {
                pedidosEnviados.add(pedido);
            }
        }

        return pedidosEnviados;
    }

    @Override
    public List<Pedido> listarPedidosPendientesPorCliente(String emailCliente) throws SQLException {
        List<Pedido> pedidosPendientes = listarPedidosPendientes();
        List<Pedido> pedidosPendientesCliente = new ArrayList<>();

        for (Pedido pedido : pedidosPendientes) {
            if (pedido.getCliente().getEmailCliente().equalsIgnoreCase(emailCliente)) {
                pedidosPendientesCliente.add(pedido);
            }
        }

        return pedidosPendientesCliente;
    }

    @Override
    public List<Pedido> listarPedidosEnviadosPorCliente(String emailCliente) throws SQLException {
        List<Pedido> pedidosEnviados = listarPedidosEnviados();
        List<Pedido> pedidosEnviadosCliente = new ArrayList<>();

        for (Pedido pedido : pedidosEnviados) {
            if (pedido.getCliente().getEmailCliente().equalsIgnoreCase(emailCliente)) {
                pedidosEnviadosCliente.add(pedido);
            }
        }

        return pedidosEnviadosCliente;
    }
}