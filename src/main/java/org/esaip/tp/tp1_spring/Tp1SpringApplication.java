package org.esaip.tp.tp1_spring;

import jakarta.annotation.PostConstruct;
import org.esaip.tp.tp1_spring.service.BavardService;
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

    @Autowired
    private BavardService bavardService;

    public static void main(String[] args) {
        SpringApplication.run(Tp1SpringApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("=== @PostConstruct appelé : le bean BavardService est prêt ===");
        System.out.println(bavardService.parler());
    }

    @GetMapping("hello")
    public String hello() {
        System.out.println("3. Endpoint /hello appelé ! Appel de la méthode du bean : " + monBean.faireQuelqueChose());
        return "Bonjour, monde ! " + monBean.faireQuelqueChose();
    }

    @GetMapping("/blabla")
    public String blabla() {
        return bavardService.parler();
    }

}