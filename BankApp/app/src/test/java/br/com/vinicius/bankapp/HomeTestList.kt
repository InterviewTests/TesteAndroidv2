package br.com.vinicius.bankapp

import br.com.vinicius.bankapp.data.remote.model.StatementModel
import br.com.vinicius.bankapp.data.repository.StatementRepository
import br.com.vinicius.bankapp.domain.StatementContract
import br.com.vinicius.bankapp.domain.User
import br.com.vinicius.bankapp.internal.BaseCallback
import br.com.vinicius.bankapp.ui.home.HomeContract
import br.com.vinicius.bankapp.ui.home.HomePresenter
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.stub
import org.junit.Before
import org.junit.Test
import kotlin.random.Random
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*

class HomeTestList {

    @Mock
    private lateinit var view:HomeContract.View

    @Mock
    private lateinit var repository: StatementRepository

    private var presenter: HomePresenter? = null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        view = mock(HomeContract.View::class.java)
        presenter = HomePresenter(view)
    }

    @Test
    fun loadListStatements() {
        presenter?.fetchListStatements(1)
//        var argumentCaptor =
//            ArgumentCaptor.forClass(BaseCallback::class.java) as ArgumentCaptor<BaseCallback<List<StatementModel>>>
        argumentCaptor<BaseCallback<List<StatementModel>>>().apply {
            Mockito.`when`(repository.startStatements(eq(1), capture())).then{
                (it.getArgument(1) as BaseCallback<List<StatementModel>>). onSuccessful(getListStatements())
            }
            //verify(repository).startStatements(eq(1), capture())
            //assertTrue(.isNotEmpty())
            //capture().onSuccessful(getListStatements())
        }

        verify(view).showProgressRecycler(true)

        argumentCaptor<List<StatementModel>>().apply {
            `when`(view.initRecyclerView(capture())).then{

            }
        }

        //verify(view).notification(eq("Message"))
        //verify(view).initRecyclerView(getListStatements())


    }

    @Test
    fun loadListError() {
//        val argumentCaptor =
//            ArgumentCaptor.forClass(BaseCallback::class.java) as ArgumentCaptor<BaseCallback<List<StatementModel>>>
        presenter?.fetchListStatements(1)
        argumentCaptor<BaseCallback<List<StatementModel>>>().apply {
            Mockito.`when`(repository.startStatements(eq(1), capture())).then{
                (it.getArgument(1) as BaseCallback<List<StatementModel>>).onUnsuccessful("Error")
            }

            //verify(repository, times(1)).startStatements(eq(1), capture())
           // capture().onUnsuccessful("Error")
        }

        argumentCaptor<String>().apply {
            Mockito.`when`(view.notification(capture())).then {  }
            //verify(view, times(1)).notification(capture())
           // Mockito.`when`(view.notification(firstValue)).then {  }

        }
    }



    private fun getListStatements():List<StatementModel> {
        var models:ArrayList<StatementModel> = ArrayList()
        models.add(StatementModel("2019-06-21", "Loja X", "Pagamento", Random.nextDouble()))
        models.add(StatementModel("2019-06-21", "Loja R", "Pagamento", Random.nextDouble()))
        models.add(StatementModel("2019-06-21", "Loja T", "Pagamento", Random.nextDouble()))
        models.add(StatementModel("2019-06-21", "Loja Y", "Pagamento", Random.nextDouble()))
        models.add(StatementModel("2019-06-21", "Loja F", "Pagamento", Random.nextDouble()))
        models.add(StatementModel("2019-06-21", "Loja S", "Pagamento", Random.nextDouble()))
        models.add(StatementModel("2019-06-21", "Loja XF", "Pagamento", Random.nextDouble()))
        return models
    }

}