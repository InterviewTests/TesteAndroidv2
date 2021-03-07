package br.com.silas.domain.user

import br.com.silas.domain.preferences.PreferencesRepository

class GetUserInteractor(private val preferencesRepository: PreferencesRepository) {
    fun execute() = preferencesRepository.getUser()
}