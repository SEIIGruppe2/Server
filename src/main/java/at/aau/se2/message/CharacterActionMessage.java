package at.aau.se2.message;

import at.aau.se2.action.ActionType;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.beans.ConstructorProperties;


@Getter
public class CharacterActionMessage {

    @Setter
    private Character character;
    @Setter
    private ActionType action;


}
