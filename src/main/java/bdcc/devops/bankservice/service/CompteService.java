package bdcc.devops.bankservice.service;

import bdcc.devops.bankservice.dto.CompteRequestDTO;
import bdcc.devops.bankservice.dto.CompteResponseDTO;

import java.util.List;

public interface CompteService {
    CompteResponseDTO addCompte(CompteRequestDTO compteDTO);
    CompteResponseDTO updateCompte(String id, CompteRequestDTO compteDTO);
    List<CompteResponseDTO> getAllComptes();
    CompteResponseDTO getCompteById(String id);
    void deleteCompte(String id);
}