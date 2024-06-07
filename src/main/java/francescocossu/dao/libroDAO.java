package francescocossu.dao;

import francescocossu.entities.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class libroDAO {

    private final EntityManager em;

    public libroDAO(EntityManager em) {
        this.em = em;
    }

    public List<Libro> getLibroByAutore(String autore) {

        TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }
}
