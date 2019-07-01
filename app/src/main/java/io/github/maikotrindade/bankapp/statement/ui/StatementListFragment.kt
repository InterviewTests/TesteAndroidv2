package io.github.maikotrindade.bankapp.statement.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.maikotrindade.bankapp.R
import io.github.maikotrindade.bankapp.login.LoginRouter.Companion.navLoginStatements
import io.github.maikotrindade.bankapp.login.model.UserData

class StatementListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statement_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userData = arguments?.getParcelable<UserData>(navLoginStatements)
    }
}
