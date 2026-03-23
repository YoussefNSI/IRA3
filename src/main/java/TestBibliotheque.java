import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

void main() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-essai");

    try (emf; EntityManager em = emf.createEntityManager()) {
        // Emprunt et ses livres associés
        Long idEmprunt = 1L;
        Emprunt emprunt = em.find(Emprunt.class, idEmprunt);
        if (emprunt != null) {
            IO.println("Emprunt : " + emprunt);
            IO.println("Livres associés :");
            emprunt.getLivres().forEach(l -> IO.println("  " + l));
        } else {
            IO.println("Emprunt non trouvé (id=" + idEmprunt + ")");
        }

        IO.println();

        // Tous les emprunts d'un client donné
        Long idClient = 1L;
        TypedQuery<Emprunt> query = em.createQuery(
                "SELECT e FROM Emprunt e WHERE e.client.id = :idClient", Emprunt.class);
        query.setParameter("idClient", idClient);
        List<Emprunt> emprunts = query.getResultList();

        IO.println("Emprunts du client id=" + idClient + " : " + emprunts.size());
        emprunts.forEach(e -> {
            IO.println("  " + e);
            e.getLivres().forEach(l -> IO.println("    -> " + l.getTitre()));
        });

    } catch (Exception e) {
        System.err.println("Erreur : " + e.getMessage());
    }
}

