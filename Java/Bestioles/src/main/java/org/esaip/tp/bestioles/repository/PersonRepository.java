package org.esaip.tp.bestioles.repository;

import org.esaip.tp.bestioles.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByLogin(String login);
    List<Person> findByLastname(String lastname);
    List<Person> findByActiveTrue();
}

