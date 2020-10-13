package com.zupchallenge.SpringBootChallenge.domains.Customers.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.shared.validations.validDate.ValidDate;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table
@EnableJpaAuditing
@Data
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "You Should provide a valid name")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "You Should provide a valid surname")
    private String surname;

	//TODO CHECK A BETTER CASCADING VALIDATION
    @Column(nullable = false)
    @NotNull(message = "You should provide a valid date")
    @Past(message = "The date should be in the past")
    @ValidDate(message = "You should be older than 18 years")
    private LocalDate birth_date;

    @Column(unique = true,nullable = false,length = 11)
    @NotEmpty(message = "CPF Should be provided")
    @CPF(message = "A valid CPF is required")
    private String cpf;

    @Column(unique = true,nullable = false)
    @NotEmpty(message = "You should provide an email")
    @Email(message = "You should provide a valid email")
    private String email;

    @OneToOne(mappedBy = "customer",fetch = FetchType.LAZY)
    @JsonIgnore
    private AccountProposals accountProposal;

}
