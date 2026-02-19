import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJpa {
    static void main() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-essai");
        EntityManager em = emf.createEntityManager();

        if (em.isOpen()) {
            System.out.println("Connexion à la base de données réussie !");
        } else {
            System.out.println("Échec de la connexion.");
        }



        em.close();
        emf.close();
    }
}
