package ma.enset.ebank_service.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.ebank_service.enums.AccountType;

import java.util.Date;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class BankAccountRequestDTO { //ds request on va pas envoyer id et pour la date c est la date de system
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
}
