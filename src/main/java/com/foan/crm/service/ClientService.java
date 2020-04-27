package com.foan.crm.service;

import com.foan.crm.constants.CRMServiceErrorCode;
import com.foan.crm.entity.Client;
import com.foan.crm.exception.CRMException;
import com.foan.crm.handler.CRMExceptionFactory;
import com.foan.crm.repository.ClientRepository;
import com.foan.crm.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepospory;

    @Autowired
    private RedisRepository redisRepository;

    public List<Client> getClientByCompanyId(Long company_id){
        return null;
    }

    public Client getClient(String name){
      return clientRepospory.findByName(name).
                orElseThrow(() -> CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCLINETDATA));
    }

    public Client addClient(long companyid, String name, String email, String phone, String token){
        Client client = new Client();
        client.setCompany_id(companyid);
        client.setName(name);
        client.setEmail(email);
        client.setPhone(phone);
        try {
            redisRepository.isKeyExist(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String createdby = "";
        try {
            createdby = redisRepository.findKey(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.setCreatedby(createdby);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        client.setCreatedat(ts);
        return clientRepospory.save(client);
    }

    public Client updateClient(long companyid, String name, String email, String phone, String token){
        if(clientRepospory.findByName(name).isPresent()){
            throw CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCLINETDATA);
        }
        Client client = clientRepospory.findByName(name).get();
        client.setCompany_id(companyid);
        client.setEmail(email);
        client.setPhone(phone);
        String updatedby = "";
        try {
            updatedby = redisRepository.findKey(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.setUpdatedby(updatedby);
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        client.setUpdatedat(ts);
        return clientRepospory.save(client);
    }

    public Boolean deleteClient(String name, String token){
        if(clientRepospory.findByName(name).isPresent()){
            throw CRMExceptionFactory.create(CRMException.class, CRMServiceErrorCode.CAN_NOT_FINDCLINETDATA);
        }
        String updatedby = "";
        try {
            updatedby = redisRepository.findKey(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Client client = clientRepospory.findByName(name).get();
        clientRepospory.delete(client);
        return true;
    }
}

