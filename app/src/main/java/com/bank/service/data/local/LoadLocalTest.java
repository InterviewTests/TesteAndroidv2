package com.bank.service.data.local;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.bank.service.ui.statements.domain.model.StatementList;
import com.bank.service.ui.statements.domain.model.Statements;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;



public class LoadLocalTest {

    final String TAG = "STATEMENTS";
    public String FILE_DATA;


       /// userCaseTeste();


    public void userCaseTeste(){

       // Log.i(TAG, "M/userCaseTeste/START");

        String  loadDataTest; // 01>
        List<Statements> gsonTolistTest = new ArrayList<>(); // 02>
        //List<StatementList> gsonTolistTest = new ArrayList<>(); // 02>
        HashMap<String, String> listToMapTest = new HashMap<>(); // 03>
        Map<String, String> gsonToMapTest = new HashMap<>(); //03 <

        //loadDataTest =   loadDataLocal(null);
        //loadDataFrom = loadData(null);

         gsonTolistTest = gsonToList(loadDataLocal(null));
          // listToMapTest =  listToMap(gsonTolistTest);
         gsonToMapTest = gsonToMap(gsonTolistTest);

       // Log.e(TAG,"TESTCASE > listToMapTest=" + (listToMapTest != null));

    }


    public void showData(ArrayList<Statements> Statements){



        String testeItens =  "Statements{" +
                " id='" + Statements.get(0).getTitle()+ '\'' +
                ", type='" + Statements.get(0).getDate() + '\'' +
                ", message='" + Statements.get(0).getDesc() + '\'' +
                ", typefield='" + Statements.get(0).getValue() + '\'' +
                '}';


        //tvTest.setText(testeItens);

        Log.d(TAG, "M/TESTE/response/" + (Statements.size()));

    }


    public String loadData(String area) {

        Log.e("LOADR","M/loadFile/>Carregando json..." + area);


        //SERVER_URL =  new ConfigServers().getDataServer(area);
        String SERVER_URL = "http://www.issam.com.br/lab/acento/cels1.txt";

        try {
            URL url = new URL(SERVER_URL);
            StringBuilder SB = new StringBuilder();
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            // Log.d(TAG, "buffer = " + (buffer!=null));
            String line = null;
            while ((line = buffer.readLine()) != null) {

                SB.append(line);
                // Log.d(TAG, "" + line);
            }
            buffer.close();


            FILE_DATA = SB.toString();
            // Log.d(TAG, "FILE_DATA = " + FILE_DATA);

            //  Log.d(TAG, "M/loadData/listaArray/" + listaArray.size() );

        } catch (Exception e) {
            // errorData( "", 4);
            //nextData(area);
            Log.d(TAG, "Erro ao carregar arquivo!" + (e));
        }


        return FILE_DATA;
    }


    public List<Statements> gsonToList(String listStr){

         if(listStr==null){return null;}
         if(listStr.length()< 100 ){return null;}
         //if(listStr.length()> 2000 ){return null;}

           Log.e(TAG, "M/gsonToList/listStr=" + listStr.length());

          List<Statements> list = new ArrayList<>();

         try {

                Gson GS = new Gson();
                StatementList response = GS.fromJson(listStr, StatementList.class);
                Log.i(TAG, "M/gsonToList/response="+(response.statementList.size())+" / " + response.statementList.get(0).getDate());

                 list = response.getList();

              }
            catch (JsonParseException ex) { }
            catch (IndexOutOfBoundsException ex) { }
            catch (Exception ex) { }


        return list;
    }

    /**
     *
     *  1 - > converte List em String
     *  2 - > Converte String em Map
     *  3 - > Salva em Pref
     *
     * @param list
     * @return
     */


    public HashMap<String, String> gsonToMap(List<Statements> list) {

       Log.i(TAG, "M/gsonToMap/list.size=" + list.size());

        HashMap<String, String> map = new HashMap<>();
        if(list==null){return null;}
       // if(!(list.size() == 6)){return null;}

        try {

            Gson gson;
            Type type;
            String gsonToStr;

            for(int i=0; i<list.size(); i++){
                gson = new Gson();
                type = new TypeToken<Map<String, String>>(){}.getType();

                gsonToStr = gson.toJson(list.get(i));
                Map<String, String> strToMap = gson.fromJson(gsonToStr, type);

                //Log.e(TAG, "M/gsonToMap/list=" + gsonToStr);
                Log.e(TAG, "--- elmento " +i+"--- ");

                for (Map.Entry<String, String> entry : strToMap.entrySet()) {

                    Log.e(TAG, "M/gsonToMap/strToMap=" + entry.getKey() + "=" + entry.getValue());
                }

            }

        }

        catch (JsonParseException ep) {
            Log.e(TAG, "M/gsonToMap/parser=" + ep);
        }
        catch (IndexOutOfBoundsException eb) {
            Log.e(TAG, "M/gsonToMap/bound=" + eb);
        }
        catch (Exception ex) {
            Log.e(TAG, "M/gsonToMap/ecep=" + ex);
        }
        return map;
    }


    public HashMap<String, String> listToMap(List<Statements> list) {
        HashMap<String, String> map = new HashMap<>();

        Log.e(TAG,"M/listToMap/list:" + (list.size()));

        for (Statements Statements : list) {
             // HashMap<String, String> map = new HashMap<>();
              //map.put(KEY_VER, conteudo.getId());
             // Log.d(TAG, "dataToMap:" + Statements.getId());
              Log.d(TAG, "listToMap:" + Statements.getDesc());
        }

        return map;
    }



    public String loadDataLocal(String area){

        FILE_DATA = "{'statementList':[{'title':'Pagamento','desc':'Conta de luz','date':'2018-08-15','value':-50},{'title':'TED Recebida','desc':'Joao Alfredo','date':'2018-07-25','value':745.03},{'title':'DOC Recebida','desc':'Victor Silva','date':'2018-06-23','value':399.9},{'title':'Pagamento','desc':'Conta de internet','date':'2018-05-12','value':-73.4},{'title':'Pagamento','desc':'Faculdade','date':'2018-09-10','value':-500},{'title':'Pagamento','desc':'Conta de telefone','date':'2018-10-17','value':-760},{'title':'TED Enviada','desc':'Roberto da Luz','date':'2018-07-27','value':-35.67},{'title':'Pagamento','desc':'Boleto','date':'2018-08-01','value':-200},{'title':'TED Recebida','desc':'Salário','date':'2018-08-21','value':1400.5}],'error':{}}";

        return FILE_DATA;
    }



    String itens = null;

    public void onNext() {
        itens = null;

        //loadData(null);
    }

    public void onError() {
        itens = null;
    }

    public void onComplete() {

        itens = "1";
    }





    public Object objectToClass(Class c){

        Object object = null;

        try {
            object = c.newInstance();
        } catch (InstantiationException ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
           // Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  object;
    }

    }

