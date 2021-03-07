package br.com.silas.domain.interactor

import br.com.silas.domain.mocks.UserMock
import br.com.silas.domain.preferences.PreferencesRepository
import br.com.silas.domain.user.GetUserInteractor
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUserInteractorTest {
    @Mock
    lateinit var preferencesRepository: PreferencesRepository
    lateinit var getUserInteractor: GetUserInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getUserInteractor = GetUserInteractor(preferencesRepository)
    }

    @Test
    fun `when call sharedPreferences should be return an user`() {
        val user = UserMock.getUserMock()
        `when`(preferencesRepository.getUser()).thenReturn(user)
        val result = getUserInteractor.execute()
        assertThat(result, `is`(user))
    }
}