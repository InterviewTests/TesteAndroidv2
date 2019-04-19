package br.com.rms.bankapp

import br.com.rms.bankapp.data.local.database.dao.AccountDao
import br.com.rms.bankapp.data.local.database.dao.UserDao
import br.com.rms.bankapp.data.local.database.entity.User
import br.com.rms.bankapp.data.remote.api.BankAppApiService
import br.com.rms.bankapp.data.repository.user.UserRepository
import br.com.rms.bankapp.utils.validations.user.UserValidations
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TestUserRepository {

    val userEmail = User(0, "teste@email.com", "@Teste1", 1000)
    val userCpf = User(0, "40182994007", "@Teste1", 1000)


    @Mock
    lateinit var mockUserDao: UserDao

    @Mock
    lateinit var mockAccountDao: AccountDao

    @Mock
    lateinit var mockApiService: BankAppApiService

    @Mock
    lateinit var mockUserValidator: UserValidations

    lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(
            userDao = mockUserDao,
            accountDao = mockAccountDao,
            apiService = mockApiService,
            validation = mockUserValidator
        )
    }

    @Test
    fun userIsValid() {
        val testObserver = userRepository.validateLoginInformation(
            user = "",
            password = ""
        ).test()

        testObserver.assertNoErrors()
    }


}