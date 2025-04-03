package dao;


import model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ClientDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    // ✅ Ajouter un nouveau client (CREATE)
    public void addClient(Client client) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(client);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // ✅ Récupérer tous les clients (READ)
    public List<Client> getAllClients() {
        EntityManager em = emf.createEntityManager();
        List<Client> clients = null;
        try {
            clients = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        } finally {
            em.close();
        }
        return clients;
    }

    // ✅ Mettre à jour un client (UPDATE)
    public void updateClient(Client client) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Client existingClient = em.find(Client.class, client.getIdClient());
            if (existingClient != null) {
                existingClient.setNomClient(client.getNomClient());
                existingClient.setPrenomClient(client.getPrenomClient());
                existingClient.setEmailClient(client.getEmailClient());
                existingClient.setMotDePassClient(client.getMotDePassClient());
                existingClient.setTelClient(client.getTelClient());
                existingClient.setDateNaissance(client.getDateNaissance());
                existingClient.setSexe(client.getSexe());
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }



    // ✅ Supprimer un client (DELETE)
    public void deleteClient(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Client client = em.find(Client.class, id);
            if (client != null) {
                em.remove(client);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // ✅ Trouver un client par ID
    public Client getClientById(Long id) {
        EntityManager em = emf.createEntityManager();
        Client client = null;
        try {
            client = em.find(Client.class, id);
        } finally {
            em.close();
        }
        return client;
    }

    public List<Client> searchClients(String searchQuery) {
        EntityManager em = emf.createEntityManager();
        List<Client> clients = null;

        try {
            clients = em.createQuery(
                            "SELECT c FROM Client c WHERE " +
                                    "LOWER(c.nomClient) LIKE :searchQuery OR " +
                                    "LOWER(c.prenomClient) LIKE :searchQuery OR " +
                                    "LOWER(c.emailClient) LIKE :searchQuery OR " +
                                    "c.telClient LIKE :searchQuery", Client.class)
                    .setParameter("searchQuery", "%" + searchQuery.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
        return clients;
    }


}
