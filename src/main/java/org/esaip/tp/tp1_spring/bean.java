package org.esaip.tp.tp1_spring;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class bean {

    public bean() {
        System.out.println("Constructeur de la classe 'bean' appelé.");
    }

    @PostConstruct
    public void init() {
        System.out.println("Méthode @PostConstruct appelée ! (Le bean est prêt et injecté)");
    }

    public String faireQuelqueChose() {
        return "Le bean fonctionne !";
    }
}
