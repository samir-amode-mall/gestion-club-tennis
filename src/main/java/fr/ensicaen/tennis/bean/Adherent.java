package fr.ensicaen.tennis.bean;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "Adherent")
public class Adherent {
    @Id
    @Column(name = "identifiant")
    private String identifiant;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "motDePasse", nullable = false)
    private String motDePasse;

    @OneToMany(mappedBy = "adherent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inscription> inscriptions = new ArrayList<>();

    public Adherent() {
    }

    public Adherent(String email, String nom, String prenom, String identifiant, String motDePasse) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public String getNomComplet() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void ajouterCompetition(Tournoi competition) {
        Inscription inscription = new Inscription(this, competition, new Date());
        this.inscriptions.add(inscription);
    }

    public void retirerCompetition(Tournoi competition) {
        Iterator<Inscription> iterator = inscriptions.iterator();
        while (iterator.hasNext()) {
            Inscription inscription = iterator.next();
            if (inscription.getCompetition().equals(competition)) {
                iterator.remove();
                break;
            }
        }
    }

}
