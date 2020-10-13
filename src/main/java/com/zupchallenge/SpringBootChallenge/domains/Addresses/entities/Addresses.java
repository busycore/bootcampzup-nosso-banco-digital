package com.zupchallenge.SpringBootChallenge.domains.Addresses.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table
@Data
public class Addresses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,length = 11)
    @NotEmpty(message = "CEP Should not be empty")
    @Pattern(regexp = "\\d{5}-\\d{3}",message = "A Valid CEP should be provided(XXXXX-XXX)")
    private String cep;

    @Column(nullable = false)
    @NotEmpty(message = "Complement should not be empty")
    private String complement;

    @Column(nullable = false)
    @NotEmpty(message = "Street should not be empty")
    private String street;

    @Column(nullable = false)
    @NotEmpty(message = "Neighborhood should not be empty")
    private String neighborhood;

    @Column(nullable = false)
    @NotEmpty(message = "City should not be empty")
    private String city;

    @Column(nullable = false,length = 2)
    @NotEmpty(message = "State should not be empty")
    private String state;

    @OneToOne(mappedBy = "address",fetch = FetchType.LAZY)
    @JsonIgnore
    private AccountProposals accountProposal;
}
