package com.resource.bankapplication.domain;

import com.resource.bankapplication.config.BaseCallback;
import com.resource.bankapplication.data.repository.StatementsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class SpentTest {

    @Mock
    private StatementsRepository repository;
    @Mock
    private Spent spent;

    @Captor
    private ArgumentCaptor<BaseCallback<List<Spent>>> loadSpentCallback;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListSpent() {
        spent.setRepository(repository);
        spent.listSpent(Mockito.eq(1),loadSpentCallback.capture());
        Mockito.verify(spent).listSpent(Mockito.anyLong(),loadSpentCallback.capture());
        repository.listStatements(Mockito.anyLong(), loadSpentCallback.capture());
        Mockito.verify(repository).listStatements(Mockito.anyLong(), loadSpentCallback.capture());
    }

    @Test(expected = Exception.class)
    public void testIdLessOrEqualsZero(){
        spent = new Spent();
        spent.setRepository(repository);
        spent.listSpent(Mockito.eq(-1),loadSpentCallback.capture());
    }

}