package bdcc.devops.bankservice;

import bdcc.devops.bankservice.entities.Compte;
import bdcc.devops.bankservice.entities.TypeCompte;
import bdcc.devops.bankservice.repository.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                Compte compte = Compte.builder()
                        .id(UUID.randomUUID().toString())
                        .type(Math.random() > 0.5 ? TypeCompte.COURANT : TypeCompte.EPARGNE)
                        .solde(1000 + Math.random() * 90000)
                        .dateCreation(new Date())
                        .devise("MAD")
                        .build();
                compteRepository.save(compte);
            }
            compteRepository.findAll().forEach(c ->
                    System.out.println(c.getId() + " | " + c.getType() + " | " + c.getSolde()));
        };
    }
}
