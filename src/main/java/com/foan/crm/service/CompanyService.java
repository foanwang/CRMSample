package com.foan.crm.service;

import com.foan.crm.constants.CRMServiceErrorCode;
import com.foan.crm.entity.Company;
import com.foan.crm.exception.CRMException;
import com.foan.crm.exception.CRMExceptionFactory;

import com.foan.crm.repository.CompanyRepository;
import com.foan.crm.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepospory;

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private PermissionService permissionService;

    public Company getCompany(String name){
        return companyRepospory.findByName(name).
                orElseThrow(() -> CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCLINETDATA));
    }

    public Company addCompany(String name, String address, String token){
        if(!permissionService.checkPermission(new Exception().getStackTrace()[0].getMethodName(), token)){
            throw  CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.PERMISSION_DENIED.getMessage(), CRMServiceErrorCode.PERMISSION_DENIED.getErrorCode());
        }
        Company company = new Company();
        company.setName(name);
        company.setAddress(address);
        String createdby = "";
        try {
            createdby = redisRepository.findKey(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        company.setCreated_by(createdby);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        company.setCreatedat(ts);
        System.out.println("name:"+ name +" address:"+address+" createdby:"+createdby);
        return companyRepospory.save(company);
    }

    public Company updateCompany(String name, String address, String token){
        if(!permissionService.checkPermission(new Exception().getStackTrace()[0].getMethodName(), token)){
            throw  CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.PERMISSION_DENIED.getMessage(), CRMServiceErrorCode.PERMISSION_DENIED.getErrorCode());
        }
        if(!companyRepospory.findByName(name).isPresent()){
            throw  CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCOMPANYDATA.getMessage(), CRMServiceErrorCode.CAN_NOT_FINDCOMPANYDATA.getErrorCode());
        }
        Company company = companyRepospory.findByName(name).get();
        company.setAddress(address);
        String updatedby = "";
        try {
            updatedby = redisRepository.findKey(token);
        } catch (Exception e) {
            throw  CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.USER_DOES_NOT_LOGIN.getMessage(), CRMServiceErrorCode.USER_DOES_NOT_LOGIN.getErrorCode());
        }
        company.setUpdateby(updatedby);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        company.setUpdatedat(ts);
        return companyRepospory.save(company);
    }

    public Boolean deleteCompany(String name, String token){
        if(!permissionService.checkPermission(new Exception().getStackTrace()[0].getMethodName(), token)){
            throw  CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.PERMISSION_DENIED.getMessage(), CRMServiceErrorCode.PERMISSION_DENIED.getErrorCode());
        }
        if(!companyRepospory.findByName(name).isPresent()){
            throw  CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCOMPANYDATA.getMessage(), CRMServiceErrorCode.CAN_NOT_FINDCOMPANYDATA.getErrorCode());
        }
        Company company = companyRepospory.findByName(name).get();
        companyRepospory.delete(company);
        return true;
    }
}
