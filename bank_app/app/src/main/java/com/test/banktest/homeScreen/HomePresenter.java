package com.test.banktest.homeScreen;

import android.util.Log;

import com.test.banktest.model.StatementModel;
import com.test.banktest.model.StatementViewModel;
import com.test.banktest.model.UserViewModel;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

interface HomePresenterInput {
    public void presentHomeData(HomeResponse response);

    void presentLogout();
}


public class HomePresenter implements HomePresenterInput {

    public static String TAG = HomePresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<HomeActivityInput> output;


    @Override
    public void presentHomeData(HomeResponse response) {
        HomeViewModel homeViewModel = new HomeViewModel();
        UserViewModel userViewModel = new UserViewModel();
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        userViewModel.name = response.user.name;
        userViewModel.agencyAccount = response.user.bankAccount + " / " + response.user.agency.replaceFirst("(\\d{2})(\\d{6})(\\d{1})", "$1.$2-$3");
        userViewModel.balance = formatter.format(Float.parseFloat(response.user.balance));
        homeViewModel.user = userViewModel;

        homeViewModel.statementList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));

        for (StatementModel m : response.statementList) {
            StatementViewModel vm = new StatementViewModel();
            vm.title = m.title;
            vm.value = formatter.format(Float.parseFloat(m.value));
            try {
                vm.date = getDateFormated(m.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            vm.desc = m.desc;
            homeViewModel.statementList.add(vm);
        }
        output.get().displayHomeData(homeViewModel);
    }

    public static String getDateFormated(String date) throws ParseException {

        Locale local = new Locale("pt", "BR");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", local);
        java.util.Date d = null;
        d = df.parse(date);

        Calendar c = Calendar.getInstance();
        c.setTime(d);
        SimpleDateFormat dfTest = new SimpleDateFormat("dd/MM/yyyy", local);
        return dfTest.format(c.getTime());
    }

    @Override
    public void presentLogout() {
        output.get().logout();
    }
}
