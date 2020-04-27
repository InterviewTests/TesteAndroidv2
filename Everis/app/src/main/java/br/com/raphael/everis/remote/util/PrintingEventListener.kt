package br.com.raphael.everis.remote.util


import android.util.Log
import okhttp3.Call
import okhttp3.Connection
import okhttp3.EventListener
import okhttp3.Protocol
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.concurrent.atomic.AtomicLong

class PrintingEventListener(private val callId: Long, private val callStartNanos: Long) : EventListener() {
    companion object {
        val nextCallId = AtomicLong(1L)
        const val LIMITE_RESPOSTA_API = 8000
    }

    private val elapsed : Double
        get() = (System.nanoTime() - callStartNanos) /  1000000.0

    private fun printEvent(name: String, call: Call, info: String? = null){
        Log.d("HTTP_EVENT_LISTENER", "$callId\t$elapsed ms\t$name\t${call.request().url()}" +
                (info ?: ""))
    }

    override fun callStart(call: Call) {
        printEvent("callStart", call)
    }

    override fun connectionAcquired(call: Call, connection: Connection) {
        printEvent("connectionAcquired", call)
    }

    override fun connectionReleased(call: Call, connection: Connection) {
        printEvent("connectionReleased", call)
        if (elapsed > LIMITE_RESPOSTA_API){
            Log.e("NOT DONE", "Limite de conexão estourado para o request (${elapsed}ms): \n${call.request()}")
        }
    }

    override fun connectFailed(call: Call, inetSocketAddress: InetSocketAddress, proxy: Proxy, protocol: Protocol?, ioe: IOException) {
        printEvent("connectFailed", call, "\t${protocol?.name ?: ""}\n${ioe.message ?: ""}\n" +
                ioe.stackTrace.joinToString(separator = "\n") { it.toString() })
    }

    override fun callFailed(call: Call, ioe: IOException) {
        printEvent("callFailed", call, "\n${ioe.message ?: ""}\n" +
                ioe.stackTrace.joinToString(separator = "\n") { it.toString() })
    }
}