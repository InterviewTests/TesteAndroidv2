package br.com.rms.bankapp.utils.extensions
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun String?.formatYmdDateToDmyDateFormat(): String {
    return if (!this.isNullOrEmpty()) {
          try {
            val locale = Locale.getDefault()
            val parser = SimpleDateFormat("yyyy-MM-dd", locale)
            val formatter = SimpleDateFormat("dd/MM/yyyy", locale)
            formatter.format(parser.parse(this))
        } catch (e: ParseException){
            ""
        }
    }else{
        ""
    }
}

