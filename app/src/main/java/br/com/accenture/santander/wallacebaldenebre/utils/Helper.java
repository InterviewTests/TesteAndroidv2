package br.com.accenture.santander.wallacebaldenebre.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Helper {
    public static void toast(Activity a, String msg) {
        Toast.makeText(a, msg, Toast.LENGTH_LONG).show();
    }

    public static void openClass(Activity a, Class clazz) {
        a.startActivity(new Intent(a, clazz));
    }

    public static boolean isPhone(String phone) {
        return phone.trim().matches("^\\([1-9]{2}\\)(?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$");
    }

    public static LinearLayout.LayoutParams margins(int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(left, top, right, bottom);
        return lp;
    }

    public static RelativeLayout.LayoutParams marginsRL(int left, int top, int right, int bottom) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(left, top, right, bottom);
        lp.alignWithParent = true;
        return lp;
    }

    public static int doubleToInt(double number) {
        return (int) number;
    }

    public static void hideSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //  ==================== req WebService
    private static String streamToString(InputStream input) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) sb.append(line).append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        return sb.toString();
    }

    public static String reqGET(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //  lê o response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = streamToString(in);
        } catch (MalformedURLException e) {
            Log.e("LOG", "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e("LOG", "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e("LOG", "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("LOG", "Exception: " + e.getMessage());
        }
        return response;
    }

    public static String reqPOST(String reqUrl, HashMap<String, String>... params) {
        String response = "";
        HttpURLConnection conn = null;

        try {
            URL url = new URL(reqUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(30 * 1000);
            conn.connect();

            StringBuilder parsedParams = new StringBuilder();
            boolean isFirst = true;

            for (int i = 0; i < params.length; i++) {
                if (!isFirst) {
                    parsedParams.append("&");
                    isFirst = false;
                }

                parsedParams.append(URLEncoder.encode(i + "=" + params[i], "UTF-8"));
                Log.d("LOG", "Helper\nResultado: " + i + "| Params: " + params[i]);
            }


            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            bw.write(parsedParams.toString());
            bw.flush();
            conn.getOutputStream().close();
            bw.close();

            response = streamToString(new BufferedInputStream(conn.getInputStream()));
        } catch (MalformedURLException e) {
            Log.e("LOG", "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e("LOG", "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e("LOG", "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("LOG", "Exception: " + e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return response;
    }

}
