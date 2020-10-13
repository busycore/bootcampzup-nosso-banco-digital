package com.zupchallenge.SpringBootChallenge.domains.Customers.services.implementations;

import com.zupchallenge.SpringBootChallenge.domains.Customers.entities.Customers;
import com.zupchallenge.SpringBootChallenge.domains.Customers.repositories.CustomersRepository;
import com.zupchallenge.SpringBootChallenge.domains.Customers.services.ICreateBasicCustomerDataService;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.AlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateBasicCustomerDataService implements ICreateBasicCustomerDataService {

    private CustomersRepository customersRepository;

    public CreateBasicCustomerDataService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public CustomersRepository getCustomersRepository() {
        return customersRepository;
    }

    public Customers execute(Customers customer){
        Optional<Customers> cliente = customersRepository.findByCpf(customer.getCpf());

        //If the customer is not found throw an rest exception
        if (cliente.isPresent()){
            throw new AlreadyExistsException("This CPF is already registered");
        }

        Optional<Customers> clientemail = customersRepository.findByemail(customer.getEmail());

        //If the customer is not found throw an rest exception
        if (clientemail.isPresent()){
            throw new AlreadyExistsException("This email is already registered");
        }

        Customers CreatedCustomer = customersRepository.save(customer);

        return CreatedCustomer;
    }


}
