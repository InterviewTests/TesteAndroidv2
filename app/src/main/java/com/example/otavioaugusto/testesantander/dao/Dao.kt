package com.example.otavioaugusto.testesantander.dao

import com.example.otavioaugusto.testesantander.model.User
import com.orhanobut.hawk.Hawk

class Dao {

    companion object {
        fun salvarDados(user:String, pass:String){
            Hawk.put("user", user)
            Hawk.put("password", pass)
        }



    }
}