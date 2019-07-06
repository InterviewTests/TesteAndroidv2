package com.projeto.testedevandroidsantander.util;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.projeto.santander.model.UsuarioDTO;

import java.lang.reflect.Type;


public class Deserialize implements JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement jsonElement = json.getAsJsonObject();
        return ( new Gson().fromJson( jsonElement, UsuarioDTO.class ));
    }
}