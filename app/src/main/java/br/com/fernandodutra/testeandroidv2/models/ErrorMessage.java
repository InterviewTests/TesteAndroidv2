package br.com.fernandodutra.testeandroidv2.models;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 10:02
 * TesteAndroidv2_CleanCode
 */
public class ErrorMessage {

    private Integer messageId;
    private String messageName;

    public ErrorMessage() {
    }

    public ErrorMessage(Integer messageId, String messageName) {
        this.messageId = messageId;
        this.messageName = messageName;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }
}
