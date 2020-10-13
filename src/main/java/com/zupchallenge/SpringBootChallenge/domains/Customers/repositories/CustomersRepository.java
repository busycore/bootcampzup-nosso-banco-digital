package com.zupchallenge.SpringBootChallenge.domains.Customers.repositories;

import com.zupchallenge.SpringBootChallenge.domains.Customers.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customers,Long> {

    Optional<Customers> findByCpf(String cpf);
    Optional<Customers> findByemail(String email);
}
