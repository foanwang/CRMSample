package com.foan.crm.handler;

import com.foan.crm.constants.CRMServiceErrorCode;
import com.foan.crm.exception.AbstractCustomException;
import com.foan.crm.exception.CRMException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler extends AbstractGlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<String> handle(RuntimeException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public ResponseEntity<String> handle(HttpClientErrorException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseBody
    public ResponseEntity<String> handle(HttpServerErrorException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle the artifact CRUD related exceptions.
     *
     * @param ex autowired exceptions.
     * @return the Http status and the json string represents the exception.
     */
    @ExceptionHandler({CRMException.class})
    @ResponseBody
    public ResponseEntity<String> handle(AbstractCustomException ex) {
        logger.error(ex.getMessage(), ex);
        CRMServiceErrorCode error = CRMServiceErrorCode.getFrom(ex.getError().getCode());
        HttpStatus status = error.getHttpStatus();
        if (error == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(ex.toJson(), status);
    }
}
