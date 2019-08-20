package br.com.giovanni.testebank;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.giovanni.testebank.Presenter.ListAdapter;
import br.com.giovanni.testebank.Presenter.ListExamples;
import br.com.giovanni.testebank.R;
import br.com.giovanni.testebank.UserAccount;

public class DetailActivity extends AppCompatActivity {

    public TextView nameId;
    public TextView agencyId;
    public TextView balanceId;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListExamples> listExamples;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameId = findViewById(R.id.txtNameId);
        agencyId = findViewById(R.id.txtAgencyId);
        balanceId = findViewById(R.id.txtBalanceId);

        //comeco recycler view
        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listExamples = new ArrayList<>();

        for (int i = 0; i<=10; i++ ){
            ListExamples listExamplesArray = new ListExamples(
                    "caixa economica" + i+1,
                    "Pagar essa conta",
                    123.23,
                    "14/10/2018");
            listExamples.add(listExamplesArray);
        }

        adapter = new ListAdapter(listExamples, this);
        recyclerView.setAdapter(adapter);

        // fim recycler
        UserAccount userAccount = (UserAccount)getIntent().getSerializableExtra("userAccount_key");
        nameId.setText(userAccount.getName());
        agencyId.setText(userAccount.getBankAccount() + " / " + userAccount.getAgency());
        balanceId.setText( "R$ " + userAccount.getBalance());

        Toast.makeText(this, userAccount.getName(), Toast.LENGTH_LONG).show();



    }
}
