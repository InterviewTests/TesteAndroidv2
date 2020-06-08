package br.com.testeandroidv2.utils.http

import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.SocketTimeoutException
import java.net.URL

class Http(private val requestParams: MutableList<HttpParams>?,
           private val headerParams: MutableList<HttpParams>?) {

    companion object {
        private const val DEFAULT_CHARSET = "UTF-8"
        private const val SIXTY_SECONDS = 60000
    }

    fun sendGet(sendUrl: String?): HttpResponse? {
        return send(sendUrl, null, HttpStatus.GET)
    }

    fun sendPost(sendUrl: String?, jsonBody: String?): HttpResponse? {
        return send(sendUrl, jsonBody, HttpStatus.POST)
    }

    private fun send(sendUrl: String?, jsonBody: String?, method: Int?): HttpResponse? {
        var result: HttpResponse? = null
        var urlToRead: String? = sendUrl

        val rd: BufferedReader
        val start: Long = System.currentTimeMillis()
        var connResponseMessage = ""
        try {
            // inicia
            var line: String?
            var urlParameters = ""
            var sepa = if (method == HttpStatus.GET) { "?" } else { "" }
            var requestMethod: String = when (method) {
                HttpStatus.GET    -> "GET"
                HttpStatus.POST   -> "POST"
                HttpStatus.PUT    -> "PUT"
                HttpStatus.DELETE -> "DELETE"
                else -> ""
            }

            if (requestMethod.isEmpty()) { return errorResponse() }

            // verifica os params
            requestParams?.forEach {
                val linha = "${it.nome}=${it.valor}"
                urlParameters += if (urlParameters.isNotEmpty()) { "&$linha" } else { "$sepa$linha" }
            }

            // incrementa a URL
            if (urlParameters.isNotEmpty() && method == HttpStatus.GET) { urlToRead += urlParameters }

            // realiza a conexao
            val conn = URL(urlToRead).openConnection() as HttpURLConnection
                conn.requestMethod = requestMethod
                conn.doInput = true
                conn.useCaches = false
                conn.readTimeout = SIXTY_SECONDS
                conn.connectTimeout = SIXTY_SECONDS

            if (method == HttpStatus.POST) { conn.doOutput = true }

            // verifica os headers
            headerParams?.forEach { conn.setRequestProperty(it.nome, it.valor) }

            if (method == HttpStatus.POST) {
                // send data
                val wr = DataOutputStream(conn.outputStream)

                // parametrs
                if (urlParameters.isNotEmpty()) {
                    wr.write(urlParameters.toByteArray(charset(DEFAULT_CHARSET)))
                }

                // JSON Object
                if (jsonBody != null) {
                    wr.write(jsonBody.toByteArray(charset(DEFAULT_CHARSET)))
                }

                // flush send data
                wr.flush()
                wr.close()
            }

            // initialize InputStream
            val input: InputStream

            // status response
            val codeStatus = conn.responseCode
            connResponseMessage = conn.responseMessage

            // verify status
            input = if (codeStatus == HttpURLConnection.HTTP_OK ||
                        codeStatus == HttpURLConnection.HTTP_CREATED) {
                conn.inputStream
            }
            else { conn.errorStream }

            // seta o BufferReader
            rd = BufferedReader(InputStreamReader(input, DEFAULT_CHARSET))

            // message
            var message: String? = ""

            // percorre o arquivo e retorna o result
            while (rd.readLine().also { line = it } != null) {
                message += line
            }

            // set Result
            result = HttpResponse()
            result.codeHttp = codeStatus
            result.codeError = 0
            result.message = message
            result.status = "OK"

            // fecha o BufferedReader
            rd.close()
            conn.disconnect()
        }
        catch (me: MalformedURLException) {
            connResponseMessage = "MalformedURLException: " + me.message
        }
        catch (ue: UnsupportedEncodingException) {
            connResponseMessage = "UnsupportedEncodingException: " + ue.message
        }
        catch (ie: IOException) {
            if (ie is SocketTimeoutException) {
                result = HttpResponse()
                result.codeHttp = HttpURLConnection.HTTP_CLIENT_TIMEOUT
                result.codeError = 99
                result.message = ""
                result.status = "ERROR"
            }
            else {
                connResponseMessage = "IOException: " + ie.message
            }
        }

        if (result == null) {
            result = HttpResponse()
            result.codeHttp = HttpURLConnection.HTTP_BAD_REQUEST
            result.codeError = 99
            result.message = ""
            result.status = "ERROR"
        }

        // calcula o tempo em milesegundos
        val finish: Long = System.currentTimeMillis()
        val total: Long = finish - start

        // set Response
        result.timeFinish = total
        result.messageResponse = connResponseMessage

        // retorna
        return result
    }

    private fun errorResponse(): HttpResponse {
        val response = HttpResponse()
            response.codeHttp = HttpURLConnection.HTTP_BAD_REQUEST
            response.codeError = 99
            response.message = "Method invalid"
            response.status = "Error"
        return response
    }
}