package com.zupchallenge.SpringBootChallenge.domains.Addresses.repositories;

import com.zupchallenge.SpringBootChallenge.domains.Addresses.entities.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressesRepository extends JpaRepository<Addresses,Long> {
}
