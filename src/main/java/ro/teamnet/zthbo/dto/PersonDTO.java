package ro.teamnet.zthbo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonDTO {
    private String nume;
    private String prenume;
    private String varsta;
    private String oras;

//    @JsonIgnore
    @Builder.Default
    private String ignored = "ignored";

}
