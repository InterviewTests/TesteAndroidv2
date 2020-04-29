package com.br.web.glix.interviewgiovanipaleologo.homeScreen;

import com.br.web.glix.interviewgiovanipaleologo.models.Statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

interface StatementWorkerInput {
    List<Statement> getStatementList();
}

public class StatementWorker implements StatementWorkerInput {

    public ArrayList<Statement> getStatementList(){
        ArrayList<Statement> statementList = new ArrayList<>();

        Statement item1 = new Statement();
        item1.setTitle("Pagamento");
        item1.setDesc("Conta de luz");
        item1.setDate("2018-08-15");
        item1.setValue(-50);
        statementList.add(item1);

        Statement item2 = new Statement();
        item2.setTitle("TED Recebida");
        item2.setDesc("Joao Alfredo");
        item2.setDate("2018-07-25");
        item2.setValue(745.03);
        statementList.add(item2);

        Statement item3 = new Statement();
        item3.setTitle("DOC Recebida");
        item3.setDesc("Victor Silva");
        item3.setDate("2018-06-23");
        item3.setValue(399.9);
        statementList.add(item3);

        Statement item4 = new Statement();
        item4.setTitle("Pagamento");
        item4.setDesc("Conta de internet");
        item4.setDate("2018-05-12");
        item4.setValue(-73.4);
        statementList.add(item4);

        Statement item5 = new Statement();
        item5.setTitle("Pagamento");
        item5.setDesc("Faculdade");
        item5.setDate("2018-09-10");
        item5.setValue(-500);
        statementList.add(item5);

        Statement item6 = new Statement();
        item6.setTitle("Pagamento");
        item6.setDesc("Conta de telefone");
        item6.setDate("2018-10-17");
        item6.setValue(-760);
        statementList.add(item6);

        Statement item7 = new Statement();
        item7.setTitle("TED Enviada");
        item7.setDesc("Roberto da Luz");
        item7.setDate("2018-07-27");
        item7.setValue(-35.67);
        statementList.add(item7);

        Statement item8 = new Statement();
        item8.setTitle("Pagamento");
        item8.setDesc("Boleto");
        item8.setDate("2018-08-01");
        item8.setValue(-200);
        statementList.add(item8);

        Statement item9 = new Statement();
        item9.setTitle("TED Recebida");
        item9.setDesc("Salário");
        item9.setDate("2018-08-21");
        item9.setValue(1400.5);
        statementList.add(item9);

        Collections.sort(statementList, new Comparator<Statement>() {
            @Override
            public int compare(Statement st1, Statement st2) {
                return st2.getDate().compareTo(st1.getDate());
            }
        });

        return statementList;
    }
}
