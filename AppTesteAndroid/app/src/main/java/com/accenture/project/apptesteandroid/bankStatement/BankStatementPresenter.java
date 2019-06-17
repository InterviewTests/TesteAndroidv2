package com.accenture.project.apptesteandroid.bankStatement;

import com.accenture.project.apptesteandroid.model.BankStatement;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Classe responsável por apresentar e formatar os dados para a BankStatementActivity
 */

interface IBankStatementPresenter {

    void presentMessage(String message);

    void presentBankStatement(List<BankStatement> bankStatementList);


}

public class BankStatementPresenter implements IBankStatementPresenter {

    public WeakReference<IBankStatementActivity> iStatementActivity;


    @Override
    public void presentBankStatement(List<BankStatement> bankStatementList) {

        for (int x = 0; x < bankStatementList.size(); x++) {

            String formattedDate = formatDate(bankStatementList.get(x).getDate());
            String formattedValue = formatValue(bankStatementList.get(x).getValue());

            if (formattedDate != null) {
                bankStatementList.get(x).setDate(formattedDate);
            }

            bankStatementList.get(x).setValue(formattedValue);

        }

        iStatementActivity.get().displayBankStatement(bankStatementList);

    }

    @Override
    public void presentMessage(String message) {

        iStatementActivity.get().displayMessage(message);

    }

    private String formatDate(String originalDate) {

        String formattedDate = null;

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(originalDate);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            formattedDate = dateFormatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    private String formatValue(String originalValue) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(Double.parseDouble(originalValue));

    }

}
