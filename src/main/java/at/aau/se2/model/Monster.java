package at.aau.se2.model;

import lombok.Getter;
import lombok.Setter;
@Getter
public abstract class Monster extends Tower{
    String name;
    @Setter
    int zone;
}
