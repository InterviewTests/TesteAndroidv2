package br.com.fernandodutra.testeandroidv2.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.models.StatementList;
import br.com.fernandodutra.testeandroidv2.models.StatementListResponse;

import java.text.NumberFormat;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 20/06/2019
 * Time: 14:15
 * TesteAndroidv2
 */
public class StatementListAdapter extends RecyclerView.Adapter<StatementListAdapter.StatementListHolder> {

    private StatementListResponse statementListsModel = new StatementListResponse();

    @NonNull
    @Override
    public StatementListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.statement_item, viewGroup, false);
        return new StatementListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementListHolder statementListHolder, int i) {
        StatementList statementList = statementListsModel.getStatementList().get(i);

        String dataFormated = statementList.getDate().substring(8,10) + "/" +
                      statementList.getDate().substring(5,7) + "/" +
                      statementList.getDate().substring(0,4);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        String valueFormated = numberFormat.format(statementList.getValue());

        statementListHolder.tv_title.setText(statementList.getTitle());
        statementListHolder.tv_desc.setText(statementList.getDesc());
        statementListHolder.tv_date.setText(dataFormated);
        statementListHolder.tv_value.setText(valueFormated);
    }

    @Override
    public int getItemCount() {
        return statementListsModel.getStatementList().size();
    }

    public void setStatementListsModel(StatementListResponse statementListsModel) {
        this.statementListsModel = statementListsModel;
        notifyDataSetChanged();
    }

    class StatementListHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_desc;
        private TextView tv_date;
        private TextView tv_value;

        public StatementListHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.statement_item_et_title);
            tv_desc = itemView.findViewById(R.id.statement_item_et_desc);
            tv_date = itemView.findViewById(R.id.statement_item_et_date);
            tv_value = itemView.findViewById(R.id.statement_item_et_value);
        }
    }

}
