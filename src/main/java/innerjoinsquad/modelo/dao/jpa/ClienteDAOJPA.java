package innerjoinsquad.modelo.dao.jpa;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.dao.ClienteDAO;
import innerjoinsquad.modelo.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAOJPA implements ClienteDAO {

    @Override
    public void insertarCliente(Cliente cliente) throws SQLException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new SQLException("Error al insertar cliente con JPA", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente obtenerClientePorEmail(String email) throws SQLException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            return em.find(Cliente.class, email);
        } catch (Exception e) {
            throw new SQLException("Error al obtener cliente con JPA", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cliente> listarClientes() throws SQLException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } catch (Exception e) {
            throw new SQLException("Error al listar clientes con JPA", e);
        } finally {
            em.close();
        }
    }
}
