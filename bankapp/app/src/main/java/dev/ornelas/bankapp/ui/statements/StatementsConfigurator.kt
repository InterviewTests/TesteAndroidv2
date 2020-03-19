package dev.ornelas.bankapp.ui.statements

import dev.ornelas.banckapp.domain.interactors.*
import dev.ornelas.bankapp.AppContainer
import dev.ornelas.bankapp.ui.statements.adapter.StatementsAdapter
import java.lang.ref.WeakReference

object StatementsConfigurator {

    fun configure(activity: StatementsActivity) {

        val appContainer = activity.application as AppContainer

        val getStatements = GetStatements(appContainer.dataComponent.statementRepository)

        val presenter = StatementsPresenter(
            activity,
            getStatements
        )

        activity.presenter = presenter

        activity.router = StatementsRouter(WeakReference(activity))

        activity.statementsAdapter = StatementsAdapter()
    }
}