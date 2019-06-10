package br.com.douglas.fukuhara.bank.network.vo;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class StatementListVo {

    @SerializedName("statementList")
    List<StatementItem> statementList;

    @SerializedName("error")
    UserError error;

    public List<StatementItem> getStatementList() {
        return statementList;
    }

    public UserError getUserError() {
        return  error;
    }

    public class StatementItem {
        @SerializedName("title")
        String title;
        @SerializedName("desc")
        String desc;
        @SerializedName("date")
        String date;
        @SerializedName("value")
        BigDecimal value;

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }

        public String getDate() {
            return date;
        }

        public BigDecimal getValue() {
            return value;
        }
    }
}
