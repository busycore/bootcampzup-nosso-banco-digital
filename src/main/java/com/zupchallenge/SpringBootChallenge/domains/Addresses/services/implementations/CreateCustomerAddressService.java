package com.zupchallenge.SpringBootChallenge.domains.Addresses.services.implementations;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.repositories.AccountProposalsRepository;
import com.zupchallenge.SpringBootChallenge.domains.Addresses.entities.Addresses;
import com.zupchallenge.SpringBootChallenge.domains.Addresses.repositories.AddressesRepository;
import com.zupchallenge.SpringBootChallenge.domains.Addresses.services.ICreateCustomerAddressService;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.AlreadyExistsException;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.IncompleteStepsException;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.NotExistsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateCustomerAddressService implements ICreateCustomerAddressService {

    private AddressesRepository addressesRepository;
    private AccountProposalsRepository accountProposalsRepository;

    public CreateCustomerAddressService(AccountProposalsRepository accountProposalsRepository, AddressesRepository addressesRepository) {
        this.accountProposalsRepository = accountProposalsRepository;
        this.addressesRepository = addressesRepository;
    }

    public Addresses execute(AccountProposals accountProposal,Addresses address){

        if(accountProposal.getStatus().ordinal()>1){
            throw new AlreadyExistsException("This account proposal was provided with an address,please update it with a proper request");
        }

        if(accountProposal.getStatus().ordinal()<1){
            throw new IncompleteStepsException("You should complete the earlier steps first");
        }

        //address.setAccountProposal(accountProposal.get());

        Addresses NewAddress = addressesRepository.save(address);
        return NewAddress;
    }
}
