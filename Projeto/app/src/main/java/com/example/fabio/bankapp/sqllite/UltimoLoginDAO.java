package com.example.fabio.bankapp.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class UltimoLoginDAO {

    private DbGateway gw;
    private Boolean temregistro;

    public UltimoLoginDAO(Context ctx){ gw = DbGateway.getInstance(ctx); }

    public long Salvar(String user){
        ContentValues cv = new ContentValues();
        cv.put("user", user);
        if (!VerificaRegistro()) {
            return gw.getDatabase().insert("Ultimologin", null, cv);
        }else{
            return gw.getDatabase().update("Ultimologin", cv, "", null);
        }
    }

    public String CarregaUltimoLogin(){
        Cursor cur;
        String ultimologin = "";

        cur = gw.getDatabase().rawQuery("SELECT * FROM Ultimologin", null);
        while(cur.moveToNext()){
            ultimologin = cur.getString(cur.getColumnIndex("user"));
        }
        cur.close();
        return ultimologin;
    }

    public boolean VerificaRegistro(){
        temregistro = false;
        Cursor cur = gw.getDatabase().rawQuery("SELECT EXISTS (SELECT user FROM Ultimologin)", null);
        if (cur != null) {
            cur.moveToFirst();
            if (cur.getInt (0) > 0) { temregistro = true; }
        }
        cur.close();
        return temregistro;
    }
}
