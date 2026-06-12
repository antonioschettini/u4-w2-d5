package antonioschettini.entities;

import antonioschettini.exceptions.ValoriFuoriRangeException;

import java.time.LocalDate;

public class GiocoDaTavola extends Gioco {
    //Attrbuti
    private Integer numeroGiocatori;
    private Integer durataPartita;

    //Costruttori
    public GiocoDaTavola(String titolo, LocalDate dataPubblicazione, Double prezzo, Integer numeroGiocatori, Integer durataPartita) {
        super(titolo, dataPubblicazione, prezzo);
        if (durataPartita <= 0) {
            throw new ValoriFuoriRangeException("La durata della partita deve essere almeno di 1 ora");
        }
        this.durataPartita = durataPartita;

        if (numeroGiocatori < 2 || numeroGiocatori > 10) {
            throw new ValoriFuoriRangeException("Il numero di giocatori deve essere tra 2 e 10"); // utilizzo lo stesso controllo fatto per il prezzo
        }
        this.numeroGiocatori = numeroGiocatori;
    }

    public Integer getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public void setNumeroGiocatori(Integer numeroGiocatori) {
        if (numeroGiocatori < 2 || numeroGiocatori > 10) {
            throw new ValoriFuoriRangeException("Il numero di giocatori deve essere tra 2 e 10"); // utilizzo lo stesso controllo fatto per il prezzo anche nel setter
        }
        this.numeroGiocatori = numeroGiocatori;
    }

    public Integer getDurataPartita() {
        return durataPartita;
    }

    public void setDurataPartita(Integer durataPartita) {
        if (durataPartita <= 0) {
            throw new ValoriFuoriRangeException("La durata della partita deve essere almeno di 1 ora");
        }
        this.durataPartita = durataPartita;
    }

    @Override
    public String toString() {
        // prendi i dati come titolo id ecc dalla superclasse e ci aggiungo i dati del gioco tavolo
        return super.toString() + " [GIOCO DA TAVOLO] Giocatori: " + numeroGiocatori +
                " | Durata Partita: " + durataPartita + " min";
    }
}
