package br.com.kakobotasso.testeandroidv2.currency;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.kakobotasso.testeandroidv2.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyAdapter extends RecyclerView.Adapter{
    private List<CurrencyItem> items;
    private Context context;

    public CurrencyAdapter(List<CurrencyItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_currency, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CurrencyViewHolder holder = (CurrencyViewHolder) viewHolder;
        CurrencyItem item = items.get(position);
        Locale locale = new Locale("pt", "BR");

        holder.tvCurrencyType.setText(item.getTitle());
        holder.tvCurrencyDescription.setText(item.getDescription());

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        String formattedDate = df.format(item.getDate());
        holder.tvCurrencyDate.setText(formattedDate);

        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        String valueString = formatter.format(item.getValue());
        holder.tvCurrencyValue.setText(valueString);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_currency_type)
        TextView tvCurrencyType;
        @BindView(R.id.tv_currency_date)
        TextView tvCurrencyDate;
        @BindView(R.id.tv_currency_description)
        TextView tvCurrencyDescription;
        @BindView(R.id.tv_currency_value)
        TextView tvCurrencyValue;

        public CurrencyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
