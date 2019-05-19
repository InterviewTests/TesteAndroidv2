package com.br.projetotestesantanter.statementscreen;

import android.app.Activity;
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
        initComponents(context);

    }

    private void initComponents(final Context context) {

        txtNameCliente = view.findViewById(R.id.txt_name_cliente);
        imgExit = view.findViewById(R.id.img_exit);
        txtNumberAccount = view.findViewById(R.id.txt_number_account);
        txtValueBalance = view.findViewById(R.id.txt_value_balance);

    }

    public void bind(LoginResponse loginResponse){

        txtNameCliente.setText(loginResponse.getUserAccount().getName());
        txtNumberAccount.setText(loginResponse.getUserAccount().getAgency() + " / " + loginResponse.getUserAccount().getBankAccount());
        txtValueBalance.setText(Utils.Companion.converMoney(loginResponse.getUserAccount().getBalance()));

    }



    public void setListener(final OnItemClickListener listener) {
        this.listener = listener;
        imgExit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(imgExit);
            }
        });
    }





}
