package francescocossu.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Prestito {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utenteID;
    @ManyToOne
    @JoinColumn(name = "elemento_prestato_id")
    private Lettura elementoPrestato;
    private LocalDate dataInizioPrestito;
    private LocalDate dataRestituzionePrevista;
    private LocalDate dataRestituzioneEffettiva;


    public Prestito() {
    }


    public Prestito(Utente utenteID, Lettura elementoPrestato, LocalDate dataInizioPrestito, LocalDate dataRestituzioneEffettiva) {
        this.utenteID = utenteID;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public Prestito(Utente utenteID, Lettura elementoPrestato, LocalDate dataInizioPrestito) {
        this.utenteID = utenteID;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
    }

    public int getId() {
        return id;
    }

    public Utente getUtenteID() {
        return utenteID;
    }

    public void setUtenteID(Utente utenteID) {
        this.utenteID = utenteID;
    }

    public Lettura getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(Lettura elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }
}






