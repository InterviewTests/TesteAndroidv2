package dev.vitorpacheco.solutis.bankapp.statementsScreen

import dev.vitorpacheco.solutis.bankapp.api.BankService.Companion.createService
import java.lang.ref.WeakReference

enum class StatementsConfigurator {

    INSTANCE;

    fun configure(activity: StatementsActivity) {
        val router = StatementsRouter()
        router.activity = WeakReference(activity)

        val presenter = StatementsPresenter()
        presenter.output = WeakReference(activity)

        val interactor = StatementsInteractor()
        interactor.output = presenter
        interactor.service = createService()

        if (activity.output == null) {
            activity.output = interactor
        }

        if (activity.router == null) {
            activity.router = router
        }
    }

}