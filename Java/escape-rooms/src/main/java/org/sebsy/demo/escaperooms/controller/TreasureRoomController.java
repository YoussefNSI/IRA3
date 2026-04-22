package org.sebsy.demo.escaperooms.controller;

import org.sebsy.demo.escaperooms.bll.TreasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TreasureRoomController {
    private TreasureService tresorService;

    public String fin() {
        return tresorService.ouvrir();
    }

    @Autowired
    public void setTresorService(TreasureService tresorService) {
        this.tresorService = tresorService;
    }
}
