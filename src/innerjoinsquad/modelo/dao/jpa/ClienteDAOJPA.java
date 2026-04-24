package innerjoinsquad.modelo.dao.jpa;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.dao.ClienteDAO;
import innerjoinsquad.modelo.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAOJPA implements ClienteDAO {

    @Override
    public void insertarCliente(Cliente cliente) throws SQLException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();

            tx.begin();
            em.persist(cliente);
            tx.commit();

            System.out.println("Cliente insertado correctamente con JPA.");

        } catch (PersistenceException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new SQLException("Error al insertar cliente con JPA.", e);

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Cliente obtenerClientePorEmail(String email) throws SQLException {
        EntityManager em = null;

        try {
            em = JPAUtil.getEntityManager();
            return em.find(Cliente.class, email);

        } catch (PersistenceException e) {
            throw new SQLException("Error al obtener cliente por email con JPA.", e);

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Cliente> listarClientes() throws SQLException {
        EntityManager em = null;

        try {
            em = JPAUtil.getEntityManager();

            TypedQuery<Cliente> query =
                    em.createQuery("SELECT c FROM Cliente c", Cliente.class);

            return query.getResultList();

        } catch (PersistenceException e) {
            throw new SQLException("Error al listar clientes con JPA.", e);

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}