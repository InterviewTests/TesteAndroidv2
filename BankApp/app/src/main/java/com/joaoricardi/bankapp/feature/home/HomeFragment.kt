package com.joaoricardi.bankapp.feature.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.joaoricardi.bankapp.R
import com.joaoricardi.bankapp.models.login.UserAccont


class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {

        var TAG = "home_fragment"
        var USER_ACCOUONT = "USER_ACCOUNT"

        @JvmStatic
        fun newInstance(userAccont: UserAccont) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER_ACCOUONT, userAccont)
                }
            }
    }
}
