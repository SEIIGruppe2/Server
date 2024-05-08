package at.aau.se2.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.TextMessage;

@Getter
@Setter
public abstract class ParentDTO {
    private ObjectMapper mapper;
    private final String type;
    public ParentDTO(String type){
        this.mapper = new ObjectMapper();
        this.type = type;
    }
    public abstract TextMessage makeMessage();
}
