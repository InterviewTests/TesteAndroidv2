package br.com.kakobotasso.testeandroidv2.currency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import br.com.kakobotasso.testeandroidv2.R;
import br.com.kakobotasso.testeandroidv2.util.ScreenKeys;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

interface CurrencyActivityInput {
    void showCurrencyInfo(CurrencyResponse response);
}

public class CurrencyActivity extends AppCompatActivity implements CurrencyActivityInput {

    CurrencyInteractorInput output;
    CurrencyRouter router;
    int userId = 0;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_bank_info)
    TextView tvBankInfo;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.pb_currency)
    ProgressBar progressBar;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        ButterKnife.bind(this);

        loadIntentInfo();

        CurrencyConfigurator.INSTANCE.configure(this);
        output.fetchCurrencyData(userId);
    }

    @OnClick(R.id.ib_logout)
    public void logout() {
    }

    private void loadIntentInfo() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            tvName.setText(extras.getString(ScreenKeys.NAME));

            String bankInfo = extras.getString(ScreenKeys.AGENCY) + " / "
                    + extras.getString(ScreenKeys.BANK_ACCOUNT);
            tvBankInfo.setText(bankInfo);

            NumberFormat formatter = NumberFormat.getCurrencyInstance(
                    new Locale("pt", "BR"));
            String moneyString = formatter.format(extras.getDouble(ScreenKeys.BALANCE));
            tvBalance.setText(moneyString);

            userId = extras.getInt(ScreenKeys.USER_ID);
        }
    }

    @Override
    public void showCurrencyInfo(CurrencyResponse response) {
        progressBar.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
    }
}
