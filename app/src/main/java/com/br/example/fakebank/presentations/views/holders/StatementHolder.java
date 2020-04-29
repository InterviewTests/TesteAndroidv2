package com.br.example.fakebank.presentations.views.holders;

import androidx.recyclerview.widget.RecyclerView;

import com.br.example.fakebank.BR;
import com.br.example.fakebank.databinding.RvItemStatementBinding;

public class StatementHolder extends RecyclerView.ViewHolder {

    public RvItemStatementBinding rvItemStatementBinding;

    public StatementHolder(RvItemStatementBinding rvItemStatementBinding) {
        super(rvItemStatementBinding.getRoot());
        this.rvItemStatementBinding = rvItemStatementBinding;
    }

    public void bind(Object obj){
        rvItemStatementBinding.setVariable(BR.statement,obj);
        rvItemStatementBinding.executePendingBindings();
    }
}
