package teste.santander.com.santander;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class StatementAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<StatementModel> statementModelArrayList;

    public StatementAdapter(Activity context, ArrayList<StatementModel> statementModelArrayList) {

        this.context = context;
        this.statementModelArrayList = statementModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return statementModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return statementModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_statements, null, true);

            holder.tvtitle = (TextView) convertView.findViewById(R.id.title);
            holder.tvdesc = (TextView) convertView.findViewById(R.id.desc);
            holder.tvdate = (TextView) convertView.findViewById(R.id.date);
            holder.tvvalue = (TextView) convertView.findViewById(R.id.value);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvtitle.setText(statementModelArrayList.get(position).getTitle());
        holder.tvdesc.setText(statementModelArrayList.get(position).getDesc());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data = null;
        try {
            data = dateFormat.parse(statementModelArrayList.get(position).getDate());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvdate.setText(new SimpleDateFormat("dd-MM-yyyy").format(data));

        Double d =Double.parseDouble(statementModelArrayList.get(position).getValue());
        Locale ptBr = new Locale("pt", "BR");
        String valorString = NumberFormat.getCurrencyInstance(ptBr).format(d);
        System.out.println(valorString);

        holder.tvvalue.setText(valorString);

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvtitle, tvdesc, tvdate, tvvalue;
        protected ImageView iv;
    }

}
