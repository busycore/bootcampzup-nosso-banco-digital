package com.zupchallenge.SpringBootChallenge.shared.providers.CPFValidationProvider.implementations;

import com.zupchallenge.SpringBootChallenge.shared.providers.CPFValidationProvider.ICPFValidationProvider;

public class FakeCPFValidationProvider implements ICPFValidationProvider {

    @Override
    public boolean validateCPF(String cpfurl) {
        return true;
    }
}
