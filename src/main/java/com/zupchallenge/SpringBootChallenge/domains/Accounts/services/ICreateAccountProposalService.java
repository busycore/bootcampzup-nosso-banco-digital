package com.zupchallenge.SpringBootChallenge.domains.Accounts.services;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Customers.entities.Customers;

public interface ICreateAccountProposalService {
    AccountProposals execute(Customers customer);
}
