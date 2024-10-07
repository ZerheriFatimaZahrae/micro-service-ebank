package ma.enset.ebank_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.ebank_service.enums.AccountType;

import java.util.Date;
@Entity @Builder
@NoArgsConstructor @AllArgsConstructor @Data
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @ManyToOne()
    private Customer customer;

}
