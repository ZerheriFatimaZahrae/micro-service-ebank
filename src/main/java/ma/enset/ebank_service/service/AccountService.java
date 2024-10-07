package ma.enset.ebank_service.service;

import ma.enset.ebank_service.dto.BankAccountRequestDTO;
import ma.enset.ebank_service.dto.BankAccountResponseDTO;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
