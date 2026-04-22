package org.esaip.tp.bestioles.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "common_name", nullable = false, length = 50)
    private String commonName;

    @Column(name = "latin_name", nullable = false, length = 200)
    private String latinName;

    @OneToMany(mappedBy = "species", fetch = FetchType.LAZY)
    private List<Animal> animals;

    public Species() {}

    public Species(String commonName, String latinName) {
        this.commonName = commonName;
        this.latinName  = latinName;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCommonName() { return commonName; }
    public void setCommonName(String commonName) { this.commonName = commonName; }

    public String getLatinName() { return latinName; }
    public void setLatinName(String latinName) { this.latinName = latinName; }

    public List<Animal> getAnimals() { return animals; }
    public void setAnimals(List<Animal> animals) { this.animals = animals; }

    @Override
    public String toString() {
        return "Species{id=" + id
                + ", commonName='" + commonName + "'"
                + ", latinName='" + latinName + "'}";
    }
}
