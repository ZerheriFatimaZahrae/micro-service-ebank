package ma.enset.ebank_service.web;

import jakarta.annotation.PostConstruct;
import ma.enset.ebank_service.dto.BankAccountRequestDTO;
import ma.enset.ebank_service.dto.BankAccountResponseDTO;
import ma.enset.ebank_service.entities.BankAccount;
import ma.enset.ebank_service.mappers.AccountMapper;
import ma.enset.ebank_service.repositories.BankAccountRepository;
import ma.enset.ebank_service.service.AccountService;
import ma.enset.ebank_service.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountServiceImpl accountService;
    // faire le mapper entre BankAccount et BankAccountResponseDTO
    @Autowired
    private AccountMapper accountMapper;

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount getBankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id).
                orElseThrow(()->new RuntimeException(String.format("Account %s not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount account) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow();
        if(account.getBalance()!=null)  bankAccount.setBalance(account.getBalance());
        if(account.getType()!=null)  bankAccount.setType(account.getType());
        if(account.getCurrency()!=null)  bankAccount.setCurrency(account.getCurrency());
        if(account.getCreatedAt()!=null)  bankAccount.setCreatedAt(new Date());
        return bankAccountRepository.save(bankAccount);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void delete(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}
