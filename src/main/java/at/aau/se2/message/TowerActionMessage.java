package at.aau.se2.message;

import at.aau.se2.action.ActionType;
import at.aau.se2.model.Tower;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
public class TowerActionMessage {

    @Setter
    private Tower tower;
    @Setter
    private ActionType action;



}

