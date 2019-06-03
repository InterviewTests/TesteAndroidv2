package com.example.testeacclogin;

import android.support.v7.widget.RecyclerView;

import com.example.testeacclogin.ui.statements.StateAdapter;
import com.example.testeacclogin.ui.statements.StateItem;
import com.example.testeacclogin.ui.statements.StatementsActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class StatementApiTest {


    @Test
    public void TestaStateItem(){
        StateItem stateItem = Mockito.mock(StateItem.class);
        stateItem.getmTitle();
        stateItem.getmDesc();
        stateItem.getmData();
        stateItem.getmValue();

        Mockito.verify(stateItem, Mockito.times(1)).getmTitle();
        Mockito.verify(stateItem, Mockito.times(1)).getmDesc();
        Mockito.verify(stateItem, Mockito.times(1)).getmData();
        Mockito.verify(stateItem, Mockito.times(1)).getmValue();

    }

}
