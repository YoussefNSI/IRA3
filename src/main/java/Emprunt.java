import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "EMPRUNT")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "delai_max")
    private Integer delaiMax;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;

    @ManyToMany
    @JoinTable(
        name = "COMPO",
        joinColumns = @JoinColumn(name = "id_emprunt"),
        inverseJoinColumns = @JoinColumn(name = "id_livre")
    )
    private List<Livre> livres = new java.util.ArrayList<>();

    public Emprunt() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public Integer getDelaiMax() { return delaiMax; }
    public void setDelaiMax(Integer delaiMax) { this.delaiMax = delaiMax; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public List<Livre> getLivres() { return livres; }
    public void setLivres(List<Livre> livres) { this.livres = livres; }

    @Override
    public String toString() {
        return "Emprunt{id=" + id + ", dateDebut=" + dateDebut + ", delaiMax=" + delaiMax + ", dateFin=" + dateFin + "}";
    }
}

