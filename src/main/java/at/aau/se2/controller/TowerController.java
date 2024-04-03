package at.aau.se2.controller;


import at.aau.se2.action.ActionType;
import at.aau.se2.message.TowerActionMessage;
import at.aau.se2.model.Tower;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import javax.swing.*;

@Controller
public class TowerController {

    @MessageMapping("/tower-action")
    public void handleTowerAction(@Payload TowerActionMessage actionMessage) {
        Tower tower = actionMessage.getTower();
        ActionType action = actionMessage.getAction();


    }


}
