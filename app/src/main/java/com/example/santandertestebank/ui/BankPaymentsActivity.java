package com.example.santandertestebank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.santandertestebank.R;
import com.example.santandertestebank.model.models.PaymentsStatmentList;
import com.example.santandertestebank.ui.adapter.AdapterBankPayments;

import java.util.ArrayList;
import java.util.List;

public class BankPaymentsActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewBankAccount;
    private TextView textViewAccountBalance;
    private ImageView imageViewLogout;

    private RecyclerView recyclerViewPayments;
    private List<PaymentsStatmentList> paymentsList = new ArrayList<> ();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_bank_payments);
        getSupportActionBar ().hide ();

        loadUI ();

        this.createPayment ();

        AdapterBankPayments adapterBankPayments = new AdapterBankPayments (paymentsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager
                (getApplicationContext ());

        recyclerViewPayments.setLayoutManager (layoutManager);
        recyclerViewPayments.setHasFixedSize (true);
        recyclerViewPayments.setAdapter (adapterBankPayments);

        imageViewLogout.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                logoutApp ();
            }
        });

    }

    private void logoutApp() {
        Intent intent = new Intent (this, LoginActivity.class);
        startActivity (intent);
        finish ();
    }

    public void createPayment() {

        PaymentsStatmentList payment = new PaymentsStatmentList ("Pagamento", "Conta de Luz", "12/12/18", 1000.00);
        paymentsList.add (payment);

        PaymentsStatmentList payment2 = new PaymentsStatmentList ("Pagamento", "Conta de Luz", "13/12/18", 2000.00);
        paymentsList.add (payment2);

        PaymentsStatmentList payment3 = new PaymentsStatmentList ("Pagamento", "Conta de Luz", "14/12/18", 3000.00);
        paymentsList.add (payment3);

        PaymentsStatmentList payment4 = new PaymentsStatmentList ("Pagamento", "Conta de Luz", "15/12/18", 4000.00);
        paymentsList.add (payment4);

        PaymentsStatmentList payment5 = new PaymentsStatmentList ("Pagamento", "Conta de Luz", "16/12/18", 5000.00);
        paymentsList.add (payment5);
    }

    private void loadUI() {
        textViewName = findViewById (R.id.text_view_name);
        textViewBankAccount = findViewById (R.id.text_view_bank_account);
        textViewAccountBalance = findViewById (R.id.text_view_account_balance);
        imageViewLogout = findViewById (R.id.image_view_logout);
        recyclerViewPayments = findViewById (R.id.recycler_view_payments);
    }
}
