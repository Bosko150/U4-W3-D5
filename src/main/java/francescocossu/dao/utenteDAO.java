package francescocossu.dao;

import francescocossu.entities.Lettura;
import francescocossu.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class utenteDAO {

    private final EntityManager em;

    public utenteDAO(EntityManager em) {
        this.em = em;
    }

    public void saveUtente(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(utente);
        transaction.commit();
    }

    public List<Lettura> getLettureInPrestitoByUtente(int numeroTessera) {
        TypedQuery<Lettura> query = em.createQuery("SELECT l FROM Lettura l WHERE l.prestiti IS NOT EMPTY AND l.prestiti.utenteID.numeroTessera = :numeroTessera", Lettura.class);
        query.setParameter("numeroTessera", numeroTessera);
        return query.getResultList();
    }

    public Utente getUtenteByNumeroTessera(int numeroTessera) {
        return em.find(Utente.class, numeroTessera);
    }
}
