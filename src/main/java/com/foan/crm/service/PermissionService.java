package com.foan.crm.service;

import com.foan.crm.constants.CRMServiceErrorCode;
import com.foan.crm.constants.RoleType;
import com.foan.crm.exception.CRMException;
import com.foan.crm.exception.CRMExceptionFactory;
import com.foan.crm.repository.PermissionRepository;
import com.foan.crm.util.DESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public Boolean checkPermission(String function, String token){
        boolean check = false;
        String decodedString = null;
        try {
            decodedString = DESUtil.decrypt(token);
        } catch (Exception e) {
            throw CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.UNKNOWN.getMessage(), CRMServiceErrorCode.UNKNOWN.getErrorCode());
        }
        RoleType role = RoleType.valueOf(decodedString);
        if(permissionRepository.findByFunctionnameAndRole(function, role).isPresent()) {
            check = true;
        }
        return check;
    }
}
