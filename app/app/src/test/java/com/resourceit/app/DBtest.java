package com.resourceit.app;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.resourceit.app.dao.LoginDao;
import com.resourceit.app.models.LoginModel;
import com.resourceit.app.tools.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

@RunWith(RobolectricTestRunner.class)
public class DBtest {
    private LoginDao loginDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();
        loginDao = db.loginDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        LoginModel user = new LoginModel();
        Gson gson = new Gson();
        user.setName("Test");
        user.setUserId(1);

        loginDao.insertAll(user);

        LoginModel byid = loginDao.findById(1);

        Assert.assertEquals(gson.toJson(user), gson.toJson(byid));
    }


}
