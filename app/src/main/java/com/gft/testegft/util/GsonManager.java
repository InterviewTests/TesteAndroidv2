package com.gft.testegft.util;

import com.google.gson.Gson;

public class GsonManager {

    private static Gson gson;

    static private Gson getInstance() {
        if (gson == null)
            gson = new Gson().newBuilder().setPrettyPrinting().create();

        return gson;
    }

    public static String toJson(Object object) {
        return getInstance().toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return getInstance().fromJson(json, classOfT);

    }

}
