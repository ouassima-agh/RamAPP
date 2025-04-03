package model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdministrateur;

    private String nomAdmi;
    private String prenomAdmi;
    private Date dateNaissance;
    private String sexe;
    private String telephone;
    private String emailAdmi;
    private String motDePass;

    public Admin() {}

    public Long getIdAdministrateur() {
        return idAdministrateur;
    }

    public void setIdAdministrateur(Long idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
    }

    public String getNomAdmi() {
        return nomAdmi;
    }

    public void setNomAdmi(String nomAdmi) {
        this.nomAdmi = nomAdmi;
    }

    public String getPrenomAdmi() {
        return prenomAdmi;
    }

    public void setPrenomAdmi(String prenomAdmi) {
        this.prenomAdmi = prenomAdmi;
    }

    public String getEmailAdmi() {
        return emailAdmi;
    }

    public void setEmailAdmi(String emailAdmi) {
        this.emailAdmi = emailAdmi;
    }

    public String getMotDePass() {
        return motDePass;
    }

    public void setMotDePass(String motDePass) {
        this.motDePass = motDePass;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
