package com.zupchallenge.SpringBootChallenge.domains.Accounts.services.implementations;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.enums.AccountProposalStatus;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.repositories.AccountProposalsRepository;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.services.IAddAddressToAccountProposalService;
import com.zupchallenge.SpringBootChallenge.domains.Addresses.entities.Addresses;
import org.springframework.stereotype.Service;

@Service
public class AddAddressToAccountProposalervice implements IAddAddressToAccountProposalService {

    private AccountProposalsRepository accountProposalsRepository;

    public AddAddressToAccountProposalervice(AccountProposalsRepository accountProposalsRepository) {
        this.accountProposalsRepository = accountProposalsRepository;
    }

    @Override
    public void execute(AccountProposals accountProposal, Addresses address) {
        accountProposal.setAddress(address);
        accountProposal.setStatus(AccountProposalStatus.WAITING_CPF);
        accountProposalsRepository.save(accountProposal);
    }
}
