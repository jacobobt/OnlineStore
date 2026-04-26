package innerjoinsquad;

import innerjoinsquad.modelo.util.JPAUtil;
import jakarta.persistence.EntityManagerFactory;

public class PruebaConexionJPA {

    public static void main(String[] args) {
        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        if (emf != null && emf.isOpen()) {
            System.out.println("Conexión realizada correctamente con la base de datos.");
        } else {
            System.out.println("Error al conectar con la base de datos.");
        }
    }
}