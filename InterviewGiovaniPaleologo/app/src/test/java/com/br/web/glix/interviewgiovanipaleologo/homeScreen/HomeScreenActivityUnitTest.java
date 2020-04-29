package com.br.web.glix.interviewgiovanipaleologo.homeScreen;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class HomeScreenActivityUnitTest {

    @Test
    public void HomeActivity_NOT_Null(){
        //Given
        HomeScreenActivity activity = Robolectric.setupActivity(HomeScreenActivity.class);
        //When

        // Then
        Assert.assertNotNull(activity);
    }
}
