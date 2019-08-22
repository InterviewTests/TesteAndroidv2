package teste.santander.com.santander;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

interface CurrencyInteractorInput {
    void fetchCurrencyMetaData(CurrencyRequest cr);
}

public class CurrencyInteractor implements CurrencyInteractorInput {

    public CurrencyPresenterInput output;
    public WeakReference<Currency> activity;
    private StatementAdapter statementAdapter;
    ArrayList<StatementModel> statementModelArrayList = new ArrayList<>();

    public void bringStatements(CurrencyRequest cr) {
        String customUrl = "https://bank-app-test.herokuapp.com/api/statements/" + cr.userId;
        RequestQueue queue = Volley.newRequestQueue(activity.get());
        StringRequest sr = new StringRequest(Request.Method.GET,customUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray dataArray = jsonObject.getJSONArray("statementList");

                    for (int i = 0; i < dataArray.length(); i++) {
                        StatementModel statements = new StatementModel();

                        JSONObject dataobj = dataArray.getJSONObject(i);
                        statements.setTitle(dataobj.getString("title"));
                        statements.setDesc(dataobj.getString("desc"));
                        statements.setDate(dataobj.getString("date"));
                        statements.setValue(dataobj.getString("value"));
                        statementModelArrayList.add(statements);
                    }
                    statementAdapter = new StatementAdapter(activity.get(),statementModelArrayList);
                    output.presentCurrencyMetaData(statementAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mPostCommentResponse.requestEndedWithError(error);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    @Override
    public void fetchCurrencyMetaData(CurrencyRequest cr) {
        bringStatements(cr);
    }
}
