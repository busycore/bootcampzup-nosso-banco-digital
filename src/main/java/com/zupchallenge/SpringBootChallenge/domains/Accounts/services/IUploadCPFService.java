package com.zupchallenge.SpringBootChallenge.domains.Accounts.services;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadCPFService {
    void execute(AccountProposals accountProposal, MultipartFile file);
}
