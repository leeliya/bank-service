package bdcc.devops.bankservice.repository;

import bdcc.devops.bankservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {
}
