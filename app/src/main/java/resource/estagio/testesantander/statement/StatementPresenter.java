package resource.estagio.testesantander.statement;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import resource.estagio.testesantander.R;
import resource.estagio.testesantander.infra.ApiServices;
import resource.estagio.testesantander.infra.RetrofitClient;
import resource.estagio.testesantander.login.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementPresenter implements StatementContract.Presenter {

    private StatementContract.View view;

    public StatementPresenter(StatementContract.View view) {
        this.view = view;
    }

    @Override
    public void getStatement(long id) {
        Call<StatementResponse> call = RetrofitClient.getInstance()
                .createService(ApiServices.class).getStatementList(id);


        call.enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
                StatementResponse statementResponse = response.body();
                List<Statement> statements = new ArrayList<Statement>(statementResponse.getStatementList());
                view.showList(statementResponse.getStatementList());

            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public void showDialogExit() {
        final Dialog dialog = new Dialog(view.getActivity(), R.style.CustomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_exit);
        dialog.setCancelable(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.
                SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.show();

        Button dialog_yes = dialog.findViewById(R.id.button_dialog_yes);
        Button dialog_no = dialog.findViewById(R.id.button_dialog_no);
        dialog_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.navigateToLogin();

            }
        });
        dialog_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}

