package br.com.satandertest.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.satandertest.R;
import br.com.satandertest.models.Statement;
import br.com.satandertest.utils.RecyclerViewOnClickListenerHack;
import br.com.satandertest.utils.Utilities;

public class StatementsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Statement> itemList;
    private Context context;

    private RecyclerView mRecyclerView;

    private static RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public StatementsAdapter(Context context, List<Statement> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statement, null);
        StatementViewHolders rcv = new StatementViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final Statement item = itemList.get(position);

        //Atribuindo valores de cada instância do statment
        ((StatementViewHolders) holder).tv_date_statement.setText(Utilities.formatDatePt(item.getDate()));
        ((StatementViewHolders) holder).tv_desc_statement.setText(item.getDesc());

        Utilities.setCurrencyText(((StatementViewHolders) holder).tv_balance_statement, item.getValue());

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        this.mRecyclerViewOnClickListenerHack = r;
    }

    public void removeListItem(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);
    }

    public Statement getItemList(int position) {

        Statement item = itemList.get(position);

        return item;
    }

    public class StatementViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_date_statement;
        public TextView tv_desc_statement;
        public TextView tv_balance_statement;
        public CardView card;

        public StatementViewHolders(View itemView) {
            super(itemView);

            tv_date_statement = (TextView) itemView.findViewById(R.id.tv_date_statement);
            tv_desc_statement = (TextView) itemView.findViewById(R.id.tv_desc_statement);
            tv_balance_statement = (TextView) itemView.findViewById(R.id.tv_balance_statement);

            card = (CardView) itemView.findViewById(R.id.card);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(view, getPosition());
            }
        }

    }

}
