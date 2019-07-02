package resource.estagio.testesantander.statement.adapter;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import resource.estagio.testesantander.R;
import resource.estagio.testesantander.statement.Statement;

public class AdapterStatement extends RecyclerView.Adapter<AdapterStatement.ViewHolder> {
    List<Statement> list;
    public static final String Helvetica_Neue_Light = "HelveticaNeue-Light.otf";

    public AdapterStatement(List<Statement> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_statement, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Statement statement = list.get(i);

        viewHolder.pagamento.setText(statement.getTitle());
        viewHolder.conta.setText(statement.getDesc());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(statement.getDate());
        viewHolder.data.setText(date);

        Locale locale = new Locale("pt", "BR");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        String currency = format.format(statement.getValue());
        viewHolder.valor.setText(currency);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pagamento, conta, data, valor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Typeface font = Typeface.createFromAsset(itemView.getContext().getAssets(), Helvetica_Neue_Light );
            pagamento = itemView.findViewById(R.id.title);
            conta = itemView.findViewById(R.id.desc);
            data = itemView.findViewById(R.id.date);
            valor = itemView.findViewById(R.id.value);

            pagamento.setTypeface(font);
            conta.setTypeface(font);
            data.setTypeface(font);
            valor.setTypeface(font);
        }

    }

}
