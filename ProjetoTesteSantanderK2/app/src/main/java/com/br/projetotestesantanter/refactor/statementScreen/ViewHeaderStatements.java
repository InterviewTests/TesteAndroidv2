package com.br.projetotestesantanter.refactor.statementScreen;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.br.projetotestesantanter.R;
import com.br.projetotestesantanter.Utils;
import com.br.projetotestesantanter.api.model.LoginResponse;
import com.br.projetotestesantanter.refactor.loginScreen.LoginModel;


public class ViewHeaderStatements extends ConstraintLayout {

    private  View view;
    private TextView txtNameCliente;
    private ImageView imgExit;
    private TextView txtNumberAccount;
    private TextView txtValueBalance;
    private OnItemClickListener listener = null;


    public interface OnItemClickListener {
        void onItemClick(ImageView item);
    }

    public ViewHeaderStatements(Context context) {
        super(context);
        initView(context);

    }

    public ViewHeaderStatements(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public ViewHeaderStatements(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_header_statements, this, true);
        initComponents();

    }

    private void initComponents() {

        txtNameCliente = view.findViewById(R.id.txt_name_cliente);
        imgExit = view.findViewById(R.id.img_exit);
        txtNumberAccount = view.findViewById(R.id.txt_number_account);
        txtValueBalance = view.findViewById(R.id.txt_value_balance);

    }

    public void bind(LoginResponse loginResponse){

        txtNameCliente.setText(loginResponse.getUserAccount().getName());
        txtNumberAccount.setText(Utils.Companion.formatAgencyAccount(loginResponse.getUserAccount().getBankAccount(),loginResponse.getUserAccount().getAgency()));
        txtValueBalance.setText(Utils.Companion.converMoney(loginResponse.getUserAccount().getBalance()));

    }

    public void bind(LoginModel.Login login) {

        if(null != login.getLoginResponse().getName()) {
            txtNameCliente.setText(login.getLoginResponse().getName());
        }

        if(null != login.getLoginResponse().getBankAccount() && null != login.getLoginResponse().getAgency()) {
            txtNumberAccount.setText(Utils.Companion.formatAgencyAccount(login.getLoginResponse().getBankAccount(),
                    login.getLoginResponse().getAgency()));
        }
        if(String.valueOf(login.getLoginResponse().getBalance()).length() >0) {
            txtValueBalance.setText(Utils.Companion.converMoney(login.getLoginResponse().getBalance()));
        }
    }

    public void setListener(final OnItemClickListener listener) {
        this.listener = listener;
        imgExit.setOnClickListener(v -> listener.onItemClick(imgExit));
    }
}
