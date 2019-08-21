package br.com.giovanni.testebank.Services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class StatementsWebService {


    public String get(int idUser) {

        try {
            URL url = new URL("https://bank-app-test.herokuapp.com/api/statements/" + idUser);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);


            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.nextLine();

            while (scanner.hasNext()) {
                resposta += scanner.nextLine();
            }
            scanner.close();

            return resposta;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;


    }
}






//    private RecyclerView.Adapter adapter;
//    private List<ListExamples> listExamples;
//
//    DetailActivity detailActivity = new DetailActivity();
//
//
//    private static final String URL_DATA = "https://bank-app-test.herokuapp.com/api/statements/1";
//
//    public void loadRecyclerViewData (){
//

//        final ProgressDialog progressDialog = new ProgressDialog(detailActivity.this);
//        progressDialog.setMessage("Loading data...");
//        progressDialog.show();

//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
////                        progressDialog.dismiss();
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray array = jsonObject.getJSONArray("statementList");
//
//                            for (int i = 0; i<array.length(); i++){
//                                JSONObject object = array.getJSONObject(i);
//                                ListExamples item = new ListExamples(
//                                        object.getString("title"),
//                                        object.getString("desc"),
//                                        object.getString("date"),
//                                        object.getString("value")
//                                );
//                                listExamples.add(item);
//                            }
//
//                            adapter = new ListAdapter(listExamples, detailActivity.getApplicationContext());
//                            detailActivity.recyclerView.setAdapter(adapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        progressDialog.dismiss();
//                        Toast.makeText(detailActivity.getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//
//        RequestQueue requestQueue = Volley.newRequestQueue(detailActivity);
//        requestQueue.add(stringRequest);
//
//    }