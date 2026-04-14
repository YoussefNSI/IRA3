package org.esaip.tp.tp1_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@RestController
public class Tp1SpringApplication {

    @Autowired
    private bean monBean;

    public static void main(String[] args) {
        SpringApplication.run(Tp1SpringApplication.class, args);
    }

    @GetMapping("hello")
    public String hello() {
        System.out.println("3. Endpoint /hello appelé ! Appel de la méthode du bean : " + monBean.faireQuelqueChose());
        return "Bonjour, monde ! " + monBean.faireQuelqueChose();
    }

}