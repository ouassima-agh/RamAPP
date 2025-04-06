package model;
import jakarta.persistence.*;

@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code; // ex: CDG, CMN

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String name; // ex: Mohammed V, Orly

    @Column(nullable = false)
    private String city; // ex: Casablanca

    // --- Constructeurs ---
    public Airport() {}

    public Airport(String code, String name, String city, String country) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    // --- Getters / Setters ---
    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // --- toString ---
    @Override
    public String toString() {
        return city + " (" + code + ") - " + country;
    }

    // --- equals & hashCode (essentiel pour les comparaisons) ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport airport = (Airport) o;
        return id != null && id.equals(airport.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}