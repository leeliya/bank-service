package bdcc.devops.bankservice.web;

import bdcc.devops.bankservice.entities.Compte;
import bdcc.devops.bankservice.repository.CompteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comptes")
public class CompteRestController {
    private final CompteRepository compteRepository;

    public CompteRestController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @GetMapping
    public List<Compte> comptes() {
        return compteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Compte compte(@PathVariable String id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));
    }

    @PostMapping
    public Compte save(@RequestBody Compte compte) {
        if (compte.getId() == null) compte.setId(UUID.randomUUID().toString());
        if (compte.getDateCreation() == null) compte.setDateCreation(new Date());
        return compteRepository.save(compte);
    }

    @PutMapping("/{id}")
    public Compte update(@PathVariable String id, @RequestBody Compte compte) {
        Compte existant = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));
        if (compte.getSolde() != null) existant.setSolde(compte.getSolde());
        if (compte.getDateCreation() != null) existant.setDateCreation(compte.getDateCreation());
        if (compte.getDevise() != null) existant.setDevise(compte.getDevise());
        if (compte.getType() != null) existant.setType(compte.getType());
        return compteRepository.save(existant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        compteRepository.deleteById(id);
    }
}
