package org.esaip.tp.bestioles;

import org.esaip.tp.bestioles.repository.AnimalRepository;
import org.esaip.tp.bestioles.repository.PersonRepository;
import org.esaip.tp.bestioles.repository.RoleRepository;
import org.esaip.tp.bestioles.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BestiolesApplication implements CommandLineRunner {

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BestiolesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Espèces ===");
        speciesRepository.findAll().forEach(System.out::println);

        System.out.println("\n=== Animaux ===");
        animalRepository.findAll().forEach(System.out::println);

        System.out.println("\n=== Personnes ===");
        personRepository.findAll().forEach(System.out::println);

        System.out.println("\n=== Rôles ===");
        roleRepository.findAll().forEach(System.out::println);
    }
}
