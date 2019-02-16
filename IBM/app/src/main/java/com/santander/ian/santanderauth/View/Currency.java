package com.santander.ian.santanderauth.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.santander.ian.santanderauth.Utils.Auxiliar;
import com.santander.ian.santanderauth.Utils.Constantes;
import com.santander.ian.santanderauth.Utils.HMAux;
import com.santander.ian.santanderauth.R;

import com.santander.ian.santanderauth.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


public class Currency extends AppCompatActivity{

    private Context context;
    private ListView curr_lv_recentes;
    private TextView curr_tv_name;
    private TextView curr_tv_bankaccount;
    private TextView curr_tv_balance;
    private ImageView curr_iv_logout;

    private SimpleAdapter adapter;
    private ArrayList<HMAux> map;
    private RequestQueue queue;

    private User auth_user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.currency);

        initVars();
        initActions();
    }

    private void initVars() {
        context = getBaseContext();

        queue = Volley.newRequestQueue(this);

        curr_lv_recentes = findViewById(R.id.curr_lv_recentes);
        curr_tv_name = findViewById(R.id.curr_tv_name);
        curr_tv_bankaccount = findViewById(R.id.curr_tv_bankaccount);
        curr_tv_balance = findViewById(R.id.curr_tv_balance);
        curr_iv_logout = findViewById(R.id.curr_iv_logout);

        auth_user = new User();

        map = new ArrayList<>();

        load_intent_variables();

        //mock();

        int[] to = {R.id.celula_tv_title, R.id.celula_tv_desc, R.id.celula_tv_date, R.id.celula_tv_value};
        String[] from = {Constantes.TITLE,Constantes.DESC,Constantes.DATE,Constantes.VALUE};

        adapter = new SimpleAdapter(
                context,
                map,
                R.layout.celula,
                from,
                to
        );

        curr_lv_recentes.setAdapter(adapter);
    }


    private void initActions() {
        print_user_screen();

        get_and_read_json();

        curr_iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(context, Auth.class) ;
                startActivity(mIntent);
                finish();
            }
        });

    }

    private void load_intent_variables() {

        auth_user = getIntent().getParcelableExtra(Constantes.AUTHUSER);

    }


    private void print_user_screen() {

        curr_tv_name.setText(auth_user.getName());

        String agency = String.format(Locale.US, "%09d",auth_user.getAgency());

        StringBuilder bankaccount = new StringBuilder();
        bankaccount.append(auth_user.getBankaccount());
        bankaccount.append(" / ");
        bankaccount.append(
                agency.substring(0,2)
        );
        bankaccount.append(".");
        bankaccount.append(
                agency.substring(2,8)
        );
        bankaccount.append("-");
        bankaccount.append(
                agency.substring(8,9)
        );



        curr_tv_bankaccount.setText(bankaccount.toString());

        curr_tv_balance.setText(Auxiliar.convert_to_currency(auth_user.getBalance()));

    }

    private void mock() {
        HMAux aux = new HMAux();
        aux.put(Constantes.TITLE,"Pagamento");
        aux.put(Constantes.DESC,"Conta de luz");
        aux.put(Constantes.DATE,"2018-08-15");
        aux.put(Constantes.VALUE,"-R$ 50,00");
        map.add(aux);
        HMAux aux1 = new HMAux();
        aux1.put(Constantes.TITLE,"TED Recebida");
        aux1.put(Constantes.DESC,"Joao Alfredo");
        aux1.put(Constantes.DATE,"2018-07-25");
        aux1.put(Constantes.VALUE,"R$ 745,03");
        map.add(aux1);
        HMAux aux2 = new HMAux();
        aux2.put(Constantes.TITLE,"DOC Recebida");
        aux2.put(Constantes.DESC,"Victor Silva");
        aux2.put(Constantes.DATE,"2018-06-23");
        aux2.put(Constantes.VALUE,"R$ 399,90");
        map.add(aux2);
        HMAux aux3 = new HMAux();
        aux3.put(Constantes.TITLE,"Pagamento");
        aux3.put(Constantes.DESC,"Conta de internet");
        aux3.put(Constantes.DATE,"2018-05-12");
        aux3.put(Constantes.VALUE,"-R$ 73,40");
        map.add(aux2);
    }


    private void get_and_read_json() {

        final String url = Constantes.API_STATEMENS +auth_user.getUserid();

// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response

                        try {
                            JSONArray json_array = response.getJSONArray(Constantes.STATEMENT_LIST);

                            for(int i = 0 ; i < json_array.length() ; i++){
                                HMAux aux = new HMAux();

                                aux.put(Constantes.TITLE,
                                        json_array.getJSONObject(i).getString(Constantes.TITLE));
                                aux.put(Constantes.DESC,
                                        json_array.getJSONObject(i).getString(Constantes.DESC));
                                aux.put(Constantes.DATE,
                                        Auxiliar.format_date(
                                            json_array.getJSONObject(i).getString(Constantes.DATE)));
                                aux.put(Constantes.VALUE,
                                        Auxiliar.convert_to_currency(
                                                json_array.getJSONObject(i).getString(Constantes.VALUE)));

                                map.add(aux);
                            }

                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }


        ) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        // add it to the RequestQueue
        queue.add(getRequest);


    }


}
