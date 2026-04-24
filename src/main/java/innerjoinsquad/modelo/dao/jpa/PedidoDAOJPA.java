package innerjoinsquad.modelo.dao.jpa;

import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.PedidoDAO;
import innerjoinsquad.modelo.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class PedidoDAOJPA implements PedidoDAO {

    @Override
    public void insertarPedido(Pedido pedido) throws SQLException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new SQLException("Error al insertar pedido con JPA", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Pedido obtenerPedidoPorNumero(int numeroPedido) throws SQLException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            return em.find(Pedido.class, numeroPedido);
        } catch (Exception e) {
            throw new SQLException("Error al obtener pedido con JPA", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> listarPedidos() throws SQLException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
        } catch (Exception e) {
            throw new SQLException("Error al listar pedidos con JPA", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarPedido(int numeroPedido) throws SQLException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, numeroPedido);

            if (pedido != null) {
                em.remove(pedido);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new SQLException("Error al eliminar pedido con JPA", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> listarPedidosPendientes() throws SQLException {
        return listarPedidos().stream()
                .filter(pedido -> !pedido.estaEnviado())
                .toList();
    }

    @Override
    public List<Pedido> listarPedidosEnviados() throws SQLException {
        return listarPedidos().stream()
                .filter(Pedido::estaEnviado)
                .toList();
    }

    @Override
    public List<Pedido> listarPedidosPendientesPorCliente(String emailCliente) throws SQLException {
        return listarPedidosPendientes().stream()
                .filter(pedido -> pedido.getCliente().getEmailCliente().equalsIgnoreCase(emailCliente))
                .toList();
    }

    @Override
    public List<Pedido> listarPedidosEnviadosPorCliente(String emailCliente) throws SQLException {
        return listarPedidosEnviados().stream()
                .filter(pedido -> pedido.getCliente().getEmailCliente().equalsIgnoreCase(emailCliente))
                .toList();
    }
}
