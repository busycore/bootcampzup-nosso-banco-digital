package com.zupchallenge.SpringBootChallenge.domains.Customers.services;

import com.zupchallenge.SpringBootChallenge.domains.Customers.entities.Customers;

public interface ICreateBasicCustomerDataService {
    public Customers execute(Customers customer);
}
