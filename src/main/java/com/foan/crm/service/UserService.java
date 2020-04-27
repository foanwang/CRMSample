package com.foan.crm.service;

import com.foan.crm.constants.CRMServiceErrorCode;
import com.foan.crm.constants.RoleType;
import com.foan.crm.entity.User;
import com.foan.crm.entity.dto.response.LoginResponse;
import com.foan.crm.exception.CRMException;
import com.foan.crm.handler.CRMExceptionFactory;
import com.foan.crm.repository.RedisRepository;
import com.foan.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisRepository redisRepository;


    public LoginResponse UserLogin(String name, String password){

        if(userRepository.findByName(name).isPresent()){
           CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCLINETDATA);
       }
       User loginuser = userRepository.findByName(name).get();
       if (loginuser.getPassword()!=password){
           CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCLINETDATA);
       }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setName(name);
        UUID token = UUID.randomUUID();
        loginResponse.setToken(token.toString());
        redisRepository.saveByKey(token.toString(), loginuser.getName(), 300);
        return loginResponse;
    }

    public Boolean CreateUser(String name, String password, RoleType role, String createdby){
        if(userRepository.findByName(name).isPresent()){
            CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCLINETDATA);
        }
        User createUser = new User();
        createUser.setName(name);
        createUser.setPassword(password);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        createUser.setCreatedat(ts);
        createUser.setRole(role);
        createUser.setCreated_by(createdby);
        userRepository.save(createUser);
        return true;
    }
}
