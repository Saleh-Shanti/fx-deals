package com.sshanti.datawarehouse.boundary;


import java.io.Serializable;
import java.util.List;

public class FXDealResponse implements Serializable {

    public static final String SUCCESS_MESSAGE = "FX Deal request has been saved to the DB with id [%s].";

    private String status;
    private List<String> messages;

    public FXDealResponse() {
    }

    public FXDealResponse(List<String> messages, String status) {
        this.messages = messages;
        this.status = status;
    }

    public FXDealResponse(List<String> messages) {
        this.messages = messages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
