package com.renanferrari.testeandroidv2.domain.model.statements;

import io.reactivex.Single;
import java.util.List;

public interface StatementsRepository {

  Single<List<Statement>> getRecentStatements(int userId);
}