package dao;

import jakarta.persistence.*;
import model.CarteBancaire;
import model.Payment;

import java.util.List;

public class PaymentDAO {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPU");

    public void save(Payment payment) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            if (payment.getId() == null) {
                em.persist(payment);
            } else {
                em.merge(payment);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public CarteBancaire findCard(Long number, String name, int month, int year, Long cvv) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CarteBancaire card = null;

        try {
            TypedQuery<CarteBancaire> query = em.createQuery(
                    "SELECT c FROM CarteBancaire c WHERE c.cardNumber = :number " +
                            "AND c.cardHolderName = :name AND c.expiryMonth = :month " +
                            "AND c.expiryYear = :year AND c.cvv = :cvv",
                    CarteBancaire.class
            );

            query.setParameter("number", number);
            query.setParameter("name", name);
            query.setParameter("month", month);
            query.setParameter("year", year);
            query.setParameter("cvv", cvv);

            List<CarteBancaire> results = query.getResultList();
            if (!results.isEmpty()) {
                card = results.get(0);
            }
        } catch (NoResultException e) {
            // Do nothing, return null
        } finally {
            em.close();
        }

        return card;
    }

    public static void closeFactory() {
        entityManagerFactory.close();
    }
}
