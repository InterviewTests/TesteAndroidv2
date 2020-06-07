package com.bank.testeandroidv2.statementScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bank.testeandroidv2.R;
import com.bank.testeandroidv2.BankSharedPreferences;

import java.util.ArrayList;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


interface StatementActivityInput {
    void displayStatementDataHeader(StatementHeaderViewModel statementHeaderViewModel);
    void displayStatementData(StatementViewModel viewModel);
    void callApiError(String error);
}


public class StatementActivity extends AppCompatActivity
        implements StatementActivityInput {

    public static String TAG = StatementActivity.class.getSimpleName();
    StatementInteractorInput output;
    StatementRouter router;

    public ArrayList<StatementViewModel> listOfStatements;

    private TextView nameTextView;
    private TextView contaTextView;
    private TextView saldoTextView;
    private ImageButton logoutButton;

    private BankSharedPreferences bankSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);
        StatementConfigurator.INSTANCE.configure(this);
        bankSharedPreferences = new BankSharedPreferences(this);

        bindViews();

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("shm_extra");

        StatementHeaderModel shm = b.getParcelable("shm_parcel");
        Log.d("TAG", "shm: " + shm.toString());
        if(shm != null) {
            loadStatementHeaderBundle(shm);
            loadStatement(shm);
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void bindViews() {
        nameTextView = findViewById(R.id.tv_name);
        contaTextView = findViewById(R.id.tv_conta);
        saldoTextView = findViewById(R.id.tv_saldo);
        logoutButton = findViewById(R.id.btn_logout);
    }

    private void loadStatementHeaderBundle(StatementHeaderModel shvm) {
        StatementHeaderRequest statementHeaderRequest = new StatementHeaderRequest();
        
        statementHeaderRequest.userId = shvm.userId;
        statementHeaderRequest.name = shvm.name;
        statementHeaderRequest.agency = shvm.agency;
        statementHeaderRequest.bankAccount = shvm.bankAccount;
        statementHeaderRequest.balance = shvm.balance;
        output.loadStatementHeaderData(statementHeaderRequest);
    }

    private void loadStatement(StatementHeaderModel shvm) {
        StatementRequest statementRequest = new StatementRequest();
        statementRequest.userId = shvm.userId;
        output.fetchStatementData(statementRequest);
    }

    private void createStatementListView() {
        ListView listView = (ListView) findViewById(R.id.lv_recentes);
        listView.setAdapter(new StatementListAdapter());
    }

    private void logout() {
        bankSharedPreferences.resetAll();
        router.goToLoginScene();
    }

    @Override
    public void displayStatementDataHeader(StatementHeaderViewModel statementHeaderViewModel) {
        Log.e(TAG, "displayStatementDataHeader() called with: viewModel = [" + statementHeaderViewModel + "]");
        // Deal with the data
        nameTextView.setText(statementHeaderViewModel.name);
        contaTextView.setText(statementHeaderViewModel.bankAccount);
        saldoTextView.setText(statementHeaderViewModel.balance);
    }

    @Override
    public void displayStatementData(StatementViewModel statementViewModel) {
        Log.e(TAG, "displayStatementData() called with: statementViewModel = [" + statementViewModel + "]");
        listOfStatements = statementViewModel.list;
        createStatementListView();
    }

    @Override
    public void callApiError(String error) {
        CharSequence text = error;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    private class StatementListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        StatementListAdapter(){
            layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return listOfStatements.size();
        }

        @Override
        public Object getItem(int position) {
            return listOfStatements.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.item_statement,null);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.tituloTextView = (TextView) convertView.findViewById(R.id.tv_titulo);
                viewHolder.descricaoTextView = (TextView) convertView.findViewById(R.id.tv_nome_conta);
                viewHolder.dataTextView = (TextView) convertView.findViewById(R.id.tv_data);
                viewHolder.valorTextView = (TextView) convertView.findViewById(R.id.tv_valor);
                convertView.setTag(viewHolder);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.tituloTextView.setText( listOfStatements.get(position).title);
            viewHolder.descricaoTextView.setText( listOfStatements.get(position).desc);
            viewHolder.dataTextView.setText( listOfStatements.get(position).date);
            viewHolder.valorTextView.setText( listOfStatements.get(position).value);
            if(listOfStatements.get(position).positive)
                viewHolder.valorTextView.setTextColor(GREEN);
            else
                viewHolder.valorTextView.setTextColor(RED);
            return convertView;
        }
    }

    class ViewHolder {
        TextView tituloTextView;
        TextView descricaoTextView;
        TextView dataTextView;
        TextView valorTextView;
    }
}
