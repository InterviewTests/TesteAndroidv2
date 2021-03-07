package br.com.silas.domain.interactor

import br.com.silas.domain.Schedulers
import br.com.silas.domain.mocks.UserMock
import br.com.silas.domain.preferences.PreferencesRepository
import br.com.silas.domain.user.SaveUserInteractor
import br.com.silas.domain.util.TestScheduler
import io.reactivex.rxjava3.core.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveUserInteractorTest {

    @Mock
    private lateinit var preferencesRepository: PreferencesRepository
    private lateinit var schedulers: Schedulers


    private lateinit var saveUserInteractor: SaveUserInteractor


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        schedulers = TestScheduler()
        saveUserInteractor = SaveUserInteractor(preferencesRepository, schedulers)
    }

    @Test
    fun `Should save an user in shared preferences`() {
        val user = UserMock.getUserMock()
        `when`(preferencesRepository.save(user)).thenReturn(Completable.complete())
        val result = saveUserInteractor.execute(saveUserInteractor.Request(user)).test()

        result
            .assertNoErrors()
            .assertComplete()

        verify(preferencesRepository).save(user)
    }

    @Test
    fun `When save user is unsuccessful should return an exception`() {
        val user = UserMock.getUserMock()
        val exception = Exception()
        `when`(preferencesRepository.save(user)).thenReturn(Completable.error(exception))
        val result = saveUserInteractor.execute(saveUserInteractor.Request(user)).test()

        result
            .assertError(exception)
            .assertNotComplete()

        verify(preferencesRepository).save(user)
    }
}