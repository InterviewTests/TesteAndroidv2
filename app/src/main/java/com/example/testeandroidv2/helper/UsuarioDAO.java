package com.example.testeandroidv2.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.testeandroidv2.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UsuarioDAO implements IUsuarioDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public UsuarioDAO(Context context) {

        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();

    }

    @Override
    public boolean create(Usuario usuario) {

        ContentValues cv = new ContentValues();
        cv.put("userId", usuario.getId());
        cv.put("name", usuario.getNome());
        cv.put("login", usuario.getLogin());
        cv.put("lastPassword", usuario.getLastPassword());
        cv.put("agency", usuario.getAgency());
        cv.put("bankAccount", usuario.getBankAccount());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(Calendar.getInstance().getTime());//
        cv.put("loginData", now);

        try {

            escreve.insert(DbHelper.TABLE_USUARIO, null, cv);
            Log.i("INFO DB", "usuario salvo");
        }
        catch (Exception e) {
            Log.i("INFO DB", "Erro ao salvar no db: " + e.getMessage());
            return false;
        }

        return true;    }

    @Override
    public boolean update(Usuario usuario) {

        ContentValues cv = new ContentValues();
        cv.put("name", usuario.getNome());
        cv.put("login", usuario.getLogin());
        cv.put("lastPassword", usuario.getLastPassword());
        cv.put("agency", usuario.getAgency());
        cv.put("bankAccount", usuario.getBankAccount());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(Calendar.getInstance().getTime());////
        cv.put("loginData", now);

        try {

            String[] args = {String.valueOf(usuario.getId())};
            escreve.update(DbHelper.TABLE_USUARIO, cv, "userId=?", args);

            Log.i("INFO DB", "usuario atualizado");
        }
        catch (Exception e) {
            Log.i("INFO DB", "Erro ao atualizar no db: " + e.getMessage());
            return false;
        }

        return true;

    }

    @Override
    public boolean delete(Usuario usuario) {

        try {

            String[] args = {usuario.getId().toString()};
            escreve.delete(DbHelper.TABLE_USUARIO, "id=?", args);

            Log.i("INFO DB", "usuario excluido");
        }
        catch (Exception e) {
            Log.i("INFO DB", "Erro ao excluir no db: " + e.getMessage());
            return false;
        }

        return true;

    }

    @Override
    public Object buscar(Integer userId) throws JSONException {

        String sql = "SELECT * FROM " + DbHelper.TABLE_USUARIO +
                " WHERE userId='"+ userId + "' LIMIT 1";
        Cursor c = le.rawQuery(sql, null);

        Usuario usuario = new Usuario();
        if (c.moveToFirst()) {

            usuario.put("userId", Integer.valueOf(c.getString(0)));
            usuario.put("name", c.getString(1));
            usuario.put("login", c.getString(2));
            usuario.put("lastPassword", c.getString(3));
            usuario.put("agency", c.getString(4));
            usuario.put("bankAccount", c.getString(5));

        }

        return usuario;

    }

    public Object ultimoLogin() throws JSONException {

        String sql = "SELECT * FROM " + DbHelper.TABLE_USUARIO + " ORDER BY loginData DESC LIMIT 1";
        Cursor c = le.rawQuery(sql, null);
        JSONObject usuario = new JSONObject();

        if (c.moveToFirst()) {

            usuario.put("userId", Integer.valueOf(c.getString(0)));
            usuario.put("name", c.getString(1));
            usuario.put("login", c.getString(2));
            usuario.put("lastPassword", c.getString(3));
            usuario.put("agency", c.getString(4));
            usuario.put("bankAccount", c.getString(5));

        }

        return usuario;

    }

}
