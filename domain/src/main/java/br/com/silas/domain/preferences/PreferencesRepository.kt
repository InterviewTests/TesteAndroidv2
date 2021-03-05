package br.com.silas.domain.preferences

import br.com.silas.domain.user.User
import io.reactivex.rxjava3.core.Completable

interface PreferencesRepository {
    fun save(user: User?) : Completable
}