package bdcc.devops.bankservice.repository;

import bdcc.devops.bankservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
