package bdcc.devops.bankservice.web;

import bdcc.devops.bankservice.dto.CompteRequestDTO;
import bdcc.devops.bankservice.dto.CompteResponseDTO;
import bdcc.devops.bankservice.entities.Compte;
import bdcc.devops.bankservice.repository.CompteRepository;
import bdcc.devops.bankservice.service.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comptes")
public class CompteRestController {

    private final CompteService compteService;

    public CompteRestController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping
    public List<CompteResponseDTO> comptes() { return compteService.getAllComptes(); }

    @GetMapping("/{id}")
    public CompteResponseDTO compte(@PathVariable String id) { return compteService.getCompteById(id); }

    @PostMapping
    public CompteResponseDTO save(@RequestBody CompteRequestDTO compteDTO) { return compteService.addCompte(compteDTO); }

    @PutMapping("/{id}")
    public CompteResponseDTO update(@PathVariable String id, @RequestBody CompteRequestDTO compteDTO) {
        return compteService.updateCompte(id, compteDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) { compteService.deleteCompte(id); }
}