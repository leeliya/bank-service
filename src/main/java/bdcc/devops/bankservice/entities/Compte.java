package bdcc.devops.bankservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compte {
    @Id
    private String id;
    private Date dateCreation;
    private Double solde;
    private String devise;
    @Enumerated(EnumType.STRING)
    private TypeCompte type;
}
