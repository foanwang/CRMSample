package com.foan.crm.controller;

import com.foan.crm.entity.Client;
import com.foan.crm.entity.dto.request.CreateClientRequest;
import com.foan.crm.entity.dto.request.DeleteClientRequest;
import com.foan.crm.entity.dto.request.GetClientRequest;
import com.foan.crm.entity.dto.request.UpdateClientRequest;
import com.foan.crm.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${api-version}")
@Api(value = "${api-version}", tags = "ClientController")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @ApiOperation(value ="/Client", notes = "User login API")
    @RequestMapping(value ="/Client", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Client> CreateClient(HttpServletRequest request, CreateClientRequest createClientRequest) {
        return new ResponseEntity<>(clientService.addClient(createClientRequest.getCompanyid(), createClientRequest.getName(), createClientRequest.getEmail(), createClientRequest.getPhone(), createClientRequest.getToken()), HttpStatus.OK);
    }

    @ApiOperation(value ="/Client", notes = "Get Client API")
    @RequestMapping(value ="/Client", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Client> GetClient(HttpServletRequest request, GetClientRequest getClientRequest) {
        return new ResponseEntity<>(clientService.getClient(getClientRequest.getName()), HttpStatus.OK);
    }

    @ApiOperation(value ="/Client", notes = "Update Client API")
    @RequestMapping(value ="/Client", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Client> UpdateClient(HttpServletRequest request, UpdateClientRequest updateClientRequest) {
        return new ResponseEntity<>(clientService.updateClient(updateClientRequest.getCompanyid(), updateClientRequest.getName(), updateClientRequest.getEmail(), updateClientRequest.getPhone(), updateClientRequest.getToken()), HttpStatus.OK);
    }

    @ApiOperation(value ="/Client", notes = "Delete login API")
    @RequestMapping(value ="/Client", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Boolean> DeleteClient(HttpServletRequest request, DeleteClientRequest deleteClientRequest) {
        return new ResponseEntity<>(clientService.deleteClient(deleteClientRequest.getName(), deleteClientRequest.getToken()), HttpStatus.OK);
    }
}
