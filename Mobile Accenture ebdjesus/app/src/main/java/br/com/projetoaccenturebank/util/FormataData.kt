package br.com.projetoaccenturebank.util

import java.text.SimpleDateFormat
import java.util.*

enum class FormataData private constructor(mFormato: String) {

    Completo("dd/MM/yyyy HH:mm:ss"),
    CompletoInvertido("HH:mm:ss dd/MM/yyyy"),
    DiaMes("dd/MM"),
    Dia("dd"),
    Mes("MM"),
    MesNome("MMM"),
    Ano("yyyy"),
    DiaMesAno("dd/MM/yyyy"),
    HoraMin("HH:mm"),
    HoraMinSeg("HH:mm:ss"),
    SQL("yyyy-MM-dd HH:mm:ss"),
    SQL_2("yyyy-MM-dd kk:mm:ss"),
    SQL_3("yyyy-MM-dd kk:mm:ss.SSS"),
    SQLData("yyyy-MM-dd"),
    DataFoto("MMddyyyy_kkmmss"),
    SQL_7("yyyyMMddHHmmssSSS"),
    SQL_8("yyyyMMddkkmmssSSS"),
    Numerico("yyyyMMdd");

    private var formato: String
        set

    init {
        this.formato = mFormato
    }

    companion object {

        val NULL_DATE = "1900-01-01"

        /**
         * Obtem string da data com o formato selecionado
         */
        operator fun get(data: Calendar, dataFormato: FormataData): String {

            val dataFormatada: String
            val formato = dataFormato.formato

            //Data formatada
            dataFormatada = SimpleDateFormat(formato, Locale.getDefault()).format(data.time)

            return dataFormatada
        }

        /**
         * Obtem calendar da data com o formato selecionado
         */
        @Throws(Exception::class)
        fun getCalendar(data: String, dataFormato: FormataData): Calendar {

            val date: Date
            val formatter: SimpleDateFormat
            val calendar = Calendar.getInstance()
            val formato = dataFormato.formato

            //Data formatada
            formatter = SimpleDateFormat(formato, Locale.getDefault())
            date = formatter.parse(data)
            calendar.time = date

            return calendar
        }

        /**
         * RETORNA DIA MES ANO
         *
         * @return
         */
        fun retornaDataFormat(dataFormato: FormataData): String {
            val diaMesAno = SimpleDateFormat(dataFormato.formato)

            val dataCorrenteDate = Date()

            return diaMesAno.format(dataCorrenteDate)
        }
    }
}