package model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Column(nullable = false)
    private String transactionId; // Identifiant unique du paiement qui sera visible au client.......

    @ManyToOne
    @JoinColumn(name = "carte_id", nullable = false)
    private CarteBancaire carteBancaire; // Carte utilisée pour payer

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    //@Enumerated(EnumType.STRING)
    @Column(name ="status",nullable = false)
    private PaymentStatus status;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;

    @Column(name = "ticket_sent")
    private boolean ticketSent; // le billet est envoyer ou non

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public CarteBancaire getCarteBancaire() {
        return carteBancaire;
    }

    public void setCarteBancaire(CarteBancaire carteBancaire) {
        this.carteBancaire = carteBancaire;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isTicketSent() {
        return ticketSent;
    }

    public void setTicketSent(boolean ticketSent) {
        this.ticketSent = ticketSent;
    }

    public Payment() {
    }

    public Payment(Long id, String transactionId, CarteBancaire carteBancaire, Reservation reservation, PaymentStatus status, Date paymentDate, boolean ticketSent) {
        this.id = id;
        this.transactionId = transactionId;
        this.carteBancaire = carteBancaire;
        this.reservation = reservation;
        this.status = status;
        this.paymentDate = paymentDate;
        this.ticketSent = ticketSent;
    }
}
