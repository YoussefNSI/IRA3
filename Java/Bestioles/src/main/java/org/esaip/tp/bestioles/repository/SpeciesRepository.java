package org.esaip.tp.bestioles.repository;

import org.esaip.tp.bestioles.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    Optional<Species> findByCommonName(String commonName);
    Optional<Species> findByLatinName(String latinName);
}
