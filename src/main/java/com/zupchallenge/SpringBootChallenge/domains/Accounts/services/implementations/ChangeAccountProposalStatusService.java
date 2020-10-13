package com.zupchallenge.SpringBootChallenge.domains.Accounts.services.implementations;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.enums.AccountProposalStatus;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.repositories.AccountProposalsRepository;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.services.IChangeAccountProposalStatusService;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.NotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangeAccountProposalStatusService implements IChangeAccountProposalStatusService {

    @Autowired
    private AccountProposalsRepository accountProposalsRepository;


    @Override
    public void setStatus(Long id, AccountProposalStatus Status) {

        Optional<AccountProposals> accountProp = accountProposalsRepository.findById(id);

        if(!accountProp.isPresent()){
            throw new NotExistsException("This account proposal does not exists");
        }

        accountProp.get().setStatus(Status);

        accountProposalsRepository.save(accountProp.get());
    }
}
