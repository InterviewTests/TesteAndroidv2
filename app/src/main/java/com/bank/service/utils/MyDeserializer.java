package com.bank.service.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class MyDeserializer implements JsonDeserializer<Object>
{

    private JSONObject jsonObject;
    private JsonArray jsonArray;

    public MyDeserializer(){

        jsonObject = new JSONObject();
        jsonArray = new JsonArray();
    }

    @Override
    public Object deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException
    {

        try
        {
            if (json.isJsonArray()) {

                jsonArray = json.getAsJsonArray();

                     Log.e("STATEMENTS", "getAsJsonArray");
                return context.deserialize(json, Object.class);

            }else if (json.isJsonObject() ){
                Log.e("STATEMENTS", "isJsonObject");
            }

        }
        catch (JsonParseException e)
        {
            Log.e("STATEMENTS", "MyDeserializer/ERRO=" + e);
        }

       // Log.e("STATEMENTS", "MyDeserializer/element="+(json!=null));
        return (new Gson().fromJson(jsonArray, Object.class));

    }










}