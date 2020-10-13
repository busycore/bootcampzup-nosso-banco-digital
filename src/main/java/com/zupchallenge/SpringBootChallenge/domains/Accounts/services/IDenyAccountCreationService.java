package com.zupchallenge.SpringBootChallenge.domains.Accounts.services;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;

public interface IDenyAccountCreationService {
    void execute(AccountProposals accountProposal);
}
