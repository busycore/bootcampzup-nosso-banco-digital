package com.zupchallenge.SpringBootChallenge.domains.Accounts.services.implementations;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.Accounts;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.enums.AccountProposalStatus;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.repositories.AccountProposalsRepository;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.repositories.AccountsRepository;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.services.ICreateAccountService;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.BusinessRuleError;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.IncompleteStepsException;
import com.zupchallenge.SpringBootChallenge.shared.providers.CPFValidationProvider.ICPFValidationProvider;
import com.zupchallenge.SpringBootChallenge.shared.providers.MailSendProvider.IMailSendProvider;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class CreateAccountService implements ICreateAccountService {

    private AccountProposalsRepository accountProposalsRepository;
    private AccountsRepository accountsRepository;
    private IMailSendProvider mailSendProvider;
    private ICPFValidationProvider CPFValidationprovider;


    public CreateAccountService(AccountProposalsRepository accountProposalsRepository, AccountsRepository accountsRepository,IMailSendProvider mailSendProvider) {
        this.accountsRepository = accountsRepository;
        this.accountProposalsRepository=accountProposalsRepository;
        this.mailSendProvider = mailSendProvider;
    }

    @Override
    @Transactional
    public Accounts execute(AccountProposals accountProposals) {

        if(accountProposals.getStatus().ordinal()<3){
            throw new IncompleteStepsException("You should complete the earlier steps first");
        }

        //Just a quick mock on how the CPF would be validated using an external service
        //this case returns true always
        boolean validCPF=CPFValidationprovider.validateCPF(accountProposals.getCPFImagePictureURL());

        if (!validCPF){
            throw new BusinessRuleError("You should wait for you CPF to be approved");
        }

        //Create the Account
        Accounts account = new Accounts();
        account.setAccountProposals(accountProposals);

        int randomAgency = (int)(Math.random() * (9999 - 0000 + 1) + 0000);
        account.setAgency(randomAgency);

        int randomAccountCode = (int)(Math.random() * (99999999 - 00000000 + 1) + 00000000);
        account.setAccountNumber(randomAccountCode);
        account.setMoneyAmmount(BigDecimal.valueOf(00.00));
        account.setBankCode(1234);
        Accounts NewAccount = accountsRepository.save(account);


        //Update the Status of account proposal
        accountProposals.setStatus(AccountProposalStatus.COMPLETED);
        accountProposalsRepository.save(accountProposals);

        //Send email using our fake mail provider
        mailSendProvider.sendMail("nosso-banco@zupbootcamp.com",
                accountProposals.getCustomer().getEmail(),
                "Seja bem vindo ao Nosso Banco Digital");

        return NewAccount;
    }
}
