package dao;

import jakarta.persistence.*;
import model.Admin;
import model.Client;

public class LoginDAO {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPU");

    public static Object authenticateUser(String email, String password) {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            // Vérifier d'abord dans Client
            TypedQuery<Client> queryClient = em.createQuery(
                    "SELECT c FROM Client c WHERE c.emailClient = :email AND c.motDePassClient = :password",
                    Client.class);
            queryClient.setParameter("email", email);
            queryClient.setParameter("password", password);

            Client client = queryClient.getSingleResult();
            return client; // Si trouvé, on retourne le client
        } catch (NoResultException e) {
            try {
                // Vérifier dans Administrateur si non trouvé dans Client
                TypedQuery<Admin> queryAdmin = em.createQuery(
                        "SELECT a FROM Admin a WHERE a.emailAdmi = :email AND a.motDePass = :password",
                        Admin.class);
                queryAdmin.setParameter("email", email);
                queryAdmin.setParameter("password", password);

                Admin admin = queryAdmin.getSingleResult();
                return admin; // Si trouvé, on retourne l'admin
            } catch (NoResultException ex) {
                return null; // Aucun utilisateur trouvé
            }
        } finally {
            em.close();
        }
    }

    public static void closeFactory() {
        entityManagerFactory.close();
    }
}

