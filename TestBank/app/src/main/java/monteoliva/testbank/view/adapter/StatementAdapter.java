package monteoliva.testbank.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import monteoliva.testbank.R;
import monteoliva.testbank.controller.StatementList;
import monteoliva.testbank.utils.UtilsHelper;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ItemViewHolder> {
    private List<StatementList> list;

    /**
     * Constructor
     *
     * @param list
     */
    public StatementAdapter(List<StatementList> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ItemViewHolder ivh = new ItemViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        StatementList item = getItem(position);

        itemViewHolder.item_column1.setText(item.getTitle());
        itemViewHolder.item_column2.setText(UtilsHelper.formatDate(item.getDate()));
        itemViewHolder.item_column3.setText(item.getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private StatementList getItem(int position) {
        return list.get(position);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView item_column1;
        TextView item_column2;
        TextView item_column3;
        TextView item_column4;

        /**
         * Constructor
         *
         * @param view
         */
        public ItemViewHolder(View view) {
            super(view);
            item_column1 = view.findViewById(R.id.item_column1);
            item_column2 = view.findViewById(R.id.item_column2);
            item_column3 = view.findViewById(R.id.item_column3);
            item_column4 = view.findViewById(R.id.item_column4);
        }
    }
}