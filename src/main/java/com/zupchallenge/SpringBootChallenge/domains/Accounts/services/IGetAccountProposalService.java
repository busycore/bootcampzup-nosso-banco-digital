package com.zupchallenge.SpringBootChallenge.domains.Accounts.services;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;

public interface IGetAccountProposalService {
    AccountProposals execute(Long id);
}
