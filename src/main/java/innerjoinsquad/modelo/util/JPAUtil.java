package innerjoinsquad.modelo.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    // Crea la conexión JPA usando el nombre indicado en persistence.xml
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("OnlineStorePU");

    // Permite que los DAOs pidan acceso a JPA
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // Permite cerrar factory en caso de ser necesario
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
