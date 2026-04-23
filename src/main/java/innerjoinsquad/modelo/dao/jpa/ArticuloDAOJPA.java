package innerjoinsquad.modelo.dao.jpa;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.ArticuloDAO;
import innerjoinsquad.modelo.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class ArticuloDAOJPA implements ArticuloDAO {

    @Override
    public void insertarArticulo(Articulo articulo) throws SQLException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(articulo);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new SQLException("Error al insertar artículo con JPA", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Articulo obtenerArticuloPorCodigo(String codigo) throws SQLException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            return em.find(Articulo.class, codigo);
        } catch (Exception e) {
            throw new SQLException("Error al obtener artículo con JPA", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Articulo> listarArticulos() throws SQLException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            return em.createQuery("SELECT a FROM Articulo a", Articulo.class).getResultList();
        } catch (Exception e) {
            throw new SQLException("Error al listar artículos con JPA", e);
        } finally {
            em.close();
        }
    }
}

