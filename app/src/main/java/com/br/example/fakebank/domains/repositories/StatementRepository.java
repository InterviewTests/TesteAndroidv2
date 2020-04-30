package com.br.example.fakebank.domains.repositories;

import io.reactivex.Observable;

public interface StatementRepository {
    Observable listStatements(Integer userId);
}
