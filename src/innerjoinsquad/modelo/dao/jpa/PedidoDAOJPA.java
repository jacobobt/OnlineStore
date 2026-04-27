package innerjoinsquad.modelo.dao.jpa;

import innerjoinsquad.modelo.Articulo;
import innerjoinsquad.modelo.Cliente;
import innerjoinsquad.modelo.Pedido;
import innerjoinsquad.modelo.dao.PedidoDAO;
import innerjoinsquad.modelo.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PedidoDAOJPA implements PedidoDAO {

    // EntityManagerFactory gestiona la conexión con la BD
    private EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

    @Override
    public void insertarPedido(Pedido pedido) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); // iniciamos la transacción
            // obtenemos referencias gestionadas por este EntityManager
            Cliente clienteGestionado = em.find(Cliente.class, pedido.getCliente().getEmailCliente());
            Articulo articuloGestionado = em.find(Articulo.class, pedido.getArticulo().getCodigoArticulo());
            // asignamos las referencias gestionadas al pedido
            pedido.setCliente(clienteGestionado);
            pedido.setArticulo(articuloGestionado);
            em.persist(pedido); // guardamos el pedido en la BD
            em.getTransaction().commit(); // confirmamos los cambios
        } catch (Exception e) {
            em.getTransaction().rollback(); // si falla, deshacemos los cambios
            System.out.println("Error al insertar el pedido: " + e.getMessage());
        } finally {
            em.close(); // cerramos el EntityManager
        }
    }

    @Override
    public Pedido obtenerPedidoPorNumero(int numeroPedido) {
        EntityManager em = emf.createEntityManager();
        try {
            // em.find busca un pedido por su clave primaria (numeroPedido)
            return em.find(Pedido.class, numeroPedido);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> listarPedidos() {
        EntityManager em = emf.createEntityManager();
        try {
            // JPQL: consulta todos los pedidos de la BD
            return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarPedido(int numeroPedido) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); // iniciamos la transacción
            Pedido pedido = em.find(Pedido.class, numeroPedido); // buscamos el pedido
            if (pedido != null) {
                em.remove(pedido); // eliminamos el pedido de la BD
            }
            em.getTransaction().commit(); // confirmamos los cambios
        } catch (Exception e) {
            em.getTransaction().rollback(); // si falla, deshacemos los cambios
            System.out.println("Error al eliminar el pedido: " + e.getMessage());
        } finally {
            em.close(); // cerramos el EntityManager
        }
    }

    @Override
    public List<Pedido> listarPedidosPendientes() {
        EntityManager em = emf.createEntityManager();
        try {
            // obtenemos todos los pedidos y filtramos los pendientes
            List<Pedido> todos = em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
            List<Pedido> pendientes = new java.util.ArrayList<>();
            for (Pedido p : todos) {
                if (!p.estaEnviado()) pendientes.add(p);
            }
            return pendientes;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> listarPedidosEnviados() {
        EntityManager em = emf.createEntityManager();
        try {
            // obtenemos todos los pedidos y filtramos los enviados
            List<Pedido> todos = em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
            List<Pedido> enviados = new java.util.ArrayList<>();
            for (Pedido p : todos) {
                if (p.estaEnviado()) enviados.add(p);
            }
            return enviados;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> listarPedidosPendientesPorCliente(String emailCliente) {
        EntityManager em = emf.createEntityManager();
        try {
            // JPQL: consulta los pedidos pendientes de un cliente concreto
            List<Pedido> todos = em.createQuery(
                            "SELECT p FROM Pedido p WHERE p.cliente.emailCliente = :email",
                            Pedido.class)
                    .setParameter("email", emailCliente)
                    .getResultList();
            List<Pedido> pendientes = new java.util.ArrayList<>();
            for (Pedido p : todos) {
                if (!p.estaEnviado()) pendientes.add(p);
            }
            return pendientes;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Pedido> listarPedidosEnviadosPorCliente(String emailCliente) {
        EntityManager em = emf.createEntityManager();
        try {
            // JPQL: consulta los pedidos enviados de un cliente concreto
            List<Pedido> todos = em.createQuery(
                            "SELECT p FROM Pedido p WHERE p.cliente.emailCliente = :email",
                            Pedido.class)
                    .setParameter("email", emailCliente)
                    .getResultList();
            List<Pedido> enviados = new java.util.ArrayList<>();
            for (Pedido p : todos) {
                if (p.estaEnviado()) enviados.add(p);
            }
            return enviados;
        } finally {
            em.close();
        }
    }
}