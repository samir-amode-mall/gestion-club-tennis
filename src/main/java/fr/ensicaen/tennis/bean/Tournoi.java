package fr.ensicaen.tennis.bean;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tournoi")
public class Tournoi {
    @Id
    @Column(name = "codeCompetition")
    private String codeCompetition;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "lieu", nullable = false)
    private String lieu;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inscription> inscriptions = new ArrayList<>();

    public Tournoi(int id, String nom, String date, String lieu) {
        this.codeCompetition = String.valueOf(id);
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tournoi tournoi = (Tournoi) obj;
        return codeCompetition != null && codeCompetition.equals(tournoi.codeCompetition);
    }

    @Override
    public int hashCode() {
        return codeCompetition != null ? codeCompetition.hashCode() : 0;
    }



    public String getCodeCompetition() {
        return codeCompetition;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }
}
