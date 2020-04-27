package com.foan.crm.exception;

public class CRMException extends AbstractCustomException{
    public CRMException(String service, String message, int code) {
        super(service, message, code);
    }

    public CRMException(String service, ServiceResponse responseCode) {
        super(service, responseCode);
    }

}
