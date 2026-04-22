package org.esaip.tp.bestioles.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "age")
    private Integer age;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Column(name = "login", nullable = false, unique = true, length = 50)
    private String login;

    @Column(name = "mdp", nullable = false, length = 100)
    private String mdp;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToMany
    @JoinTable(
        name = "person_animals",
        joinColumns        = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "animals_id")
    )
    private List<Animal> animals;

    @ManyToMany
    @JoinTable(
        name = "person_role",
        joinColumns        = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public Person() {}

    public Person(String firstname, String lastname, String login, String mdp) {
        this.firstname = firstname;
        this.lastname  = lastname;
        this.login     = login;
        this.mdp       = mdp;
        this.active    = true;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getMdp() { return mdp; }
    public void setMdp(String mdp) { this.mdp = mdp; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public List<Animal> getAnimals() { return animals; }
    public void setAnimals(List<Animal> animals) { this.animals = animals; }

    public List<Role> getRoles() { return roles; }
    public void setRoles(List<Role> roles) { this.roles = roles; }

    @Override
    public String toString() {
        return "Person{id=" + id
                + ", firstname='" + firstname + "'"
                + ", lastname='" + lastname + "'"
                + ", login='" + login + "'"
                + ", age=" + age
                + ", active=" + active + "}";
    }
}
