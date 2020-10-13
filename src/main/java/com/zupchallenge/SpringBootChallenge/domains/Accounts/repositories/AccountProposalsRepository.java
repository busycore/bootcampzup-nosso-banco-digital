package com.zupchallenge.SpringBootChallenge.domains.Accounts.repositories;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountProposalsRepository extends JpaRepository<AccountProposals,Long> {
}
