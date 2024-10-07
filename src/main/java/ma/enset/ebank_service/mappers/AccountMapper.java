package ma.enset.ebank_service.mappers;

import ma.enset.ebank_service.dto.BankAccountResponseDTO;
import ma.enset.ebank_service.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
     public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
         BankAccountResponseDTO bankAccountResponseDTO=new BankAccountResponseDTO();
         BeanUtils.copyProperties(bankAccount , bankAccountResponseDTO);
         return bankAccountResponseDTO;

     }
}
