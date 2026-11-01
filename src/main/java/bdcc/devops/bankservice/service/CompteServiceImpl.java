package bdcc.devops.bankservice.service;

import bdcc.devops.bankservice.dto.CompteRequestDTO;
import bdcc.devops.bankservice.dto.CompteResponseDTO;
import bdcc.devops.bankservice.entities.Compte;
import bdcc.devops.bankservice.mappers.CompteMapper;
import bdcc.devops.bankservice.repository.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    public CompteResponseDTO addCompte(CompteRequestDTO compteDTO) {
        Compte compte = compteMapper.fromRequest(compteDTO);
        compte.setId(UUID.randomUUID().toString());
        compte.setDateCreation(new Date());
        return compteMapper.fromCompte(compteRepository.save(compte));
    }

    @Override
    public CompteResponseDTO updateCompte(String id, CompteRequestDTO compteDTO) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));
        if (compteDTO.getSolde() != null) compte.setSolde(compteDTO.getSolde());
        if (compteDTO.getDevise() != null) compte.setDevise(compteDTO.getDevise());
        if (compteDTO.getType() != null) compte.setType(compteDTO.getType());
        return compteMapper.fromCompte(compteRepository.save(compte));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompteResponseDTO> getAllComptes() {
        return compteRepository.findAll().stream().map(compteMapper::fromCompte).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CompteResponseDTO getCompteById(String id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Compte %s introuvable", id)));
        return compteMapper.fromCompte(compte);
    }

    @Override
    public void deleteCompte(String id) {
        compteRepository.deleteById(id);
    }
}