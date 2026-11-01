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
public class CompteRequestDTO {
    private Double solde;
    private String devise;
    private TypeCompte type;
}