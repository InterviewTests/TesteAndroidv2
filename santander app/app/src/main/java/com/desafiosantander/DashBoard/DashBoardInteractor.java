package com.desafiosantander.DashBoard;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

interface DashBoardInteractorInput {
    public void fetchDashBoardData(DashBoardRequest request);
    public void loadStatements();
    public void getResponse(Boolean success, String response);
}


public class DashBoardInteractor implements DashBoardInteractorInput {

    public static String TAG = DashBoardInteractor.class.getSimpleName();
    public DashBoardPresenterInput output;
    public DashBoardWorkerInput aDashBoardWorkerInput;

    public DashBoardWorkerInput getDashBoardWorkerInput() {
        if (aDashBoardWorkerInput == null) return new DashBoardWorker();
        return aDashBoardWorkerInput;
    }

    public void setDashBoardWorkerInput(DashBoardWorkerInput aDashBoardWorkerInput) {
        this.aDashBoardWorkerInput = aDashBoardWorkerInput;
    }

    @Override
    public void fetchDashBoardData(DashBoardRequest request) {
        aDashBoardWorkerInput = getDashBoardWorkerInput();
        DashBoardResponse DashBoardResponse = new DashBoardResponse();
        // Call the workers

        output.presentDashBoardData(DashBoardResponse);
    }

    @Override
    public void loadStatements() {
        DashBoardRequest task = new DashBoardRequest(this);
        task.execute("https://bank-app-test.herokuapp.com/api/statements/1");
    }

    @Override
    public void getResponse(Boolean success, String response) {
        Log.d("RESPONSE: ","Success: "+success+" REsponse: "+response);
        JSONObject obj;
        try{
            obj = new JSONObject(response);

            Gson gson = new Gson();
            Type type = new TypeToken<List<Statement>>(){}.getType();
            List<Statement> contactList = gson.fromJson(obj.getString("statementList"), type);

            output.setStatmentList(contactList);

        }catch (Exception e){

        }
    }
}
