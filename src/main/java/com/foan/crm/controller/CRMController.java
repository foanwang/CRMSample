package com.foan.crm.controller;

import com.foan.crm.entity.Company;
import com.foan.crm.entity.dto.request.CreateCompanyRequest;
import com.foan.crm.entity.dto.request.CreateUserRequest;
import com.foan.crm.entity.dto.request.LoginRequest;
import com.foan.crm.entity.dto.response.LoginResponse;
import com.foan.crm.service.ClientService;
import com.foan.crm.service.CompanyService;
import com.foan.crm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${api-version}")
@Api(value = "${api-version}", tags = "CMSController")
public class CRMController {

    @Autowired
    private UserService userService;

    @ApiOperation(value ="/login", notes = "User login API")
    @RequestMapping(value ="/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<LoginResponse> Login(HttpServletRequest request, LoginRequest loginRequest) {
        return new ResponseEntity<>(userService.userLogin(loginRequest.getName(),loginRequest.getPassword()), HttpStatus.OK);
    }


    @ApiOperation(value ="/User", notes = "create user")
    @RequestMapping(value ="/User", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Boolean> CreateUser(HttpServletRequest request, CreateUserRequest createUserRequest) {
        return new ResponseEntity<>(userService.createUser(createUserRequest.getName(), createUserRequest.getPassword(), createUserRequest.getRole(), createUserRequest.getCreatedby()   ), HttpStatus.OK);
    }

}
