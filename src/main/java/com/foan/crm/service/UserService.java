package com.foan.crm.service;

import com.foan.crm.constants.CRMServiceErrorCode;
import com.foan.crm.constants.RoleType;
import com.foan.crm.entity.User;
import com.foan.crm.entity.dto.response.LoginResponse;
import com.foan.crm.exception.CRMException;
import com.foan.crm.exception.CRMExceptionFactory;
import com.foan.crm.repository.RedisRepository;
import com.foan.crm.repository.UserRepository;
import com.foan.crm.util.DESUtil;
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


    public LoginResponse userLogin(String name, String password){
        User loginuser =userRepository.findByName(name);
        if (loginuser == null){
            throw  CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDUSERDATA.getMessage(), CRMServiceErrorCode.CAN_NOT_FINDUSERDATA.getErrorCode());
        }
        if (!loginuser.getPassword().equals(password)){
            throw  CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.PASSWORD_IS_WRONG.getMessage(), CRMServiceErrorCode.PASSWORD_IS_WRONG.getErrorCode());
        }
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setName(name);
            String token = null;
            try {
                token = DESUtil.encrypt(String.valueOf(loginuser.getRole()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            redisRepository.saveByKey(token, name, 180);
            loginResponse.setToken(token);
            return loginResponse;
    }

    public Boolean createUser(String name, String password, RoleType role, String createdby){
        User createUser =userRepository.findByName(name);
        if(createUser != null){
            throw  CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.USERDATA_IS_EXIST.getMessage(), CRMServiceErrorCode.USERDATA_IS_EXIST.getErrorCode());
        }
        createUser = new User();
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
