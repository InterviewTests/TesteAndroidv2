package com.br.example.fakebank.domains.repositories;

import io.reactivex.Observable;

public interface MainRepository {
    Observable authLogin(String user, String password);
}
