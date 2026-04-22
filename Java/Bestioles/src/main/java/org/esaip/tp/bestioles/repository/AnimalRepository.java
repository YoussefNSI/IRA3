package org.esaip.tp.bestioles.repository;

import org.esaip.tp.bestioles.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findByName(String name);
    List<Animal> findBySex(String sex);
    List<Animal> findBySpecies_CommonName(String commonName);
}
