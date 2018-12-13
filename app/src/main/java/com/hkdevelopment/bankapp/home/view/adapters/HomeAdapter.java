package com.hkdevelopment.bankapp.home.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hkdevelopment.bankapp.R;
import com.hkdevelopment.bankapp.home.model.StatementList;
import com.hkdevelopment.bankapp.utils.MaskFormater;
import com.hkdevelopment.bankapp.utils.MaskFormaterInt;

import java.text.ParseException;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<StatementList> statementLists;
    private MaskFormaterInt format;

    public HomeAdapter(Context context, List<StatementList> statementLists) {
        this.context = context;
        this.statementLists = statementLists;
        format=new MaskFormater();
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_adapter_home, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder viewHolder, int i) {
        StatementList listObjct = statementLists.get(i);
        viewHolder.txtTitle.setText(listObjct.getTitle());
        Log.d("testeAdapter",listObjct.getTitle());
        viewHolder.txtDesc.setText(listObjct.getDesc());
        viewHolder.txtValue.setText(format.formatValue(listObjct.getValue()));
        try {
            viewHolder.txtData.setText(format.formateDate(listObjct.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
            viewHolder.txtData.setText("Vazio!");
        }

    }

    @Override
    public int getItemCount() {
        return statementLists.size();
    }

//    public static String formatString(String string, String mask)
//            throws java.text.ParseException {
//        javax.swing.text.MaskFormatter mf =
//                new javax.swing.text.MaskFormatter(mask);
//        mf.setValueContainsLiteralCharacters(false);
//        return mf.valueToString(string);
//    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtData, txtDesc, txtValue;

        ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtData = itemView.findViewById(R.id.txtData);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtValue = itemView.findViewById(R.id.txtValue);
        }
    }
}
