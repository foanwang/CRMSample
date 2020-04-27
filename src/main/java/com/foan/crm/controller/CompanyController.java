package com.foan.crm.controller;

import com.foan.crm.constants.RoleType;
import com.foan.crm.entity.Company;
import com.foan.crm.entity.dto.request.CreateCompanyRequest;
import com.foan.crm.entity.dto.request.DeleteCompanyRequest;
import com.foan.crm.entity.dto.request.GetCompanyRequest;
import com.foan.crm.entity.dto.request.UpdateCompanyRequest;
import com.foan.crm.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${api-version}")
@Api(value = "${api-version}", tags = "CompanyController")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value ="/Company", notes = "Create new Company data")
    @RequestMapping(value ="/Company", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Company> CreateCompany(HttpServletRequest request, CreateCompanyRequest createCompanyRequest) {
        return new ResponseEntity<>(companyService.addCompany(createCompanyRequest.getName(), createCompanyRequest.getAddress(), createCompanyRequest.getToken()), HttpStatus.OK);
    }

    @ApiOperation(value ="/Company", notes = "get compacy API")
    @RequestMapping(value ="/Company", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Company> GetCompany(HttpServletRequest request, GetCompanyRequest getCompanyRequest) {
        return new ResponseEntity<>(companyService.getCompany(getCompanyRequest.getName()), HttpStatus.OK);
    }

    @ApiOperation(value ="/Company", notes = "update company API")
    @RequestMapping(value ="/Company", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Company> UpdateCompany(HttpServletRequest request, UpdateCompanyRequest updateCompanyRequest) {
        return new ResponseEntity<>(companyService.updateCompany(updateCompanyRequest.getName(), updateCompanyRequest.getAddress(), updateCompanyRequest.getToken()), HttpStatus.OK);
    }

    @ApiOperation(value ="/Company", notes = "delete company API")
//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="/Company", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Boolean> DeleteCompany(HttpServletRequest request, DeleteCompanyRequest deleteCompanyRequest) {
        return new ResponseEntity<>(companyService.deleteCompany(deleteCompanyRequest.getName(), deleteCompanyRequest.getToken()), HttpStatus.OK);
    }
}
