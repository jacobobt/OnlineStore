package innerjoinsquad.modelo.dao.jpa;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.dao.ArticuloDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ArticuloDAOJPA implements ArticuloDAO {

    // EntityManagerFactory es el equivalente a ConexionBD, gestiona la conexión con la BD
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineStorePU");

    @Override
    public void insertarArticulo(Articulo articulo) {
        // EntityManager es el objeto que realiza las operaciones en la BD
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); // iniciamos la transacción
            em.persist(articulo); // guardamos el artículo en la BD
            em.getTransaction().commit(); // confirmamos los cambios
        } catch (Exception e) {
            em.getTransaction().rollback(); // si falla, deshacemos los cambios
            System.out.println("Error al insertar artículo: " + e.getMessage());
        } finally {
            em.close(); // cerramos el EntityManager
        }
    }

    @Override
    public Articulo obtenerArticuloPorCodigo(String codigo) {
        EntityManager em = emf.createEntityManager();
        try {
            // em.find busca un artículo por su clave primaria (codigo)
            return em.find(Articulo.class, codigo);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Articulo> listarArticulos() {
        EntityManager em = emf.createEntityManager();
        try {
            // JPQL: lenguaje de consultas de JPA, similar a SQL pero usando clases Java
            return em.createQuery("SELECT a FROM Articulo a", Articulo.class).getResultList();
        } finally {
            em.close();
        }
    }
}