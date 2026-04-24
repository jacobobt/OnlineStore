package innerjoinsquad.modelo.dao.jpa;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.ArticuloDAO;
import innerjoinsquad.modelo.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class ArticuloDAOJPA implements ArticuloDAO {

    @Override
    public void insertarArticulo(Articulo articulo) throws SQLException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();

            tx.begin();
            em.persist(articulo);
            tx.commit();

            System.out.println("Artículo insertado correctamente con JPA.");

        } catch (PersistenceException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new SQLException("Error al insertar artículo con JPA.", e);

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Articulo obtenerArticuloPorCodigo(String codigo) throws SQLException {
        EntityManager em = null;

        try {
            em = JPAUtil.getEntityManager();
            return em.find(Articulo.class, codigo);

        } catch (PersistenceException e) {
            throw new SQLException("Error al obtener artículo por código con JPA.", e);

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Articulo> listarArticulos() throws SQLException {
        EntityManager em = null;

        try {
            em = JPAUtil.getEntityManager();

            TypedQuery<Articulo> query =
                    em.createQuery("SELECT a FROM Articulo a", Articulo.class);

            return query.getResultList();

        } catch (PersistenceException e) {
            throw new SQLException("Error al listar artículos con JPA.", e);

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}