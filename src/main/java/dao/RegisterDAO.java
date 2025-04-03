package dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Admin;
import model.Client;
public class RegisterDAO {
    private static final EntityManager entityManager = Persistence.createEntityManagerFactory("myPU").createEntityManager();

    // Vérifier si un utilisateur existe déjà avec cet email
    public static boolean emailExists(String email) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(c) FROM Client c WHERE c.emailClient = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();

        count += entityManager.createQuery(
                        "SELECT COUNT(a) FROM Admin a WHERE a.emailAdmi = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();

        return count > 0;
    }

    // Ajouter un client dans la base de données
    public static boolean registerClient(Client client) {
        if (emailExists(client.getEmailClient())) {
            return false; // Email déjà utilisé
        }
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(client);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Ajouter un administrateur dans la base de données
    public static boolean registerAdmin(Admin admin) {
        if (emailExists(admin.getEmailAdmi())) {
            return false; // Email déjà utilisé
        }
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(admin);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}

