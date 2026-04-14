package org.esaip.tp.tp1_spring.service;

import org.springframework.stereotype.Service;

@Service
public class BavardService {

    private String nom = "Jean Bavard";

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String parler() {
        return "Classe : " + this.getClass().getSimpleName() + " | Nom : " + nom;
    }
}

