package br.com.amilton.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import br.com.amilton.LiveDataTestUtil;
import br.com.amilton.model.Statement;
import br.com.amilton.model.UserAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class StatementViewModelTest {

    private UserAccount userAccount = new UserAccount("1", "Jose da Silva Teste", "2050", "012314564", 3.3445);

    @Rule
    public InstantTaskExecutorRule instantTaskExecuteRule = new InstantTaskExecutorRule();

    private StatementViewModel viewModel;

    @Before
    public void setUP() {
        viewModel = new StatementViewModel();
    }

    @Test
    public void getStatementsMutableLiveData() {
        try {
            List<Statement> list = LiveDataTestUtil.getValue(viewModel.getStatementsMutableLiveData(userAccount.getUserId()));
            assertTrue(list.size() != 0);
        } catch (InterruptedException e) {
            fail("Exception :" + e.getMessage());
        }
    }

    @Test
    public void getUserAccount() {
        viewModel.setUserAccount(userAccount);
        try {
            UserAccount localUserAccount = LiveDataTestUtil.getValue(viewModel.getUserAccount());
            assertEquals(userAccount, localUserAccount);
        } catch (InterruptedException e) {
            fail("Exception :" + e.getMessage());
        }
    }

}