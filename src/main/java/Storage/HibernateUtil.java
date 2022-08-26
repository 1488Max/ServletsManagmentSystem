package Storage;

import Entities.*;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    @Getter
    private final SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate_project_db")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "admin")
                .addAnnotatedClass(Company.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Skill.class)
                .addAnnotatedClass(Developer.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }
}