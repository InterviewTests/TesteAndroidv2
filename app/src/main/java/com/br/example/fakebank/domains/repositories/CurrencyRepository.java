package com.br.example.fakebank.domains.repositories;

import io.reactivex.Observable;

public interface CurrencyRepository {
    Observable listStatements(Integer userId);
}
