package ma.enset.ebank_service.repositories;

import ma.enset.ebank_service.entities.BankAccount;
import ma.enset.ebank_service.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource //dire a spring de creer un rest controller pour entity BankAccount
public interface BankAccountRepository extends JpaRepository<BankAccount,String > {
    List<BankAccount> findByCurrency(String Currency);
    @RestResource(path = "/byType")//changer le nom de la methode pour laceder pas data rest
    // uri sera localhost:8081/search/byType?t=...
    List<BankAccount> findByType(@Param("t") AccountType type);
}
