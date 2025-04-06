package model;

public enum PaymentStatus {
    PENDING("En attente"),
    COMPLETED("Terminé"),
    FAILED("Échoué"),
    REFUNDED("Remboursé");

    private final String label;

    PaymentStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

