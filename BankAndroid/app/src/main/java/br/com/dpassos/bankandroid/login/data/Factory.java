package br.com.dpassos.bankandroid.login.data;

import br.com.dpassos.bankandroid.infra.GenericFactory;

public class Factory extends GenericFactory {

    public LoginRepository getRepositoryImpl() {
        return newInstance(SimpleLoginRepository.class);
    }
}
