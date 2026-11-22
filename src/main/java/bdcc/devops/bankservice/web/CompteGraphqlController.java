package bdcc.devops.bankservice.web;

import bdcc.devops.bankservice.dto.CompteRequestDTO;
import bdcc.devops.bankservice.dto.CompteResponseDTO;
import bdcc.devops.bankservice.entities.Client;
import bdcc.devops.bankservice.entities.Compte;
import bdcc.devops.bankservice.repository.ClientRepository;
import bdcc.devops.bankservice.repository.CompteRepository;
import bdcc.devops.bankservice.service.CompteService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CompteGraphqlController {

    private final CompteRepository compteRepository;
    private final CompteService compteService;
    private final ClientRepository clientRepository;

    public CompteGraphqlController(CompteRepository compteRepository, CompteService compteService,
                                   ClientRepository clientRepository) {
        this.compteRepository = compteRepository;
        this.compteService = compteService;
        this.clientRepository = clientRepository;
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

    @QueryMapping
    public List<Client> clients() {
        return clientRepository.findAll();
    }

    @MutationMapping
    public CompteResponseDTO addCompte(@Argument CompteRequestDTO compte) {
        return compteService.addCompte(compte);
    }

    @MutationMapping
    public CompteResponseDTO updateCompte(@Argument String id, @Argument CompteRequestDTO compte) {
        return compteService.updateCompte(id, compte);
    }

    @MutationMapping
    public Boolean deleteCompte(@Argument String id) {
        compteService.deleteCompte(id);
        return true;
    }
}
