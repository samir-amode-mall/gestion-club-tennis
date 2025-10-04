package fr.ensicaen.tennis.bean;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Inscription")
public class Inscription {
    @Id
    @ManyToOne
    @JoinColumn(name = "identifiant")
    private Adherent adherent;

    @Id
    @ManyToOne
    @JoinColumn(name = "codeTournoi")
    private Tournoi competition;

    @Column(name = "dateEnregistrement")
    private Date dateEnregistrement;

    public Inscription() {
    }

    public Inscription(Adherent adherent, Tournoi competition, Date dateEnregistrement) {
        this.adherent = adherent;
        this.competition = competition;
        this.dateEnregistrement = dateEnregistrement;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Tournoi getCompetition() {
        return competition;
    }

}
