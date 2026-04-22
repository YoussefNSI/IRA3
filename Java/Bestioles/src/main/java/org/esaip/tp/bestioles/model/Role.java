package org.esaip.tp.bestioles.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "label", nullable = false, length = 50)
    private String label;

    @ManyToMany(mappedBy = "roles")
    private List<Person> persons;

    public Role() {}

    public Role(String label) {
        this.label = label;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public List<Person> getPersons() { return persons; }
    public void setPersons(List<Person> persons) { this.persons = persons; }

    @Override
    public String toString() {
        return "Role{id=" + id + ", label='" + label + "'}";
    }
}
