package br.com.projetoaccenturebank.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Point
import android.os.Handler
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.gson.Gson
import org.json.JSONObject
import java.io.*
import java.text.NumberFormat
import java.util.HashMap
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream


internal object SupportBase {

    var Context: android.content.Context? = null
    var DefaultColor = 0

    /**
     * Obtem retorno das informações referente a package (APP)
     * @param context
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getInfoApp(context: android.content.Context): PackageInfo {
        val packageManager = context.packageManager
        return packageManager.getPackageInfo(context.packageName, 0)
    }

    /**
     * Escreve uma texto em uma stream
     * @param os
     * @param parametro
     * @throws Exception
     */
    @Throws(Exception::class)
    fun writeOutputStream(os: OutputStream, parametro: String) {
        val writer: BufferedWriter

        writer = BufferedWriter(OutputStreamWriter(os))

        writer.write(parametro)
        writer.flush()

        writer.close()
        os.close()
    }

    /**
     * Le uma stream.
     * Para melhor utilização deste metodo recomendo utilizar no AndroidManifest.xml
     * na parte do <application> a propriedade android:largeHeap="true",
     * isso faz com que o applicativo utilize mais memoria e faz com que não
     * aconteça o problema de 'out of memory error' execption
     * @param is
     * @return
     * @throws Exception
    </application> */
    @Synchronized
    @Throws(Exception::class)
    fun readInputStream(`is`: InputStream): String {
        val sb = StringBuilder()
        val lenByte = 1024
        val bytes = ByteArray(lenByte)
        var numRead: Int

        try {
            numRead = `is`.read(bytes)
            while (numRead >= 0) {
                sb.append(String(bytes, 0, numRead))
            }
        } finally {
            `is`.close()
        }

        return sb.toString()
    }

    /**
     * Obtem diretorio do app
     * @param context
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getAppDir(context: android.content.Context): File {
        val packageManager: PackageManager
        val packageName: String
        val packageInfo: PackageInfo
        val file: File

        packageManager = context.packageManager
        packageName = context.packageName

        packageInfo = packageManager.getPackageInfo(packageName, 0)
        file = File(packageInfo.applicationInfo.dataDir)

        return file
    }

    /**
     * ZIP
     * @param files
     * @param zipFile
     * @throws IOException
     */
    @Throws(IOException::class)
    fun zip(files: Array<File>, zipFile: String) {
        val BUFFER_SIZE = 1024
        var origin: BufferedInputStream? = null
        val out = ZipOutputStream(BufferedOutputStream(FileOutputStream(zipFile)))
        try {
            val data = ByteArray(BUFFER_SIZE)

            for (i in files.indices) {
                val fi = FileInputStream(files[i])
                origin = BufferedInputStream(fi, BUFFER_SIZE)
                try {
                    val entry = ZipEntry(files[i].name)
                    out.putNextEntry(entry)
                    var count: Int
                    count = origin.read(data, 0, BUFFER_SIZE)
                    while (count != -1) {
                        out.write(data, 0, count)
                    }
                } finally {
                    origin.close()
                }
            }
        } finally {
            out.close()
        }
    }

    /**
     * UNZIP
     * @param zipFile
     * @par?am location
     * @throws IOException
     */
    @Throws(IOException::class)
    fun unzip(zipFile: String, location: String) {
        try {
            val f = File(location)
            if (!f.isDirectory) {
                f.mkdirs()
            }
            val zin = ZipInputStream(FileInputStream(zipFile))
            try {
                var ze: ZipEntry?
                ze = zin.nextEntry
                while (ze != null) {
                    val path = location + File.separator + ze!!.name

                    if (ze.isDirectory) {
                        val unzipFile = File(path)
                        if (!unzipFile.isDirectory) {
                            unzipFile.mkdirs()
                        }
                    } else {
                        val fout = FileOutputStream(path, false)
                        try {
                            val buffer = ByteArray(1024)
                            var read = zin.read(buffer)
                            while (read != -1) {
                                fout.write(buffer, 0, read)
                            }

                            zin.closeEntry()
                        } finally {
                            fout.close()
                        }
                    }
                }
            } finally {
                zin.close()
            }
        } catch (e: Exception) {
            Log.e("ZIP", "Unzip exception", e)
        }

    }

    /**
     * Fecha/Esconde teclado
     */
    fun hideKeyBoard(context: android.content.Context, editText: EditText) {
        hideKeyBoard(context, editText, 200)
    }

    /**
     * Fecha/Esconde teclado
     */
    fun hideKeyBoard(context: Context, editText: EditText, time: Int) {
        val imm = context.getSystemService(Context!!.packageName) as InputMethodManager
        editText.clearFocus()
        Handler().postDelayed({ imm.hideSoftInputFromWindow(editText.windowToken, 0) }, time.toLong())
    }

    /**
     * Formata moeda
     */
    fun formataMoeda(valor: Double): String {
        return NumberFormat.getCurrencyInstance().format(valor)
    }

    /**
     * Tenta converter valor string em int, caso der erro retorna valor default
     * @param value
     * @param valueDefault
     * @return
     */
    fun tryParseInt(value: String, valueDefault: Int): Int {
        try {
            return Integer.parseInt(value.trim { it <= ' ' })
        } catch (err: Exception) {
            return valueDefault
        }

    }

    /**
     * Obtem width/height total da Activity
     * @param activity
     * @return
     */
    fun getXYActivity(activity: Activity): HashMap<String, Int> {
        val hashMap = HashMap<String, Int>()
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val height = size.y

        hashMap["width"] = width
        hashMap["height"] = height

        return hashMap
    }

    fun <T> clone(objeto: T): T? {
        val gson = Gson()
        var target: T? = null
        val jsonObject: JSONObject

        try {
            jsonObject = JSONObject(gson.toJson(objeto))
            target = gson.fromJson<Any>(jsonObject.toString(), javaClass) as T
        } catch (err: Exception) {
            err.printStackTrace()
        }

        return target
    }
}
