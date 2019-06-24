package com.resource.bankapplication.data.remote.dto;

import com.resource.bankapplication.domain.Spent;
import com.resource.bankapplication.util.DateTypeDeserializer;

import java.util.ArrayList;
import java.util.List;

public class SpentDto {
    private List<StatementsDto> statementList;
    private Error error;

    public List<StatementsDto> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<StatementsDto> statementList) {
        this.statementList = statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public List<Spent> toDomain() {
        List<Spent> list = new ArrayList<>();
        for (StatementsDto statementsDto : statementList) {

            list.add(new Spent(statementsDto.getTitle(), statementsDto.getDesc(),
                    DateTypeDeserializer.formatDate(statementsDto.getDate()), statementsDto.getValue()));
        }
        return list;
    }
}
