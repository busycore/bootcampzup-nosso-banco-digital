package com.zupchallenge.SpringBootChallenge.domains.Accounts.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.enums.AccountProposalStatus;
import com.zupchallenge.SpringBootChallenge.domains.Addresses.entities.Addresses;
import com.zupchallenge.SpringBootChallenge.domains.Customers.entities.Customers;
import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class AccountProposals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String CPFImagePictureURL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountProposalStatus Status = AccountProposalStatus.INCOMPLETE;

    @OneToOne
    @JoinColumn(name = "address")
    private Addresses address;

    @OneToOne(mappedBy = "accountProposals",fetch = FetchType.LAZY)
    @JsonIgnore
    private Accounts account;

    @OneToOne
    @JoinColumn(name="customer")
    private Customers customer;

}
