package antonioschettini.entities;

import java.time.LocalDate;

public class GiocoDaTavola extends Gioco {
    //Attrbuti
    private Integer numeroGiocatori;
    private Integer durataPartita;

    //Costruttori
    public GiocoDaTavola(String titolo, LocalDate dataPubblicazione, Double prezzo, Integer numeroGiocatori, Integer durataPartita) {
        super(titolo, dataPubblicazione, prezzo);
        this.durataPartita = durataPartita;
        if (numeroGiocatori < 2 || numeroGiocatori > 10) {
            throw new IllegalArgumentException("Il numero di giocatori deve essere tra 2 e 10"); // utilizzo lo stesso controllo fatto per il prezzo
        }
        this.numeroGiocatori = numeroGiocatori;
    }

    public Integer getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public void setNumeroGiocatori(Integer numeroGiocatori) {
        if (numeroGiocatori < 2 || numeroGiocatori > 10) {
            throw new IllegalArgumentException("Il numero di giocatori deve essere tra 2 e 10"); // utilizzo lo stesso controllo fatto per il prezzo anche nel setter
        }
        this.numeroGiocatori = numeroGiocatori;
    }

    public Integer getDurataPartita() {
        return durataPartita;
    }

    public void setDurataPartita(Integer durataPartita) {
        this.durataPartita = durataPartita;
    }

    @Override
    public String toString() {
        return "GiocoDaTavola{" +
                "numeroGiocatori=" + numeroGiocatori +
                ", durataPartita=" + durataPartita +
                '}';
    }
}
