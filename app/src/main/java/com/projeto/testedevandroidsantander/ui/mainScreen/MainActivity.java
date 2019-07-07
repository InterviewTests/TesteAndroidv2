package com.projeto.testedevandroidsantander.ui.mainScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projeto.santander.R;
import com.projeto.testedevandroidsantander.model.LancamentoViewModel;
import com.projeto.testedevandroidsantander.model.UsuarioModel;
import com.projeto.testedevandroidsantander.util.Constants;
import com.projeto.testedevandroidsantander.util.Uteis;

import java.util.List;

interface MainActivityInput {
    void displayMainMetaData(MainViewModel viewModel);
    void showProgressBar();
    void hideProgressBar();
}

public class MainActivity extends AppCompatActivity implements MainActivityInput {

    private Toolbar mToolbar;
    public List<LancamentoViewModel> lancamentoViewModelArrayList;

    MainInteractorInput output;
    MainRouter router;
    ProgressBar progressBar;
    TextView contaTextView;
    TextView saldoTextView;
    private UsuarioModel usuarioModel;

    public static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento);

        init();

        usuarioModel = getIntent().getParcelableExtra(Constants.OBJETO_USUARIO);

        setValues();

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(usuarioModel.getNome());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        MainConfigurator.INSTANCE.configure(this);
        fetchMetaData();
    }

    private void init() {
        progressBar = findViewById(R.id.progressBar);
        contaTextView = findViewById(R.id.contaTextView);
        saldoTextView = findViewById(R.id.saldoTextView);
    }

    private void setValues(){
        contaTextView.setText(usuarioModel.getConta() + " / " +Uteis.addMaskContaAgencia( usuarioModel.getAgencia(), "##.######-#" ));
        saldoTextView.setText(Uteis.formatCurrencyBr( usuarioModel.getSaldo()) );
    }

    private void createLancamentoRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMainActitvity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LancamentoAdapter());
    }

    public void fetchMetaData() {
        MainRequest mainRequest = new MainRequest();
        mainRequest.usuarioModel = usuarioModel;
        output.fetchMainMetaData(mainRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lancamento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {}

    @Override
    public void displayMainMetaData(MainViewModel viewModel) {
        lancamentoViewModelArrayList = viewModel.lancamentos;
        createLancamentoRecycleView();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public class LancamentoAdapter extends RecyclerView.Adapter<LancamentoAdapter.LancamentoViewHolder> {
        LancamentoAdapter(){}

        @Override
        public LancamentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.cell_lancamento_list, parent, false);
            return new LancamentoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(LancamentoViewHolder holder, int position) {
            LancamentoViewModel lancamentoViewModel = lancamentoViewModelArrayList.get(position);
            holder.tituloTextView.setText(lancamentoViewModel.titulo);
            holder.dataTextView.setText(Uteis.formatDataParaBr(lancamentoViewModel.data));
            holder.descricaoTextView.setText(lancamentoViewModel.descricao);
            holder.valorTextView.setText(Uteis.formatCurrencyBr(lancamentoViewModel.valor));
        }

        @Override
        public int getItemCount() {
            return lancamentoViewModelArrayList != null ? lancamentoViewModelArrayList.size() : 0;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class LancamentoViewHolder extends RecyclerView.ViewHolder {

            TextView tituloTextView;
            TextView dataTextView;
            TextView descricaoTextView;
            TextView valorTextView;

            public LancamentoViewHolder(View itemView) {
                super(itemView);
                tituloTextView = itemView.findViewById(R.id.tituloTextView);
                dataTextView = itemView.findViewById(R.id.dataTextView);
                descricaoTextView = itemView.findViewById(R.id.descricaoTextView);
                valorTextView = itemView.findViewById(R.id.valorTextView);
            }
        }
    }
}
