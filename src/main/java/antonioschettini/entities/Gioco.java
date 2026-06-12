package antonioschettini.entities;

import antonioschettini.exceptions.PrezzoNonValidoException;

import java.time.LocalDate;

public abstract class Gioco {
    // Attributi
    private static Long contatoreId = 1L; // lo setto static per passarlo all'id gioco
    private Long idGioco;
    private String titolo;
    private LocalDate dataPubblicazione;
    private Double prezzo;

    // Costruttori
    public Gioco(String titolo, LocalDate dataPubblicazione, Double prezzo) {
        this.idGioco = contatoreId;
        contatoreId++; // aumento il counter settato a static +1 globalmente
        this.titolo = titolo;
        this.dataPubblicazione = dataPubblicazione;
        if (prezzo <= 0) {
            throw new PrezzoNonValidoException("Il prezzo deve essere maggiore di 0"); // utilizzo l'expection di default per gli argument per lanciare l'errore
        }
        this.prezzo = prezzo;
    }

    public Long getIdGioco() {
        return idGioco;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(LocalDate dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        // setto il controllo per <0 anche per il setter
        if (prezzo <= 0) {
            throw new PrezzoNonValidoException("IL prezzo deve essere maggiore di 0");
        }
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "Gioco{" +
                "idGioco=" + idGioco +
                ", titolo='" + titolo + '\'' +
                ", dataPubblicazione=" + dataPubblicazione +
                ", prezzo=" + prezzo +
                '}';
    }
}
