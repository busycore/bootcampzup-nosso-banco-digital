package com.zupchallenge.SpringBootChallenge.domains.Accounts.services.implementations;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.repositories.AccountProposalsRepository;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.services.IGetAccountProposalService;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.IncompleteStepsException;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.NotExistsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetAccountProposalService implements IGetAccountProposalService {

    private AccountProposalsRepository accountProposalsRepository;

    public GetAccountProposalService(AccountProposalsRepository accountProposalsRepository) {
        this.accountProposalsRepository = accountProposalsRepository;
    }

    @Override
    public AccountProposals execute(Long id) {
        Optional<AccountProposals> accountProposal = accountProposalsRepository.findById(id);

        if(!accountProposal.isPresent()){
            throw new NotExistsException("This account proposal does not exists");
        }

        if (accountProposal.get().getStatus().ordinal()<1){
            throw new IncompleteStepsException("You should complete the other steps first");
        }
        return accountProposal.get();
    }
}
