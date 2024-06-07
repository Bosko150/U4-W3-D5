package francescocossu;

import francescocossu.dao.letturaDAO;
import francescocossu.dao.libroDAO;
import francescocossu.dao.prestitoDAO;
import francescocossu.dao.utenteDAO;
import francescocossu.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Date;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d5");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        letturaDAO ldao = new letturaDAO(em);
        prestitoDAO pdao = new prestitoDAO(em);
        utenteDAO udao = new utenteDAO(em);
        libroDAO ldao2 = new libroDAO(em);

        Utente utenteFromDB = udao.getUtenteByNumeroTessera(102);
        Utente utenteFromDB2 = udao.getUtenteByNumeroTessera(103);
        Lettura letturaFromDB = ldao.getLetturaByISBN("ISBN1");
        Lettura letturaFromDB2 = ldao.getLetturaByISBN("ISBN2");
        Lettura letturaFromDB3 = ldao.getLetturaByISBN("ISBN3");


        Libro libro1 = new Libro("ISBN1", "Coding for Dummies", 2024, 151, "Jhon Coder", "Guida");
        Libro libro2 = new Libro("ISBN2", "Lord of the Rings", 1954, 300, "Tolkien", "Fantasy");
        Libro libro3 = new Libro("ISBN3", "Harry Potter", 1997, 400, "Rowling", "Fantasy");
        Libro libro4 = new Libro("ISBN4", "The Hobbit", 1937, 500, "Tolkien", "Fantasy");
        Rivista rivista1 = new Rivista("ISBN5", "The New York Times", 2024, 100, Periodicità.MENSILE);
        Rivista rivista2 = new Rivista("ISBN6", "The Guardian", 2024, 100, Periodicità.SEMESTRALE);
        Rivista rivista3 = new Rivista("ISBN7", "The Atlantic", 2024, 100, Periodicità.MENSILE);

        /*ldao.saveLettura(libro1);
        ldao.saveLettura(libro2);
        ldao.saveLettura(libro3);
        ldao.saveLettura(libro4);
        ldao.saveLettura(rivista1);
        ldao.saveLettura(rivista2);
        ldao.saveLettura(rivista3);*/


        Utente utente1 = new Utente("Antonio", "Rossi", Date.valueOf("1995-06-21").toLocalDate());
        Utente utente2 = new Utente("Marco", "Bianchi", Date.valueOf("1995-05-04").toLocalDate());
        Utente utente3 = new Utente("Luca", "Verdi", Date.valueOf("1995-03-01").toLocalDate());

        /*udao.saveUtente(utente1);
        udao.saveUtente(utente2);
        udao.saveUtente(utente3);*/


        Prestito prestito1 = new Prestito(utenteFromDB, letturaFromDB, Date.valueOf("2022-06-01").toLocalDate(), Date.valueOf("2022-06-21").toLocalDate());
        Prestito prestito2 = new Prestito(utenteFromDB, letturaFromDB2, Date.valueOf("2022-06-21").toLocalDate());
        Prestito prestito3 = new Prestito(utenteFromDB2, letturaFromDB3, Date.valueOf("2022-06-21").toLocalDate(), Date.valueOf("2022-07-21").toLocalDate());


        /*pdao.savePrestito(prestito1);
        pdao.savePrestito(prestito2);
        pdao.savePrestito(prestito3);*/

        //-Rimozione di un elemento del catalogo dato un codice ISBN
        //ldao.deleteLetturaByISBN("ISBN6");

        //-Ricerca per ISBN
        System.out.println("Il libro corrispondente è " + ldao.getLetturaByISBN("ISBN1"));


        //-Ricerca per anno di pubblicazione
        System.out.println("le letture dell'anno 2024 sono:");
        ldao.getByAnno(2024).forEach(System.out::println);


        //-Ricerca per titolo
        System.out.println("i risultati della ricerca per titolo sono: ");
        ldao.getByTitolo("The").forEach(System.out::println);

        //-Ricerca per autore
        System.out.println("i risultati della ricerca per autore sono: ");
        ldao2.getLibriByAutore("Tolkien").forEach(System.out::println);


        //-Ricerca di tutti i prestiti scaduti e non ancora restituiti
        System.out.println("i prestiti scaduti e non restituiti sono: ");
        pdao.getPrestitiScadutiNonRestituiti().forEach(System.out::println);

        //-Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
        System.out.println("Gli elementi attualmente in prestito corrispondenti alla tessera utente sono: ");
        udao.getLettureInPrestitoByUtente(102).forEach(System.out::println);

        em.close();
        emf.close();
    }
}
