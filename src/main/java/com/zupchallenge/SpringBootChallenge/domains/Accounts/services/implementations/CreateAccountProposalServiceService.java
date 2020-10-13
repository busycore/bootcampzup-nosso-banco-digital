package com.zupchallenge.SpringBootChallenge.domains.Accounts.services.implementations;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.enums.AccountProposalStatus;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.repositories.AccountProposalsRepository;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.services.ICreateAccountProposalService;
import com.zupchallenge.SpringBootChallenge.domains.Customers.entities.Customers;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountProposalServiceService implements ICreateAccountProposalService {

    private AccountProposalsRepository accountProposalsRepository;

    public CreateAccountProposalServiceService(AccountProposalsRepository accountProposalsRepository) {
        this.accountProposalsRepository = accountProposalsRepository;
    }

    @Override
    public AccountProposals execute(Customers customer) {
        //Create the AccountProposal
        AccountProposals accountProposal = new AccountProposals();

        accountProposal.setCustomer(customer);
        accountProposal.setStatus(AccountProposalStatus.WAITING_ADDRESS);

        AccountProposals NewAccountProposal = accountProposalsRepository.save(accountProposal);
        return NewAccountProposal;
    }
}
