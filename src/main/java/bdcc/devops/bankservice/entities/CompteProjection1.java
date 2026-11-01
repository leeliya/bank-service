package bdcc.devops.bankservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1", types = Compte.class)
public interface CompteProjection1 {
    String getId();
    TypeCompte getType();
}
