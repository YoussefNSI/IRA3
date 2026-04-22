package org.sebsy.demo.escaperooms.boot;

import org.sebsy.demo.escaperooms.controller.EscapeRoom1Controller;
import org.sebsy.demo.escaperooms.controller.EscapeRoom2Controller;
import org.sebsy.demo.escaperooms.controller.TreasureRoomController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Startup implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    @Autowired
    public Startup(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Entrée dans la salle 1 : ");
        EscapeRoom1Controller room1 = applicationContext.getBean(EscapeRoom1Controller.class);
        room1.entreeSalle1();

        System.out.println("Entrée dans la salle 2 : ");
        EscapeRoom2Controller room2 = (EscapeRoom2Controller) applicationContext.getBean("room2");
        room2.entreeSalle2();

        System.out.println("Entrée dans la salle du trésor : ");
        TreasureRoomController treasureRoom = applicationContext.getBean(TreasureRoomController.class);
        System.out.println(treasureRoom.fin());
    }
}
