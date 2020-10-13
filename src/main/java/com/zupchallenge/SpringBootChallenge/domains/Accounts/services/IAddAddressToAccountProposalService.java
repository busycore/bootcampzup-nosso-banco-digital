package com.zupchallenge.SpringBootChallenge.domains.Accounts.services;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Addresses.entities.Addresses;

public interface IAddAddressToAccountProposalService {
    void execute(AccountProposals accountProposal, Addresses address);
}
