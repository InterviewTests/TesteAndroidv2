package com.hkdevelopment.bankapp;

import android.util.Log;

import com.hkdevelopment.bankapp.login.model.User;
import com.hkdevelopment.bankapp.login.utils.VerifyData;
import com.hkdevelopment.bankapp.retrofit.RetrofitInstance;
import com.hkdevelopment.bankapp.retrofit.services.Services;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginUnitTest {

    VerifyData verifyData=new VerifyData();
    Response<User> userResponse;
    @Test
    public void addition_isCpf() {
        assertEquals(true, verifyData.isCpf("000.000.000-00"));
    }

    @Test
    public void addition_isEmail() {
        assertEquals(true, verifyData.isEmail("humbertokalex@gmail.com"));
    }

    @Test
    public void doLoginTest() {
        Services service = RetrofitInstance.getRetrofitInstance().create(Services.class);
        Call<User> call = service.getUser("teste@gmail.com", "teste09A@");
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("EntrouTestLogin",response.body().getUserAccount().getName());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("ErroLoginTest",t.getMessage());
            }
        });
    }

}