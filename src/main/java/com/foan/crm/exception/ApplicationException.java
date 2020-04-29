package com.foan.crm.exception;

import com.foan.crm.constants.CRMServiceErrorCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class ApplicationException extends RuntimeException {

    private static final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    @Expose
    private ApplicationError error = new ApplicationError();

    public ApplicationException(String message) {
        this.getError().setMessage(message);
    }

    public ApplicationException(String message, int code) {
        this.getError().setMessage(message);
        this.getError().setCode(code);
    }

    public ApplicationException(CRMServiceErrorCode responseCode) {
        this.getError().setMessage(responseCode.getMessage());
        this.getError().setCode(responseCode.getErrorCode());
    }

    public ApplicationError getError() {
        return error;
    }

    public void setError(ApplicationError error) {
        this.error = error;
    }

    public String toJson() {
        return gson.toJson(this);
    }
}
