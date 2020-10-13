package com.zupchallenge.SpringBootChallenge.domains.Accounts.services.implementations;

import com.zupchallenge.SpringBootChallenge.domains.Accounts.entities.AccountProposals;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.enums.AccountProposalStatus;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.repositories.AccountProposalsRepository;
import com.zupchallenge.SpringBootChallenge.domains.Accounts.services.IUploadCPFService;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.AlreadyExistsException;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.FileStorageException;
import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.IncompleteStepsException;
import com.zupchallenge.SpringBootChallenge.shared.providers.FileUploadProvider.IFileUploadProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadCPFService implements IUploadCPFService {

    private AccountProposalsRepository accountProposalsRepository;

    private IFileUploadProvider fileUploadProvider;

    public UploadCPFService(IFileUploadProvider fileUploadProvider, AccountProposalsRepository accountProposalsRepository) {
        this.fileUploadProvider =fileUploadProvider;
        this.accountProposalsRepository = accountProposalsRepository;
    }

    @Override
    public void execute(AccountProposals accountProposal, MultipartFile file) {


        //Check if the address is already filled
        if(accountProposal.getStatus().ordinal()<2){
            throw new IncompleteStepsException("You should complete the earlier steps first");
        }

        //Check if the file is a jpeg,jpg or png
        String MimeType=file.getContentType();
        if(!((MimeType.equals("image/jpeg")) || (MimeType.equals("image/jpg")) || (MimeType.equals("image/png")))){
            throw new FileStorageException("Please use a valid file format (JPG,JPEG,PNG)");
        }

        //Upload file using the provider and return the URL Path
        String CPFPictureURL=fileUploadProvider.uploadFile(file);

        //Update the customer
        accountProposal.setCPFImagePictureURL(CPFPictureURL);

        accountProposal.setStatus(AccountProposalStatus.WAITING_CONFIRMATION);

        //Persist it in database
        accountProposalsRepository.save(accountProposal);

    }
}
