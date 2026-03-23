package banque;

import banque.entite.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestBanque {

    static void main() {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("banque");
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            tx.begin();

            // Création de la banque
            Banque bnp = new Banque("BNP Paribas");
            em.persist(bnp);

            // Création des comptes
            Compte compte1 = new Compte("FR7612345000", 1500.0);
            compte1.setBanque(bnp);
            em.persist(compte1);

            LivretA livretA = new LivretA("FR7612345001", 5000.0, 3.0);
            livretA.setBanque(bnp);
            em.persist(livretA);

            AssuranceVie assuranceVie = new AssuranceVie("FR7612345002", 20000.0,
                    LocalDate.of(2040, 1, 1), 1.5);
            assuranceVie.setBanque(bnp);
            em.persist(assuranceVie);

            // Création de clients avec adresse
            Client client1 = new Client("Dupont", "Jean", LocalDate.of(1990, 5, 15));
            client1.setAdresse(new Adresse(10, "Rue de la Paix", 75002, "Paris"));
            client1.getComptes().add(compte1);
            client1.getComptes().add(livretA);
            em.persist(client1);

            Client client2 = new Client("Martin", "Sophie", LocalDate.of(1985, 3, 22));
            client2.setAdresse(new Adresse(25, "Avenue des Champs-Élysées", 75008, "Paris"));
            client2.getComptes().add(compte1);
            client2.getComptes().add(assuranceVie);
            em.persist(client2);

            // Création d'opérations
            Operation op1 = new Operation(LocalDateTime.now(), -50.0, "Achat en ligne");
            op1.setCompte(compte1);
            em.persist(op1);

            Virement virement = new Virement(LocalDateTime.now(), -200.0, "Loyer", "Dupont Pierre");
            virement.setCompte(compte1);
            em.persist(virement);

            tx.commit();

            System.out.println("=== Données insérées avec succès ===");
            System.out.println(bnp);
            System.out.println(compte1);
            System.out.println(livretA);
            System.out.println(assuranceVie);
            System.out.println(client1);
            System.out.println(client2);
            System.out.println(op1);
            System.out.println(virement);
        } catch (Exception e) {
            if (em != null) {
                EntityTransaction tx = em.getTransaction();
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
            System.err.println("Erreur pendant l'insertion des données : " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}
