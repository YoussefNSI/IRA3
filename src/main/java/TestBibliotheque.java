import jakarta.persistence.*;
import java.util.List;

public class TestBibliotheque {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-essai");
        EntityManager em = emf.createEntityManager();

        try {
            // Emprunt et ses livres associés
            Long idEmprunt = 1L;
            Emprunt emprunt = em.find(Emprunt.class, idEmprunt);
            if (emprunt != null) {
                System.out.println("Emprunt : " + emprunt);
                System.out.println("Livres associés :");
                emprunt.getLivres().forEach(l -> System.out.println("  " + l));
            } else {
                System.out.println("Emprunt non trouvé (id=" + idEmprunt + ")");
            }

            System.out.println();

            // Tous les emprunts d'un client donné
            Long idClient = 1L;
            TypedQuery<Emprunt> query = em.createQuery(
                "SELECT e FROM Emprunt e WHERE e.client.id = :idClient", Emprunt.class);
            query.setParameter("idClient", idClient);
            List<Emprunt> emprunts = query.getResultList();

            System.out.println("Emprunts du client id=" + idClient + " : " + emprunts.size());
            emprunts.forEach(e -> {
                System.out.println("  " + e);
                e.getLivres().forEach(l -> System.out.println("    -> " + l.getTitre()));
            });

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}

