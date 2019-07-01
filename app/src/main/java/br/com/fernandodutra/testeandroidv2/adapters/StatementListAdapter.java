package br.com.fernandodutra.testeandroidv2.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.models.StatementList;
import br.com.fernandodutra.testeandroidv2.models.StatementListWorker;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 20/06/2019
 * Time: 14:15
 * TesteAndroidv2
 */
public class StatementListAdapter extends RecyclerView.Adapter<StatementListAdapter.StatementListHolder> {

    private StatementListWorker statementListWorker = new StatementListWorker();

    @NonNull
    @Override
    public StatementListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.statement_item, viewGroup, false);
        return new StatementListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementListHolder statementListHolder, int i) {
        StatementList statementList = statementListWorker.getStatementList().get(i);
        statementListHolder.tv_title.setText(statementList.getTitle());
        statementListHolder.tv_desc.setText(statementList.getDesc());
        statementListHolder.tv_date.setText(statementList.getDate());
        statementListHolder.tv_value.setText(statementList.getFormatedValue());
    }

    @Override
    public int getItemCount() {
        return statementListWorker.getStatementList().size();
    }

    public void setStatementListsModel(StatementListWorker statementListWorker) {
        this.statementListWorker = statementListWorker;
        notifyDataSetChanged();
    }

    public class StatementListHolder extends RecyclerView.ViewHolder {
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
