package at.aau.se2.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.TextMessage;

/**
 * The ParentDTO class is an abstract class representing a parent data transfer object (DTO).
 * It provides common functionality and attributes for all DTOs.
 */
@Getter
@Setter
public abstract class ParentDTO {
    private ObjectMapper mapper;
    private final String type;

    /**
     * Constructs a new ParentDTO object with the specified type.
     *
     * @param type the type of the DTO
     */
    public ParentDTO(String type){
        this.mapper = new ObjectMapper();
        this.type = type;
    }

    /**
     * Abstract method to create a TextMessage representing the DTO.
     *
     * @return a TextMessage representing the DTO
     */
    public abstract TextMessage makeMessage();
}
