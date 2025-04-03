package model;


import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nomClient;
    private String prenomClient;
    private Date dateNaissance;
    private String sexe;
    private String telClient;
    private String emailClient;
    private String motDePassClient;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Client() {

    }

    public Client(Long idClient, String nomClient, String prenomClient, String emailClient, String motDePassClient, String telClient, List<Reservation> reservations) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.emailClient = emailClient;
        this.motDePassClient = motDePassClient;
        this.telClient = telClient;
        this.reservations = reservations;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getMotDePassClient() {
        return motDePassClient;
    }

    public void setMotDePassClient(String motDePassClient) {
        this.motDePassClient = motDePassClient;
    }

    public String getTelClient() {
        return telClient;
    }

    public void setTelClient(String telClient) {
        this.telClient = telClient;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

}
