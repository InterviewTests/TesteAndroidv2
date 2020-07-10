package com.accenture.android.app.testeandroid.presentation.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.accenture.android.app.testeandroid.databinding.ItemRecyclerStatementBinding;
import com.accenture.android.app.testeandroid.domain.Statement;
import com.accenture.android.app.testeandroid.helpers.FormatHelper;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class StatementsRecyclerAdapter extends RecyclerView.Adapter<StatementsRecyclerAdapter.StatementViewHolder> {
    private final static String TAG = "CustomLog - " + StatementsRecyclerAdapter.class.getSimpleName();

    private final Context context;

    private ArrayList<Statement> statements;

    public StatementsRecyclerAdapter(Context context, ArrayList<Statement> statements) {
        this.context = context;
        this.statements = statements;
    }

    @NonNull
    @Override
    public StatementsRecyclerAdapter.StatementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ItemRecyclerStatementBinding binding = ItemRecyclerStatementBinding.inflate(layoutInflater, parent, false);

        return new StatementViewHolder(this.context, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementsRecyclerAdapter.StatementViewHolder holder, int position) {
        holder.bindStatement(this.statements.get(position));
    }

    @Override
    public int getItemCount() {
        return this.statements.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class StatementViewHolder extends RecyclerView.ViewHolder {
        private final static String TAG = "CustomLog - " + StatementViewHolder.class.getSimpleName();

        private Context context;
        private ItemRecyclerStatementBinding binding;

        public StatementViewHolder(Context context, ItemRecyclerStatementBinding binding) {
            super(binding.getRoot());

            this.context = context;
            this.binding = binding;
        }

        public void bindStatement(Statement statement) {
            this.binding.txtTitulo.setText(statement.getTitle());
            this.binding.txtDescricao.setText(statement.getDesc());

            try {
                this.binding.txtData.setText(FormatHelper.formatarData(statement.getDateCalendar()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            this.binding.txtValor.setText(FormatHelper.formatarReal(statement.getValue()));
        }
    }
}
