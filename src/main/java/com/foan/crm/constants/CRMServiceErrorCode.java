package com.foan.crm.constants;

import com.foan.crm.exception.ServiceResponse;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public enum CRMServiceErrorCode implements ServiceResponse {
    UNKNOWN(0, "Unknown",HttpStatus.INTERNAL_SERVER_ERROR),

    CAN_NOT_FINDCLINETDATA(10001, "Can not find client data", HttpStatus.BAD_REQUEST),
    CAN_NOT_FINDCOMPANYDATA(10002, "Can not find company data", HttpStatus.BAD_REQUEST),
    PERMISSION_DENIED(10003, "PERMISSION_DENIED", HttpStatus.BAD_REQUEST);

    private final static Map<Integer, CRMServiceErrorCode> code2Error = new HashMap<>();

    static {
        for (CRMServiceErrorCode se : CRMServiceErrorCode.values()) {
            code2Error.put(se.errorCode, se);
        }
    }

    /**
     * An integer to identify the error.
     */
    private int errorCode;
    private String message;
    private HttpStatus httpStatus;


    CRMServiceErrorCode(int errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    /**
     * Get the error from the error code.
     *
     * @param errorCode The error code.
     * @return a transmit service error.
     */
    public static CRMServiceErrorCode getFrom(int errorCode) {
        return code2Error.get(errorCode);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
