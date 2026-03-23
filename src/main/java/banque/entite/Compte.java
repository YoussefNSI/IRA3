package banque.entite;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "COMPTE")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private double solde;

    @ManyToOne
    @JoinColumn(name = "banque_id")
    private Banque banque;

    @ManyToMany(mappedBy = "comptes")
    private List<Client> clients = new ArrayList<>();

    @OneToMany(mappedBy = "compte")
    private List<Operation> operations = new ArrayList<>();

    public Compte() {}

    public Compte(String numero, double solde) {
        this.numero = numero;
        this.solde = solde;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
    public Banque getBanque() { return banque; }
    public void setBanque(Banque banque) { this.banque = banque; }
    public List<Client> getClients() { return clients; }
    public void setClients(List<Client> clients) { this.clients = clients; }
    public List<Operation> getOperations() { return operations; }
    public void setOperations(List<Operation> operations) { this.operations = operations; }

    @Override
    public String toString() {
        return "Compte{id=" + id + ", numero='" + numero + "', solde=" + solde + "}";
    }
}
