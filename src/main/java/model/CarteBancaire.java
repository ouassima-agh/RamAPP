package model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="carte_bancaire")
public class CarteBancaire {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public Long getCvv() {
        return cvv;
    }

    public void setCvv(Long cvv) {
        this.cvv = cvv;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public CarteBancaire() {
    }

    public CarteBancaire(Long id, Long cardNumber, String cardHolderName, int expiryMonth, int expiryYear, Long cvv, BigDecimal balance) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
        this.balance = balance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carte")
    private Long id;

    @Column(name = "numero_carte",unique = true, length = 16, nullable = false)
    private Long cardNumber;

    @Column(name = "nom_proprietaire", nullable = false)
    private String cardHolderName;

    @Column(name = "mois_expiration", nullable = false)
    private int expiryMonth;

    @Column(name = "annee_expiration", nullable = false)
    private int expiryYear;

    @Column(name = "code_verification",nullable = false, length = 3)
    private Long cvv;

    @Column(name = "solde", nullable = false)
    private BigDecimal balance;
}
