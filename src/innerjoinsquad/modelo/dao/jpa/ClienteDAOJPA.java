package innerjoinsquad.modelo.dao.jpa;

import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.dao.ClienteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ClienteDAOJPA implements ClienteDAO {

    // EntityManagerFactory gestiona la conexión con la BD
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineStorePU");

    @Override
    public void insertarCliente(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); // iniciamos la transacción
            em.persist(cliente); // guardamos el cliente en la BD
            em.getTransaction().commit(); // confirmamos los cambios
        } catch (Exception e) {
            em.getTransaction().rollback(); // si falla, deshacemos los cambios
            System.out.println("Error al insertar cliente: " + e.getMessage());
        } finally {
            em.close(); // cerramos el EntityManager
        }
    }

    @Override
    public Cliente obtenerClientePorEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            // em.find busca un cliente por su clave primaria (email)
            return em.find(Cliente.class, email);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        EntityManager em = emf.createEntityManager();
        try {
            // JPQL: consulta todos los clientes de la BD
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } finally {
            em.close();
        }
    }
}