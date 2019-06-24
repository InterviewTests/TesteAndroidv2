package com.example.santandertestebank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.santandertestebank.adapter.AdapterBankPayments;
import com.example.santandertestebank.model.Payments;

import java.util.ArrayList;
import java.util.List;

public class BankPaymentsActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewBankAccount;
    private TextView textViewAccountBalance;
    private ImageView imageViewLogout;

    private RecyclerView recyclerViewPayments;
    private List<Payments> paymentsList = new ArrayList<> ();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_bank_payments);

        loadUI ();

        this.createPaymet ();

        AdapterBankPayments adapterBankPayments = new AdapterBankPayments (paymentsList);

        recyclerViewPayments.setHasFixedSize (true);
        recyclerViewPayments.setAdapter (adapterBankPayments);

    }

    public void createPaymet(){

        Payments payment = new Payments ("Pagamento", "Conta de Luz", "12/12/18", 1000.00);
        paymentsList.add (payment);

        Payments payment2 = new Payments ("Pagamento", "Conta de Luz", "12/12/18", 1000.00);
        paymentsList.add (payment);

        Payments payment3 = new Payments ("Pagamento", "Conta de Luz", "12/12/18", 1000.00);
        paymentsList.add (payment);

    }

    private void loadUI() {
        textViewName = findViewById (R.id.text_view_name);
        textViewBankAccount = findViewById (R.id.text_view_bank_account);
        textViewAccountBalance = findViewById (R.id.text_view_account_balance);
        imageViewLogout = findViewById (R.id.image_view_logout);
        recyclerViewPayments = findViewById (R.id.recyclerViewPayments);
    }
}
