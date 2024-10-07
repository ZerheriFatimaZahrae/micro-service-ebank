package ma.enset.ebank_service;

import ma.enset.ebank_service.entities.BankAccount;
import ma.enset.ebank_service.entities.Customer;
import ma.enset.ebank_service.enums.AccountType;
import ma.enset.ebank_service.repositories.BankAccountRepository;
import ma.enset.ebank_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankServiceApplication.class, args);
	}
    @Bean  //etre appeler au demarage
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
       return args -> {
		   Stream.of("Mohmed","yassine","Fatima").forEach(c->{
			    Customer customer = Customer.builder()
						.name(c)
						.build();
                 customerRepository.save(customer);
		   });

		   customerRepository.findAll().forEach(
				   customer -> {
					   for (int i = 0; i < 10; i++) {
						   BankAccount bankAccount=BankAccount.builder()
								   .id(UUID.randomUUID().toString())
								   .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
								   .balance(100000+Math.random()*90000)
								   .currency("MAD")
								   .createdAt(new Date())
								   .customer(customer)
								   .build();

						   bankAccountRepository.save(bankAccount);
					   }

				   }
		   );


		   
	   };
	}
}
