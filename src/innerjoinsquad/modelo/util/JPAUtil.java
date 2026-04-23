package innerjoinsquad.modelo.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

// Clase utilitaria que configura JPA directamente en código sin necesidad del archivo persistence.xml

public class JPAUtil {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            Map<String, String> props = new HashMap<>();
            props.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
            props.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/online_store");
            props.put("jakarta.persistence.jdbc.user", "root");
            props.put("jakarta.persistence.jdbc.password", "Holass2021"); // cambia por tu contraseña
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            props.put("hibernate.hbm2ddl.auto", "validate");
            props.put("hibernate.show_sql", "true");
            emf = Persistence.createEntityManagerFactory("OnlineStorePU", props);
        }
        return emf;
    }
}
