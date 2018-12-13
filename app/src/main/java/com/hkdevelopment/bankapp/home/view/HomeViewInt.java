package com.hkdevelopment.bankapp.home.view;

import com.hkdevelopment.bankapp.home.model.StatementList;

import java.util.List;

public interface HomeViewInt {

    void findViews();

    void setAdapter(List<StatementList> pojo);
}
