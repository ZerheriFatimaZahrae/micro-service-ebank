package ma.enset.ebank_service.service;

import ma.enset.ebank_service.dto.BankAccountRequestDTO;
import ma.enset.ebank_service.dto.BankAccountResponseDTO;
import ma.enset.ebank_service.entities.BankAccount;
import ma.enset.ebank_service.mappers.AccountMapper;
import ma.enset.ebank_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service @Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    // faire le mapper entre BankAccount et BankAccountResponseDTO
    @Autowired
    private AccountMapper accountMapper;


    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        try {
            // Création de l'objet BankAccount
            BankAccount bankAccount = BankAccount.builder()
                    .id(UUID.randomUUID().toString())
                    .createdAt(new Date())
                    .balance(bankAccountDTO.getBalance())
                    .type(bankAccountDTO.getType())
                    .currency(bankAccountDTO.getCurrency())
                    .build();

            // Sauvegarde dans la base de données
            BankAccount savedAccount = bankAccountRepository.save(bankAccount);
            return accountMapper.fromBankAccount(savedAccount);
        } catch (Exception e) {
            e.printStackTrace(); // Affiche l'erreur dans la console
            throw e; // Relance l'exception pour la capture dans GraphQL
        }
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        try {
            // Recherche de l'objet BankAccount existant par ID
            BankAccount existingAccount = bankAccountRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Account not found"));

            // Mise à jour des attributs
            existingAccount.setBalance(bankAccountDTO.getBalance());
            existingAccount.setCurrency(bankAccountDTO.getCurrency());
            existingAccount.setType(bankAccountDTO.getType());

            // Sauvegarde de l'objet mis à jour dans la base de données
            BankAccount updatedAccount = bankAccountRepository.save(existingAccount);
            return accountMapper.fromBankAccount(updatedAccount);
        } catch (Exception e) {
            e.printStackTrace(); // Affiche l'erreur dans la console
            throw e; // Relance l'exception pour la capture dans GraphQL
        }
    }

}
