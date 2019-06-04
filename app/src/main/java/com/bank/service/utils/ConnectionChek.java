 /*
     @fazer
     implementar @ import android.net.NetworkCapabilities;
     cehcar versao do android suporta este
  */

package com.bank.service.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

 public class ConnectionChek {


     public static boolean isNetworkAvailable(Context context) {

         try {
             ConnectivityManager connectivityManager = (ConnectivityManager)
                     context.getSystemService(Context.CONNECTIVITY_SERVICE);

             if (connectivityManager != null) {
                 NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                 return (activeNetwork != null && activeNetwork.isConnected());
             } else {
                 return false;
             }

         }catch (Exception e){
             return false;
         }

     }











 }
