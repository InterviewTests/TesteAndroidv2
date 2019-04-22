package br.com.bankapp.util

import android.content.Context
import br.com.bankapp.model.Retorno
import org.json.JSONObject
import org.json.JSONTokener
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class BankHelper {

    companion object{
        fun request(url: String, method: String, user: String?, password: String?, json: String?): Retorno{
            var retorno = Retorno()
            var inputStream: InputStream? = null
            val url = URL(url)
            val connection = url.openConnection() as HttpURLConnection

            try {

                connection.readTimeout = Constantes.READ_TIMEOUT
                connection.connectTimeout = Constantes.CONNECT_TIMEOUT
                connection.requestMethod = method
                connection.setRequestProperty(Constantes.WS_ACCEPT, Constantes.WS_APPLICATION_JSON)
                connection.setRequestProperty(Constantes.WS_CONTENT_TYPE, Constantes.WS_APPLICATION_X)
                connection.doInput = true

                if (method.equals(Constantes.REQUEST_METHOD_POST, true)){
                    connection.setRequestProperty(Constantes.REQUEST_PROPERTY_CONTENT_LENGTH, json?.length?.toByte().toString())

                    val writer = OutputStreamWriter(connection.outputStream)
                    writer.write(json)
                    writer.flush()
                    writer.close()
                }

                connection.connect()

                if (connection.responseCode == HttpURLConnection.HTTP_CREATED
                    || connection.responseCode == HttpURLConnection.HTTP_ACCEPTED
                    || connection.responseCode == HttpURLConnection.HTTP_OK){

                    inputStream = connection.inputStream
                    val message = JSONTokener(convertStreamToStringRequest(inputStream)).nextValue() as JSONObject
                    retorno.statusCode = connection.responseCode
                    retorno.strRetorno = message.toString()
//                    retorno.mensagem = message[""].toString()
                }else if (connection.responseCode == HttpURLConnection.HTTP_BAD_REQUEST){
                    inputStream = connection.inputStream
                    val message = JSONTokener(convertStreamToStringRequest(inputStream)).nextValue() as JSONObject
                    retorno.statusCode = connection.responseCode
                    retorno.mensagem = message.toString()
                }else{
                    inputStream = connection.inputStream
                    val message = JSONTokener(convertStreamToStringRequest(inputStream)).nextValue() as JSONObject
                    retorno.statusCode = connection.responseCode
                    retorno.mensagem = message.toString()
                }
                return retorno
            }finally{
                inputStream?.close()
                connection?.disconnect()
            }
        }

        fun verificarConexao(context: Context): Boolean{
            return ConnectionDetector.isConnectionDetector(context)
        }

        fun convertStreamToStringRequest(inpustStream: InputStream):String {
            if (inpustStream != null) {
                val writer: Writer = StringWriter()
                val sb = StringBuilder()
                var line: String?

                val br = BufferedReader(InputStreamReader(inpustStream, "UTF-8"))
                line = br.readLine()

                while (line != null) {
                    sb.append(line)
                    line = br.readLine()
                    writer.write(line)
                }
                br.close()
                return sb.toString()
            } else {
                return ""
            }
        }
    }
}