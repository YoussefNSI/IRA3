package banque;

import banque.entite.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBanque {

    private static final Logger LOGGER = Logger.getLogger(TestBanque.class.getName());

    public static void main(String[] args) {
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

            // Association d'un compte à 2 clients
            Compte compte1 = new Compte("FR7612345000", 1500.0);
            compte1.setBanque(bnp);
            em.persist(compte1);

            Client client1 = new Client("Dupont", "Jean", LocalDate.of(1990, 5, 15));
            client1.setAdresse(new Adresse(10, "Rue de la Paix", 75002, "Paris"));
            client1.getComptes().add(compte1);
            em.persist(client1);

            Client client2 = new Client("Martin", "Sophie", LocalDate.of(1985, 3, 22));
            client2.setAdresse(new Adresse(25, "Avenue des Champs-Élysées", 75008, "Paris"));
            client2.getComptes().add(compte1);
            em.persist(client2);

            System.out.println("=== Compte associé à 2 clients ===");
            System.out.println(compte1 + " -> " + client1 + ", " + client2);

            // Client avec Livret A + Assurance Vie
            LivretA livretA = new LivretA("FR7612345001", 5000.0, 3.0);
            livretA.setBanque(bnp);
            em.persist(livretA);

            AssuranceVie assuranceVie = new AssuranceVie("FR7612345002", 20000.0,
                    LocalDate.of(2040, 1, 1), 1.5);
            assuranceVie.setBanque(bnp);
            em.persist(assuranceVie);

            Client client3 = new Client("Durand", "Pierre", LocalDate.of(1978, 11, 3));
            client3.setAdresse(new Adresse(5, "Rue Victor Hugo", 69002, "Lyon"));
            client3.getComptes().add(livretA);
            client3.getComptes().add(assuranceVie);
            em.persist(client3);

            System.out.println("=== Client avec LivretA + AssuranceVie ===");
            System.out.println(client3 + " -> " + livretA + ", " + assuranceVie);

            // Insertion de virements dans le compte1
            Virement virement1 = new Virement(LocalDateTime.now(), -200.0, "Loyer", "Dupont Pierre");
            virement1.setCompte(compte1);
            em.persist(virement1);

            Virement virement2 = new Virement(LocalDateTime.now(), -50.0, "Remboursement", "Martin Sophie");
            virement2.setCompte(compte1);
            em.persist(virement2);

            System.out.println("=== Virements ===");
            System.out.println(virement1);
            System.out.println(virement2);

            // Insertion d'opérations dans le compte1
            Operation op1 = new Operation(LocalDateTime.now(), -30.0, "Achat en ligne");
            op1.setCompte(compte1);
            em.persist(op1);

            Operation op2 = new Operation(LocalDateTime.now(), 1000.0, "Salaire");
            op2.setCompte(compte1);
            em.persist(op2);

            System.out.println("=== Opérations ===");
            System.out.println(op1);
            System.out.println(op2);

            tx.commit();
            System.out.println("\n=== Toutes les données ont été insérées avec succès ===");

        } catch (Exception e) {
            if (em != null) {
                EntityTransaction tx = em.getTransaction();
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
            LOGGER.log(Level.SEVERE, "Erreur pendant l'insertion des donnees banque", e);
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
