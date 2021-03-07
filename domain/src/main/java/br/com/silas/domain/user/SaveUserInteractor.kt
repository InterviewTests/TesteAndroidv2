package br.com.silas.domain.user

import br.com.silas.domain.InteractorCompletable
import br.com.silas.domain.Schedulers
import br.com.silas.domain.preferences.PreferencesRepository
import io.reactivex.rxjava3.core.Completable

class SaveUserInteractor(private val preferencesRepository: PreferencesRepository, schedulers: Schedulers) :
    InteractorCompletable<SaveUserInteractor.Request>(schedulers) {
    override fun create(request: Request): Completable {
        return preferencesRepository.save(request.getUser())
    }

    inner class Request(private val user: User) : InteractorCompletable.Request() {
        fun getUser() = user
    }

}