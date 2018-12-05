package com.example.otavioaugusto.testesantander.statements

import com.example.otavioaugusto.testesantander.model.UserAccount

interface StatementsContrato {

    interface View{
        fun user(user: UserAccount)


    }

    interface Presenter{

    }
}