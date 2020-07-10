package com.accenture.android.app.testeandroid;

import com.accenture.android.app.testeandroid.data.converters.UserAccountConverter;
import com.accenture.android.app.testeandroid.data.http.responses.UserAccountResponse;
import com.accenture.android.app.testeandroid.domain.UserAccount;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Denis Magno on 10/07/2020.
 * denis_magno16@hotmail.com
 */
public class UserAccountConverterUnitTest {
    private final UserAccountResponse.UserAccountData userAccountDataResponse;
    private final UserAccountResponse.UserAccountData userAccountDataResponseNull;

    private final static Long ID = 1L;
    private final static String NAME = "Nome";
    private final static String BANK_ACCOUNT = "012345678";
    private final static String AGENCY = "2020";
    private final static Double BALANCE = 1.2345;

    public UserAccountConverterUnitTest() {
        this.userAccountDataResponse = new UserAccountResponse.UserAccountData(ID, NAME, BANK_ACCOUNT, AGENCY, BALANCE);
        this.userAccountDataResponseNull = new UserAccountResponse.UserAccountData();
    }

    @Test
    public void toDomain_isCorrect() {
        UserAccount statement = UserAccountConverter.toDomain(this.userAccountDataResponse);

        assertEquals(ID, statement.getUserId());
        assertEquals(NAME, statement.getName());
        assertEquals(BANK_ACCOUNT, statement.getBankAccount());
        assertEquals(AGENCY, statement.getAgency());
        assertEquals(BALANCE, statement.getBalance());

        statement = UserAccountConverter.toDomain(this.userAccountDataResponseNull);
        assertEquals(new Long(0), statement.getUserId());
        assertEquals("", statement.getName());
        assertEquals("", statement.getBankAccount());
        assertEquals("", statement.getAgency());
        assertEquals(new Double(0), statement.getBalance());
    }
}
