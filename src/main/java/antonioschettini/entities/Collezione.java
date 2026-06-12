package antonioschettini.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Collezione {
    //Attributi
    private List<Gioco> listaGiochi; // utilizzo la classe super Gioco per essere generico tanto estende anche video e tavola;

    //COstruttori
    public Collezione() {
        this.listaGiochi = new ArrayList<>(); // nel costruttore appena instazio una nuova collezione mi crea una arraylist vuota
    }

    //Metodi
    //Task1, aggiunta di un elemento e non posso inserire un elemento con lo stesso id
    public void aggiungiGioco(Gioco nuovoGioco) {
        boolean idGiaEsistente = listaGiochi.stream() // apro lo stream
                .anyMatch(gioco -> gioco.getIdGioco().equals(nuovoGioco.getIdGioco()));
        // utilizzo l'anymatch per lambda predicate per ogni gioco prendi l'id e contrtolla se è uguale al gioco creato
        // passato come parametro all'invocazione del metodo, uso i getter per recuperare l'id.

        //attivo i controlli se l'id è già esistente lancio l'errore
        if (idGiaEsistente) {
            throw new IllegalArgumentException("Impossibile inserire, poichè esiste già un gioco con lo stesso id " + nuovoGioco.getIdGioco());
        }
        // se passa il controllo
        listaGiochi.add(nuovoGioco);
        System.out.println("Gioco inserito avrà l'id : " + nuovoGioco.getIdGioco());
    }

    //Task2, ricerco un gioco tramite id
    public Gioco cercaperId(Long id) {  //passo Gioco poichè il metodo mi restituisce un gioco, non posso farlo void
        // utilizzo un ciclo for
        for (Gioco gioco : listaGiochi) {
            // attivo i controlli se il gioco ciclato ha l'id passato nel parametro il ciclo si ferma subito
            if (gioco.getIdGioco().equals(id)) {
                return gioco; //
            }
        }
        throw new RuntimeException("Non ho trovato nessun gioco con ID: " + id);
    }

    //Task3 ricerca per prezzo, inferiore a quello fornito nel parametro del metodo
    public List<Gioco> cercaPerPrezzoMenoCostosoDi(double prezzomassimo) {
        return listaGiochi.stream()
                .filter(gioco -> gioco.getPrezzo() < prezzomassimo).toList(); // avvio lo stream effettuo un filter per ogni gioco prendimi
        //il prezzo e comparamelo con il prezzo inserito nell parametro del metodo e poi inseriscimelo nella lista che ritorno.
    }

    //Task4 ricerca per numero giocatori
    public List<GiocoDaTavola> ricercaPerNumeroGiocatori(Integer numeroGiocatori) {
        return listaGiochi.stream() //Avendo una lista generica confronto prima se è una istanza di giochi da tavolo
                .filter(gioco -> gioco instanceof GiocoDaTavola)
                .map(gioco -> (GiocoDaTavola) gioco) //effettuo un casting per trattarlo come gioco tavola
                .filter(giocoDaTavola -> giocoDaTavola.getNumeroGiocatori().equals(numeroGiocatori)).toList();
        // effettuo un fiilter per ogni giocodatavola prendine i giocatore e confronta se è uguale al numero interito
    }

    //Task5 rimuovi un elemento per codice id
    public void rimuoviPerId(Long id) {
        // uso il mio metodo cercaperid per trovarlo e nel caso per rimuoverlo
        Gioco giocoDaRimuovere = cercaperId(id);// lo assegno ad una variabile che passero al remove
        listaGiochi.remove(giocoDaRimuovere);
        System.out.println("Gioco con l'id: " + id + " è stato rimosso");
    }

    //Task6 aggiornamento di un elemento tramite id
    public void aggiornaGioco(Long id, String nuovoTitolo, LocalDate nuovaData, Double nuovoPrezzo) {
        //ho già il metodo per cercare per id, lo utilizzo così nel caso non c'è un gioco ho già il controllo dell'errore
        Gioco giocoDaModificare = cercaperId(id);

        //Setter per sovrascrivere con i parametri forniti, ed ho già nei setter il controllo sui prezzi
        giocoDaModificare.setTitolo(nuovoTitolo);
        giocoDaModificare.setDataPubblicazione(nuovaData);
        giocoDaModificare.setPrezzo(nuovoPrezzo);

        System.out.println("Gioco con id: " + id + "Aggiornato");
    }

    //Task7 statistiche della collezione
    public void stampaStatistiche() {
        System.out.println("--Statistiche Collezione--");
        // Controlli
        // se la collezione è vuota stampo un messaggio di avvis
        if (listaGiochi.isEmpty()) {
            System.out.println("La collezione è vuota non sono disponibili statistiche");
            return; // interrompo subito il metodo
        }

        // Conto quanti sono i videogiochi  e li assegno in una variabile per facilitare la stampa
        long numeroViceogiochi = listaGiochi.stream()
                .filter(gioco -> gioco instanceof Videogioco)
                .count();

        //conto i g da tavola e li assegno in una variabile per facilitare la stampa
        long numeroGiochiTavola = listaGiochi.stream()
                .filter(gioco -> gioco instanceof GiocoDaTavola)
                .count();

        System.out.println("Totale Videogiochi: " + numeroViceogiochi);
        System.out.println("Totale Giochi da Tavola: " + numeroGiochiTavola);

        // Gioco con il prezzo più alto
        Gioco giocoPiuCaro = listaGiochi.stream()
                .max((gioco1, gioco2) -> gioco1.getPrezzo().compareTo(gioco2.getPrezzo())).get();
        System.out.println("Il gioco più costoso è: " + giocoPiuCaro.getTitolo() + "costo €: " + giocoPiuCaro.getPrezzo());

        // Media prezzi
        double mediaPrezzi = listaGiochi.stream()
                .mapToDouble(gioco -> gioco.getPrezzo())
                .average()
                .getAsDouble();
        System.out.println("La media dei prezzi è €; " + mediaPrezzi);
    }

}
