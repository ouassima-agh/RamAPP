package model;


import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoyage;

    private Date dateDepart;
    private Date dateRetours;
    private int nombreDePlacesDisponible;
    private String offrePromo;

    @OneToMany(mappedBy = "voyage", cascade = CascadeType.ALL)
    private List<Reservation> reservations;




    public Voyage() {}

    public Long getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(Long idVoyage) {
        this.idVoyage = idVoyage;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateRetours() {
        return dateRetours;
    }

    public void setDateRetours(Date dateRetours) {
        this.dateRetours = dateRetours;
    }

    public int getNombreDePlacesDisponible() {
        return nombreDePlacesDisponible;
    }

    public void setNombreDePlacesDisponible(int nombreDePlacesDisponible) {
        this.nombreDePlacesDisponible = nombreDePlacesDisponible;
    }

    public String getOffrePromo() {
        return offrePromo;
    }

    public void setOffrePromo(String offrePromo) {
        this.offrePromo = offrePromo;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}

