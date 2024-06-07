package francescocossu.dao;

import francescocossu.entities.Lettura;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class letturaDAO {
    private final EntityManager em;

    public letturaDAO(EntityManager em) {
        this.em = em;
    }

    public void saveLettura(Lettura lettura) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(lettura);
        transaction.commit();
    }

    public Lettura getLetturaByISBN(String ISBN) {
        return em.find(Lettura.class, ISBN);
    }

    public void deleteLetturaByISBN(String ISBN) {
        Lettura lettura = getLetturaByISBN(ISBN);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(lettura);
        transaction.commit();
    }

    public List<Lettura> getByAnno(int annoPubblicazione) {
        TypedQuery<Lettura> query = em.createQuery("SELECT l FROM Lettura l WHERE l.annoPubblicazione = :annoPubblicazione", Lettura.class);
        query.setParameter("annoPubblicazione", annoPubblicazione);
        return query.getResultList();
    }

    public List<Lettura> getByTitolo(String titoloquery) {
        TypedQuery<Lettura> query = em.createQuery("SELECT l FROM Lettura l WHERE l.titolo LIKE :titolo", Lettura.class);
        query.setParameter("titolo", "%" + titoloquery + "%");
        return query.getResultList();
    }
}
