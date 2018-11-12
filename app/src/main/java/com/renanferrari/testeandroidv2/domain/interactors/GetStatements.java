package com.renanferrari.testeandroidv2.domain.interactors;

import com.renanferrari.testeandroidv2.common.Optional;
import com.renanferrari.testeandroidv2.domain.model.statements.Statement;
import com.renanferrari.testeandroidv2.domain.model.statements.StatementsRepository;
import com.renanferrari.testeandroidv2.domain.model.user.User;
import com.renanferrari.testeandroidv2.domain.model.user.UserManager;
import io.reactivex.Single;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class GetStatements {

  private final StatementsRepository statementsRepository;
  private final UserManager userManager;

  @Inject public GetStatements(final StatementsRepository statementsRepository,
      final UserManager userManager) {
    this.statementsRepository = statementsRepository;
    this.userManager = userManager;
  }

  public Single<List<Statement>> execute() {
    return userManager.getUserObservable()
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(User::getUserId)
        .flatMapSingle(statementsRepository::getRecentStatements)
        .first(Collections.emptyList());
  }
}