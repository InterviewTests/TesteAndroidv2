package com.test.banktest.homeScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.banktest.R;
import com.test.banktest.model.StatementViewModel;
import com.test.banktest.model.UserModel;

import java.util.ArrayList;


interface HomeActivityInput {
    public void displayHomeData(HomeViewModel viewModel);
    void logout();
}


public class HomeActivity extends AppCompatActivity
        implements HomeActivityInput {

    public static String TAG = HomeActivity.class.getSimpleName();
    HomeInteractorInput output;
    HomeRouter router;
    
    private UserModel userAccount;
    private TextView txtName;
    private TextView txtBalance;
    private TextView txtAccount;
    private ImageButton btLogout;
    private RecyclerView recyclerStatements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userAccount = this.getIntent().getParcelableExtra("user");
        bindViews();

        HomeConfigurator.INSTANCE.configure(this);
        HomeRequest aHomeRequest = new HomeRequest();
        aHomeRequest.user = userAccount;

        output.fetchHomeData(aHomeRequest);
    }

    private void bindViews() {
        txtName = this.findViewById(R.id.txtName);
        txtAccount = this.findViewById(R.id.txtAccount);
        txtBalance = this.findViewById(R.id.txtBalance);

        recyclerStatements = this.findViewById(R.id.recyclerStatements);
        recyclerStatements.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerStatements.setLayoutManager(layoutManager);

        btLogout = this.findViewById(R.id.btLogout);
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.logout();
            }
        });
    }


    @Override
    public void displayHomeData(HomeViewModel viewModel) {
        this.txtName.setText(viewModel.user.name);
        this.txtAccount.setText(viewModel.user.agencyAccount);
        this.txtBalance.setText(viewModel.user.balance);
        recyclerStatements.setAdapter(new StatementAdapter(viewModel.statementList));
    }

    @Override
    public void logout() {
        this.finish();
    }

    private class StatementAdapter extends RecyclerView.Adapter {

        private final ArrayList<StatementViewModel> list;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtTitle;
            public TextView txtDate;
            public TextView txtDesc;
            public TextView txtValue;
            public MyViewHolder(ViewGroup vg) {
                super(vg);
                txtTitle = vg.findViewById(R.id.txtTitle);
                txtDate = vg.findViewById(R.id.txtDate);
                txtDesc = vg.findViewById(R.id.txtDesc);
                txtValue = vg.findViewById(R.id.txtValue);
            }
        }

        public StatementAdapter(ArrayList<StatementViewModel> statementList) {
            this.list = statementList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_statement, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }


        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            StatementViewModel st = list.get(position);
            MyViewHolder myHolder = (MyViewHolder) holder;
            myHolder.txtTitle.setText(st.title);
            myHolder.txtDate.setText(st.date);
            myHolder.txtDesc.setText(st.desc);
            myHolder.txtValue.setText(st.value);
        }

        @Override
        public int getItemCount() {
            return this.list.size();
        }
    }
}
