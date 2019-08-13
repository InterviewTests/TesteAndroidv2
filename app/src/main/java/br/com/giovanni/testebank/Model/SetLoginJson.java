package br.com.giovanni.testebank.Model;

import org.json.JSONException;
import org.json.JSONStringer;

public class SetLoginJson {

    public String user;
    public String password;
    public String setJsonLogin = setLoginJson();

    public void getUser (String getUserConvert){
        user = getUserConvert;
    }

    public void getPassword (String getPasswordConvert){
        password = getPasswordConvert;
    }


    public String setLoginJson(){

        JSONStringer js = new JSONStringer();

        try{
            js.object().
                    key("user").value(user).
                    key("password").value(password).
                    endObject();
            return js.toString();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }


}
