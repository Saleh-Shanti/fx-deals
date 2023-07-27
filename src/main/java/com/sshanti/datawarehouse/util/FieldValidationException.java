package com.sshanti.datawarehouse.util;

import java.util.List;

public class FieldValidationException extends Exception {

    private List<String> errorMessages;

    public FieldValidationException(List<String> fieldsErrorMessages) {
        this.errorMessages = fieldsErrorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
