package com.zupchallenge.SpringBootChallenge.domains.Accounts.services;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.Accounts;

public interface ICreateAccountService {
    Accounts execute(AccountProposals accountProposals);
}
