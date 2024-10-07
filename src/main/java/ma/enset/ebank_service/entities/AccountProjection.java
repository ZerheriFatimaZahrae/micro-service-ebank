package ma.enset.ebank_service.entities;

import ma.enset.ebank_service.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;


// ce class sert a faire des projections dans Bankaccount c ad acceder seulement pas exp au
// id et  type
@Projection(types = BankAccount.class , name = "p1")
public interface AccountProjection {
    String getId();
    public AccountType getType();
    public Double getBalance();
}
