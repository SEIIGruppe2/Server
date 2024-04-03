package at.aau.se2.controller;

import at.aau.se2.action.ActionType;
import at.aau.se2.message.MonsterActionMessage;
import at.aau.se2.model.Monster;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import javax.swing.*;

@Controller
public class MonsterController {

    @MessageMapping("/monster-action")
    public void handleMonsterAction(@Payload MonsterActionMessage actionMessage) {
        Monster monster = actionMessage.getMonster();
        ActionType action = actionMessage.getAction();

    }
}


