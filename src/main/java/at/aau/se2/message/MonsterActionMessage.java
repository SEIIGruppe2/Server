package at.aau.se2.message;

import at.aau.se2.action.ActionType;
import at.aau.se2.model.Monster;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;


@Getter
public class MonsterActionMessage {
    @Setter
    private Monster monster;
    @Setter
    private ActionType action;
}
