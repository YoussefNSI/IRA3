package org.esaip.tp.bestioles.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "sex", nullable = false, length = 255)
    private String sex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;

    @ManyToMany(mappedBy = "animals")
    private List<Person> persons;

    public Animal() {}

    public Animal(String name, String sex, String color, Species species) {
        this.name    = name;
        this.sex     = sex;
        this.color   = color;
        this.species = species;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public Species getSpecies() { return species; }
    public void setSpecies(Species species) { this.species = species; }

    public List<Person> getPersons() { return persons; }
    public void setPersons(List<Person> persons) { this.persons = persons; }

    @Override
    public String toString() {
        return "Animal{id=" + id
                + ", name='" + name + "'"
                + ", sex='" + sex + "'"
                + ", color='" + color + "'}";
    }
}
