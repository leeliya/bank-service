package bdcc.devops.bankservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

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

    @PrePersist
    void prePersist() {
        if (id == null) id = UUID.randomUUID().toString();
        if (dateCreation == null) dateCreation = new Date();
    }
}
