package com.example.santanderapplication;

import com.example.santanderapplication.ui.login.BankLoginContract;
import com.example.santanderapplication.ui.login.BankLoginPresenter;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TesteLogin extends TestCase {
    @Mock
    private BankLoginContract.View view;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks( this );
    }

    @Test
    public void testUserSuccess(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validateUser( "success@." );
        Assert.assertEquals( true , b );
    }
    @Test
    public void testUserErrorCaracterPoint(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validateUser( "error." );
        Assert.assertEquals( false, b );
    }
    @Test
    public void testUserErrorCaracter(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validateUser( "error@" );
        Assert.assertEquals( false, b );
    }
    @Test
    public void testUserErrorText(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validateUser( "text" );
        Assert.assertEquals( false, b );
    }
    @Test
    public void testUserErrorSpace(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validateUser( "s pace@." );
        Assert.assertEquals( false, b );
    }
    @Test
    public void testUserErrorTextNull(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validateUser( "" );
        Assert.assertEquals( false, b );
    }

    @Test
    public void testPasswordSuccess(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validatePassword( "Hh3@" );
        Assert.assertEquals( true, b );
    }
    @Test
    public void testPasswordErrorCaracter(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validatePassword( "Hh3" );
        Assert.assertEquals( false, b );
    }
    @Test
    public void testPasswordErrorUppercaseletter(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validatePassword( "h3@" );
        Assert.assertEquals( false, b );
    }
    @Test
    public void testPasswordErrorLowercaseletter(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validatePassword( "H3@" );
        Assert.assertEquals( false, b );
    }
    @Test
    public void testPasswordErrorNumber(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validatePassword( "Hh@" );
        Assert.assertEquals( false, b );
    }
    @Test
    public void testPasswordErrorNull(){
        BankLoginPresenter presenter = new BankLoginPresenter( view );
        boolean b = presenter.validatePassword( "" );
        Assert.assertEquals( false, b );
    }
}
