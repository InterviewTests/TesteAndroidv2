package com.br.example.fakebank.domains.repositories;

import io.reactivex.Observable;

public interface LoginRepository {
    Observable authLogin(String user, String password);
}
