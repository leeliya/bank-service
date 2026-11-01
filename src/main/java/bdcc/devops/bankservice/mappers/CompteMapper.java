package bdcc.devops.bankservice.mappers;

import bdcc.devops.bankservice.dto.CompteRequestDTO;
import bdcc.devops.bankservice.dto.CompteResponseDTO;
import bdcc.devops.bankservice.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    public CompteResponseDTO fromCompte(Compte compte) {
        CompteResponseDTO dto = new CompteResponseDTO();
        BeanUtils.copyProperties(compte, dto);
        return dto;
    }

    public Compte fromRequest(CompteRequestDTO dto) {
        Compte compte = new Compte();
        BeanUtils.copyProperties(dto, compte);
        return compte;
    }
}