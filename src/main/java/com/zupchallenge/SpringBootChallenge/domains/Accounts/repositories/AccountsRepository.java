package com.zupchallenge.SpringBootChallenge.domains.Accounts.repositories;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts,Long> {
}
