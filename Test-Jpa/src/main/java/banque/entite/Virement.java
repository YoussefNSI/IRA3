package banque.entite;

import jakarta.persistence.*;

@Entity
@Table(name = "VIREMENT")
public class Virement extends Operation {

    private String beneficiaire;

    public Virement() {}

    public Virement(java.time.LocalDateTime date, double montant, String motif, String beneficiaire) {
        super(date, montant, motif);
        this.beneficiaire = beneficiaire;
    }

    public String getBeneficiaire() { return beneficiaire; }
    public void setBeneficiaire(String beneficiaire) { this.beneficiaire = beneficiaire; }

    @Override
    public String toString() {
        return "Virement{" + super.toString() + ", beneficiaire='" + beneficiaire + "'}";
    }
}
