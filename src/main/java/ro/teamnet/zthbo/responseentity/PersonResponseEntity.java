package ro.teamnet.zthbo.responseentity;

import lombok.Builder;
import lombok.Data;
import ro.teamnet.zthbo.dto.PersonDTO;

/**
 * Wrapper class peste personDTO, ca sa pot adauga informatii aditionale (gen exceptia)
 */
@Builder
@Data
public class PersonResponseEntity extends GenericResponseEntity {
    private PersonDTO personDTO;
    private RuntimeException exception;
}
