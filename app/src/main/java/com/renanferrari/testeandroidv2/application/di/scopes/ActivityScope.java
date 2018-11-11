package com.renanferrari.testeandroidv2.application.di.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface ActivityScope {}