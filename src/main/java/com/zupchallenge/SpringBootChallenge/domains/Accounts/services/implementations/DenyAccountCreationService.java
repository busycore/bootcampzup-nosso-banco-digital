package com.zupchallenge.SpringBootChallenge.domains.Accounts.services.implementations;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.enums.AccountProposalStatus;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.repositories.AccountProposalsRepository;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.services.IDenyAccountCreationService;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.BusinessRuleError;
import com.zupchallenge.SpringBootChallenge.shared.providers.MailSendProvider.IMailSendProvider;
import org.springframework.stereotype.Service;

@Service
public class DenyAccountCreationService implements IDenyAccountCreationService {

    private IMailSendProvider mailSendProvider;
    private AccountProposalsRepository accountProposalsRepository;

    public DenyAccountCreationService(IMailSendProvider mailSendProvider, AccountProposalsRepository accountProposalsRepository) {
        this.mailSendProvider = mailSendProvider;
        this.accountProposalsRepository = accountProposalsRepository;
    }


    @Override
    public void execute(AccountProposals accountProposal) {


        if(accountProposal.getStatus().equals(AccountProposalStatus.COMPLETED)){
            throw new BusinessRuleError("This Account is created already.");
        }

        accountProposal.setStatus(AccountProposalStatus.DENIED);
        accountProposalsRepository.save(accountProposal);

        //Send email using our fake mail provider
        mailSendProvider.sendMail("nosso-banco@zupbootcamp.com",
                accountProposal.getCustomer().getEmail(),
                "Por favor aceite a criação da conta!!!");

    }
}
