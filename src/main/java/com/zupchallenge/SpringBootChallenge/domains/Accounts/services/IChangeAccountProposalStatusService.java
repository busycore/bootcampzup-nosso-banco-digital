package com.zupchallenge.SpringBootChallenge.domains.Accounts.services;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.enums.AccountProposalStatus;

public interface IChangeAccountProposalStatusService {
    void setStatus(Long id, AccountProposalStatus Status);
}
