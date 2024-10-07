package ma.enset.ebank_service.repositories;

import ma.enset.ebank_service.entities.BankAccount;
import ma.enset.ebank_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long > {
}
