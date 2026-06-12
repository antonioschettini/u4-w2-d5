package antonioschettini.entities;

import antonioschettini.enums.Genere;
import antonioschettini.enums.Piattaforma;

import java.time.LocalDate;

public class Videogioco extends Gioco {
    //Attributi
    private Piattaforma piattaforma;
    private Integer durataGioco;
    private Genere genere;

    //Costruttori
    public Videogioco(Long idGioco, String titolo, LocalDate dataPubblicazione, Double prezzo, Piattaforma piattaforma, Integer durataGioco, Genere genere) {
        super(titolo, dataPubblicazione, prezzo);
        this.piattaforma = piattaforma;
        this.durataGioco = durataGioco;
        this.genere = genere;

    }

    public Piattaforma getPiattaforma() {
        return piattaforma;
    }

    public void setPiattaforma(Piattaforma piattaforma) {
        this.piattaforma = piattaforma;
    }

    public Integer getDurataGioco() {
        return durataGioco;
    }

    public void setDurataGioco(Integer durataGioco) {
        this.durataGioco = durataGioco;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Videogioco{" +
                "piattaforma=" + piattaforma +
                ", durataGioco=" + durataGioco +
                ", genere=" + genere +
                '}';
    }
}
