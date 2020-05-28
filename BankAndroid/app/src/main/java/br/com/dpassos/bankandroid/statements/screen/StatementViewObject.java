package br.com.dpassos.bankandroid.statements.screen;

import android.annotation.SuppressLint;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import br.com.dpassos.bankandroid.infra.ExceptionHandler;
import br.com.dpassos.bankandroid.statements.business.Statement;

class StatementViewObject {

    String title;
    String date;
    String desc;
    String value;

    @SuppressLint("SimpleDateFormat")
    StatementViewObject(Statement statement) {
        this.title = statement.title;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyLocalizedPattern("yyyy-MM-dd");
            Date date = sdf.parse(statement.date);
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.date = sdf.format(date);
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
        this.value = NumberFormat.getCurrencyInstance().format(statement.value);
        this.desc = statement.desc;

    }
}
