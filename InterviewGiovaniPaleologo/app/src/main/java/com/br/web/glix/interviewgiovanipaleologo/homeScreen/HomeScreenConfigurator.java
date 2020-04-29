package com.br.web.glix.interviewgiovanipaleologo.homeScreen;

import java.lang.ref.WeakReference;


public enum HomeScreenConfigurator {
    INSTANCE;

    public void configure(HomeScreenActivity homeScreenActivity) {
        HomeScreenPresenter homeScreenPresenter = new HomeScreenPresenter();
        homeScreenPresenter.homeScreenActivity = new WeakReference<HomeScreenActivityInput>(homeScreenActivity);

        HomeScreenInteractor homeScreenInteractor = new HomeScreenInteractor();
        homeScreenInteractor.homeScreenPresenterInput = homeScreenPresenter;

        if (homeScreenActivity.homeScreenInteractor == null) {
            homeScreenActivity.homeScreenInteractor = homeScreenInteractor;
        }
    }
}
