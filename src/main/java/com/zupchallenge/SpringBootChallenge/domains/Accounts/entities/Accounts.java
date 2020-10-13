package com.zupchallenge.SpringBootChallenge.domains.Accounts.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Data
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer agency;
    @Column(nullable = false)
    private Integer accountNumber;
    @Column(nullable = false)
    private Integer bankCode;
    @Column(nullable = false)
    private BigDecimal moneyAmmount= BigDecimal.valueOf(00.00);

    @OneToOne
    @JoinColumn(name = "accountProposal")
    private AccountProposals accountProposals;


}
