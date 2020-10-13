package com.zupchallenge.SpringBootChallenge.domains.Accounts.controllers;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.Accounts;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.services.*;
import com.zupchallenge.SpringBootChallenge.domains.Addresses.entities.Addresses;
import com.zupchallenge.SpringBootChallenge.domains.Addresses.services.implementations.CreateCustomerAddressService;
import com.zupchallenge.SpringBootChallenge.domains.Customers.entities.Customers;
import com.zupchallenge.SpringBootChallenge.domains.Customers.services.implementations.CreateBasicCustomerDataService;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.FileStorageException;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.IncompleteStepsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("")
public class AccountsProposalController {


    @Autowired
    CreateBasicCustomerDataService createBasicCustomerData;

    @Autowired
    CreateCustomerAddressService createCustomerAddress;

    @Autowired
    IUploadCPFService uploadCPFService;

    @Autowired
    ICreateAccountProposalService createAccountProposalService;

    @Autowired
    IGetAccountProposalService getAccountProposalService;

    @Autowired
    IAddAddressToAccountProposalService addAddressToAccountProposalService;

    @Autowired
    ICreateAccountService createAccountService;

    @Autowired
    IDenyAccountCreationService denyAccountCreationService;

    @PostMapping("account-proposals/")
    public ResponseEntity<Customers> CreateAccount(@RequestBody @Valid Customers customer){
        //Create the customer basic data
        Customers createdCustomer = createBasicCustomerData.execute(customer);

        //Create an account proposal for the customer
        AccountProposals NewAccountProposal= createAccountProposalService.execute(createdCustomer);

        //Set-up the location headers
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                //.fromCurrentRequest()
                .fromCurrentContextPath()
                .path("account-proposals/{id}/address/")
                .buildAndExpand(NewAccountProposal.getId())
                .toUri();
        headers.setLocation(location);

        return new ResponseEntity(createdCustomer,headers, HttpStatus.CREATED);
    }


    @PostMapping("account-proposals/{id}/address/")
    public ResponseEntity<Addresses> CreateAccountAddress(@PathVariable Long id,@RequestBody @Valid Addresses address){
        //Retrieve the account proposal using the service.
        AccountProposals accountProposal = getAccountProposalService.execute(id);

        //Create a customer address
        Addresses NewAddress=createCustomerAddress.execute(accountProposal,address);

        //
        addAddressToAccountProposalService.execute(accountProposal,NewAddress);

        //setCustomerStatus.setStatus(id, CustomerStatus.WAITING_CPF);
        //Set-up the location headers
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("account-proposals/{id}/cpf-upload/")
                .buildAndExpand(id)
                .toUri();

        headers.setLocation(location);

        return new ResponseEntity(NewAddress,headers, HttpStatus.CREATED);

    }

    @PostMapping("account-proposals/{id}/cpf-upload/")
    public ResponseEntity<String> uploadCpf(@PathVariable Long id, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){


        AccountProposals accountProposal = getAccountProposalService.execute(id);

        uploadCPFService.execute(accountProposal,file);

        //Set-up the location headers
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("account-proposals/{id}/review/")
                .buildAndExpand(id)
                .toUri();
        headers.setLocation(location);

        return new ResponseEntity("",headers, HttpStatus.CREATED);

    }

    @GetMapping("account-proposals/{id}/review/")
    public ResponseEntity<AccountProposals> ReviewAccountProposal(@PathVariable Long id){

        AccountProposals accountProposal = getAccountProposalService.execute(id);

        if(accountProposal.getStatus().ordinal()<3){
            throw new IncompleteStepsException("You should complete the earlier steps first");
        }

        //Set-up the location headers
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("account-proposals/{id}/commit/")
                .buildAndExpand(id)
                .toUri();
        headers.setLocation(location);

        return new ResponseEntity(accountProposal,headers, HttpStatus.CREATED);
    }

    @PostMapping("account-proposals/{id}/commit/")

    public ResponseEntity<Accounts> CreateAccount(@PathVariable Long id){

        AccountProposals accountProposal = getAccountProposalService.execute(id);

        Accounts account = createAccountService.execute(accountProposal);

        //Set-up the location headers
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/account-proposals/{id}/commit")
                .buildAndExpand(id)
                .toUri();
        headers.setLocation(location);

        return new ResponseEntity(account,headers, HttpStatus.CREATED);
    }

    @PostMapping("account-proposals/{id}/deny/")
    public ResponseEntity DenyAccount(@PathVariable Long id){

        AccountProposals accountProposal = getAccountProposalService.execute(id);

        denyAccountCreationService.execute(accountProposal);

        //Set-up the location headers
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/account-proposals/{id}/commit")
                .buildAndExpand(id)
                .toUri();
        headers.setLocation(location);


        return new ResponseEntity("{\"meessage\":\"Recebemos o cancelamento\"}",headers, HttpStatus.CREATED);
    }
}
