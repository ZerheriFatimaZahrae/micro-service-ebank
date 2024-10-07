package ma.enset.ebank_service.web;

import ma.enset.ebank_service.dto.BankAccountRequestDTO;
import ma.enset.ebank_service.dto.BankAccountResponseDTO;
import ma.enset.ebank_service.entities.BankAccount;
import ma.enset.ebank_service.entities.Customer;
import ma.enset.ebank_service.repositories.BankAccountRepository;
import ma.enset.ebank_service.repositories.CustomerRepository;
import ma.enset.ebank_service.service.AccountService;
import ma.enset.ebank_service.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private CustomerRepository customerRepository;
    // accountLIst meme nom qui eiste ds query ds shemagraphql
    @QueryMapping //si l utili veut executer query accountList executer cette fct
    public List<BankAccount> accountList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping //si l utili veut executer query accountList executer cette fct
    public List<Customer> customers(){
        return customerRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){
        return bankAccountRepository.findById(id).
                orElseThrow(()->new RuntimeException(String.format("BankAccount %s not found", id)));
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount) {
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id ,@Argument BankAccountRequestDTO bankAccount) {
        return accountService.updateAccount(id,bankAccount);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument String id ) {
        bankAccountRepository.deleteById(id);
        return true;
    }

    /*
    record BankAccount{

    }
    */

}
