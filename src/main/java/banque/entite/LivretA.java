package banque.entite;

import jakarta.persistence.*;

@Entity
@Table(name = "LIVRET_A")
public class LivretA extends Compte {

    private double taux;

    public LivretA() {}

    public LivretA(String numero, double solde, double taux) {
        super(numero, solde);
        this.taux = taux;
    }

    public double getTaux() { return taux; }
    public void setTaux(double taux) { this.taux = taux; }

    @Override
    public String toString() {
        return "LivretA{" + super.toString() + ", taux=" + taux + "}";
    }
}
