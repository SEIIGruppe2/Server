package at.aau.se2.controller;

import at.aau.se2.action.ActionType;
import at.aau.se2.message.CharacterActionMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import javax.swing.*;


@Controller
public class CharacterController {


    @MessageMapping("/character-action") // Empfängt Nachrichten mit diesem Ziel
    public void handleCharacterAction(@Payload CharacterActionMessage actionMessage) {
        // Hier können die empfangene Nachricht verarbeiten und mit dem entsprechenden Charakter interagieren
        Character character = actionMessage.getCharacter();
        ActionType action = actionMessage.getAction();

        // Führe die Aktion mit dem Charakter aus
        // ...

        // Sende eine Antwort oder aktualisieren Sie andere Clients über den MessageBroker
        // ...
    }

}
