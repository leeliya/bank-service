package bdcc.devops.bankservice.web;

import bdcc.devops.bankservice.entities.Compte;
import bdcc.devops.bankservice.repository.CompteRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CompteGraphqlController {

    private final CompteRepository compteRepository;

    public CompteGraphqlController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @QueryMapping
    public List<Compte> listComptes() {
        return compteRepository.findAll();
    }

    @QueryMapping
    public Compte compteById(@Argument String id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));
    }
}
