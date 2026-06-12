package antonioschettini.entities;

import antonioschettini.enums.Genere;
import antonioschettini.enums.Piattaforma;
import antonioschettini.exceptions.ValoriFuoriRangeException;

import java.time.LocalDate;

public class Videogioco extends Gioco {
    //Attributi
    private Piattaforma piattaforma;
    private Integer durataGioco;
    private Genere genere;

    //Costruttori
    public Videogioco(String titolo, LocalDate dataPubblicazione, Double prezzo, Piattaforma piattaforma, Integer durataGioco, Genere genere) {
        super(titolo, dataPubblicazione, prezzo);
        this.piattaforma = piattaforma;
        if (durataGioco <= 0) {
            throw new ValoriFuoriRangeException("La durata del videogioco deve essere di almeno un ora");
        }
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
        if (durataGioco <= 0) {
            throw new ValoriFuoriRangeException("La durata del videogioco deve essere di almeno un ora");
        }
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
        // utilizzo il super per prendere dalla superclasse titolo id ecc ecc e ci aggiungo i dati del videogioco
        return super.toString() + " [VIDEOGIOCO] Piattaforma: " + piattaforma +
                " | Durata: " + durataGioco + " ore | Genere: " + genere;
    }
}
