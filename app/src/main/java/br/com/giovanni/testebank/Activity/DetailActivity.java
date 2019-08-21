package br.com.giovanni.testebank.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.giovanni.testebank.Interactor.DetailInterector;
import br.com.giovanni.testebank.Helpers.ListAdapter;
import br.com.giovanni.testebank.Presenter.PresenterDetail;
import br.com.giovanni.testebank.Model.Transacao;
import br.com.giovanni.testebank.R;
import br.com.giovanni.testebank.Model.UserAccount;

public class DetailActivity extends AppCompatActivity implements IPresenterActivity {

    public TextView nameId;
    public TextView agencyId;
    public TextView balanceId;
    public ImageView logoutButton;

    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Transacao> listExamples;

//    StatementsWebService statementsWebService = new StatementsWebService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlue));
            }

        nameId = findViewById(R.id.txtNameId);
        agencyId = findViewById(R.id.txtAgencyId);
        balanceId = findViewById(R.id.txtBalanceId);
        logoutButton = findViewById(R.id.btnLogoutId);
        recyclerView = findViewById(R.id.recyclerViewId);
//      comeco recycler view


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listExamples = new ArrayList<>();


//        COMENTADO PARA CRIAÇÃO DA CLASSE STATEMENTSWEBSERVICE
//        for (int i = 0; i<=10; i++ ){
//            ListExamples listExamplesArray = new ListExamples(
//                    "caixa economica",
//                    "Pagar essa conta",
//                    "R$ 2.222,20",
//                    "14/10/2018");

//            listExamples.add(listExamplesArray);
//        }

//
//        recyclerView.setAdapter(adapter);

        // fim recycler


//        statementsWebService.loadRecyclerViewData();



        UserAccount userAccount = (UserAccount)getIntent().getSerializableExtra("userAccount_key");
        nameId.setText(userAccount.getName());
        agencyId.setText(userAccount.getBankAccount() + " / " + userAccount.getAgency());
        balanceId.setText( "R$ " + userAccount.getBalance());

        PresenterDetail presenterDetail = new PresenterDetail(this);
        DetailInterector detailInterector = new DetailInterector(presenterDetail);

        detailInterector.getDetail(userAccount.getId());

        Toast.makeText(this, "Welcome " + userAccount.getName(), Toast.LENGTH_LONG).show();

        btnLogoutOnClick();

    }

    public void btnLogoutOnClick (){
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//               intent.addCategory(Intent.CATEGORY_HOME);
//               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//               startActivity(intent);

            }
        });
    }



    @Override
    public void getResponse(List<Transacao> getList) {
        adapter = new ListAdapter(getList, this);
        recyclerView.setAdapter(adapter);
    }
}
