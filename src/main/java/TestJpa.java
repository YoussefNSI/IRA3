import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TestJpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-essai");

        try (emf; EntityManager em = emf.createEntityManager()) {
            // Insérer un nouveau livre
            insertLivre(em, "Le Petit Prince", "Antoine de Saint-Exupéry");

            // Find simple par ID
            findLivreById(em, 1L);

            // Modifier le titre du livre ID=5
            updateLivreTitre(em, 5L, "Du plaisir dans la cuisine");

            // Requête JPQL par titre
            findLivreByTitre(em, "Le Petit Prince");

            // Requête JPQL par auteur
            findLivresByAuteur(em, "Antoine de Saint-Exupéry");

            // Supprimer un livre
            deleteLivre(em, 2L);

            // Afficher tous les livres
            afficherTousLesLivres(em);

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    private static void insertLivre(EntityManager em, String titre, String auteur) {
        em.getTransaction().begin();
        try {
            Livre livre = new Livre(titre, auteur);
            em.persist(livre);
            em.getTransaction().commit();
            System.out.println("Livre inséré : " + livre);
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erreur insertion : " + e.getMessage());
        }
    }

    private static void findLivreById(EntityManager em, Long id) {
        Livre livre = em.find(Livre.class, id);
        if (livre != null) {
            System.out.println("Livre trouvé : " + livre.getTitre() + " - " + livre.getAuteur());
        } else {
            System.out.println("Livre non trouvé (id=" + id + ")");
        }
    }

    private static void updateLivreTitre(EntityManager em, Long id, String nouveauTitre) {
        em.getTransaction().begin();
        try {
            Livre livre = em.find(Livre.class, id);
            if (livre != null) {
                livre.setTitre(nouveauTitre);
                em.getTransaction().commit();
                System.out.println("Titre modifié : " + nouveauTitre);
            } else {
                em.getTransaction().rollback();
                System.out.println("Livre non trouvé (id=" + id + ")");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erreur modification : " + e.getMessage());
        }
    }

    private static void findLivreByTitre(EntityManager em, String titre) {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l WHERE l.titre = :titre", Livre.class);
        query.setParameter("titre", titre);
        List<Livre> livres = query.getResultList();

        if (!livres.isEmpty()) {
            System.out.println("Livres trouvés (titre=" + titre + ") : " + livres.size());
            livres.forEach(System.out::println);
        } else {
            System.out.println("Aucun livre trouvé (titre=" + titre + ")");
        }
    }

    private static void findLivresByAuteur(EntityManager em, String auteur) {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l WHERE l.auteur = :auteur", Livre.class);
        query.setParameter("auteur", auteur);
        List<Livre> livres = query.getResultList();

        if (!livres.isEmpty()) {
            System.out.println("Livres de " + auteur + " : " + livres.size());
            livres.forEach(l -> System.out.println("  " + l.getTitre()));
        } else {
            System.out.println("Aucun livre trouvé (auteur=" + auteur + ")");
        }
    }

    private static void deleteLivre(EntityManager em, Long id) {
        em.getTransaction().begin();
        try {
            Livre livre = em.find(Livre.class, id);
            if (livre != null) {
                em.remove(livre);
                em.getTransaction().commit();
                System.out.println("Livre supprimé : " + livre);
            } else {
                em.getTransaction().rollback();
                System.out.println("Livre non trouvé (id=" + id + ")");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erreur suppression : " + e.getMessage());
        }
    }

    private static void afficherTousLesLivres(EntityManager em) {
        TypedQuery<Livre> query = em.createQuery("SELECT l FROM Livre l", Livre.class);
        List<Livre> livres = query.getResultList();

        System.out.println("Total livres : " + livres.size());
        livres.forEach(l -> System.out.println("  [" + l.getId() + "] " + l.getTitre() + " - " + l.getAuteur()));
    }
}

