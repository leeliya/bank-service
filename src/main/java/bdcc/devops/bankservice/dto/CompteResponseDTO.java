package bdcc.devops.bankservice.dto;

import bdcc.devops.bankservice.entities.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteResponseDTO {
    private String id;
    private Date dateCreation;
    private Double solde;
    private String devise;
    private TypeCompte type;
}
