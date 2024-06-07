package francescocossu.dao;

import francescocossu.entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class prestitoDAO {

    private final EntityManager em;

    public prestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void savePrestito(Prestito prestito) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(prestito);
        transaction.commit();
    }

    public List<Prestito> getPrestitiScadutiNonRestituiti() {
        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzione IS NULL AND p.dataScadenza < CURRENT_DATE", Prestito.class);
        return query.getResultList();

    }
}
