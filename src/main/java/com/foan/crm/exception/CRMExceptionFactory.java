package com.foan.crm.exception;

import com.foan.crm.entity.error.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Component
public class CRMExceptionFactory {
    private static final Logger log = LoggerFactory.getLogger(CRMExceptionFactory.class);
    @Value("${info.service-name}")
    private String serviceName0;
    private static String serviceName;

    public CRMExceptionFactory() {
    }

    @PostConstruct
    private void initServiceName() {
        serviceName = this.serviceName0;
    }

    public static AbstractCustomException create(Class<? extends AbstractCustomException> classType, String message, int errorCode) {
        try {
            Constructor<?> ctor = classType.getConstructor(String.class, String.class, Integer.TYPE);
            return (AbstractCustomException)ctor.newInstance(serviceName, message, errorCode);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException var4) {
            return create(var4);
        }
    }

    public static AbstractCustomException create(Class<? extends AbstractCustomException> classType, ServiceResponse response) {
        try {
            Constructor<?> ctor = classType.getConstructor(String.class, String.class, Integer.TYPE);
            return (AbstractCustomException)ctor.newInstance(serviceName, response.getMessage(), response.getErrorCode());
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException var3) {
            return create(var3);
        }
    }

    private static CRMException create(Exception ex) {
        log.info(ex.getMessage(), ex);
        return new CRMException(serviceName, ex.getMessage(), 9999);
    }
}
