package br.com.giovanni.testebank.Presenter;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.giovanni.testebank.R;
import br.com.giovanni.testebank.UserAccount;

public class DetailActivity extends AppCompatActivity {

    public TextView nameId;
    public TextView agencyId;
    public TextView balanceId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameId = findViewById(R.id.txtNameId);
        agencyId = findViewById(R.id.txtAgencyId);
        balanceId = findViewById(R.id.txtBalanceId);

        UserAccount userAccount = (UserAccount)getIntent().getSerializableExtra("userAccount_key");
        nameId.setText(userAccount.getName());
        agencyId.setText(userAccount.getBankAccount() + " / " + userAccount.getAgency());
        balanceId.setText( "R$ " + userAccount.getBalance());

        Toast.makeText(this, userAccount.getName(), Toast.LENGTH_LONG).show();



    }
}
