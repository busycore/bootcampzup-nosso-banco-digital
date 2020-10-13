package com.zupchallenge.SpringBootChallenge.domains.Addresses.services;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Addresses.entities.Addresses;

public interface ICreateCustomerAddressService {

    Addresses execute(AccountProposals accountProposal, Addresses address);
}
