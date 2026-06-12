package antonioschettini;

import antonioschettini.entities.Collezione;
import antonioschettini.entities.Gioco;
import antonioschettini.entities.GiocoDaTavola;
import antonioschettini.entities.Videogioco;
import antonioschettini.enums.Genere;
import antonioschettini.enums.Piattaforma;
import antonioschettini.exceptions.ElementoDuplicatoException;
import antonioschettini.exceptions.GiocoNonTrovatoException;
import antonioschettini.exceptions.PrezzoNonValidoException;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Collezione miaCollezione = new Collezione();
        boolean esecuzione = true;

        //Test dei metodi
        System.out.println("Gestione collezioni Giochi");

        while (esecuzione) {
            System.out.println("-------");
            System.out.println("Menù Operazioni");
            System.out.println("-------");
            System.out.println("1: Aggiungi un Videogioco");
            System.out.println("2: Aggiungi un Gioco da Tavola");
            System.out.println("3: Cerca un codice tramite Id");
            System.out.println("4: Cerca giochi per prezzi inferiori");
            System.out.println("5: Cerca giochi da tavolo per numero giocatori");
            System.out.println("6: Rimuovi un gioco tramite Id");
            System.out.println("7: Aggiorna un gioco tramite id");
            System.out.println("8: Mostra le statistiche della collezione");
            System.out.println("9: Stampa tutta la collezione");
            System.out.println("0: Esci");
            System.out.println("Scegli un azione: ");

            try {
                int scelta = scanner.nextInt();
                scanner.nextLine(); // svuoto la linea

                switch (scelta) {
                    case 1: // Task1 aggiungo un videogioco
                        System.out.println("Aggiunta nuovo Videogioco");
                        System.out.println("Inserisci il titolo: ");
                        String titoloVidscelto = scanner.nextLine();

                        System.out.println("Inserisci il prezzo ad esempio 49,99");
                        double prezzoVidscelto = scanner.nextDouble();
                        scanner.nextLine(); // pulisco la line<a

                        System.out.println("Inserisci la durata in ore: ");
                        int durataVidScelto = scanner.nextInt();
                        scanner.nextLine();

                        // provo ad inserire randomicamente uno tra gli enums scelti
                        // Piattaforme
                        Random random = new Random();
                        Piattaforma[] tutteLePiattaforme = Piattaforma.values(); // creo l'array con le opzioni enums
                        int indicePerRandomPiattaforma = random.nextInt(tutteLePiattaforme.length); // sceglie un numero per la lunghezza tra 0 e 2
                        Piattaforma piattaformaCasuale = tutteLePiattaforme[indicePerRandomPiattaforma]; // ho la variabile random con il numero estratto
                        //Genere
                        Genere[] tuttiIGeneri = Genere.values(); // creo l'array con le opzioni enums
                        int indicePerGenereRandom = random.nextInt(tuttiIGeneri.length); // sceglie un numero tra 0 e 6
                        Genere genereCasuale = tuttiIGeneri[indicePerGenereRandom]; // variabile da inserire nel costruttore

                        // Creo il videogioco in base ai valori inseriti
                        Videogioco nuovoVideogioco = new Videogioco(titoloVidscelto, LocalDate.now(), prezzoVidscelto, piattaformaCasuale, durataVidScelto, genereCasuale);

                        miaCollezione.aggiungiGioco(nuovoVideogioco);
                        break;

                    case 2: //Task1 aggiungo cun gioco da tavola
                        System.out.println("Anggiunta nuovo Gioco da Tavola");
                        System.out.println("Inserisci il titolo: ");
                        String titoloTavScelto = scanner.nextLine();

                        System.out.println("Inserisci il prezzo esempio 19,50");
                        double prezzoTavScelto = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("Inserisci il numero di giocatore da 2 a 10");
                        int numGiocatoriScelto = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Inserisci la durata stimata della partita in minuti");
                        int durataGiocoTav = scanner.nextInt();
                        scanner.nextLine();

                        // Creo il giocodatavola con i dati inseriti dall'utente
                        GiocoDaTavola nuovoGiocoTavolo = new GiocoDaTavola(titoloTavScelto, LocalDate.now(), prezzoTavScelto, numGiocatoriScelto, durataGiocoTav);
                        miaCollezione.aggiungiGioco(nuovoGiocoTavolo);
                        break;

                    case 3: //Task2 ricerca per id
                        System.out.println("Inserisci l'id del gioco da cercare");
                        long cercaId = scanner.nextLong();
                        scanner.nextLine();

                        Gioco giocoTrovato = miaCollezione.cercaperId(cercaId);
                        System.out.println("Il gioco ricercato è: " + giocoTrovato);
                        break;

                    case 4: //Task 3 ricerca per prezzo
                        System.out.println("Inserisci un prezzo per vederne i gioche che costano meno del prezzo richiesto es meno di 19,99");
                        double prezzoMassimo = scanner.nextDouble();
                        scanner.nextLine();

                        List<Gioco> giochiEconomici = miaCollezione.cercaPerPrezzoMenoCostosoDi(prezzoMassimo);
                        if (giochiEconomici.isEmpty()) {
                            System.out.println("Non ho trovato nessun gioco sotto €: " + prezzoMassimo);
                        } else {
                            System.out.println("I giochi con prezzo inferiore a €: " + prezzoMassimo);
                            giochiEconomici.forEach(System.out::println);
                        }
                        break;

                    case 5: //Task 4 Ricerca per numero di giocatori
                        System.out.println("Inserisci il numero di giocatori per cercare un gioco da tavola: ");
                        int numGiocatoriCerca = scanner.nextInt();
                        scanner.nextLine();

                        List<GiocoDaTavola> giochiNumRichiesto = miaCollezione.ricercaPerNumeroGiocatori(numGiocatoriCerca);
                        if (giochiNumRichiesto.isEmpty()) {
                            System.out.println("Nessun gioco da tavola trovato per: " + numGiocatoriCerca + " giocatori");
                        } else {
                            System.out.println("Giochi per " + numGiocatoriCerca + " giocatori");
                            giochiNumRichiesto.forEach(System.out::println);
                        }
                        break;

                    case 6: //Task 5 Rimuovi per id
                        System.out.println("Inserisci l'id del gioco da rimuovere, tips, verifica prima nella collezione azione 9 per essere sicuri dell id");
                        long idRimuovere = scanner.nextLong();
                        scanner.nextLine();

                        miaCollezione.rimuoviPerId(idRimuovere);
                        break;

                    case 7: //Task 6 aggiorna gioco
                        System.out.println("Inserisci l'id del gioco che vuoi modificare/aggiornare (p.s ricorda l'azione 9 per verificare tutti gli id");
                        long idAggiorna = scanner.nextLong();
                        scanner.nextLine();

                        // Verifico prima se è esistente ed uso la mia cestione degli errori del metodo
                        miaCollezione.cercaperId(idAggiorna);
                        System.out.println("Inserisci un nuovo titolo: ");
                        String nuovoTitolo = scanner.nextLine();

                        System.out.println("Inserisci il nuovo Prezzo");
                        double nuovoPrezzo = scanner.nextDouble();
                        scanner.nextLine();

                        // aggiorno i dati
                        miaCollezione.aggiornaGioco(idAggiorna, nuovoTitolo, LocalDate.now(), nuovoPrezzo);
                        break;

                    case 8: // Task 7 stastiche
                        miaCollezione.stampaStatistiche();
                        break;

                    case 9: // stampa tutta la collezione
                        miaCollezione.stampaTuttiGiochi();
                        break;

                    case 0: // Uscita
                        System.out.println("Chiusura del programma");
                        esecuzione = false;
                        break;

                    default:
                        System.out.println("Opzione non valida! inserisci un azione con numero compreso tra 0 e 9");
                }
            } catch (InputMismatchException e) {
                // catturo errore di inserimento sbagliato se chiedo un int e mi fornisce una stringa come default dallo scanner
                System.out.println("Errore di digitazione, inserisci un valore valido ");
                scanner.nextLine(); // svuoto la linea
            } catch (ElementoDuplicatoException e) {
                // catturo l'errore in caso di inserimento di un id doppio
                System.out.println("L'id inserito è già esistente: " + e.getMessage());

            } catch (GiocoNonTrovatoException e) {
                // catturo l'errore di ricerca gioco o rimozione gioco
                System.out.println("Errore gioco non trovato: " + e.getMessage());

            } catch (PrezzoNonValidoException e) {
                // catturo l'errore per prezzi negativi o zero
                System.out.println("Errore di inserimento prezzo: " + e.getMessage());

            } catch (RuntimeException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        scanner.close(); // chiudo lo scanner al termine del programma
    }
}
